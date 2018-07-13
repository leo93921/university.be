package it.unisalento.se.api.rest;

import it.unisalento.se.exceptions.DocumentNotFoundException;
import it.unisalento.se.iservices.IDocumentService;
import it.unisalento.se.models.DocumentModel;
import it.unisalento.se.test.utils.TestUtils;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class DocumentRestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private IDocumentService service;
    @InjectMocks
    private DocumentRestController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = TestUtils.getMockMvc(controller);
    }


    @Test
    public void getDocumentByID_404() throws Exception {
        when(service.getDocumentByID(any(Integer.class))).thenThrow(new DocumentNotFoundException());

        mockMvc.perform(get("/document/{id}", 12))
                .andExpect(status().isNotFound());

        verify(service, times(1)).getDocumentByID(12);
        verifyNoMoreInteractions(service);
    }

    @Test
    public void getDocumentByID_OK() throws Exception {
        DocumentModel docFound = new DocumentModel();
        docFound.setID(19);
        docFound.setName("A name");
        docFound.setNote("A note");
        docFound.setPublishDate(new Date());

        when(service.getDocumentByID(any(Integer.class))).thenReturn(docFound);

        mockMvc.perform(get("/document/{id}", 19))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", Matchers.is(docFound.getID())))
                .andExpect(jsonPath("$.name", Matchers.is(docFound.getName())))
                .andExpect(jsonPath("$.note", Matchers.is(docFound.getNote())))
                .andExpect(jsonPath("$.publishDate", Matchers.is(docFound.getPublishDate().getTime())));

        verify(service, times(1)).getDocumentByID(19);
        verifyNoMoreInteractions(service);
    }

    /*@Test
    public void saveDocument() throws Exception, StorageException {
        DocumentModel savedDocument = new DocumentModel();
        savedDocument.setID(43);
        savedDocument.setPublishDate(new Date());
        savedDocument.setNote("A note");
        savedDocument.setName("The name");

        when(service.saveDocument(
                any(),
                any(String.class),
                any(String.class),
                any(String.class),
                any(String.class)
        )).thenReturn(savedDocument);

        mockMvc.perform(post("/document")
                .contentType(MediaType.MULTIPART_FORM_DATA_VALUE)
                .content(1, "", "", "", ""))
                //.andExpect(status().isOk())
                //.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.id", Matchers.is(savedDocument.getID())))
                .andExpect(jsonPath("$.name", Matchers.is(savedDocument.getName())))
                .andExpect(jsonPath("$.note", Matchers.is(savedDocument.getNote())))
                .andExpect(jsonPath("$.publishDate", Matchers.is(savedDocument.getPublishDate().getTime())));

        verify(service, times(1)).saveDocument(refEq(savedDocument));
        verifyNoMoreInteractions(service);
    }*/
}