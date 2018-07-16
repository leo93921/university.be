package it.unisalento.se.converters.dtoToDao;

import it.unisalento.se.dao.PublicChatMessage;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.PublicChatMessageModel;

public class PublicChatMessageDtoToDao {

    public static PublicChatMessage convert(PublicChatMessageModel message, String UUID) throws UserTypeNotSupported {
        PublicChatMessage dao = new PublicChatMessage();
        dao.setUser(UserDtoToDao.convert(message.getSender()));
        dao.setUuid(UUID);
        dao.setSendDate(message.getSendDate());
        dao.setId(message.getID());
        dao.setContent(message.getContent());
        dao.setSubject(SubjectDtoToDao.convert(message.getRecipient()));
        return dao;
    }
}
