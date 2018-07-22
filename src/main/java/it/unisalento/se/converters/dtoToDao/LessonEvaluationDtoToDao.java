package it.unisalento.se.converters.dtoToDao;

import it.unisalento.se.dao.LessonEvaluation;
import it.unisalento.se.dto.EvaluationDto;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.EvaluationModel;
import it.unisalento.se.models.LessonModel;
import it.unisalento.se.models.UserModel;


public class LessonEvaluationDtoToDao {
    public static LessonEvaluation convert(EvaluationModel model) throws UserTypeNotSupported {
        return convert(model.getID(), model.getScore(), model.getNote(), model.getSender(), (LessonModel) model.getRecipient());
    }


    public static LessonEvaluation convert(EvaluationDto dto) throws UserTypeNotSupported {
        return convert(dto.getId(), dto.getScore(), dto.getNote(), dto.getSender(), dto.getRecipientL());
    }

    private static LessonEvaluation convert(Integer id, Integer score, String note, UserModel sender, LessonModel recipientL) throws UserTypeNotSupported {
        LessonEvaluation dao = new LessonEvaluation();
        dao.setId(id);
        dao.setScore(score);
        dao.setNote(note);
        dao.setUser(UserDtoToDao.convert(sender));
        dao.setLesson(LessonDtoToDao.convert(recipientL));
        return dao;
    }
}


