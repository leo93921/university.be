package it.unisalento.se.converters.dtoToDao;

import it.unisalento.se.dao.Subject;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.SubjectModel;

public class SubjectDtoToDao {

    public static Subject convert(SubjectModel model) throws UserTypeNotSupported {
        Subject dao = new Subject();
        dao.setId(model.getID());
        dao.setName(model.getName());
        dao.setCfu(model.getCFU());
        dao.setYear(model.getTeachingYear());
        dao.setUser(UserDtoToDao.convert(model.getProfessor()));
        dao.setCourseOfStudy(CourseOfStudyDtoToDao.convert(model.getCourseOfStudy()));
        return dao;
    }

}
