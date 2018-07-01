package it.unisalento.se.services;


import it.unisalento.se.dao.SupportDevice;
import it.unisalento.se.exceptions.SupportDeviceNotFoundException;
import it.unisalento.se.models.SupportDeviceModel;
import it.unisalento.se.repositories.SupportDeviceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class SupportDeviceServiceTest {

    @Mock
    private SupportDeviceRepository supportDeviceRepository;
    @InjectMocks
    private SupportDeviceService supportDeviceService;

    @Test
    public void getSupportDevice_OK() throws SupportDeviceNotFoundException {


        SupportDevice supportDevice = new SupportDevice();
        supportDevice.setId(1);
        supportDevice.setName("Wi-Fi");
        when(supportDeviceRepository.getOne(1)).thenReturn(supportDevice);

        SupportDeviceModel model = supportDeviceService.getSupportDeviceByID(1);
        assertEquals(new Integer(1), model.getID());
        assertEquals("Wi-Fi", model.getName());

    }


    /*
        @Test(expected = ExamNotFoundException.class)
    public void getExam_shouldFail() throws ExamNotFoundException, UserTypeNotSupported {
        when(examRepository.getOne(10)).thenThrow(new EntityNotFoundException());

        ExamModel model = examService.getExamByID(10);
    }
     */

    @Test(expected = SupportDeviceNotFoundException.class)
    public void getSupportExam_shouldFaild() throws SupportDeviceNotFoundException {
        when(supportDeviceRepository.getOne(18)).thenThrow(new EntityNotFoundException());
        SupportDeviceModel model = supportDeviceService.getSupportDeviceByID(18);
    }


    @Test
    public void createSupportDevice() {

        SupportDevice sd = new SupportDevice();
        sd.setId(1);
        sd.setName("Riscaldamento");

        SupportDeviceModel sdM = new SupportDeviceModel();
        sdM.setID(1);
        sdM.setName("Riscaldamento");


        when(supportDeviceRepository.save(any(SupportDevice.class))).thenReturn(sd);

        SupportDeviceModel model1 = supportDeviceService.createSupportDevice(sdM);


        assertEquals(sd.getId(), model1.getID());
        assertEquals(sd.getName(), model1.getName());


    }


}
