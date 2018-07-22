package it.unisalento.se.converters.daoToDto;


import it.unisalento.se.common.Constants;
import it.unisalento.se.dao.*;
import it.unisalento.se.exceptions.EvaluationRecipientNotSupported;
import it.unisalento.se.exceptions.ScoreNotValidException;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.DocumentModel;
import it.unisalento.se.models.EvaluationModel;
import it.unisalento.se.models.LessonModel;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;

public class EvaluationDaoToDtoTest {

    @Test
    public void convertLesson() throws ScoreNotValidException, UserTypeNotSupported, EvaluationRecipientNotSupported {
        AcademicYear ay = getAcademicYear();
        CourseOfStudy cs = getCourseOfStudy(ay);
        UserType ut = getUserType();
        User u = getUser(ut);
        Subject s = getSubject(cs, u);
        Timeslot ts = getTimeslot();
        Classroom cr = getClassroom();
        Lesson l = getLesson(s, ts, cr);

        User user = getUser();
        LessonEvaluation le = new LessonEvaluation(
                l,
                user,
                5
        );
        le.setId(23);
        le.setNote("A note");

        EvaluationModel converted = EvaluationDaoToDto.convert(le);

        assertEquals(le.getId(), converted.getID());
        assertEquals(le.getLesson().getId(), converted.getRecipient().getID());
        assertEquals(le.getNote(), converted.getNote());
        assertEquals(Integer.valueOf(le.getScore()), converted.getScore());
        assertEquals(le.getUser().getId(), converted.getSender().getId());
        assertEquals(Constants.LESSON, converted.getRecipientType());
        assertThat(converted.getRecipient(), instanceOf(LessonModel.class));
    }

    private AcademicYear getAcademicYear() {
        AcademicYear ay = new AcademicYear();
        ay.setId(1);
        ay.setStartYear(2017);
        ay.setEndYear(2018);
        return ay;
    }

    private CourseOfStudy getCourseOfStudy(AcademicYear ay) {
        CourseOfStudy cs = new CourseOfStudy();
        cs.setId(1);
        cs.setName("Engineering");
        cs.setAcademicYear(ay);
        return cs;
    }

    private UserType getUserType() {
        UserType ut = new UserType();
        ut.setId(1);
        ut.setName("PROFESSOR");
        return ut;
    }

    private User getUser(UserType ut) {
        User u = new User();
        u.setId(1);
        u.setName("Luigi");
        u.setSurname("Mario");
        u.setEmail("mario.luigi@n.jp");
        u.setUserType(ut);
        u.setPassword("peach");
        return u;
    }

    private Subject getSubject(CourseOfStudy cs, User u) {
        Subject s = new Subject();
        s.setId(1);
        s.setName("Software");
        s.setCfu(12);
        s.setUser(u);
        s.setCourseOfStudy(cs);
        s.setYear(2018);
        return s;
    }

    private Timeslot getTimeslot() {
        Date startDate = new Date();
        startDate.setTime(startDate.getTime() - 100);
        Date endDate = new Date();
        Timeslot ts = new Timeslot();
        ts.setId(1);
        ts.setStartTime(startDate);
        ts.setEndTime(endDate);
        return ts;
    }

    private Classroom getClassroom() {
        Classroom cr = new Classroom();
        cr.setId(1);
        cr.setName("Y1");
        cr.setLatitude(1.0);
        cr.setLongitude(1.0);
        return cr;
    }

    private Lesson getLesson(Subject s, Timeslot ts, Classroom cr) {
        Lesson l = new Lesson();
        l.setId(1);
        l.setClassroom(cr);
        l.setTimeslot(ts);
        l.setSubject(s);
        return l;
    }

    private User getUser() {
        User user = new User(
                new UserType("STUDENT"),
                "Mario",
                "Rossi",
                "m.rossi@test.it",
                "Ciao"
        );
        user.setId(96);
        return user;
    }

    @Test
    public void convertDocument() throws ScoreNotValidException, UserTypeNotSupported, EvaluationRecipientNotSupported {
        AcademicYear ay = getAcademicYear();
        CourseOfStudy cs = getCourseOfStudy(ay);
        UserType ut = getUserType();
        User u = getUser(ut);
        Subject s = getSubject(cs, u);
        Timeslot ts = getTimeslot();
        Classroom cr = getClassroom();
        Lesson l = getLesson(s, ts, cr);

        Document document = new Document();
        document.setId(1);
        document.setNote("A note");
        document.setName("A name");
        document.setPublishDate(new Date());
        document.setLink("A new linlk");
        document.setLesson(l);

        DocumentEvaluation de = new DocumentEvaluation();
        de.setId(1);
        de.setNote("A note");
        de.setScore(3);
        de.setUser(getUser());
        de.setDocument(document);

        EvaluationModel converted = EvaluationDaoToDto.convert(de);

        assertEquals(de.getId(), converted.getID());
        assertEquals(de.getDocument().getId(), converted.getRecipient().getID());
        assertEquals(de.getNote(), converted.getNote());
        assertEquals(Integer.valueOf(de.getScore()), converted.getScore());
        assertEquals(de.getUser().getId(), converted.getSender().getId());
        assertEquals(Constants.DOCUMENT, converted.getRecipientType());
        assertThat(converted.getRecipient(), instanceOf(DocumentModel.class));
    }
}