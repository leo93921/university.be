package it.unisalento.se.converters.daoToDto;

import it.unisalento.se.dao.Timeslot;
import it.unisalento.se.models.TimeSlotModel;

public class TimeSlotDaoToDto {

    public static TimeSlotModel convert(Timeslot dao) {
        TimeSlotModel model = new TimeSlotModel();
        model.setID(dao.getId());
        model.setStartTime(dao.getStartTime());
        model.setEndTime(dao.getEndTime());
        return model;
    }
}
