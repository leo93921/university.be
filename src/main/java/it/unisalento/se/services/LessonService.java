package it.unisalento.se.services;


import it.unisalento.se.converters.daoToDto.LessonDaoToDto;
import it.unisalento.se.converters.dtoToDao.LessonDtoToDao;
import it.unisalento.se.dao.Lesson;
import it.unisalento.se.exceptions.LessonNotFoundException;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.iservices.ILessonService;
import it.unisalento.se.models.LessonModel;
import it.unisalento.se.repositories.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class LessonService implements ILessonService {

    @Autowired
    private LessonRepository repository;

    @Override
    @Transactional(readOnly = true)
    public LessonModel getLessonByID(Integer ID) throws UserTypeNotSupported, LessonNotFoundException {
        try {
            Lesson dao = repository.getOne(ID);
            return LessonDaoToDto.convert(dao);
        } catch (EntityNotFoundException e) {
            throw new LessonNotFoundException();
        }
    }


    @Override
    @Transactional
    public LessonModel saveLesson(LessonModel model) throws UserTypeNotSupported {
        // TODO check if user is a professor, otherwise throw a new exception
        Lesson lesson = LessonDtoToDao.convert(model);
        Lesson saved = repository.save(lesson);
        return LessonDaoToDto.convert(saved);
    }

}