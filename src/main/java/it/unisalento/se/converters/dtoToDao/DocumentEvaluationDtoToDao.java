package it.unisalento.se.converters.dtoToDao;

import it.unisalento.se.dao.Document;
import it.unisalento.se.dao.DocumentEvaluation;
import it.unisalento.se.dao.User;
import it.unisalento.se.dto.EvaluationDto;
import it.unisalento.se.exceptions.NodeNotSupportedException;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.EvaluationModel;

public class DocumentEvaluationDtoToDao {


    public static DocumentEvaluation convert(EvaluationModel model) throws UserTypeNotSupported, NodeNotSupportedException {
        return convert(
                model.getId(),
                model.getScore(),
                model.getNote(),
                UserDtoToDao.convert(model.getSender()),
                DocumentDtoToDao.convert(model.getRecipientD()));
    }

    public static DocumentEvaluation convert(EvaluationDto model) throws UserTypeNotSupported {
        return convert(
                model.getId(),
                model.getScore(),
                model.getNote(),
                UserDtoToDao.convert(model.getSender()),
                DocumentDtoToDao.convert(model.getRecipientD()));
    }

    private static DocumentEvaluation convert(Integer id, Integer score, String note, User sender, Document document) {
        DocumentEvaluation dao = new DocumentEvaluation();
        dao.setId(id);
        dao.setScore(score);
        dao.setNote(note);
        dao.setUser(sender);
        dao.setDocument(document);
        return dao;
    }

}
