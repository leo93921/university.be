package it.unisalento.se.converters.dtoToDao;

import it.unisalento.se.dao.LessonEvaluation;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.EvaluationModel;


public class LessonEvaluationDtoToDao {
    public static LessonEvaluation convert(EvaluationModel model) throws UserTypeNotSupported {

        LessonEvaluation dao = new LessonEvaluation();
        dao.setId(model.getId());
        dao.setScore(model.getScore());
        dao.setNote(model.getNote());
        dao.setUser(UserDtoToDao.convert(model.getSender()));
        dao.setLesson(LessonDtoToDao.convert(model.getRecipientL()));
        return dao;

    }


}


