package it.unisalento.se.services;

import it.unisalento.se.converters.daoToDto.CourseOfStudyDaoToDto;
import it.unisalento.se.converters.dtoToDao.CourseOfStudyDtoToDao;
import it.unisalento.se.dao.CourseOfStudy;
import it.unisalento.se.exceptions.CourseOfStudyNotFound;
import it.unisalento.se.iservices.ICourseOfStudyService;
import it.unisalento.se.models.CourseOfStudyModel;
import it.unisalento.se.repositories.CourseOfStudyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseOfStudyService implements ICourseOfStudyService {

    @Autowired
    private CourseOfStudyRepository repository;

    @Override
    @Transactional(readOnly = true)
    public CourseOfStudyModel getCourseOfStudyByID(Integer ID) throws CourseOfStudyNotFound {
        try {
            CourseOfStudy dao = repository.getOne(ID);
            return CourseOfStudyDaoToDto.convert(dao);
        } catch (EntityNotFoundException e) {
            throw new CourseOfStudyNotFound();
        }
    }

    @Override
    @Transactional
    public CourseOfStudyModel saveCourseOfStudy(CourseOfStudyModel model) {
        CourseOfStudy dao = CourseOfStudyDtoToDao.convert(model);
        CourseOfStudy saved = repository.save(dao);
        return CourseOfStudyDaoToDto.convert(saved);
    }

    @Override
    public List<CourseOfStudyModel> getAllCourses() {
        List<CourseOfStudy> daos = repository.findAll();
        List<CourseOfStudyModel> models = new ArrayList<>();
        for (CourseOfStudy dao : daos) {
            models.add(CourseOfStudyDaoToDto.convert(dao));
        }
        return models;
    }
}
