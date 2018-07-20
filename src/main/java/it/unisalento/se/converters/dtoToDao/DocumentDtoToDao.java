package it.unisalento.se.converters.dtoToDao;

import it.unisalento.se.dao.Document;
import it.unisalento.se.dto.DocumentDto;
import it.unisalento.se.exceptions.NodeNotSupportedException;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.CourseOfStudyNode;
import it.unisalento.se.models.DocumentModel;
import it.unisalento.se.models.LessonModel;

public class DocumentDtoToDao {

    public static Document convert(DocumentModel model) throws UserTypeNotSupported, NodeNotSupportedException {
        Document dao = new Document();
        dao.setId(model.getID());
        dao.setName(model.getName());
        dao.setNote(model.getNote());
        dao.setPublishDate(model.getPublishDate());
        CourseOfStudyNode node = model.getLesson();
        if (!(node instanceof LessonModel)) {
            throw new NodeNotSupportedException();
        }
        dao.setLesson(LessonDtoToDao.convert((LessonModel) node));
        dao.setLink(model.getLink());
        return dao;
    }

    public static Document convert(DocumentDto model) throws UserTypeNotSupported {
        Document dao = new Document();
        dao.setId(model.getID());
        dao.setName(model.getName());
        dao.setNote(model.getNote());
        dao.setPublishDate(model.getPublishDate());
        CourseOfStudyNode node = model.getLesson();
        dao.setLesson(LessonDtoToDao.convert(model.getLesson()));
        dao.setLink(model.getLink());
        return dao;
    }
}
