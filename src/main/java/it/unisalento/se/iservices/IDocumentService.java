package it.unisalento.se.iservices;

import it.unisalento.se.exceptions.DocumentNotFoundException;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.DocumentModel;

public interface IDocumentService {

    DocumentModel getDocumentByID(Integer ID) throws DocumentNotFoundException, UserTypeNotSupported;

    DocumentModel saveDocument(DocumentModel document) throws UserTypeNotSupported;

}
