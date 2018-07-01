package it.unisalento.se.converters.dtoToDao;

import it.unisalento.se.dao.ExamResults;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.*;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ExamResultDtoToDaoTest {
    @Test
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


        UserModel u2 = new UserModel();
        u2.setId(2);
        u2.setName("Tom");
        u2.setSurname("nook");
        u2.setEmail("tom.nook@n.jp");
        u2.setUserType(UserTypeModel.STUDENT);
        u2.setPassword("money");

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
        cr.setID(1);
        cr.setName("Y1");
        cr.setLatitude(1.0);
        cr.setLongitude(1.0);


        ExamModel exam = new ExamModel();
        exam.setID(1);
        exam.setDescription("Descrizione generica di un esame");
        exam.setSubject(s);
        exam.setClassroom(cr);
        exam.setTimeslot(ts);

        Date examDate = new Date();


        ExamResultModel examResult = new ExamResultModel();
        examResult.setID(1);
        examResult.setVote(18);
        examResult.setStudent(u2);
        examResult.setDate(examDate);
        examResult.setExam(exam);


        ExamResults dao = ExamResultDtoToDao.convert(examResult);
        assertEquals(new Integer(1), dao.getId());
        assertEquals(18, dao.getVote());
        assertEquals(u2.getName(), dao.getUser().getName());
        assertEquals(examDate, dao.getDate());
        assertEquals(exam.getDescription(), dao.getExam().getDescription());


    }


}
