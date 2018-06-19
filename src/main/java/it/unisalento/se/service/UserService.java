package it.unisalento.se.service;

import it.unisalento.se.converter.daoToDto.UserDaoToDto;
import it.unisalento.se.converter.dtoToDao.UserDtoToDao;
import it.unisalento.se.dao.User;
import it.unisalento.se.dao.UserType;
import it.unisalento.se.dto.UserModel;
import it.unisalento.se.exceptions.UserNotFoundException;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.iservice.IUserService;
import it.unisalento.se.iservice.IUserTypeService;
import it.unisalento.se.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;


@Service
public class UserService implements IUserService {

    @Autowired private UserRepository userRepository;
    @Autowired private IUserTypeService userTypeService;


    @Override
    public UserModel createUser(UserModel user) throws UserTypeNotSupported {
        UserType type = userTypeService.getUserTypeDaoByName(user.getUserType().name());
        User dao = UserDtoToDao.convert(user, type);
        dao = userRepository.save(dao);
        return UserDaoToDto.convert(dao);
    }

    @Override @Transactional(readOnly=true)
    public UserModel getUserByID(Integer ID) throws UserTypeNotSupported, UserNotFoundException {
        try {
            User user = userRepository.getOne(ID);
            return UserDaoToDto.convert(user);
        } catch (EntityNotFoundException e) {
            throw new UserNotFoundException();
        }

    }
}
