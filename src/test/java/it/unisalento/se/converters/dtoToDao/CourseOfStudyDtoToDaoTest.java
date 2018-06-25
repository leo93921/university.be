package it.unisalento.se.converters.dtoToDao;

import it.unisalento.se.dao.CourseOfStudy;
import it.unisalento.se.models.AcademicYearModel;
import it.unisalento.se.models.CourseOfStudyModel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CourseOfStudyDtoToDaoTest {

    @Test
    public void convert() {
        AcademicYearModel academicYear = new AcademicYearModel();
        academicYear.setID(1);
        academicYear.setStartYear(2017);
        academicYear.setEndYear(2018);

        CourseOfStudyModel model = new CourseOfStudyModel();
        model.setName("Computer Engineering");
        model.setID(25);
        model.setAcademicYear(academicYear);

        CourseOfStudy dao = CourseOfStudyDtoToDao.convert(model);

        assertEquals(model.getID(), dao.getId());
        assertEquals(model.getName(), dao.getName());
        assertEquals(
                model.getAcademicYear().getStartYear(),
                new Integer(dao.getAcademicYear().getStartYear()));
        assertEquals(
                model.getAcademicYear().getEndYear(),
                new Integer(dao.getAcademicYear().getEndYear())
        );
    }

}