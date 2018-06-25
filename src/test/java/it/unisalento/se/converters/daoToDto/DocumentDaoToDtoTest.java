package it.unisalento.se.converters.daoToDto;

import it.unisalento.se.dao.Document;
import it.unisalento.se.models.DocumentModel;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class DocumentDaoToDtoTest {

    @Test
    public void convert() {

        Document dao = new Document();
        dao.setId(1);
        dao.setNote("A note");
        dao.setName("A name");
        dao.setPublishDate(new Date());

        DocumentModel model = DocumentDaoToDto.convert(dao);

        assertEquals(dao.getId(), model.getID());
        assertEquals(dao.getPublishDate(), model.getPublishDate());
        assertEquals(dao.getName(), model.getName());
        assertEquals(dao.getNote(), model.getNote());
    }
}