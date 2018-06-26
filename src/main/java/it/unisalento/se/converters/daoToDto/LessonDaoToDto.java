package it.unisalento.se.converters.daoToDto;

import it.unisalento.se.dao.Lesson;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.LessonModel;

public class LessonDaoToDto {

    public static LessonModel convert(Lesson dao) throws UserTypeNotSupported {
        LessonModel model = new LessonModel();
        model.setID(dao.getId());
        model.setClassroom(ClassroomDaoToDto.convert(dao.getClassroom()));
        model.setTimeslot(TimeSlotDaoToDto.convert(dao.getTimeslot()));
        model.setSubject(SubjectDaoToDto.convert(dao.getSubject()));

        return model;


    }


}
