package it.unisalento.se.iservice;

import it.unisalento.se.dto.TimeSlotModel;
import it.unisalento.se.exceptions.TimeSlotNotFoundException;

public interface ITimeSlotService {

    TimeSlotModel getTimeSlot(Integer ID) throws TimeSlotNotFoundException;

    TimeSlotModel createTimeSlot(TimeSlotModel mode);
}
