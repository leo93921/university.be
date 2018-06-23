package it.unisalento.se.converters.daoToDto;

import it.unisalento.se.dao.User;
import it.unisalento.se.models.UserModel;
import it.unisalento.se.exceptions.UserTypeNotSupported;

public class UserDaoToDto {

    public static UserModel convert(User u) throws UserTypeNotSupported {
        UserModel user = new UserModel();
        user.setId(u.getId());
        user.setEmail(u.getEmail());
        user.setName(u.getName());
        user.setSurname(u.getSurname());
        user.setUserType(UserTypeDaoToDto.convert(u.getUserType()));
        return user;
    }

}
