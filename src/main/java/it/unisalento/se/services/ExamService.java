package it.unisalento.se.services;


import it.unisalento.se.converters.daoToDto.ExamDaoToDto;
import it.unisalento.se.converters.daoToDto.SubjectDaoToDto;
import it.unisalento.se.converters.dtoToDao.ExamDtoToDao;
import it.unisalento.se.converters.dtoToDao.SubjectDtoToDao;
import it.unisalento.se.dao.Exam;
import it.unisalento.se.dao.Subject;
import it.unisalento.se.exceptions.ExamNotFoundException;
import it.unisalento.se.exceptions.SubjectNotFoundException;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.iservices.IExamService;
import it.unisalento.se.iservices.ISubjectService;
import it.unisalento.se.models.ExamModel;
import it.unisalento.se.models.SubjectModel;
import it.unisalento.se.repositories.ExamRepository;
import it.unisalento.se.repositories.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import javax.persistence.EntityNotFoundException;

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


}
