package it.unisalento.se.converters.dtoToDao;

import it.unisalento.se.common.Constants;
import it.unisalento.se.dao.User;
import it.unisalento.se.dao.UserType;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.UserModel;
import it.unisalento.se.models.UserTypeModel;
import org.junit.Assert;
import org.junit.Test;

public class UserDtoToDaoTest {

    @Test
    public void convert() throws UserTypeNotSupported {
        UserModel model = new UserModel();
        model.setId(1);
        model.setEmail("mario.rossi@test.it");
        model.setName("Mario");
        model.setSurname("Rossi");
        model.setUserType(UserTypeModel.SECRETARIAT);
        UserType type = new UserType();
        type.setName(Constants.SECRETARIAT);
        type.setId(3);

        User dao = UserDtoToDao.convert(model);

        Assert.assertEquals(new Integer(1), dao.getId());
        Assert.assertEquals("Mario", dao.getName());
        Assert.assertEquals("Rossi", dao.getSurname());
        Assert.assertEquals("mario.rossi@test.it", dao.getEmail());
        Assert.assertEquals(new Integer(3), dao.getUserType().getId());
        Assert.assertEquals(Constants.SECRETARIAT, dao.getUserType().getName());

    }
}