package it.unisalento.se.converters.dtoToDao;

import it.unisalento.se.dao.SupportDevice;
import it.unisalento.se.models.SupportDeviceModel;
import org.junit.Assert;
import org.junit.Test;

public class SupportDeviceDtoToDaoTest {


    @Test
    public void convert() {

        SupportDeviceModel model = new SupportDeviceModel();
        model.setID(1);
        model.setName("Lavagna");

        SupportDevice dao = SupportDeviceDtoToDao.convert(model);


        Assert.assertEquals(new Integer(1), dao.getId());

        Assert.assertEquals("Lavagna", dao.getName());

    }

}
