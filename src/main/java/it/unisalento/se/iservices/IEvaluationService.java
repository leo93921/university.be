package it.unisalento.se.iservices;

import it.unisalento.se.dto.DocumentDto;
import it.unisalento.se.dto.EvaluationDto;
import it.unisalento.se.exceptions.*;
import it.unisalento.se.models.EvaluationFilterModel;
import it.unisalento.se.models.EvaluationModel;
import it.unisalento.se.models.LessonModel;

import java.util.List;

public interface IEvaluationService {

    EvaluationModel createEvaluation(EvaluationDto evaluation) throws EvaluationRecipientNotSupported, UserTypeNotSupported, ScoreNotValidException, NodeNotSupportedException;

    EvaluationModel getLessonEvaluationbyID(Integer ID) throws EvaluationNotFoundException, UserTypeNotSupported, EvaluationRecipientNotSupported, ScoreNotValidException;

    EvaluationModel getDocumentEvaluationbyID(Integer ID) throws EvaluationNotFoundException, UserTypeNotSupported, EvaluationRecipientNotSupported, ScoreNotValidException;


    List<EvaluationModel> getEvaluationsByLesson(LessonModel lesson) throws UserTypeNotSupported, EvaluationRecipientNotSupported, ScoreNotValidException;

    List<EvaluationModel> getEvaluationsByDocument(DocumentDto document) throws UserTypeNotSupported, EvaluationRecipientNotSupported, ScoreNotValidException, NodeNotSupportedException;

    boolean checkEvaluation(EvaluationFilterModel filter) throws EvaluationRecipientNotSupported;


}

