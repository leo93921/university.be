package it.unisalento.se.services;

import it.unisalento.se.common.Constants;
import it.unisalento.se.converters.daoToDto.UserDaoToDto;
import it.unisalento.se.converters.dtoToDao.UserDtoToDao;
import it.unisalento.se.dao.User;
import it.unisalento.se.exceptions.InvalidCredentialsException;
import it.unisalento.se.exceptions.UserNotFoundException;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.iservices.IUserService;
import it.unisalento.se.iservices.IUserTypeService;
import it.unisalento.se.models.RegistrationRequest;
import it.unisalento.se.models.UserCredentials;
import it.unisalento.se.models.UserModel;
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
    public UserModel register(RegistrationRequest request) throws UserTypeNotSupported {
        // TODO check if email is already present
        User dao = UserDtoToDao.convert(request);
        dao.setPassword(request.getPassword());
        User saved = userRepository.save(dao);
        return UserDaoToDto.convert(saved);
    }
}
