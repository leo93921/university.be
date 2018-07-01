package it.unisalento.se.converters.dtoToDao;

import it.unisalento.se.common.Constants;
import it.unisalento.se.dao.Subject;
import it.unisalento.se.dao.UserType;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SubjectDtoToDaoTest {

    @Test
    public void convert() throws UserTypeNotSupported {

        UserModel model = new UserModel();
        model.setId(1);
        model.setEmail("mario.rossi@test.it");
        model.setName("Mario");
        model.setSurname("Rossi");
        model.setUserType(UserTypeModel.PROFESSOR);
        UserType type = new UserType();
        type.setName(Constants.PROFESSOR);
        type.setId(2);

        AcademicYearModel academicYear = new AcademicYearModel();
        academicYear.setID(1);
        academicYear.setStartYear(2017);
        academicYear.setEndYear(2018);

        CourseOfStudyModel cs = new CourseOfStudyModel();
        cs.setName("Computer Engineering");
        cs.setID(25);
        cs.setAcademicYear(academicYear);

        SubjectModel sub = new SubjectModel();
        sub.setID(24);
        sub.setCFU(8);
        sub.setName("A new name");
        sub.setTeachingYear(3);
        sub.setProfessor(model);
        sub.setCourseOfStudy(cs);

        Subject dao = SubjectDtoToDao.convert(sub);

        assertEquals(sub.getID(), dao.getId());
        assertEquals(sub.getName(), dao.getName());
        assertEquals(sub.getCFU(), (Integer) dao.getCfu());
        assertEquals(sub.getTeachingYear(), (Integer) dao.getYear());
        assertEquals(cs.getName(),  dao.getCourseOfStudy().getName());

    }
}