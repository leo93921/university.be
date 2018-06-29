package it.unisalento.se.converters.daoToDto;

import it.unisalento.se.common.Constants;
import it.unisalento.se.dao.UserType;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.UserTypeModel;

public class UserTypeDaoToDto {

    public static UserTypeModel convert(UserType type) throws UserTypeNotSupported {
        if (type.getName().equalsIgnoreCase(Constants.STUDENT))
            return UserTypeModel.STUDENT;
        else if (type.getName().equalsIgnoreCase(Constants.PROFESSOR))
            return UserTypeModel.PROFESSOR;
        else if (type.getName().equalsIgnoreCase(Constants.SECRETARIAT))
            return UserTypeModel.SECRETARIAT;
        else
            throw new UserTypeNotSupported("User type cannot be " + type.getName());

    }

}
