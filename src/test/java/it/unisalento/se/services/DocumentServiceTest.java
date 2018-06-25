package it.unisalento.se.services;

import it.unisalento.se.dao.Document;
import it.unisalento.se.exceptions.DocumentNotFoundException;
import it.unisalento.se.models.DocumentModel;
import it.unisalento.se.repositories.DocumentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DocumentServiceTest {

    @Mock
    private DocumentRepository repository;
    @InjectMocks
    private DocumentService service;

    @Test
    public void getDocumentByID() throws DocumentNotFoundException {
        Document document = new Document();
        document.setId(1);
        document.setName("A test document");
        document.setNote("A note on document");
        document.setPublishDate(new Date());

        when(repository.getOne(any(Integer.class))).thenReturn(document);

        DocumentModel model = service.getDocumentByID(1);

        assertEquals(document.getId(), model.getID());
        assertEquals(document.getName(), model.getName());
        assertEquals(document.getNote(), model.getNote());
        assertEquals(document.getPublishDate(), model.getPublishDate());
    }

    @Test(expected = DocumentNotFoundException.class)
    public void getDocumentByID_shouldFail() throws DocumentNotFoundException {

        when(repository.getOne(any(Integer.class))).thenThrow(new EntityNotFoundException());

        DocumentModel model = service.getDocumentByID(1);

    }

    @Test
    public void saveDocument() {
        Document saved = new Document();
        saved.setId(1);
        saved.setNote("A note");
        saved.setName("A name");
        saved.setPublishDate(new Date());

        when(repository.save(any(Document.class))).thenReturn(saved);

        DocumentModel model = service.saveDocument(new DocumentModel());

        assertEquals(saved.getId(), model.getID());
        assertEquals(saved.getName(), model.getName());
        assertEquals(saved.getNote(), model.getNote());
        assertEquals(saved.getPublishDate(), model.getPublishDate());
    }
}