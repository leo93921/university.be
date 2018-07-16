package it.unisalento.se.services;

import it.unisalento.se.iservices.IChatService;
import it.unisalento.se.iservices.IFrdService;
import it.unisalento.se.models.FirebaseChatMessageModel;
import it.unisalento.se.models.PrivateChatMessageModel;
import it.unisalento.se.models.PublicChatMessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.UUID;

@Service
public class ChatService implements IChatService {

    @Autowired
    private IFrdService realtimeDbService;

    @Override
    @Transactional
    public Boolean sendPublicMessage(PublicChatMessageModel message) {

        String uniqueUUID = UUID.randomUUID().toString();

        FirebaseChatMessageModel msg = new FirebaseChatMessageModel();
        msg.setContent(message.getContent());
        msg.setSenderID(message.getSender().getId());
        msg.setSenderFullName(message.getSender().getName() + " " + message.getSender().getSurname());
        msg.setRecipientID(message.getRecipient().getID());
        msg.setUUID(uniqueUUID);
        msg.setSendDate(message.getSendDate());

        // Save on firebase
        try {
            realtimeDbService.savePublicMessage(message.getRecipient(), uniqueUUID, msg);
        } catch (IOException e) {
            System.err.println("Cannot save public chat message on firebase");
            e.printStackTrace();
        }

        // TODO send notification
        return true;
    }

    @Override
    public Boolean sendPrivateMessage(PrivateChatMessageModel message) throws IOException {

        FirebaseChatMessageModel msg = new FirebaseChatMessageModel();
        msg.setContent(message.getContent());
        msg.setRecipientID(message.getRecipient().getId());
        msg.setSendDate(message.getSendDate());
        msg.setSenderFullName(message.getSender().getName() + " " + message.getSender().getSurname());
        msg.setSenderID(message.getSender().getId());

        realtimeDbService.savePrivateMessage(msg);

        // TODO send notification
        return true;
    }
}
