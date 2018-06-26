package it.unisalento.se.iservices;

import it.unisalento.se.exceptions.LessonNotFoundException;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.LessonModel;

public interface ILessonService {

    LessonModel getLessonByID(Integer ID) throws UserTypeNotSupported, LessonNotFoundException;
    LessonModel saveLesson(LessonModel model) throws UserTypeNotSupported;
}