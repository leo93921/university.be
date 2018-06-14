package it.unisalento.se.service;

import it.unisalento.se.converter.daoToDto.UserDaoToDto;
import it.unisalento.se.dao.User;
import it.unisalento.se.dto.UserModel;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.iservice.IUserService;
import it.unisalento.se.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserModel createUser(UserModel user) {

        return null;
    }

    @Override
    public UserModel getUserByID(Integer ID) throws UserTypeNotSupported {
        User user = userRepository.getOne(ID);
        return UserDaoToDto.convert(user);
    }
}
