package it.unisalento.se.services;

import it.unisalento.se.converters.daoToDto.PublicChatMessageDaoToDto;
import it.unisalento.se.converters.dtoToDao.PublicChatMessageDtoToDao;
import it.unisalento.se.dao.PublicChatMessage;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.iservices.IChatService;
import it.unisalento.se.iservices.IFrdService;
import it.unisalento.se.models.ChatMessageRequest;
import it.unisalento.se.models.ChatMessageType;
import it.unisalento.se.models.FirebasePublicChatMessageModel;
import it.unisalento.se.models.PublicChatMessageModel;
import it.unisalento.se.repositories.PublicChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.UUID;

@Service
public class ChatService implements IChatService {

    @Autowired
    private IFrdService realtimeDbService;
    @Autowired
    private PublicChatMessageRepository repository;

    @Override
    @Transactional
    public PublicChatMessageModel sendPublicMessage(PublicChatMessageModel message) throws UserTypeNotSupported {

        String uniqueUUID = UUID.randomUUID().toString();

        FirebasePublicChatMessageModel msg = new FirebasePublicChatMessageModel();
        msg.setContent(message.getContent());
        msg.setSenderID(message.getSender().getId());
        msg.setSenderFullName(message.getSender().getName() + " " + message.getSender().getSurname());
        msg.setSubjectID(message.getRecipient().getID());
        msg.setUUID(uniqueUUID);
        msg.setSendDate(message.getSendDate());

        // Save on firebase
        try {
            realtimeDbService.savePublicMessage(message.getRecipient(), uniqueUUID, msg);
        } catch (IOException e) {
            System.err.println("Cannot save public chat message on firebase");
            e.printStackTrace();
        }

        // Save a copy on DB;
        PublicChatMessage dao = PublicChatMessageDtoToDao.convert(message, uniqueUUID);
        PublicChatMessage saved = repository.save(dao);

        // TODO send notification

        return PublicChatMessageDaoToDto.convert(saved);
    }

    @Override
    public String getPublicMessage(ChatMessageRequest request) {
        PublicChatMessage message;
        if (request.getType() == ChatMessageType.PUBBLIC) {
            message = repository.findByUuid(request.getUUID());
        } else {
            // TODO change to be private
            message = repository.findByUuid(request.getUUID());
        }
        return message.getContent();
    }
}
