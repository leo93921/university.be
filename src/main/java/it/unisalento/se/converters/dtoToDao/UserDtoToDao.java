package it.unisalento.se.converters.dtoToDao;

import it.unisalento.se.dao.User;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.UserModel;

public class UserDtoToDao {

    public static User convert(UserModel model) throws UserTypeNotSupported {
        User dao = new User();
        dao.setEmail(model.getEmail());
        dao.setId(model.getId());
        dao.setName(model.getName());
        dao.setSurname(model.getSurname());
        dao.setUserType(UserTypeDtoToDao.convert(model.getUserType()));

        return dao;
    }

}
