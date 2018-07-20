package it.unisalento.se.iservices;

import it.unisalento.se.exceptions.*;
import it.unisalento.se.models.DocumentModel;
import it.unisalento.se.models.LessonModel;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.List;

public interface IDocumentService {

    DocumentModel getDocumentByID(Integer ID) throws DocumentNotFoundException, UserTypeNotSupported;

    DocumentModel saveDocument(DocumentModel document) throws UserTypeNotSupported, NodeNotSupportedException;

    DocumentModel saveDocument(MultipartFile file,
                               String documentName,
                               String documentNote,
                               String publishDate,
                               String lessonId) throws UserTypeNotSupported, StorageException, ParseException, LessonNotFoundException, NodeNotSupportedException;

    List<DocumentModel> getDocumentsByLesson(LessonModel lesson) throws UserTypeNotSupported;

    Boolean deleteDocument(Integer documentID) throws EntityNotDeletableException;

}
