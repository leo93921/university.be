package it.unisalento.se.services;

import it.unisalento.se.common.Constants;
import it.unisalento.se.converters.daoToDto.UserDaoToDto;
import it.unisalento.se.converters.dtoToDao.CourseOfStudyDtoToDao;
import it.unisalento.se.converters.dtoToDao.UserDtoToDao;
import it.unisalento.se.dao.User;
import it.unisalento.se.exceptions.InvalidCredentialsException;
import it.unisalento.se.exceptions.UserAlreadyRegisteredException;
import it.unisalento.se.exceptions.UserNotFoundException;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.iservices.IUserService;
import it.unisalento.se.iservices.IUserTypeService;
import it.unisalento.se.models.*;
import it.unisalento.se.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IUserTypeService userTypeService;


    @Override
    @Transactional
    public UserModel createUser(UserModel user) throws UserTypeNotSupported {
        //UserType type = userTypeService.getUserTypeDaoByName(user.getUserType().name());
        User dao = UserDtoToDao.convert(user);
        dao = userRepository.save(dao);
        return UserDaoToDto.convert(dao);
    }

    @Override
    @Transactional(readOnly = true)
    public UserModel getUserByID(Integer ID) throws UserTypeNotSupported, UserNotFoundException {
        try {
            User user = userRepository.getOne(ID);
            return UserDaoToDto.convert(user);
        } catch (EntityNotFoundException e) {
            throw new UserNotFoundException();
        }
    }

    @Override
    public UserModel checkCredentials(UserCredentials credentials) throws InvalidCredentialsException, UserTypeNotSupported {
        User user = userRepository.findByCredentials(credentials.getUsername(), credentials.getPassword());
        if (user == null)
            throw new InvalidCredentialsException();
        return UserDaoToDto.convert(user);
    }

    @Override
    public List<UserModel> getAllProfessors() throws UserTypeNotSupported {
        List<User> daos = userRepository.findByUserType(Constants.PROFESSOR_VALUE);
        List<UserModel> models = new ArrayList<>();
        for (User user : daos) {
            models.add(UserDaoToDto.convert(user));
        }
        return models;
    }

    @Override
    public UserModel register(RegistrationRequest request) throws UserTypeNotSupported, UserAlreadyRegisteredException {
        List<User> daos = userRepository.findByEmail(request.getEmail());
        if (daos.size() != 0) {
            throw new UserAlreadyRegisteredException();
        }
        User dao = UserDtoToDao.convert(request);
        dao.setPassword(request.getPassword());
        User saved = userRepository.save(dao);
        return UserDaoToDto.convert(saved);
    }

    @Override
    public FCMTokenRegistration registerFCMToken(FCMTokenRegistration request) {
        User dao = userRepository.getOne(request.getModel().getId());
        dao.setFcmToken(request.getToken());
        userRepository.save(dao);
        return request;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserModel> getStudentsByCourseOfStudy(CourseOfStudyModel courseOfStudy) throws UserTypeNotSupported {
        List<User> users = userRepository.findByCourseOfStudy(
                CourseOfStudyDtoToDao.convert(courseOfStudy));
        List<UserModel> models = new ArrayList<>();
        for (User user : users) {
            models.add(UserDaoToDto.convert(user));
        }
        return models;
    }

    @Override
    public String getFCMToken(UserModel model) {
        User user = userRepository.getOne(model.getId());
        if (user.getFcmToken() != null) {
            return user.getFcmToken();
        } else {
            return "";
        }
    }

    @Override
    @Transactional
    public boolean deleteFcmToken(UserModel user) throws UserTypeNotSupported {
        User dao = UserDtoToDao.convert(user);
        userRepository.deleteFcmToken(dao.getId());
        return true;
    }
}
