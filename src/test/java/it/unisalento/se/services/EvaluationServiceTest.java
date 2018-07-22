package it.unisalento.se.services;

import it.unisalento.se.dao.*;
import it.unisalento.se.dto.DocumentDto;
import it.unisalento.se.dto.EvaluationDto;
import it.unisalento.se.exceptions.EvaluationNotFoundException;
import it.unisalento.se.exceptions.EvaluationRecipientNotSupported;
import it.unisalento.se.exceptions.ScoreNotValidException;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.*;
import it.unisalento.se.repositories.DocumentEvaluationRepository;
import it.unisalento.se.repositories.LessonEvaluationRepository;
import it.unisalento.se.repositories.LessonRepository;
import it.unisalento.se.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EvaluationServiceTest {

    @Mock
    private LessonEvaluationRepository lessonEvaluationRepository;
    @Mock
    private DocumentEvaluationRepository documentEvaluationRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private EvaluationService evaluationService;
    @InjectMocks
    private UserService userService;

    @Test
    public void getLessonEvaluationbyID() throws EvaluationRecipientNotSupported, UserTypeNotSupported, EvaluationNotFoundException, ScoreNotValidException {

        AcademicYear ay = new AcademicYear();
        ay.setId(1);
        ay.setStartYear(2017);
        ay.setEndYear(2018);


        CourseOfStudy cs = new CourseOfStudy();
        cs.setId(1);
        cs.setName("Engineering");
        cs.setAcademicYear(ay);

        UserType ut = new UserType();
        ut.setId(2);
        ut.setName("PROFESSOR");

        User u = new User();
        u.setId(1);
        u.setName("Mario");
        u.setSurname("Giallo");
        u.setEmail("mario.giallo@email.it");
        u.setUserType(ut);
        u.setPassword("ciaociaociao");


        UserType ut2 = new UserType();
        ut2.setId(1);
        ut2.setName("STUDENT");

        User u2 = new User();
        u2.setId(2);
        u2.setName("Luigi");
        u2.setSurname("Marrone");
        u2.setEmail("marrone.luigi@lot.it");
        u2.setUserType(ut2);
        u2.setPassword("adios");


        Subject s = new Subject();
        s.setId(1);
        s.setName("Software");
        s.setCfu(12);
        s.setUser(u);
        s.setCourseOfStudy(cs);
        s.setYear(2018);


        Date startDate = new Date();
        startDate.setTime(startDate.getTime() - 100);
        Date endDate = new Date();
        Timeslot ts = new Timeslot();
        ts.setId(1);
        ts.setStartTime(startDate);
        ts.setEndTime(endDate);


        Classroom cr = new Classroom();
        cr.setId(1);
        cr.setName("Y1");
        cr.setLatitude(1.0);
        cr.setLongitude(1.0);

        Lesson l = new Lesson();
        l.setId(1);
        l.setClassroom(cr);
        l.setTimeslot(ts);
        l.setSubject(s);

        LessonEvaluation le = new LessonEvaluation();
        le.setId(1);
        le.setLesson(l);
        le.setNote("Bello");
        le.setScore(1);
        le.setUser(u2);


        when(lessonEvaluationRepository.getOne(1)).thenReturn(le);
        EvaluationModel model = evaluationService.getLessonEvaluationbyID(1);
        assertEquals(new Integer(1), model.getID());
        assertEquals(new Integer(1), model.getScore());


    }

    @Test
    public void getDocumentEvaluationbyID() throws EvaluationRecipientNotSupported, UserTypeNotSupported, EvaluationNotFoundException, ScoreNotValidException {


        AcademicYear ay = new AcademicYear();
        ay.setId(1);
        ay.setStartYear(2017);
        ay.setEndYear(2018);


        CourseOfStudy cs = new CourseOfStudy();
        cs.setId(1);
        cs.setName("Engineering");
        cs.setAcademicYear(ay);

        UserType ut = new UserType();
        ut.setId(2);
        ut.setName("PROFESSOR");

        User u = new User();
        u.setId(1);
        u.setName("Mario");
        u.setSurname("Giallo");
        u.setEmail("mario.giallo@email.it");
        u.setUserType(ut);
        u.setPassword("ciaociaociao");


        UserType ut2 = new UserType();
        ut2.setId(1);
        ut2.setName("STUDENT");

        User u2 = new User();
        u2.setId(2);
        u2.setName("Luigi");
        u2.setSurname("Marrone");
        u2.setEmail("marrone.luigi@lot.it");
        u2.setUserType(ut2);
        u2.setPassword("adios");


        Subject s = new Subject();
        s.setId(1);
        s.setName("Software");
        s.setCfu(12);
        s.setUser(u);
        s.setCourseOfStudy(cs);
        s.setYear(2018);


        Date startDate = new Date();
        startDate.setTime(startDate.getTime() - 100);
        Date endDate = new Date();
        Timeslot ts = new Timeslot();
        ts.setId(1);
        ts.setStartTime(startDate);
        ts.setEndTime(endDate);


        Classroom cr = new Classroom();
        cr.setId(1);
        cr.setName("Y1");
        cr.setLatitude(1.0);
        cr.setLongitude(1.0);

        Lesson l = new Lesson();
        l.setId(1);
        l.setClassroom(cr);
        l.setTimeslot(ts);
        l.setSubject(s);


        Document d = new Document();
        d.setId(1);
        d.setLesson(l);
        d.setLink("link");
        d.setName("Nome");
        d.setNote("Nota di prova");
        d.setPublishDate(startDate);

        DocumentEvaluation de = new DocumentEvaluation();
        de.setId(1);
        de.setDocument(d);
        de.setNote("Bello");
        de.setScore(1);
        de.setUser(u2);


        when(documentEvaluationRepository.getOne(any(Integer.class))).thenReturn(de);
        EvaluationModel model = evaluationService.getDocumentEvaluationbyID(1);
        assertEquals(new Integer(1), model.getID());
        assertEquals(new Integer(1), model.getScore());




    }
/*
    @Test
    public void createDocumentEvaluation() throws ScoreNotValidException, EvaluationRecipientNotSupported, UserTypeNotSupported {



        AcademicYear ay = new AcademicYear();
        ay.setId(1);
        ay.setStartYear(2017);
        ay.setEndYear(2018);


        CourseOfStudy cs = new CourseOfStudy();
        cs.setId(1);
        cs.setName("Engineering");
        cs.setAcademicYear(ay);

        UserType ut = new UserType();
        ut.setId(2);
        ut.setName("PROFESSOR");

        User u = new User();
        u.setId(1);
        u.setName("Mario");
        u.setSurname("Giallo");
        u.setEmail("mario.giallo@email.it");
        u.setUserType(ut);
        u.setPassword("ciaociaociao");


        UserType ut2 = new UserType();
        ut2.setId(1);
        ut2.setName("STUDENT");

        User u2 = new User();
        u2.setId(2);
        u2.setName("Luigi");
        u2.setSurname("Marrone");
        u2.setEmail("marrone.luigi@lot.it");
        u2.setUserType(ut2);
        u2.setPassword("adios");


        Subject s = new Subject();
        s.setId(1);
        s.setName("Software");
        s.setCfu(12);
        s.setUser(u);
        s.setCourseOfStudy(cs);
        s.setYear(2018);


        Date startDate = new Date();
        startDate.setTime(startDate.getTime() - 100);
        Date endDate = new Date();
        Timeslot ts = new Timeslot();
        ts.setId(1);
        ts.setStartTime(startDate);
        ts.setEndTime(endDate);


        Classroom cr = new Classroom();
        cr.setId(1);
        cr.setName("Y1");
        cr.setLatitude(1.0);
        cr.setLongitude(1.0);

        Lesson l = new Lesson();
        l.setId(1);
        l.setClassroom(cr);
        l.setTimeslot(ts);
        l.setSubject(s);


        Document d = new Document();
        d.setId(1);
        d.setLesson(l);
        d.setLink("link");
        d.setName("Nome");
        d.setNote("Nota di prova");
        d.setPublishDate(startDate);

        DocumentEvaluation de = new DocumentEvaluation();
        de.setId(1);
        de.setDocument(d);
        de.setNote("Bello");
        de.setScore(1);
        de.setUser(u2);


        AcademicYearModel aym = new AcademicYearModel();
        aym.setID(1);
        aym.setStartYear(2017);
        aym.setEndYear(2018);


        CourseOfStudyModel csm = new CourseOfStudyModel();
        csm.setID(1);
        csm.setName("Engineering");
        csm.setAcademicYear(aym);



        UserModel um = new UserModel();
        um.setId(1);
        um.setName("Mario");
        um.setSurname("Giallo");
        um.setEmail("mario.giallo@email.it");
        um.setUserType(UserTypeModel.PROFESSOR);
        um.setPassword("ciaociaociao");



        UserModel u2m = new UserModel();
        u2m.setId(2);
        u2m.setName("Luigi");
        u2m.setSurname("Marrone");
        u2m.setEmail("marrone.luigi@lot.it");
        u2m.setUserType(UserTypeModel.STUDENT);
        u2m.setPassword("adios");


        SubjectModel sm = new SubjectModel();
        sm.setID(1);
        sm.setName("Software");
        sm.setCFU(12);
        sm.setProfessor(um);
        sm.setCourseOfStudy(csm);
        sm.setTeachingYear(2018);


        Date startDatem = new Date();
        startDate.setTime(startDate.getTime() - 100);
        Date endDatem = new Date();

        TimeSlotModel tsm = new TimeSlotModel();
        tsm.setID(1);
        tsm.setStartTime(startDatem);
        tsm.setEndTime(endDatem);


        ClassroomModel crm = new ClassroomModel();
        crm.setID(1);
        crm.setName("Y1");
        crm.setLatitude(1.0);
        crm.setLongitude(1.0);

        LessonModel lm = new LessonModel();
        lm.setID(1);
        lm.setClassroom(crm);
        lm.setTimeSlot(tsm);
        lm.setSubject(sm);




        DocumentDto dmdto = new DocumentDto();
        dmdto.setID(1);
        dmdto.setLesson(lm);
        dmdto.setLink("link");
        dmdto.setName("Nome");
        dmdto.setNote("Nota di prova");
        dmdto.setPublishDate(startDate);




        EvaluationDto dedto = new EvaluationDto();
        dedto.setId(1);
        dedto.setRecipientD(dmdto);
        dedto.setNote("Bello");
        dedto.setScore(1);
        dedto.setSender(u2m);
        dedto.setRecipientType("DOCUMENT");



        when(documentEvaluationRepository.save(any(DocumentEvaluation.class))).thenReturn(de);
        User user = new User();
        user.setFcmToken("1a");
        user.setId(1);
        when(userRepository.getOne(any(Integer.class))).thenReturn(user);
        when(userService.getFCMToken(any(UserModel.class))).thenReturn("1a");
        EvaluationModel model1 = evaluationService.createEvaluation(dedto);


        assertEquals(dedto.getId(), model1.getID());
        assertEquals(dedto.getNote(), model1.getNote());
        assertEquals(new Integer (dedto.getScore()), model1.getScore());





    }*/
}