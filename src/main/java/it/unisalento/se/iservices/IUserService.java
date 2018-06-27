package it.unisalento.se.iservices;

import it.unisalento.se.exceptions.InvalidCredentialsException;
import it.unisalento.se.exceptions.UserNotFoundException;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.UserCredentials;
import it.unisalento.se.models.UserModel;

public interface IUserService {

    UserModel createUser(UserModel user) throws UserTypeNotSupported;
    UserModel getUserByID(Integer ID) throws UserTypeNotSupported, UserNotFoundException;

    UserModel checkCredentials(UserCredentials credentials) throws InvalidCredentialsException, UserTypeNotSupported;
}