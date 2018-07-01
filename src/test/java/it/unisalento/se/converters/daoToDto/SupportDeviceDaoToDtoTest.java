package it.unisalento.se.converters.daoToDto;

import it.unisalento.se.dao.SupportDevice;
import it.unisalento.se.models.SupportDeviceModel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SupportDeviceDaoToDtoTest {


    @Test
    public void convert() {


        SupportDevice sd = new SupportDevice();
        sd.setId(1);
        sd.setName("Acqua calda");

        SupportDeviceModel m = SupportDeviceDaoToDto.convert(sd);
        assertEquals(new Integer(1), m.getID());
        assertEquals("Acqua calda", m.getName());


    }

}
