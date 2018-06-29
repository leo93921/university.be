package it.unisalento.se.converters.dtoToDao;

import it.unisalento.se.dao.Exam;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.*;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ExamDtoToDaoTest {


    public void convert() throws UserTypeNotSupported {
        AcademicYearModel ay = new AcademicYearModel();
        ay.setID(1);
        ay.setStartYear(2017);
        ay.setEndYear(2018);

        CourseOfStudyModel cs = new CourseOfStudyModel();
        cs.setID(1);
        cs.setName("Engineering");
        cs.setAcademicYear(ay);


        UserModel u = new UserModel();
        u.setId(1);
        u.setName("Luigi");
        u.setSurname("Mario");
        u.setEmail("mario.luigi@n.jp");
        u.setUserType(UserTypeModel.PROFESSOR);
        u.setPassword("peach");

        SubjectModel s = new SubjectModel();
        s.setID(1);
        s.setName("Software");
        s.setCFU(12);
        s.setProfessor(u);
        s.setCourseOfStudy(cs);
        s.setTeachingYear(2018);


        Date startDate = new Date();
        startDate.setTime(startDate.getTime() - 100);
        Date endDate = new Date();
        TimeSlotModel ts = new TimeSlotModel();
        ts.setID(1);
        ts.setStartTime(startDate);
        ts.setEndTime(endDate);


        ClassroomModel cr = new ClassroomModel();
        cr.setId(1);
        cr.setName("Y1");
        cr.setLatitude(1.0);
        cr.setLongitude(1.0);


        ExamModel exam = new ExamModel();
        exam.setID(1);
        exam.setDescription("Terzo appello");
        exam.setSubject(s);
        exam.setClassroom(cr);
        exam.setTimeslot(ts);

        Exam dao = ExamDtoToDao.convert(exam);
        assertEquals(new Integer(1), dao.getId());
        assertEquals(cr.getName(), dao.getClassroom().getName());
        assertEquals(ts.getStartTime(), dao.getTimeslot().getStartTime());
        assertEquals(s.getName(), dao.getSubject().getName());

    }
}
