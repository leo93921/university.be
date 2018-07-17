package it.unisalento.se.services;


import it.unisalento.se.converters.daoToDto.ExamDaoToDto;
import it.unisalento.se.converters.daoToDto.LessonDaoToDto;
import it.unisalento.se.converters.dtoToDao.ExamDtoToDao;
import it.unisalento.se.dao.Exam;
import it.unisalento.se.dao.Lesson;
import it.unisalento.se.exceptions.ExamNotFoundException;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.iservices.IExamService;
import it.unisalento.se.models.ExamFilterModel;
import it.unisalento.se.models.ExamModel;
import it.unisalento.se.models.LessonFilterModel;
import it.unisalento.se.models.LessonModel;
import it.unisalento.se.repositories.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExamService implements IExamService {

    @Autowired
    private ExamRepository repository;

    @Override
    @Transactional(readOnly = true)
    public ExamModel getExamByID(Integer ID) throws UserTypeNotSupported, ExamNotFoundException {
        try {
            Exam dao = repository.getOne(ID);
            return ExamDaoToDto.convert(dao);
        } catch (EntityNotFoundException e) {
            throw new ExamNotFoundException();
        }
    }

    @Override
    @Transactional
    public ExamModel saveExam(ExamModel model) throws UserTypeNotSupported {
        // TODO check if user is a professor, otherwise throw a new exception
        Exam exam = ExamDtoToDao.convert(model);
        Exam saved = repository.save(exam);
        return ExamDaoToDto.convert(saved);
    }


    @Override
    public List<ExamModel> filterByTimeAndCourseOfStudy(ExamFilterModel filter) throws UserTypeNotSupported {
        List<Exam> daos = repository.findByTimeAndCourseOfStudy(
                filter.getStartTime().getStartTime(),
                filter.getEndTime().getEndTime(),
                filter.getCourseOfStudy().getID());

        List<ExamModel> models = new ArrayList<>();
        for (Exam dao : daos) {
            models.add(ExamDaoToDto.convert(dao));
        }
        return models;
    }



    @Override
    public List<ExamModel> filterByTimeAndProfessor(ExamFilterModel filter) throws UserTypeNotSupported {
        List<Exam> daos = repository.findByTimeAndProfessor(
                filter.getStartTime().getStartTime(),
                filter.getEndTime().getEndTime(),
                filter.getProfessor().getId());

        List<ExamModel> models = new ArrayList<>();
        for (Exam dao : daos) {
            models.add(ExamDaoToDto.convert(dao));
        }
        return models;
    }




}
