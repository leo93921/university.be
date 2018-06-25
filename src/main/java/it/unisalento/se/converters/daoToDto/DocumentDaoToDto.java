package it.unisalento.se.converters.daoToDto;

import it.unisalento.se.dao.Document;
import it.unisalento.se.models.DocumentModel;

public class DocumentDaoToDto {

    public static DocumentModel convert(Document dao) {
        DocumentModel model = new DocumentModel();
        model.setID(dao.getId());
        model.setName(dao.getName());
        model.setNote(dao.getNote());
        model.setPublishDate(dao.getPublishDate());

        return model;
    }

}
