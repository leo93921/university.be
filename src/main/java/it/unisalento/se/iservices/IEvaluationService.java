package it.unisalento.se.iservices;

import it.unisalento.se.exceptions.EvaluationNotFoundException;
import it.unisalento.se.exceptions.EvaluationRecipientNotSupported;
import it.unisalento.se.exceptions.ScoreNotValidException;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.EvaluationModel;

public interface IEvaluationService {

    EvaluationModel createEvaluation(EvaluationModel evaluation) throws EvaluationRecipientNotSupported, UserTypeNotSupported, ScoreNotValidException;

    EvaluationModel getLessonEvaluationbyID(Integer ID) throws EvaluationNotFoundException, UserTypeNotSupported, EvaluationRecipientNotSupported, ScoreNotValidException;

    EvaluationModel getDocumentEvaluationbyID(Integer ID) throws EvaluationNotFoundException, UserTypeNotSupported, EvaluationRecipientNotSupported, ScoreNotValidException;

}

