package it.unisalento.se.services;


import it.unisalento.se.converters.daoToDto.ExamResultDaoToDto;
import it.unisalento.se.converters.dtoToDao.ExamResultDtoToDao;
import it.unisalento.se.dao.ExamResults;
import it.unisalento.se.exceptions.ExamResultNotFoundException;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.iservices.IExamResultService;
import it.unisalento.se.models.ExamResultModel;
import it.unisalento.se.models.UserTypeModel;
import it.unisalento.se.repositories.ExamResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class ExamResultService implements IExamResultService {

    @Autowired
    private ExamResultRepository repository;

    @Override
    @Transactional(readOnly = true)
    public ExamResultModel getExamResultByID(Integer ID) throws UserTypeNotSupported, ExamResultNotFoundException {
        try {
            ExamResults dao = repository.getOne(ID);
            return ExamResultDaoToDto.convert(dao);
        } catch (EntityNotFoundException e) {
            throw new ExamResultNotFoundException();
        }
    }

    @Override
    @Transactional
    public ExamResultModel saveExamResult(ExamResultModel model) throws UserTypeNotSupported {
        if (!model.getStudent().getUserType().equals(UserTypeModel.STUDENT)) {
            throw new UserTypeNotSupported("Only students can do exams.");
        }
        ExamResults examResult = ExamResultDtoToDao.convert(model);
        ExamResults saved = repository.save(examResult);
        return ExamResultDaoToDto.convert(saved);
    }


}
