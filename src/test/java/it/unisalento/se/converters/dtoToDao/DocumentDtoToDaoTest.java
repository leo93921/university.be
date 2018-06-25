package it.unisalento.se.converters.dtoToDao;

import it.unisalento.se.dao.Document;
import it.unisalento.se.models.DocumentModel;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class DocumentDtoToDaoTest {

    @Test
    public void convert() {
        DocumentModel model = new DocumentModel();
        model.setPublishDate(new Date());
        model.setNote("A note");
        model.setName("A name");
        model.setID(12);

        Document dao = DocumentDtoToDao.convert(model);

        assertEquals(model.getID(), dao.getId());
        assertEquals(model.getName(), dao.getName());
        assertEquals(model.getNote(), dao.getNote());
        assertEquals(model.getPublishDate(), dao.getPublishDate());
    }
}