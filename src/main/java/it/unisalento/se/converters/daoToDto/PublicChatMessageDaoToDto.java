package it.unisalento.se.converters.daoToDto;

import it.unisalento.se.dao.PublicChatMessage;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.PublicChatMessageModel;

public class PublicChatMessageDaoToDto {

    public static PublicChatMessageModel convert(PublicChatMessage dao) throws UserTypeNotSupported {
        PublicChatMessageModel model = new PublicChatMessageModel();
        model.setSendDate(dao.getSendDate());
        model.setContent(dao.getContent());
        model.setRecipient(SubjectDaoToDto.convert(dao.getSubject()));
        model.setID(dao.getId());
        model.setSender(UserDaoToDto.convert(dao.getUser()));
        return model;
    }
}
