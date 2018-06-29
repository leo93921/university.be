package it.unisalento.se.iservices;

import it.unisalento.se.exceptions.ExamResultNotFoundException;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.ExamResultModel;

public interface IExamResultService {
    ExamResultModel getExamResultByID(Integer ID) throws ExamResultNotFoundException, UserTypeNotSupported;

    ExamResultModel saveExamResult(ExamResultModel model) throws UserTypeNotSupported;
}

