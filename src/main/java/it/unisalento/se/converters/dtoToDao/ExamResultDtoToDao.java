package it.unisalento.se.converters.dtoToDao;

import it.unisalento.se.dao.ExamResults;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.ExamResultModel;

public class ExamResultDtoToDao {
    public static ExamResults convert(ExamResultModel model) throws UserTypeNotSupported {

        ExamResults dao = new ExamResults();
        dao.setId(model.getID());
        dao.setVote(model.getVote());
        dao.setUser(UserDtoToDao.convert(model.getStudent()));
        dao.setDate(model.getDate());
        dao.setExam(ExamDtoToDao.convert(model.getExam()));


        return dao;
    }

}
