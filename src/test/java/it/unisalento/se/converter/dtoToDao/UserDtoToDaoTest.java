package it.unisalento.se.converter.dtoToDao;

import it.unisalento.se.dao.User;
import it.unisalento.se.dao.UserType;
import it.unisalento.se.dto.UserModel;
import it.unisalento.se.dto.UserTypeModel;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDtoToDaoTest {

    @Test
    public void convert() {
        UserModel model = new UserModel();
        model.setId(1);
        model.setEmail("mario.rossi@test.it");
        model.setName("Mario");
        model.setSurname("Rossi");
        model.setUserType(UserTypeModel.SECRETARIAT);
        UserType type = new UserType();
        type.setName("SECRETARIAT");
        type.setId(3);

        User dao = UserDtoToDao.convert(model, type);

        Assert.assertEquals(new Integer(1), dao.getId());
        Assert.assertEquals("Mario", dao.getName());
        Assert.assertEquals("Rossi", dao.getSurname());
        Assert.assertEquals("mario.rossi@test.it", dao.getEmail());
        Assert.assertEquals(new Integer(3), dao.getUserType().getId());
        Assert.assertEquals("SECRETARIAT", dao.getUserType().getName());

    }
}