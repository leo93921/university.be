package it.unisalento.se.iservices;

import it.unisalento.se.exceptions.EvaluationNotFoundException;
import it.unisalento.se.exceptions.EvaluationRecipientNotSupported;
import it.unisalento.se.exceptions.ScoreNotValidException;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.DocumentModel;
import it.unisalento.se.models.EvaluationModel;
import it.unisalento.se.models.LessonModel;

import java.util.List;

public interface IEvaluationService {

    EvaluationModel createEvaluation(EvaluationModel evaluation) throws EvaluationRecipientNotSupported, UserTypeNotSupported, ScoreNotValidException;

    EvaluationModel getLessonEvaluationbyID(Integer ID) throws EvaluationNotFoundException, UserTypeNotSupported, EvaluationRecipientNotSupported, ScoreNotValidException;

    EvaluationModel getDocumentEvaluationbyID(Integer ID) throws EvaluationNotFoundException, UserTypeNotSupported, EvaluationRecipientNotSupported, ScoreNotValidException;


    List<EvaluationModel> getEvaluationsByLesson(LessonModel lesson) throws UserTypeNotSupported, EvaluationRecipientNotSupported, ScoreNotValidException;

    List<EvaluationModel> getEvaluationsByDocument(DocumentModel document) throws UserTypeNotSupported, EvaluationRecipientNotSupported, ScoreNotValidException;
}

