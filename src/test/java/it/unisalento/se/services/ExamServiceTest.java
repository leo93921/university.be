package it.unisalento.se.services;

import it.unisalento.se.dao.*;
import it.unisalento.se.exceptions.ExamNotFoundException;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.*;
import it.unisalento.se.repositories.ExamRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExamServiceTest {
    @Mock
    private ExamRepository examRepository;
    @InjectMocks
    private ExamService examService;

    @Test
    public void getExam_OK() throws ExamNotFoundException, UserTypeNotSupported {


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


        Exam exam = new Exam();
        exam.setId(1);
        exam.setDescription("Primo appello");
        exam.setSubject(s);
        exam.setClassroom(cr);
        exam.setTimeslot(ts);

        when(examRepository.getOne(1)).thenReturn(exam);

        ExamModel model = examService.getExamByID(1);
        assertEquals(new Integer(1), model.getID());
        assertEquals(exam.getDescription(), model.getDescription());
        assertEquals(s.getName(), model.getSubject().getName());
        assertEquals(cr.getName(), model.getClassroom().getName());
        assertEquals(ts.getStartTime(), model.getTimeslot().getStartTime());
    }


    @Test(expected = ExamNotFoundException.class)
    public void getExam_shouldFail() throws ExamNotFoundException, UserTypeNotSupported {
        when(examRepository.getOne(10)).thenThrow(new EntityNotFoundException());

        ExamModel model = examService.getExamByID(10);
    }


    @Test
    public void createExam() throws UserTypeNotSupported {


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
        ut.setId(1);
        ut.setName("PROFESSOR");

        User u = new User();
        u.setId(1);
        u.setName("Luigi");
        u.setSurname("Mario");
        u.setEmail("mario.luigi@n.jp");
        u.setUserType(ut);
        u.setPassword("peach");

        UserType ut2 = new UserType();
        ut2.setId(2);
        ut2.setName("STUDENT");

        User u2 = new User();
        u2.setId(2);
        u2.setName("Tom");
        u2.setSurname("Nook");
        u2.setEmail("tom.nook@n.jp");
        u2.setUserType(ut2);
        u2.setPassword("money");


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


        Exam exam = new Exam();
        exam.setId(1);
        exam.setDescription("Primo appello");
        exam.setSubject(s);
        exam.setClassroom(cr);
        exam.setTimeslot(ts);


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
        uM.setName("Luigi");
        uM.setSurname("Mario");
        uM.setEmail("mario.luigi@n.jp");
        uM.setUserType(UserTypeModel.PROFESSOR);
        uM.setPassword("peach");


        UserModel u2M = new UserModel();
        u2M.setId(2);
        u2M.setName("Tom");
        u2M.setSurname("Nook");
        u2M.setEmail("tom.nook@n.jp");
        u2M.setUserType(UserTypeModel.STUDENT);
        u2M.setPassword("money");


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


        ExamModel examM = new ExamModel();
        examM.setID(1);
        examM.setDescription("Primo appello");
        examM.setSubject(sM);
        examM.setClassroom(crM);
        examM.setTimeslot(tsM);


        when(examRepository.save(any(Exam.class))).thenReturn(exam);

        ExamModel model1 = examService.saveExam(examM);


        assertEquals(exam.getId(), model1.getID());
        assertEquals(exam.getDescription(), model1.getDescription());
        assertEquals(exam.getSubject().getName(), model1.getSubject().getName());


    }


}
