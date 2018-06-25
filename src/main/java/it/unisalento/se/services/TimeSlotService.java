package it.unisalento.se.services;

import it.unisalento.se.converters.daoToDto.TimeSlotDaoToDto;
import it.unisalento.se.converters.dtoToDao.TimeSlotDtoToDao;
import it.unisalento.se.dao.Timeslot;
import it.unisalento.se.exceptions.TimeSlotNotFoundException;
import it.unisalento.se.iservices.ITimeSlotService;
import it.unisalento.se.models.TimeSlotModel;
import it.unisalento.se.repositories.TimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class TimeSlotService implements ITimeSlotService {

    @Autowired
    private TimeSlotRepository timeSlotRepository;

    @Override
    @Transactional(readOnly = true)
    public TimeSlotModel getTimeSlot(Integer ID) throws TimeSlotNotFoundException {
        try {
            Timeslot t = timeSlotRepository.getOne(ID);
            return TimeSlotDaoToDto.convert(t);
        } catch (EntityNotFoundException e) {
            throw new TimeSlotNotFoundException();
        }

    }

    @Override
    @Transactional
    public TimeSlotModel createTimeSlot(TimeSlotModel model) {
        Timeslot dao = TimeSlotDtoToDao.convert(model);
        Timeslot saved = timeSlotRepository.save(dao);
        return TimeSlotDaoToDto.convert(saved);
    }
}
