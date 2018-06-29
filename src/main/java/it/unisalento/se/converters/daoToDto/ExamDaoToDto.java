package it.unisalento.se.converters.daoToDto;

import it.unisalento.se.dao.Exam;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.ExamModel;

public class ExamDaoToDto {
    public static ExamModel convert(Exam dao) throws UserTypeNotSupported {
        ExamModel model = new ExamModel();
        model.setID(dao.getId());
        model.setDescription(dao.getDescription());
        model.setSubject(SubjectDaoToDto.convert(dao.getSubject()));
        model.setClassroom(ClassroomDaoToDto.convert(dao.getClassroom()));
        model.setTimeslot(TimeSlotDaoToDto.convert(dao.getTimeslot()));
        return model;
    }


}


