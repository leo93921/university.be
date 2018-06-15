package it.unisalento.se.service;

import it.unisalento.se.dao.UserType;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.iservice.IUserTypeService;
import it.unisalento.se.repositories.UserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class UserTypeService implements IUserTypeService {

    @Autowired
    private UserTypeRepository userTypeRepository;

    @Override
    public UserType getUserTypeDaoByName(String name) throws UserTypeNotSupported {
        try {
            return userTypeRepository.getUserTypeByName(name);
        } catch (EntityNotFoundException e) {
            throw new UserTypeNotSupported("User type cannot be " + name);
        }
    }

}
