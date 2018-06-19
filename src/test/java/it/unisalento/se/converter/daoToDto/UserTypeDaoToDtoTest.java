package it.unisalento.se.converter.daoToDto;

import it.unisalento.se.dao.UserType;
import it.unisalento.se.dto.UserTypeModel;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTypeDaoToDtoTest {

    @Test
    public void convertWithValidType() throws UserTypeNotSupported {
        UserType profType = new UserType();
        profType.setName("PROFESSOR");

        UserType studentType = new UserType();
        studentType.setName("STUDENT");

        UserType secretariatType = new UserType();
        secretariatType.setName("SECRETARIAT");

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