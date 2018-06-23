package it.unisalento.se.iservices;

import it.unisalento.se.models.TimeSlotModel;
import it.unisalento.se.exceptions.TimeSlotNotFoundException;

public interface ITimeSlotService {

    TimeSlotModel getTimeSlot(Integer ID) throws TimeSlotNotFoundException;

    TimeSlotModel createTimeSlot(TimeSlotModel mode);
}
