package it.unisalento.se.converter.dtoToDao;

import it.unisalento.se.dao.User;
import it.unisalento.se.dto.UserModel;
import it.unisalento.se.exceptions.UserTypeNotSupported;

public class UserDtoToDao {

    public static User convert(UserModel model) throws UserTypeNotSupported {
        User dao = new User();
        dao.setEmail(model.getEmail());
        dao.setId(model.getId());
        dao.setName(model.getName());
        dao.setSurname(model.getSurname());
        dao.setUserType((new UserTypeDtoToDao()).convert(model.getUserType()));

        return dao;
    }

}
