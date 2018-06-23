package it.unisalento.se.converter.daoToDto;

import it.unisalento.se.dao.Timeslot;
import it.unisalento.se.dto.TimeSlotModel;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class TimeSlotDaoToDtoTest {

    @Test
    public void convert_OK() {

        Date startTime = new Date();
        startTime.setTime(startTime.getTime() - 100);
        Date endTime = new Date();

        Timeslot t = new Timeslot();
        t.setId(1);
        t.setStartTime(startTime);
        t.setEndTime(endTime);

        TimeSlotModel model = TimeSlotDaoToDto.convert(t);

        // Tests
        assertEquals(new Integer(1), model.getID());
        assertEquals(startTime, model.getStartTime());
        assertEquals(endTime, model.getEndTime());
    }
}