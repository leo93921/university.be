package it.unisalento.se.converter.dtoToDao;

import it.unisalento.se.dao.User;
import it.unisalento.se.dao.UserType;
import it.unisalento.se.dto.UserModel;

public class UserDtoToDao {

    public static User convert(UserModel model, UserType type) {
        User dao = new User();
        dao.setEmail(model.getEmail());
        dao.setId(model.getId());
        dao.setName(model.getName());
        dao.setSurname(model.getSurname());
        dao.setUserType(type);

        return dao;
    }

}
