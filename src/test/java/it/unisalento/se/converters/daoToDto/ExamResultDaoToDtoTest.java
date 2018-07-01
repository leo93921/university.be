package it.unisalento.se.converters.daoToDto;

import it.unisalento.se.dao.*;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.ExamModel;
import it.unisalento.se.models.ExamResultModel;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ExamResultDaoToDtoTest {


    @Test
    public void convert_OK() throws UserTypeNotSupported {


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
        exam.setDescription("Terzo appello");
        exam.setSubject(s);
        exam.setClassroom(cr);
        exam.setTimeslot(ts);

        Date examDate = new Date();
        ExamResults examResult = new ExamResults();
        examResult.setId(1);
        examResult.setVote(18);
        examResult.setUser(u2);
        examResult.setDate(examDate);
        examResult.setExam(exam);



        ExamResultModel model = ExamResultDaoToDto.convert(examResult);
        assertEquals(new Integer(1), model.getID());
        assertEquals(new Integer (18), model.getVote());
        assertEquals(u2.getName(), model.getStudent().getName());
        assertEquals(examDate, model.getDate());
        assertEquals(exam.getDescription(), model.getExam().getDescription());





    }


}
