package it.unisalento.se.iservices;

import it.unisalento.se.models.UserModel;
import it.unisalento.se.exceptions.UserNotFoundException;
import it.unisalento.se.exceptions.UserTypeNotSupported;

public interface IUserService {

    UserModel createUser(UserModel user) throws UserTypeNotSupported;
    UserModel getUserByID(Integer ID) throws UserTypeNotSupported, UserNotFoundException;
}