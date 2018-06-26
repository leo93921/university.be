package it.unisalento.se.converters.daoToDto;

import it.unisalento.se.common.Constants;
import it.unisalento.se.dao.*;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.SubjectModel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SubjectDaoToDtoTest {

    @Test
    public void convert() throws UserTypeNotSupported {

        UserType profType = new UserType();
        profType.setName(Constants.PROFESSOR);

        AcademicYear y = new AcademicYear();
        y.setId(1);
        y.setStartYear(2023);
        y.setEndYear(2024);

        Subject dao = new Subject();
        dao.setId(14);
        dao.setYear(3);
        dao.setCfu(9);
        dao.setName("Software Engineering");
        dao.setUser(new User(profType, "Mario", "Rossi", "mario.rossi@example.it"));
        dao.setCourseOfStudy(new CourseOfStudy(y, "Computer Engineering"));

        SubjectModel model = SubjectDaoToDto.convert(dao);

        assertEquals(dao.getId(), model.getID());
        assertEquals(new Integer(dao.getCfu()), model.getCFU());
        assertEquals(new Integer(dao.getYear()), model.getTeachingYear());
        assertEquals(dao.getName(), model.getName());
    }

}