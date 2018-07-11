package it.unisalento.se.converters.daoToDto;

import it.unisalento.se.dao.DocumentEvaluation;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.EvaluationModel;

public class DocumentEvaluationDaoToDto {


    public static EvaluationModel convert(DocumentEvaluation de) throws UserTypeNotSupported {
        EvaluationModel docEval = new EvaluationModel();
        docEval.setId(de.getId());
        docEval.setScore(de.getScore());
        docEval.setNote(de.getNote());
        docEval.setSender(UserDaoToDto.convert(de.getUser()));
        docEval.setRecipientD(DocumentDaoToDto.convert(de.getDocument()));
        return docEval;
    }
}
