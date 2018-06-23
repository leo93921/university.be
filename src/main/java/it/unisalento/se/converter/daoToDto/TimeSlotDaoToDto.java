package it.unisalento.se.converter.daoToDto;

import it.unisalento.se.dao.Timeslot;
import it.unisalento.se.dto.TimeSlotModel;

public class TimeSlotDaoToDto {

    public static TimeSlotModel convert(Timeslot dao) {
        TimeSlotModel model = new TimeSlotModel();
        model.setID(dao.getId());
        model.setStartTime(dao.getStartTime());
        model.setEndTime(dao.getEndTime());
        return model;
    }
}
