package it.unisalento.se.services;

import it.unisalento.se.common.CommonUtils;
import it.unisalento.se.converters.daoToDto.DocumentDaoToDto;
import it.unisalento.se.converters.dtoToDao.DocumentDtoToDao;
import it.unisalento.se.converters.dtoToDao.LessonDtoToDao;
import it.unisalento.se.dao.Document;
import it.unisalento.se.dao.Subject;
import it.unisalento.se.exceptions.*;
import it.unisalento.se.iservices.IDocumentService;
import it.unisalento.se.iservices.IFcmService;
import it.unisalento.se.iservices.ILessonService;
import it.unisalento.se.iservices.IStorageService;
import it.unisalento.se.models.DocumentModel;
import it.unisalento.se.models.LessonModel;
import it.unisalento.se.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DocumentService implements IDocumentService {

    @Autowired
    private DocumentRepository repository;
    @Autowired
    private IStorageService storageService;
    @Autowired
    private ILessonService lessonService;
    @Autowired
    private IFcmService fcmService;

    @Override
    @Transactional(readOnly = true)
    public DocumentModel getDocumentByID(Integer ID) throws DocumentNotFoundException, UserTypeNotSupported {
        try {
            Document dao = repository.getOne(ID);
            return DocumentDaoToDto.convert(dao);
        } catch (EntityNotFoundException e) {
            throw new DocumentNotFoundException();
        }

    }

    @Override
    @Transactional
    public DocumentModel saveDocument(DocumentModel document) throws UserTypeNotSupported, NodeNotSupportedException {
        Document dao = DocumentDtoToDao.convert(document);
        Document saved = repository.save(dao);
        return DocumentDaoToDto.convert(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DocumentModel> getDocumentsByLesson(LessonModel lesson) throws UserTypeNotSupported {
        List<Document> daos = repository.findByLesson(LessonDtoToDao.convert(lesson));
        List<DocumentModel> models = new ArrayList<>();
        for (Document dao : daos) {
            models.add(DocumentDaoToDto.convert(dao));
        }
        return models;
    }

    @Override
    @Transactional
    public DocumentModel saveDocument(MultipartFile file,
                                      String documentName,
                                      String documentNote,
                                      String publishDate,
                                      String lessonId) throws UserTypeNotSupported, StorageException, ParseException, LessonNotFoundException, NodeNotSupportedException {
        String name = storageService.store(file);

        DocumentModel model = new DocumentModel();
        model.setName(documentName);
        model.setNote(documentNote);

        DateFormat m_ISO8601Local = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        model.setPublishDate(m_ISO8601Local.parse(publishDate));
        LessonModel lesson = lessonService.getLessonByID(Integer.valueOf(lessonId));
        model.setLesson(lesson);
        model.setLink(name);

        Document saved = repository.save(DocumentDtoToDao.convert(model));

        Subject subject = saved.getLesson().getSubject();
        Map<String, String> notificationAdditionalData = new HashMap<>();
        notificationAdditionalData.put("type", "document");
        notificationAdditionalData.put("lesson", CommonUtils.toJson(lesson));

        // Send notification to all user attending the subject
        try {
            fcmService.sendMessageToTopic(
                    "New document available",
                    "Document available for " + subject.getName(),
                    subject.getName().replaceAll(" ", ""),
                    CommonUtils.toJson(notificationAdditionalData)
            );
        } catch (Exception e) {
            System.err.println("Cannot send notification");
            e.printStackTrace();
        }
        return DocumentDaoToDto.convert(saved);
    }

    @Override
    public Boolean deleteDocument(Integer documentID) {
        Document dao = repository.getOne(documentID);
        Boolean deleted = storageService.removeFile(dao.getLink());
        if (deleted) {
            repository.delete(dao);
        }
        return true;
    }
}
