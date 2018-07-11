package it.unisalento.se.converters.daoToDto;

import it.unisalento.se.dao.LessonEvaluation;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.EvaluationModel;


public class LessonEvaluationDaoToDto {
    public static EvaluationModel convert(LessonEvaluation le) throws UserTypeNotSupported {
        EvaluationModel lessEval = new EvaluationModel();
        lessEval.setId(le.getId());
        lessEval.setScore(le.getScore());
        lessEval.setNote(le.getNote());
        lessEval.setSender(UserDaoToDto.convert(le.getUser()));
        lessEval.setRecipientL(LessonDaoToDto.convert(le.getLesson()));
        return lessEval;
    }

}