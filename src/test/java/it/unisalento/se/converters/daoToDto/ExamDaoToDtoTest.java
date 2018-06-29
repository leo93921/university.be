package it.unisalento.se.converters.daoToDto;

import it.unisalento.se.converters.daoToDto.TimeSlotDaoToDto;
import it.unisalento.se.converters.dtoToDao.ExamDtoToDao;
import it.unisalento.se.converters.dtoToDao.LessonDtoToDao;
import it.unisalento.se.dao.*;
import it.unisalento.se.models.TimeSlotModel;
import org.junit.Test;
import it.unisalento.se.dao.*;
import it.unisalento.se.exceptions.LessonNotFoundException;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.*;
import it.unisalento.se.repositories.LessonRepository;
import it.unisalento.se.repositories.TimeSlotRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.Date;

import it.unisalento.se.dao.Timeslot;
import it.unisalento.se.exceptions.TimeSlotNotFoundException;
import it.unisalento.se.repositories.TimeSlotRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import static org.mockito.Mockito.when;

import java.util.Date;

import static org.junit.Assert.assertEquals;


public class ExamDaoToDtoTest {

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


        ExamModel model = ExamDaoToDto.convert(exam);
        assertEquals(new Integer(1), model.getID());
        assertEquals(cr.getName(), model.getClassroom().getName());
        assertEquals(ts.getStartTime(), model.getTimeslot().getStartTime());
        assertEquals(s.getName(), model.getSubject().getName());



    }

}
