package it.unisalento.se.services;


import it.unisalento.se.converters.daoToDto.LessonDaoToDto;
import it.unisalento.se.converters.dtoToDao.LessonDtoToDao;
import it.unisalento.se.converters.dtoToDao.SubjectDtoToDao;
import it.unisalento.se.dao.Lesson;
import it.unisalento.se.exceptions.LessonNotFoundException;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.iservices.ILessonService;
import it.unisalento.se.models.LessonFilterModel;
import it.unisalento.se.models.LessonModel;
import it.unisalento.se.models.SubjectModel;
import it.unisalento.se.repositories.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<LessonModel> filterByTimeAndSubject(LessonFilterModel filter) throws UserTypeNotSupported {
        List<Lesson> daos = repository.findByTimeAndSubject(
                filter.getStartTime().getStartTime(),
                filter.getEndTime().getEndTime(),
                filter.getSubject().getID());

        List<LessonModel> models = new ArrayList<>();
        for (Lesson dao : daos) {
            models.add(LessonDaoToDto.convert(dao));
        }
        return models;
    }



    @Override
    public List<LessonModel> filterByTimeAndCourseOfStudy(LessonFilterModel filter) throws UserTypeNotSupported {
        List<Lesson> daos = repository.findByTimeAndCourseOfStudy(
                filter.getStartTime().getStartTime(),
                filter.getEndTime().getEndTime(),
                filter.getCourseOfStudy().getID());

        List<LessonModel> models = new ArrayList<>();
        for (Lesson dao : daos) {
            models.add(LessonDaoToDto.convert(dao));
        }
        return models;
    }



    @Override
    public List<LessonModel> getLessonsBySubjects(SubjectModel subject) throws UserTypeNotSupported {
        List<Lesson> daos = repository.findBySubject(SubjectDtoToDao.convert(subject));
        List<LessonModel> models = new ArrayList<>();
        for (Lesson dao : daos) {
            models.add(LessonDaoToDto.convert(dao));
        }
        return models;
    }

}
