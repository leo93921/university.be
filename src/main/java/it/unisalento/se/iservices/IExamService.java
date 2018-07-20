package it.unisalento.se.iservices;

import it.unisalento.se.exceptions.ExamNotFoundException;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.ExamFilterModel;
import it.unisalento.se.models.ExamModel;

import java.util.List;

public interface IExamService {
    ExamModel getExamByID(Integer ID) throws UserTypeNotSupported, ExamNotFoundException;

    ExamModel saveExam(ExamModel model) throws UserTypeNotSupported;


    List<ExamModel> filterByTimeAndCourseOfStudy(ExamFilterModel filter) throws UserTypeNotSupported;

    List<ExamModel> filterByTimeAndProfessor(ExamFilterModel filter) throws UserTypeNotSupported;
}
