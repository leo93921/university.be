package it.unisalento.se.converters.daoToDto;

import it.unisalento.se.common.EvaluationBuilder;
import it.unisalento.se.dao.DocumentEvaluation;
import it.unisalento.se.exceptions.EvaluationRecipientNotSupported;
import it.unisalento.se.exceptions.ScoreNotValidException;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.EvaluationModel;

public class DocumentEvaluationDaoToDto {


    public static EvaluationModel convert(DocumentEvaluation de)
            throws UserTypeNotSupported, ScoreNotValidException, EvaluationRecipientNotSupported {

        EvaluationBuilder builder = new EvaluationBuilder();
        EvaluationModel docEval = builder
                .setID(de.getId())
                .setScore(de.getScore())
                .setNote(de.getNote())
                .setSender(UserDaoToDto.convert(de.getUser()))
                .setTarget(DocumentDaoToDto.convert(de.getDocument()))
                .build();

        return docEval;
    }
}
