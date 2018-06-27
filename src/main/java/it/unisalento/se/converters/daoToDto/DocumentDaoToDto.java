package it.unisalento.se.converters.daoToDto;

import it.unisalento.se.dao.Document;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.DocumentModel;

public class DocumentDaoToDto {

    public static DocumentModel convert(Document dao) throws UserTypeNotSupported {
        DocumentModel model = new DocumentModel();
        model.setID(dao.getId());
        model.setName(dao.getName());
        model.setNote(dao.getNote());
        model.setPublishDate(dao.getPublishDate());
        model.setLesson(LessonDaoToDto.convert(dao.getLesson()));
        model.setLink(dao.getLink());
        return model;
    }

}
