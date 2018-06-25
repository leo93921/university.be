package it.unisalento.se.iservices;

import it.unisalento.se.exceptions.DocumentNotFoundException;
import it.unisalento.se.models.DocumentModel;

public interface IDocumentService {

    DocumentModel getDocumentByID(Integer ID) throws DocumentNotFoundException;

    DocumentModel saveDocument(DocumentModel document);

}
