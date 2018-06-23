package it.unisalento.se.converters.daoToDto;

import it.unisalento.se.dao.AcademicYear;
import it.unisalento.se.models.AcademicYearModel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AcademicYearDaoToDtoTest {
    @Test
    public void convert()  {


        AcademicYear y = new AcademicYear();
        y.setId(1);
        y.setStartYear(2023);
        y.setEndYear(2024);


        AcademicYearModel m = AcademicYearDaoToDto.convert(y);

        assertEquals(new Integer(1), m.getId());
        assertEquals(new Integer(2023), m.getStartYear());
        assertEquals(new Integer(2024), m.getEndYear());


    }


}
