package it.unisalento.se.iservices;

import it.unisalento.se.exceptions.DocumentNotFoundException;
import it.unisalento.se.exceptions.LessonNotFoundException;
import it.unisalento.se.exceptions.StorageException;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.DocumentModel;
import it.unisalento.se.models.LessonModel;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.List;

public interface IDocumentService {

    DocumentModel getDocumentByID(Integer ID) throws DocumentNotFoundException, UserTypeNotSupported;
    DocumentModel saveDocument(DocumentModel document) throws UserTypeNotSupported;

    DocumentModel saveDocument(MultipartFile file,
                               String documentName,
                               String documentNote,
                               String publishDate,
                               String lessonId) throws UserTypeNotSupported, StorageException, ParseException, LessonNotFoundException;

    List<DocumentModel> getDocumentsByLesson(LessonModel lesson) throws UserTypeNotSupported;

    Boolean deleteDocument(Integer documentID);

}
