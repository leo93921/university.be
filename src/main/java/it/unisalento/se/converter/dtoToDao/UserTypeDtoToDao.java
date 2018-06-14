package it.unisalento.se.converter.dtoToDao;

import it.unisalento.se.dao.UserType;
import it.unisalento.se.dto.UserTypeModel;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.repositories.UserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserTypeDtoToDao {

    @Autowired private UserTypeRepository userTypeRepository;

    public UserType convert(UserTypeModel model) throws UserTypeNotSupported {
        UserType type = userTypeRepository.getUserTypeByName(model.name());
        if (type == null)
            throw new UserTypeNotSupported("User type cannot be " + type.getName());
        else
            return type;
    }
}
