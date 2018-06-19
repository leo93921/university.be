package it.unisalento.se.iservice;

import it.unisalento.se.dao.UserType;
import it.unisalento.se.exceptions.UserTypeNotSupported;

public interface IUserTypeService {

    UserType getUserTypeDaoByName(String name) throws UserTypeNotSupported;
}
