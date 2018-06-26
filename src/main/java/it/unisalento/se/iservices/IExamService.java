package it.unisalento.se.iservices;

import it.unisalento.se.exceptions.ExamNotFoundException;
import it.unisalento.se.exceptions.SubjectNotFoundException;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.ExamModel;
import it.unisalento.se.models.SubjectModel;

public interface IExamService {
    ExamModel getExamByID(Integer ID) throws UserTypeNotSupported, ExamNotFoundException;

    ExamModel saveExam(ExamModel model) throws UserTypeNotSupported;
}
