package it.unisalento.se.service;

import it.unisalento.se.converter.daoToDto.TimeSlotDaoToDto;
import it.unisalento.se.converter.dtoToDao.TimeSlotDtoToDao;
import it.unisalento.se.dao.Timeslot;
import it.unisalento.se.dto.TimeSlotModel;
import it.unisalento.se.exceptions.TimeSlotNotFoundException;
import it.unisalento.se.iservice.ITimeSlotService;
import it.unisalento.se.repositories.TimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class TimeSlotService implements ITimeSlotService {

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    @Override
    public TimeSlotModel getTimeSlot(Integer ID) throws TimeSlotNotFoundException {
        try {
            Timeslot t = timeSlotRepository.getOne(ID);
            return TimeSlotDaoToDto.convert(t);
        } catch (EntityNotFoundException e) {
            throw new TimeSlotNotFoundException();
        }

    }

    @Override
    public TimeSlotModel createTimeSlot(TimeSlotModel model) {
        Timeslot dao = TimeSlotDtoToDao.convert(model);
        Timeslot saved = timeSlotRepository.save(dao);
        return TimeSlotDaoToDto.convert(saved);
    }
}
