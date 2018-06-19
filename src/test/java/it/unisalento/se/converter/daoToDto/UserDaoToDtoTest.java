package it.unisalento.se.converter.daoToDto;

import it.unisalento.se.dao.User;
import it.unisalento.se.dao.UserType;
import it.unisalento.se.dto.UserModel;
import it.unisalento.se.dto.UserTypeModel;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserDaoToDtoTest {

    @Test
    public void convert() throws UserTypeNotSupported {
        UserType t = new UserType();
        t.setId(2);
        t.setName("PROFESSOR");

        User u = new User();
        u.setId(1);
        u.setName("Mario");
        u.setSurname("Rossi");
        u.setEmail("mario.rossi@test.it");
        u.setUserType(t);

        UserModel m = UserDaoToDto.convert(u);

        assertEquals("Mario", m.getName());
        assertEquals("Rossi", m.getSurname());
        assertEquals("mario.rossi@test.it", m.getEmail());
        assertEquals(new Integer(1), m.getId());
        assertEquals(UserTypeModel.PROFESSOR, m.getUserType());

    }
}