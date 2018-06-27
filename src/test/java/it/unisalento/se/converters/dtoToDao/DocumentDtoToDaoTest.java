package it.unisalento.se.converters.dtoToDao;

import it.unisalento.se.common.Constants;
import it.unisalento.se.dao.Document;
import it.unisalento.se.dao.UserType;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.*;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class DocumentDtoToDaoTest {

    @Test
    public void convert() throws UserTypeNotSupported {
        UserModel professor = new UserModel();
        professor.setId(1);
        professor.setEmail("mario.rossi@test.it");
        professor.setName("Mario");
        professor.setSurname("Rossi");
        professor.setUserType(UserTypeModel.PROFESSOR);
        UserType type = new UserType();
        type.setName(Constants.PROFESSOR);
        type.setId(3);

        AcademicYearModel academicYear = new AcademicYearModel();
        academicYear.setID(1);
        academicYear.setStartYear(2017);
        academicYear.setEndYear(2018);

        CourseOfStudyModel cs = new CourseOfStudyModel();
        cs.setName("Computer Engineering");
        cs.setID(25);
        cs.setAcademicYear(academicYear);


        LessonModel lesson = new LessonModel();
        lesson.setID(1);
        lesson.setTimeSlot(new TimeSlotModel());
        SubjectModel subject = new SubjectModel();
        subject.setID(1);

        subject.setProfessor(professor);
        subject.setCourseOfStudy(cs);
        subject.setCFU(8);
        subject.setTeachingYear(1);
        lesson.setSubject(subject);
        lesson.setClassroom(new ClassroomModel());

        DocumentModel model = new DocumentModel();
        model.setPublishDate(new Date());
        model.setNote("A note");
        model.setName("A name");
        model.setID(12);
        model.setLink("a.link");
        model.setLesson(lesson);

        Document dao = DocumentDtoToDao.convert(model);

        assertEquals(model.getID(), dao.getId());
        assertEquals(model.getName(), dao.getName());
        assertEquals(model.getNote(), dao.getNote());
        assertEquals(model.getPublishDate(), dao.getPublishDate());
        assertEquals(model.getLink(), dao.getLink());
    }
}