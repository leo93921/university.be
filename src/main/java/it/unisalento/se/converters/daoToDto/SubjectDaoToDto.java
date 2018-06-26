package it.unisalento.se.converters.daoToDto;

import it.unisalento.se.dao.Subject;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.SubjectModel;

public class SubjectDaoToDto {

    public static SubjectModel convert(Subject dao) throws UserTypeNotSupported {
        SubjectModel model = new SubjectModel();
        model.setID(dao.getId());
        model.setCFU(dao.getCfu());
        model.setName(dao.getName());
        model.setTeachingYear(dao.getYear());
        model.setProfessor(UserDaoToDto.convert(dao.getUser()));
        model.setCourseOfStudy(CourseOfStudyDaoToDto.convert(dao.getCourseOfStudy()));
        return model;
    }

}
