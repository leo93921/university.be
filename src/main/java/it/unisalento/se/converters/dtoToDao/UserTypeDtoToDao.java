package it.unisalento.se.converters.dtoToDao;

import it.unisalento.se.common.Constants;
import it.unisalento.se.dao.UserType;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.UserTypeModel;

public class UserTypeDtoToDao {

    public static UserType convert(UserTypeModel model) throws UserTypeNotSupported {
        UserType type = new UserType();

        if (model.equals(UserTypeModel.STUDENT)) {
            type.setId(1);
            type.setName(Constants.STUDENT);
        } else if (model.equals(UserTypeModel.PROFESSOR)) {
            type.setId(2);
            type.setName(Constants.PROFESSOR);
        } else if (model.equals(UserTypeModel.SECRETARIAT)) {
            type.setId(3);
            type.setName(Constants.SECRETARIAT);
        } else
            throw new UserTypeNotSupported("User type cannot be " + type.getName());
        return type;
    }

}
