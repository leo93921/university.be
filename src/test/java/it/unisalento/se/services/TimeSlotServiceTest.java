package it.unisalento.se.services;

import it.unisalento.se.dao.Timeslot;
import it.unisalento.se.exceptions.TimeSlotNotFoundException;
import it.unisalento.se.models.TimeSlotModel;
import it.unisalento.se.repositories.TimeSlotRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TimeSlotServiceTest {

    @Mock
    private TimeSlotRepository timeSlotRepository;
    @InjectMocks
    private TimeSlotService timeSlotService;

    @Test
    public void getTimeSlot_OK() throws TimeSlotNotFoundException {
        Date startDate = new Date();
        startDate.setTime(startDate.getTime() - 100);
        Date endDate = new Date();

        Timeslot t = new Timeslot();
        t.setId(1);
        t.setStartTime(startDate);
        t.setEndTime(endDate);
        when(timeSlotRepository.getOne(1)).thenReturn(t);

        TimeSlotModel model = timeSlotService.getTimeSlot(1);

        assertEquals(new Integer(1), model.getID());
        assertEquals(startDate, model.getStartTime());
        assertEquals(endDate, model.getEndTime());

    }


    @Test(expected = TimeSlotNotFoundException.class)
    public void getTimeSlot_shouldFail() throws TimeSlotNotFoundException {
        when(timeSlotRepository.getOne(10)).thenThrow(new EntityNotFoundException());

        TimeSlotModel model = timeSlotService.getTimeSlot(10);
    }

    @Test
    public void createTimeSlot() {
        Date startDate = new Date();
        startDate.setTime(startDate.getTime() - 100);
        Date endDate = new Date();

        Timeslot returned = new Timeslot();
        returned.setId(1);
        returned.setStartTime(startDate);
        returned.setEndTime(endDate);

        when(timeSlotRepository.save(any(Timeslot.class))).thenReturn(returned);

        TimeSlotModel m = new TimeSlotModel();
        m.setStartTime(startDate);
        m.setEndTime(endDate);

        TimeSlotModel model = timeSlotService.createTimeSlot(m);

        assertNotNull(model);
        assertEquals(new Integer(1), model.getID());
        assertEquals(startDate, model.getStartTime());
        assertEquals(endDate, model.getEndTime());

    }
}