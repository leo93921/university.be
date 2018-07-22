package it.unisalento.se.converters.dtoToDao;

import it.unisalento.se.common.Constants;
import it.unisalento.se.dao.DocumentEvaluation;
import it.unisalento.se.dao.UserType;
import it.unisalento.se.exceptions.NodeNotSupportedException;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.*;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class DocumentEvaluationDtoToDaoTest {
    @Test
    public void convert() throws UserTypeNotSupported, NodeNotSupportedException {
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

        DocumentModel document = new DocumentModel();
        document.setPublishDate(new Date());
        document.setNote("A note");
        document.setName("A name");
        document.setID(12);
        document.setLink("a.link");
        document.setLesson(lesson);


        UserModel sender = new UserModel();
        sender.setId(2);
        sender.setEmail("mario.rossi@test.it");
        sender.setName("Mario");
        sender.setSurname("Rossi");
        sender.setUserType(UserTypeModel.STUDENT);
        UserType type2 = new UserType();
        type2.setName(Constants.STUDENT);
        type2.setId(1);


        EvaluationModel model = new EvaluationModel();
        model.setId(1);
        model.setNote("Nota");
        model.setRecipientType("DOCUMENT");
        model.setRecipientD(document);
        model.setSender(sender);
        model.setScore(1);

        DocumentEvaluation dao = DocumentEvaluationDtoToDao.convert(model);

        assertEquals(model.getId(), dao.getId());
        assertEquals(model.getNote(), dao.getNote());


    }
}
