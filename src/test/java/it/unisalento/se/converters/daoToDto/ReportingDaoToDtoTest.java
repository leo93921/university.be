package it.unisalento.se.converters.daoToDto;

import it.unisalento.se.common.Constants;
import it.unisalento.se.dao.*;
import it.unisalento.se.exceptions.ReportingStatusNotSupported;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.ReportingModel;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class ReportingDaoToDtoTest {

    @Test
    public void convert() throws UserTypeNotSupported, ReportingStatusNotSupported {
        Classroom c = new Classroom();
        c.setId(1);
        c.setName("O1");
        c.setLatitude(88.6);
        c.setLongitude(95.8);

        ReportingStatus reportingStatus = new ReportingStatus(Constants.RECEIVED);

        UserType t = new UserType();
        t.setId(2);
        t.setName(Constants.PROFESSOR);

        User u = new User();
        u.setId(1);
        u.setName("Mario");
        u.setSurname("Rossi");
        u.setEmail("mario.rossi@test.it");
        u.setUserType(t);

        Reporting dao = new Reporting(c, reportingStatus, u, new Date(), "A problem!");
        dao.setId(32);


        SupportDevice sd = new SupportDevice();
        sd.setId(1);
        sd.setName("Projector");
        dao.setSupportDevice(sd);

        ReportingModel model = ReportingDaoToDto.convert(dao);

        assertEquals(Integer.valueOf(dao.getId()), model.getID());
        assertEquals(dao.getNote(), model.getNote());
        assertEquals(dao.getSupportDevice().getId(), model.getSupportDevice().getID());
        assertEquals(dao.getClassroom().getId(), model.getClassroom().getID());
        assertEquals(dao.getUser().getId(), model.getDoneBy().getId());
        assertEquals(dao.getLastModified(), model.getLastModified());
        assertEquals(dao.getProblemDescription(), model.getProblemDescription());
    }
}