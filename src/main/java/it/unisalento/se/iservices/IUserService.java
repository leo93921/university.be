package it.unisalento.se.iservices;

import it.unisalento.se.exceptions.InvalidCredentialsException;
import it.unisalento.se.exceptions.UserAlreadyRegisteredException;
import it.unisalento.se.exceptions.UserNotFoundException;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.*;

import java.util.List;

public interface IUserService {

    UserModel createUser(UserModel user) throws UserTypeNotSupported;

    UserModel getUserByID(Integer ID) throws UserTypeNotSupported, UserNotFoundException;

    UserModel checkCredentials(UserCredentials credentials) throws InvalidCredentialsException, UserTypeNotSupported;

    List<UserModel> getAllProfessors() throws UserTypeNotSupported;

    UserModel register(RegistrationRequest request) throws UserTypeNotSupported, UserAlreadyRegisteredException;

    FCMTokenRegistration registerFCMToken(FCMTokenRegistration request);

    List<UserModel> getStudentsByCourseOfStudy(CourseOfStudyModel courseOfStudy) throws UserTypeNotSupported;

    String getFCMToken(UserModel user);

    boolean deleteFcmToken(UserModel user) throws UserTypeNotSupported;
}