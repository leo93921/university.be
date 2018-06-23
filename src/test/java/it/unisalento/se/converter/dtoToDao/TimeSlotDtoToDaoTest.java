package it.unisalento.se.converter.dtoToDao;

import it.unisalento.se.dao.Timeslot;
import it.unisalento.se.dto.TimeSlotModel;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TimeSlotDtoToDaoTest {

    @Test
    public void convert() {

        Date startDate = new Date();
        startDate.setTime(startDate.getTime() - 100);
        Date endTime = new Date();

        TimeSlotModel m = new TimeSlotModel();
        m.setID(1);
        m.setStartTime(startDate);
        m.setEndTime(endTime);

        Timeslot dao = TimeSlotDtoToDao.convert(m);

        assertNotNull(dao);
        assertEquals(new Integer(1), dao.getId());
        assertEquals(startDate, dao.getStartTime());
        assertEquals(endTime, dao.getEndTime());
    }
}