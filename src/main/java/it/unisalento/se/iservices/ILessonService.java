package it.unisalento.se.iservices;

import it.unisalento.se.exceptions.LessonNotFoundException;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.LessonFilterModel;
import it.unisalento.se.models.LessonModel;
import it.unisalento.se.models.SubjectModel;

import java.util.List;

public interface ILessonService {

    LessonModel getLessonByID(Integer ID) throws UserTypeNotSupported, LessonNotFoundException;

    LessonModel saveLesson(LessonModel model) throws UserTypeNotSupported;

    List<LessonModel> filterByTimeAndSubject(LessonFilterModel filter) throws UserTypeNotSupported;

    List<LessonModel> getLessonsBySubjects(SubjectModel subject) throws UserTypeNotSupported;
}
