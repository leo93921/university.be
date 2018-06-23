package it.unisalento.se.converters.dtoToDao;

import it.unisalento.se.dao.Timeslot;
import it.unisalento.se.models.TimeSlotModel;

public class TimeSlotDtoToDao {

    public static Timeslot convert(TimeSlotModel model) {
        Timeslot t = new Timeslot();
        t.setId(model.getID());
        t.setStartTime(model.getStartTime());
        t.setEndTime(model.getEndTime());
        return t;
    }

}
