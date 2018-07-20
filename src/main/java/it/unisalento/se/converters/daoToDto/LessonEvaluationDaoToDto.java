package it.unisalento.se.converters.daoToDto;

import it.unisalento.se.common.EvaluationBuilder;
import it.unisalento.se.dao.LessonEvaluation;
import it.unisalento.se.exceptions.EvaluationRecipientNotSupported;
import it.unisalento.se.exceptions.ScoreNotValidException;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.EvaluationModel;

public class LessonEvaluationDaoToDto {
    public static EvaluationModel convert(LessonEvaluation le) throws UserTypeNotSupported, ScoreNotValidException, EvaluationRecipientNotSupported {

        EvaluationModel model = EvaluationBuilder.getInstance()
                .setID(le.getId())
                .setNote(le.getNote())
                .setScore(le.getScore())
                .setSender(UserDaoToDto.convert(le.getUser()))
                .setTarget(LessonDaoToDto.convert(le.getLesson()))
                .build();
        return model;

    }

}