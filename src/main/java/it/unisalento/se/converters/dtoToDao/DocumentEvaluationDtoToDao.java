package it.unisalento.se.converters.dtoToDao;

import it.unisalento.se.dao.DocumentEvaluation;
import it.unisalento.se.exceptions.NodeNotSupportedException;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.EvaluationModel;

public class DocumentEvaluationDtoToDao {


    public static DocumentEvaluation convert(EvaluationModel model) throws UserTypeNotSupported, NodeNotSupportedException {

        DocumentEvaluation dao = new DocumentEvaluation();
        dao.setId(model.getId());
        dao.setScore(model.getScore());
        dao.setNote(model.getNote());
        dao.setUser(UserDtoToDao.convert(model.getSender()));
        dao.setDocument(DocumentDtoToDao.convert(model.getRecipientD()));
        return dao;
    }

}
