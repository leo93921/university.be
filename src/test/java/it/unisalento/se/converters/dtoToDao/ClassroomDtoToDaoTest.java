package it.unisalento.se.converters.dtoToDao;

import it.unisalento.se.dao.Classroom;
import it.unisalento.se.dao.SupportDevice;
import it.unisalento.se.models.ClassroomModel;
import it.unisalento.se.models.SupportDeviceModel;
import org.hamcrest.core.AnyOf;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


public class ClassroomDtoToDaoTest {

    @Test
    public void convert() {
        ClassroomModel model = new ClassroomModel();
        model.setID(1);
        model.setName("M1");
        model.setLatitude(123.4);
        model.setLongitude(567.8);

        List<SupportDeviceModel> supportDevices = new ArrayList<>();
        SupportDeviceModel e = new SupportDeviceModel();
        e.setName("Projector");
        e.setID(1);
        supportDevices.add(e);
        model.setSupportDevices(supportDevices);


        Classroom dao = ClassroomDtoToDao.convert(model);

        Assert.assertEquals(new Integer(1), dao.getId());
        Assert.assertEquals("M1", dao.getName());
        Assert.assertEquals(123.4, dao.getLatitude(), 0);
        Assert.assertEquals(567.8, dao.getLongitude(), 0);

        for (SupportDevice d : dao.getSupportDevices()) {
            Assert.assertThat(d.getId(), AnyOf.anyOf(Is.is(1)));
            Assert.assertThat(d.getName(), AnyOf.anyOf(Is.is("Projector")));
        }

    }

}
