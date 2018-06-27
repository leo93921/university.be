package it.unisalento.se.converters.dtoToDao;

import it.unisalento.se.dao.Document;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.DocumentModel;

public class DocumentDtoToDao {

    public static Document convert(DocumentModel model) throws UserTypeNotSupported {
        Document dao = new Document();
        dao.setId(model.getID());
        dao.setName(model.getName());
        dao.setNote(model.getNote());
        dao.setPublishDate(model.getPublishDate());
        dao.setLesson(LessonDtoToDao.convert(model.getLesson()));
        dao.setLink(model.getLink());
        return dao;
    }

}
