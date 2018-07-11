package it.unisalento.se.iservices;

import it.unisalento.se.exceptions.EvaluationNotFoundException;
import it.unisalento.se.exceptions.EvaluationRecipientNotSupported;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.EvaluationModel;

public interface IEvaluationService {

    EvaluationModel createEvaluation(EvaluationModel evaluation) throws EvaluationRecipientNotSupported, UserTypeNotSupported;

    EvaluationModel getLessonEvaluationbyID(Integer ID) throws EvaluationNotFoundException, UserTypeNotSupported;

    EvaluationModel getDocumentEvaluationbyID(Integer ID) throws EvaluationNotFoundException, UserTypeNotSupported;

}

