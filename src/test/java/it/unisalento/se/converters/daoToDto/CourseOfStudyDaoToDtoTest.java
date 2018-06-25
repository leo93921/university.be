package it.unisalento.se.converters.daoToDto;

import it.unisalento.se.dao.AcademicYear;
import it.unisalento.se.dao.CourseOfStudy;
import it.unisalento.se.models.CourseOfStudyModel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CourseOfStudyDaoToDtoTest {

    @Test
    public void convert() {

        CourseOfStudy dao = new CourseOfStudy();
        dao.setName("Computer Engineering");
        dao.setId(25);
        dao.setAcademicYear(new AcademicYear(2017, 2018));

        CourseOfStudyModel model = CourseOfStudyDaoToDto.convert(dao);

        assertEquals(dao.getId(), model.getID());
        assertEquals(dao.getName(), model.getName());
        assertEquals(
                new Integer(dao.getAcademicYear().getStartYear()),
                model.getAcademicYear().getStartYear());
        assertEquals(
                new Integer(dao.getAcademicYear().getEndYear()),
                model.getAcademicYear().getEndYear()
        );

    }
}