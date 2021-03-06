package it.unisalento.se.converters.daoToDto;

import it.unisalento.se.common.Constants;
import it.unisalento.se.dao.UserType;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.UserTypeModel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTypeDaoToDtoTest {

    @Test
    public void convertWithValidType() throws UserTypeNotSupported {
        UserType profType = new UserType();
        profType.setName(Constants.PROFESSOR);

        UserType studentType = new UserType();
        studentType.setName(Constants.STUDENT);

        UserType secretariatType = new UserType();
        secretariatType.setName(Constants.SECRETARIAT);

        UserTypeModel profTypeModel = UserTypeDaoToDto.convert(profType);
        UserTypeModel studentTypeModel = UserTypeDaoToDto.convert(studentType);
        UserTypeModel secretariatTypeModel = UserTypeDaoToDto.convert(secretariatType);

        assertEquals(UserTypeModel.PROFESSOR, profTypeModel);
        assertEquals(UserTypeModel.STUDENT, studentTypeModel);
        assertEquals(UserTypeModel.SECRETARIAT, secretariatTypeModel);
    }

    @Test(expected = UserTypeNotSupported.class)
    public void convertWithInvalidType() throws UserTypeNotSupported {
        UserType type = new UserType();
        type.setName("Invalid");

        UserTypeModel m = UserTypeDaoToDto.convert(type);
    }
}