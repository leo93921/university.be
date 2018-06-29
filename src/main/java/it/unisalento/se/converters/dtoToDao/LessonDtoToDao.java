package it.unisalento.se.converters.dtoToDao;

import it.unisalento.se.dao.Lesson;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.LessonModel;

public class LessonDtoToDao {
    public static Lesson convert(LessonModel model) throws UserTypeNotSupported {
        Lesson dao = new Lesson();

        dao.setId(model.getID());
        dao.setClassroom(ClassroomDtoToDao.convert(model.getClassroom()));
        dao.setTimeslot(TimeSlotDtoToDao.convert(model.getTimeSlot()));
        dao.setSubject(SubjectDtoToDao.convert(model.getSubject()));
        return dao;
    }


}
