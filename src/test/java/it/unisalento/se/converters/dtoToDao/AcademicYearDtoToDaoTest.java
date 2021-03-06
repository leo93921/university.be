package it.unisalento.se.converters.dtoToDao;

import it.unisalento.se.dao.AcademicYear;
import it.unisalento.se.models.AcademicYearModel;
import org.junit.Assert;
import org.junit.Test;


public class AcademicYearDtoToDaoTest {


    @Test
    public void convert() {
        AcademicYearModel model = new AcademicYearModel();
        model.setID(1);
        model.setStartYear(1938);
        model.setEndYear(1946);


        AcademicYear dao = AcademicYearDtoToDao.convert(model);

        Assert.assertEquals(new Integer(1), dao.getId());
        Assert.assertEquals(1938, dao.getStartYear());
        Assert.assertEquals(1946, dao.getEndYear());


    }


}
