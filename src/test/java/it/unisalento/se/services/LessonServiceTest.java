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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LessonServiceTest {


    @Mock
    private LessonRepository lessonRepository;
    @InjectMocks
    private LessonService lessonService;
    @Mock
    private FcmService fcmService;


    @Test
    public void getLessonsBySubjects_OK() throws UserTypeNotSupported {
        AcademicYear ay = new AcademicYear();
        ay.setId(1);
        ay.setStartYear(2017);
        ay.setEndYear(2018);

        AcademicYearModel aym = new AcademicYearModel();
        aym.setID(1);
        aym.setStartYear(2017);
        aym.setEndYear(2018);


        CourseOfStudy cs = new CourseOfStudy();
        cs.setId(1);
        cs.setName("Engineering");
        cs.setAcademicYear(ay);


        CourseOfStudyModel csm = new CourseOfStudyModel();
        csm.setID(1);
        csm.setName("Engineering");
        csm.setAcademicYear(aym);

        UserType ut = new UserType();
        ut.setId(1);
        ut.setName("PROFESSOR");

        User u = new User();
        u.setId(1);
        u.setName("Luigi");
        u.setSurname("Mario");
        u.setEmail("mario.luigi@email.it");
        u.setUserType(ut);
        u.setPassword("1234password4567");

        UserModel um = new UserModel();
        um.setId(1);
        um.setName("Luigi");
        um.setSurname("Mario");
        um.setEmail("mario.luigi@email.it");
        um.setUserType(UserTypeModel.PROFESSOR);
        um.setPassword("1234password4567");


        Subject s = new Subject();
        s.setId(1);
        s.setName("Software");
        s.setCfu(12);
        s.setUser(u);
        s.setCourseOfStudy(cs);
        s.setYear(2018);


        SubjectModel sm = new SubjectModel();
        sm.setID(1);
        sm.setName("Software");
        sm.setCFU(12);
        sm.setProfessor(um);
        sm.setCourseOfStudy(csm);
        sm.setTeachingYear(2018);

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

        Date filter_before = new Date();
        Date filter_after = new Date();
        filter_before.setTime(startDate.getTime() - 1000);
        filter_after.setTime(endDate.getTime() + 1000);


        TimeSlotModel ts_before = new TimeSlotModel();
        ts_before.setID(2);
        ts_before.setStartTime(filter_before);
        ts_before.setEndTime(filter_after);

        TimeSlotModel ts_after = new TimeSlotModel();
        ts_after.setID(3);
        ts_after.setStartTime(filter_before);
        ts_after.setEndTime(filter_after);

        LessonFilterModel filter = new LessonFilterModel();
        filter.setStartTime(ts_before);
        filter.setEndTime(ts_after);
        filter.setProfessor(um);

        List<Lesson> lista = new ArrayList<>();
        lista.add(l);


        when(lessonRepository.findBySubject(any(Subject.class))).thenReturn(lista);

        List<LessonModel> model = lessonService.getLessonsBySubjects(sm);

        assertEquals(new Integer(1), model.get(0).getID());
        assertEquals(cr.getName(), model.get(0).getClassroom().getName());
        assertEquals(ts.getStartTime(), model.get(0).getTimeSlot().getStartTime());
        assertEquals(s.getName(), model.get(0).getSubject().getName());


    }

    @Test
    public void filterByTimeAndProfessor_OK() throws UserTypeNotSupported {
        AcademicYear ay = new AcademicYear();
        ay.setId(1);
        ay.setStartYear(2017);
        ay.setEndYear(2018);

        AcademicYearModel aym = new AcademicYearModel();
        aym.setID(1);
        aym.setStartYear(2017);
        aym.setEndYear(2018);


        CourseOfStudy cs = new CourseOfStudy();
        cs.setId(1);
        cs.setName("Engineering");
        cs.setAcademicYear(ay);


        CourseOfStudyModel csm = new CourseOfStudyModel();
        csm.setID(1);
        csm.setName("Engineering");
        csm.setAcademicYear(aym);

        UserType ut = new UserType();
        ut.setId(1);
        ut.setName("PROFESSOR");

        User u = new User();
        u.setId(1);
        u.setName("Luigi");
        u.setSurname("Mario");
        u.setEmail("mario.luigi@email.it");
        u.setUserType(ut);
        u.setPassword("1234password4567");

        UserModel um = new UserModel();
        um.setId(1);
        um.setName("Luigi");
        um.setSurname("Mario");
        um.setEmail("mario.luigi@email.it");
        um.setUserType(UserTypeModel.PROFESSOR);
        um.setPassword("1234password4567");


        Subject s = new Subject();
        s.setId(1);
        s.setName("Software");
        s.setCfu(12);
        s.setUser(u);
        s.setCourseOfStudy(cs);
        s.setYear(2018);


        SubjectModel sm = new SubjectModel();
        sm.setID(1);
        sm.setName("Software");
        sm.setCFU(12);
        sm.setProfessor(um);
        sm.setCourseOfStudy(csm);
        sm.setTeachingYear(2018);

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

        Date filter_before = new Date();
        Date filter_after = new Date();
        filter_before.setTime(startDate.getTime() - 1000);
        filter_after.setTime(endDate.getTime() + 1000);


        TimeSlotModel ts_before = new TimeSlotModel();
        ts_before.setID(2);
        ts_before.setStartTime(filter_before);
        ts_before.setEndTime(filter_after);

        TimeSlotModel ts_after = new TimeSlotModel();
        ts_after.setID(3);
        ts_after.setStartTime(filter_before);
        ts_after.setEndTime(filter_after);

        LessonFilterModel filter = new LessonFilterModel();
        filter.setStartTime(ts_before);
        filter.setEndTime(ts_after);
        filter.setProfessor(um);

        List<Lesson> lista = new ArrayList<>();
        lista.add(l);


        when(lessonRepository.findByTimeAndProfessor(ts_before.getStartTime(), ts_after.getEndTime(), sm.getID())).thenReturn(lista);

        List<LessonModel> model = lessonService.filterByTimeAndProfessor(filter);

        assertEquals(new Integer(1), model.get(0).getID());
        assertEquals(cr.getName(), model.get(0).getClassroom().getName());
        assertEquals(ts.getStartTime(), model.get(0).getTimeSlot().getStartTime());
        assertEquals(s.getName(), model.get(0).getSubject().getName());


    }


    @Test
    public void filterByTimeAndCourseOfStudy_OK() throws UserTypeNotSupported {
        AcademicYear ay = new AcademicYear();
        ay.setId(1);
        ay.setStartYear(2017);
        ay.setEndYear(2018);

        AcademicYearModel aym = new AcademicYearModel();
        aym.setID(1);
        aym.setStartYear(2017);
        aym.setEndYear(2018);


        CourseOfStudy cs = new CourseOfStudy();
        cs.setId(1);
        cs.setName("Engineering");
        cs.setAcademicYear(ay);


        CourseOfStudyModel csm = new CourseOfStudyModel();
        csm.setID(1);
        csm.setName("Engineering");
        csm.setAcademicYear(aym);

        UserType ut = new UserType();
        ut.setId(1);
        ut.setName("PROFESSOR");

        User u = new User();
        u.setId(1);
        u.setName("Luigi");
        u.setSurname("Mario");
        u.setEmail("mario.luigi@email.it");
        u.setUserType(ut);
        u.setPassword("1234password4567");

        UserModel um = new UserModel();
        um.setId(1);
        um.setName("Luigi");
        um.setSurname("Mario");
        um.setEmail("mario.luigi@email.it");
        um.setUserType(UserTypeModel.PROFESSOR);
        um.setPassword("1234password4567");


        Subject s = new Subject();
        s.setId(1);
        s.setName("Software");
        s.setCfu(12);
        s.setUser(u);
        s.setCourseOfStudy(cs);
        s.setYear(2018);


        SubjectModel sm = new SubjectModel();
        sm.setID(1);
        sm.setName("Software");
        sm.setCFU(12);
        sm.setProfessor(um);
        sm.setCourseOfStudy(csm);
        sm.setTeachingYear(2018);

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

        Date filter_before = new Date();
        Date filter_after = new Date();
        filter_before.setTime(startDate.getTime() - 1000);
        filter_after.setTime(endDate.getTime() + 1000);


        TimeSlotModel ts_before = new TimeSlotModel();
        ts_before.setID(2);
        ts_before.setStartTime(filter_before);
        ts_before.setEndTime(filter_after);

        TimeSlotModel ts_after = new TimeSlotModel();
        ts_after.setID(3);
        ts_after.setStartTime(filter_before);
        ts_after.setEndTime(filter_after);

        LessonFilterModel filter = new LessonFilterModel();
        filter.setStartTime(ts_before);
        filter.setEndTime(ts_after);
        filter.setCourseOfStudy(csm);

        List<Lesson> lista = new ArrayList<>();
        lista.add(l);


        when(lessonRepository.findByTimeAndCourseOfStudy(ts_before.getStartTime(), ts_after.getEndTime(), sm.getID())).thenReturn(lista);

        List<LessonModel> model = lessonService.filterByTimeAndCourseOfStudy(filter);

        assertEquals(new Integer(1), model.get(0).getID());
        assertEquals(cr.getName(), model.get(0).getClassroom().getName());
        assertEquals(ts.getStartTime(), model.get(0).getTimeSlot().getStartTime());
        assertEquals(s.getName(), model.get(0).getSubject().getName());


    }


    @Test
    public void filterByTimeAndSubject_OK() throws UserTypeNotSupported {
        AcademicYear ay = new AcademicYear();
        ay.setId(1);
        ay.setStartYear(2017);
        ay.setEndYear(2018);

        AcademicYearModel aym = new AcademicYearModel();
        aym.setID(1);
        aym.setStartYear(2017);
        aym.setEndYear(2018);


        CourseOfStudy cs = new CourseOfStudy();
        cs.setId(1);
        cs.setName("Engineering");
        cs.setAcademicYear(ay);


        CourseOfStudyModel csm = new CourseOfStudyModel();
        csm.setID(1);
        csm.setName("Engineering");
        csm.setAcademicYear(aym);

        UserType ut = new UserType();
        ut.setId(1);
        ut.setName("PROFESSOR");

        User u = new User();
        u.setId(1);
        u.setName("Luigi");
        u.setSurname("Mario");
        u.setEmail("mario.luigi@email.it");
        u.setUserType(ut);
        u.setPassword("1234password4567");

        UserModel um = new UserModel();
        um.setId(1);
        um.setName("Luigi");
        um.setSurname("Mario");
        um.setEmail("mario.luigi@email.it");
        um.setUserType(UserTypeModel.PROFESSOR);
        um.setPassword("1234password4567");


        Subject s = new Subject();
        s.setId(1);
        s.setName("Software");
        s.setCfu(12);
        s.setUser(u);
        s.setCourseOfStudy(cs);
        s.setYear(2018);


        SubjectModel sm = new SubjectModel();
        sm.setID(1);
        sm.setName("Software");
        sm.setCFU(12);
        sm.setProfessor(um);
        sm.setCourseOfStudy(csm);
        sm.setTeachingYear(2018);

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

        Date filter_before = new Date();
        Date filter_after = new Date();
        filter_before.setTime(startDate.getTime() - 1000);
        filter_after.setTime(endDate.getTime() + 1000);


        TimeSlotModel ts_before = new TimeSlotModel();
        ts_before.setID(2);
        ts_before.setStartTime(filter_before);
        ts_before.setEndTime(filter_after);

        TimeSlotModel ts_after = new TimeSlotModel();
        ts_after.setID(3);
        ts_after.setStartTime(filter_before);
        ts_after.setEndTime(filter_after);

        LessonFilterModel filter = new LessonFilterModel();
        filter.setStartTime(ts_before);
        filter.setEndTime(ts_after);
        filter.setSubject(sm);

        List<Lesson> lista = new ArrayList<>();
        lista.add(l);


        when(lessonRepository.findByTimeAndSubject(ts_before.getStartTime(), ts_after.getEndTime(), sm.getID())).thenReturn(lista);

        List<LessonModel> model = lessonService.filterByTimeAndSubject(filter);

        assertEquals(new Integer(1), model.get(0).getID());
        assertEquals(cr.getName(), model.get(0).getClassroom().getName());
        assertEquals(ts.getStartTime(), model.get(0).getTimeSlot().getStartTime());
        assertEquals(s.getName(), model.get(0).getSubject().getName());


    }


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
        u.setEmail("mario.luigi@email.it");
        u.setUserType(ut);
        u.setPassword("1234password4567");


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

        lessonM.setID(9);
        lessonM.setClassroom(crM);
        lessonM.setTimeSlot(tsM);
        lessonM.setSubject(sM);


        when(lessonRepository.save(any(Lesson.class))).thenReturn(lesson);
        Classroom classroom = new Classroom();
        classroom.setId(5);
        classroom.setLongitude(1.);
        classroom.setLatitude(2.);
        lesson.setClassroom(classroom);
        when(lessonRepository.getOne(any(Integer.class))).thenReturn(lesson);

        LessonModel model1 = lessonService.saveLesson(lessonM);

        assertEquals(lesson.getId(), model1.getID());
        assertEquals(lesson.getClassroom().getName(), model1.getClassroom().getName());
        assertEquals(lesson.getSubject().getName(), model1.getSubject().getName());


    }


}
