package it.unisalento.se.iservices;

import it.unisalento.se.exceptions.TimeSlotNotFoundException;
import it.unisalento.se.models.TimeSlotModel;

public interface ITimeSlotService {

    TimeSlotModel getTimeSlot(Integer ID) throws TimeSlotNotFoundException;

    TimeSlotModel createTimeSlot(TimeSlotModel mode);
}
