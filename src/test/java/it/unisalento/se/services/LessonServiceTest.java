package it.unisalento.se.services;


import it.unisalento.se.dao.*;
import it.unisalento.se.exceptions.LessonNotFoundException;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.*;
import it.unisalento.se.repositories.LessonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;

@RunWith(MockitoJUnitRunner.class)
public class LessonServiceTest {


    @Mock
    private LessonRepository lessonRepository;
    @InjectMocks
    private LessonService lessonService;


    @Test
    public void getLesson_OK() throws LessonNotFoundException, UserTypeNotSupported {
        AcademicYear ay = new AcademicYear();
        ay.setId(1);
        ay.setStartYear(2017);
        ay.setEndYear(2018);


        CourseOfStudy cs = new CourseOfStudy();
        cs.setId(1);
        cs.setName("Engineering");
        cs.setAcademicYear(ay);

        UserType ut = new UserType();
        ut.setId(1);
        ut.setName("PROFESSOR");

        User u = new User();
        u.setId(1);
        u.setName("Luigi");
        u.setSurname("Mario");
        u.setEmail("mario.luigi@n.jp");
        u.setUserType(ut);
        u.setPassword("peach");


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

        when(lessonRepository.getOne(1)).thenReturn(l);

        LessonModel model = lessonService.getLessonByID(1);

        assertEquals(new Integer(1), model.getID());
        assertEquals(cr.getName(), model.getClassroom().getName());
        assertEquals(ts.getStartTime(), model.getTimeSlot().getStartTime());
        assertEquals(s.getName(), model.getSubject().getName());
    }


    @Test(expected = LessonNotFoundException.class)
    public void getLesson_shouldFail() throws LessonNotFoundException, UserTypeNotSupported {
        when(lessonRepository.getOne(10)).thenThrow(new EntityNotFoundException());

        LessonModel model = lessonService.getLessonByID(10);
    }


    @Test
    public void createLesson() throws UserTypeNotSupported {

        //NORMALE
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
        u.setName("Francesco");
        u.setSurname("Quercia");
        u.setEmail("francesco.quercia@email.com");
        u.setUserType(ut);
        u.setPassword("ghianda");


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


        Lesson lesson = new Lesson();
        lesson.setId(1);
        lesson.setClassroom(cr);
        lesson.setTimeslot(ts);
        lesson.setSubject(s);


        //FINE NORMALE

        //MODEL
        AcademicYearModel ayM = new AcademicYearModel();
        ayM.setID(1);
        ayM.setStartYear(2017);
        ayM.setEndYear(2018);


        CourseOfStudyModel csM = new CourseOfStudyModel();
        csM.setID(1);
        csM.setName("Engineering");
        csM.setAcademicYear(ayM);


        UserModel uM = new UserModel();
        uM.setId(1);
        uM.setName("Francesco");
        uM.setSurname("Quercia");
        uM.setEmail("francesco.quercia@email.com");
        uM.setUserType(UserTypeModel.PROFESSOR);
        uM.setPassword("ghianda");



        SubjectModel sM = new SubjectModel();
        sM.setID(1);
        sM.setName("Software");
        sM.setCFU(12);
        sM.setProfessor(uM);
        sM.setCourseOfStudy(csM);
        sM.setTeachingYear(2018);


        TimeSlotModel tsM = new TimeSlotModel();
        tsM.setID(1);
        tsM.setStartTime(startDate);
        tsM.setEndTime(endDate);


        ClassroomModel crM = new ClassroomModel();
        crM.setID(1);
        crM.setName("Y1");
        crM.setLatitude(1.0);
        crM.setLongitude(1.0);


        LessonModel lessonM = new LessonModel();

        lessonM.setClassroom(crM);
        lessonM.setTimeSlot(tsM);
        lessonM.setSubject(sM);


        when(lessonRepository.save(any(Lesson.class))).thenReturn(lesson);

        LessonModel model1 = lessonService.saveLesson(lessonM);

        assertEquals(lesson.getId(), model1.getID());
        assertEquals(lesson.getClassroom().getName(), model1.getClassroom().getName());
        assertEquals(lesson.getSubject().getName(), model1.getSubject().getName());


    }


}
