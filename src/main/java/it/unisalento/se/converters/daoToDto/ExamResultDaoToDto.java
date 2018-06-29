package it.unisalento.se.converters.daoToDto;

import it.unisalento.se.dao.ExamResults;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.ExamResultModel;

public class ExamResultDaoToDto {

    public static ExamResultModel convert(ExamResults dao) throws UserTypeNotSupported {
        ExamResultModel model = new ExamResultModel();
        model.setID(dao.getId());
        model.setVote(dao.getVote());
        model.setStudent(UserDaoToDto.convert(dao.getUser()));
        model.setDate(dao.getDate());
        model.setExam(ExamDaoToDto.convert(dao.getExam()));
        return model;
    }
}
