package it.unisalento.se.converters.daoToDto;

import it.unisalento.se.dao.Classroom;
import it.unisalento.se.dao.SupportDevice;
import it.unisalento.se.models.ClassroomModel;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class ClassroomDaoToDtoTest {


    @Test
    public void convert() {

        Classroom c = new Classroom();
        c.setId(1);
        c.setName("O1");
        c.setLatitude(88.6);
        c.setLongitude(95.8);

        Set<SupportDevice> supportDevices = new HashSet<>();
        supportDevices.add(new SupportDevice("Projector"));
        c.setSupportDevices(supportDevices);

        ClassroomModel m = ClassroomDaoToDto.convert(c);

        assertEquals(new Integer(1), m.getID());
        assertEquals("O1", m.getName());
        assertEquals(88.6, m.getLatitude(), 0);
        assertEquals(95.8, m.getLongitude(), 0);
        assertEquals("Projector", m.getSupportDevices().get(0).getName());


    }
}