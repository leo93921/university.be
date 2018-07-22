package it.unisalento.se.services;

import it.unisalento.se.common.CommonUtils;
import it.unisalento.se.dao.User;
import it.unisalento.se.iservices.IChatService;
import it.unisalento.se.iservices.IFcmService;
import it.unisalento.se.iservices.IFrdService;
import it.unisalento.se.models.FirebaseChatMessageModel;
import it.unisalento.se.models.PrivateChatMessageModel;
import it.unisalento.se.models.PublicChatMessageModel;
import it.unisalento.se.models.UserModel;
import it.unisalento.se.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class ChatService implements IChatService {

    @Autowired
    private IFrdService realtimeDbService;
    @Autowired
    private IFcmService fcmService;
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public Boolean sendPublicMessage(PublicChatMessageModel message) {

        String uniqueUUID = UUID.randomUUID().toString();

        FirebaseChatMessageModel msg = new FirebaseChatMessageModel();
        msg.setContent(message.getContent());
        msg.setSenderID(message.getSender().getId());
        msg.setSenderFullName(message.getSender().getName() + " " + message.getSender().getSurname());
        msg.setRecipientID(message.getRecipient().getID());
        msg.setRecipientFullName(message.getRecipient().getName());
        msg.setUUID(uniqueUUID);
        msg.setSendDate(message.getSendDate());

        // Save on firebase
        try {
            realtimeDbService.savePublicMessage(message.getRecipient(), uniqueUUID, msg);
        } catch (IOException e) {
            System.err.println("Cannot save public chat message on firebase");
            e.printStackTrace();
        }

        Map<String, String> notificationAdditionalData = new HashMap<>();
        notificationAdditionalData.put("type", "public-chat");
        notificationAdditionalData.put("recipient", CommonUtils.toJson(message.getRecipient()));

        String subjectName = message.getRecipient().getName();
        try {
            fcmService.sendMessageToTopic(
                    "New message in " + subjectName,
                    "A new message has been left in " + subjectName,
                    subjectName.replaceAll(" ", ""),
                    CommonUtils.toJson(notificationAdditionalData)
            );
        } catch (Exception e) {
            System.err.println("Cannot send notification");
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public Boolean sendPrivateMessage(PrivateChatMessageModel message) throws IOException {

        FirebaseChatMessageModel msg = new FirebaseChatMessageModel();
        msg.setContent(message.getContent());
        msg.setRecipientID(message.getRecipient().getId());
        msg.setRecipientFullName(message.getRecipient().getName() + " " + message.getRecipient().getSurname());
        msg.setSendDate(message.getSendDate());
        UserModel sender = message.getSender();
        String senderName = sender.getName();
        msg.setSenderFullName(senderName + " " + sender.getSurname());
        msg.setSenderID(sender.getId());

        realtimeDbService.savePrivateMessage(msg);

        Map<String, String> notificationAdditionalData = new HashMap<>();
        notificationAdditionalData.put("type", "private-chat");
        notificationAdditionalData.put("recipient", CommonUtils.toJson(message.getSender()));

        // Send notification
        User user = userRepository.getOne(message.getRecipient().getId());
        String fcmToken = user.getFcmToken();
        if (fcmToken != null && !fcmToken.equals("")) {
            try {
                fcmService.sendMessageToUser(
                        senderName + " sent you a message.",
                        senderName + " " + sender.getSurname(),
                        fcmToken,
                        CommonUtils.toJson(notificationAdditionalData)

                );
            } catch (Exception e) {
                System.err.println("Cannot send notification");
                e.printStackTrace();
            }
        }

        return true;
    }
}
