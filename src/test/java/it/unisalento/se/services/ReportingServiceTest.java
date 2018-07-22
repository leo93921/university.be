package it.unisalento.se.services;

import it.unisalento.se.dao.*;
import it.unisalento.se.exceptions.ReportingNotFoundException;
import it.unisalento.se.exceptions.ReportingStatusNotSupported;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.exceptions.ValidationException;
import it.unisalento.se.models.*;
import it.unisalento.se.repositories.ReportingRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ReportingServiceTest {


    @Mock
    private ReportingRepository reportingRepository;
    @InjectMocks
    private ReportingService reportingService;

    @Test
    public void getReportingByID() throws UserTypeNotSupported, ReportingNotFoundException, ReportingStatusNotSupported {


        SupportDevice sd = new SupportDevice();
        sd.setId(1);
        sd.setName("Proiettore");

        Classroom cr = new Classroom();
        cr.setId(1);
        cr.setName("Y1");
        cr.setLatitude(1.0);
        cr.setLongitude(1.0);

        UserType ut = new UserType();
        ut.setId(1);
        ut.setName("PROFESSOR");

        User u = new User();
        u.setId(1);
        u.setName("Giovanni");
        u.setSurname("Breve");
        u.setEmail("giovannino@email.it");
        u.setUserType(ut);
        u.setPassword("pipino");

        ReportingStatus rs = new ReportingStatus();
        rs.setId(1);
        rs.setName("RECEIVED");

        Date lastModified = new Date();

        Reporting rep = new Reporting();
        rep.setId(1);
        rep.setSupportDevice(sd);
        rep.setNote("Non funziona");
        rep.setLastModified(lastModified);
        rep.setUser(u);
        rep.setReportingStatus(rs);
        rep.setClassroom(cr);


        when(reportingRepository.getOne(1)).thenReturn(rep);

        ReportingModel model = reportingService.getReportingByID(1);
        assertEquals(new Integer(1), model.getID());
        assertEquals(rep.getNote(), model.getNote());


    }

    @Test
    public void saveReporting() throws UserTypeNotSupported, ReportingStatusNotSupported, ValidationException {

        /*
           AcademicYear ay = new AcademicYear();
        ay.setId(1);
        ay.setStartYear(2017);
        ay.setEndYear(2018);



        AcademicYearModel ayM = new AcademicYearModel();
        ayM.setID(1);
        ayM.setStartYear(2017);
        ayM.setEndYear(2018);



        when(academicYearRepository.save(any(AcademicYear.class))).thenReturn(ay);

        AcademicYearModel model1 = academicYearService.createAcademicYear(ayM);


        assertEquals(ay.getId(), model1.getID());
        assertEquals(new Integer ( ay.getEndYear()), model1.getEndYear());
        assertEquals(new Integer (ay.getStartYear()), model1.getStartYear());
         */

        SupportDevice sd = new SupportDevice();
        sd.setId(1);
        sd.setName("Proiettore");

        Classroom cr = new Classroom();
        cr.setId(1);
        cr.setName("Y1");
        cr.setLatitude(1.0);
        cr.setLongitude(1.0);

        UserType ut = new UserType();
        ut.setId(1);
        ut.setName("PROFESSOR");

        User u = new User();
        u.setId(1);
        u.setName("Giovanni");
        u.setSurname("Breve");
        u.setEmail("giovannino@email.it");
        u.setUserType(ut);
        u.setPassword("pipino");

        ReportingStatus rs = new ReportingStatus();
        rs.setId(1);
        rs.setName("RECEIVED");

        Date lastModified = new Date();

        Reporting rep = new Reporting();
        rep.setId(1);
        rep.setSupportDevice(sd);
        rep.setNote("Non funziona");
        rep.setLastModified(lastModified);
        rep.setUser(u);
        rep.setReportingStatus(rs);
        rep.setClassroom(cr);


        SupportDeviceModel sdm = new SupportDeviceModel();
        sdm.setID(1);
        sdm.setName("Proiettore");

        ClassroomModel crm = new ClassroomModel();
        crm.setID(1);
        crm.setName("Y1");
        crm.setLatitude(1.0);
        crm.setLongitude(1.0);


        UserModel um = new UserModel();
        um.setId(1);
        um.setName("Giovanni");
        um.setSurname("Breve");
        um.setEmail("giovannino@email.it");
        um.setUserType(UserTypeModel.PROFESSOR);
        um.setPassword("pipino");


        ReportingModel repm = new ReportingModel();
        repm.setID(1);
        repm.setSupportDevice(sdm);
        repm.setNote("Non funziona");
        repm.setLastModified(lastModified);
        repm.setDoneBy(um);
        repm.setReportingStatus(ReportingStatusModel.RECEIVED);
        repm.setClassroom(crm);


        when(reportingRepository.save(any(Reporting.class))).thenReturn(rep);

        ReportingModel model1 = reportingService.saveReporting(repm);


        assertEquals(rep.getId(), repm.getID());
        assertEquals(rep.getNote(), rep.getNote());


    }

    @Test
    public void getAllReporting() throws UserTypeNotSupported, ReportingStatusNotSupported {

        SupportDevice sd = new SupportDevice();
        sd.setId(1);
        sd.setName("Proiettore");

        Classroom cr = new Classroom();
        cr.setId(1);
        cr.setName("Y1");
        cr.setLatitude(1.0);
        cr.setLongitude(1.0);

        UserType ut = new UserType();
        ut.setId(1);
        ut.setName("PROFESSOR");

        User u = new User();
        u.setId(1);
        u.setName("Giovanni");
        u.setSurname("Breve");
        u.setEmail("giovannino@email.it");
        u.setUserType(ut);
        u.setPassword("pipino");

        ReportingStatus rs = new ReportingStatus();
        rs.setId(1);
        rs.setName("RECEIVED");

        Date lastModified = new Date();

        Reporting rep = new Reporting();
        rep.setId(1);
        rep.setSupportDevice(sd);
        rep.setNote("Non funziona");
        rep.setLastModified(lastModified);
        rep.setUser(u);
        rep.setReportingStatus(rs);
        rep.setClassroom(cr);

        Reporting rep2 = new Reporting();
        rep2.setId(2);
        rep2.setSupportDevice(sd);
        rep2.setNote("Rotto");
        rep2.setLastModified(lastModified);
        rep2.setUser(u);
        rep2.setReportingStatus(rs);
        rep2.setClassroom(cr);

        List<Reporting> list = new ArrayList<>();
        list.add(rep);
        list.add(rep2);

        when(reportingRepository.findAll()).thenReturn(list);

        List<ReportingModel> model = reportingService.getAllReporting();
        assertEquals(new Integer(1), model.get(0).getID());
        assertEquals(rep.getNote(), model.get(0).getNote());
        assertEquals(rep2.getNote(), model.get(1).getNote());


    }
}