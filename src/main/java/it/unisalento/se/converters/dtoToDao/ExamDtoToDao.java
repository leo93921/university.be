package it.unisalento.se.converters.dtoToDao;

import it.unisalento.se.dao.Exam;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.ExamModel;

public class ExamDtoToDao {

    public static Exam convert(ExamModel model) throws UserTypeNotSupported {


        Exam dao = new Exam();
        dao.setId(model.getID());
        dao.setDescription(model.getDescription());
        dao.setSubject(SubjectDtoToDao.convert(model.getSubject()));
        dao.setClassroom(ClassroomDtoToDao.convert(model.getClassroom()));
        dao.setTimeslot(TimeSlotDtoToDao.convert(model.getTimeslot()));
        return dao;
    }


}


