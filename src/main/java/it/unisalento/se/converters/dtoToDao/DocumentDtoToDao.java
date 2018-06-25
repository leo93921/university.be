package it.unisalento.se.converters.dtoToDao;

import it.unisalento.se.dao.Document;
import it.unisalento.se.models.DocumentModel;

public class DocumentDtoToDao {

    public static Document convert(DocumentModel model) {
        Document dao = new Document();
        dao.setId(model.getID());
        dao.setName(model.getName());
        dao.setNote(model.getNote());
        dao.setPublishDate(model.getPublishDate());

        // TODO doesn't work. Lesson is necessary!
        return dao;
    }

}
