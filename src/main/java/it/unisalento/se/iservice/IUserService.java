package it.unisalento.se.iservice;

import it.unisalento.se.dto.UserModel;
import it.unisalento.se.exceptions.UserNotFoundException;
import it.unisalento.se.exceptions.UserTypeNotSupported;

public interface IUserService {

    UserModel createUser(UserModel user) throws UserTypeNotSupported;
    UserModel getUserByID(Integer ID) throws UserTypeNotSupported, UserNotFoundException;

}
