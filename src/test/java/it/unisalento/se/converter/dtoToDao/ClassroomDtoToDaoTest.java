package it.unisalento.se.converter.dtoToDao;

import it.unisalento.se.dao.Classroom;
import it.unisalento.se.dto.ClassroomModel;
import org.junit.Assert;
import org.junit.Test;


public class ClassroomDtoToDaoTest {

    @Test
    public void convert() {
        ClassroomModel model = new ClassroomModel();
        model.setId(1);
        model.setName("M1");
        model.setLatitude(123.4);
        model.setLongitude(567.8);


        Classroom dao = ClassroomDtoToDao.convert(model);

        Assert.assertEquals(new Integer(1), dao.getId());
        Assert.assertEquals("M1", dao.getName());
        Assert.assertEquals(123.4, dao.getLatitude(),0);
        Assert.assertEquals(567.8, dao.getLongitude(),0);


    }

}
