package it.unisalento.se.iservices;

import it.unisalento.se.exceptions.*;
import it.unisalento.se.models.DocumentModel;
import it.unisalento.se.models.EvaluationFilterModel;
import it.unisalento.se.models.EvaluationModel;
import it.unisalento.se.models.LessonModel;

import java.util.List;

public interface IEvaluationService {

    EvaluationModel createEvaluation(EvaluationModel evaluation) throws EvaluationRecipientNotSupported, UserTypeNotSupported, ScoreNotValidException, NodeNotSupportedException;

    EvaluationModel getLessonEvaluationbyID(Integer ID) throws EvaluationNotFoundException, UserTypeNotSupported, EvaluationRecipientNotSupported, ScoreNotValidException;

    EvaluationModel getDocumentEvaluationbyID(Integer ID) throws EvaluationNotFoundException, UserTypeNotSupported, EvaluationRecipientNotSupported, ScoreNotValidException;


    List<EvaluationModel> getEvaluationsByLesson(LessonModel lesson) throws UserTypeNotSupported, EvaluationRecipientNotSupported, ScoreNotValidException;

    List<EvaluationModel> getEvaluationsByDocument(DocumentModel document) throws UserTypeNotSupported, EvaluationRecipientNotSupported, ScoreNotValidException;

    boolean checkEvaluation(EvaluationFilterModel filter) throws EvaluationRecipientNotSupported;


}

