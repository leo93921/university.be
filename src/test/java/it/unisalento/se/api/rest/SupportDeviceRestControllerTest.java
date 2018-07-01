package it.unisalento.se.api.rest;


import it.unisalento.se.exceptions.SupportDeviceNotFoundException;
import it.unisalento.se.iservices.ISupportDeviceService;
import it.unisalento.se.models.SupportDeviceModel;
import it.unisalento.se.test.utils.TestUtils;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(MockitoJUnitRunner.class)
public class SupportDeviceRestControllerTest {
    private MockMvc mockMvc;

    @Mock
    private ISupportDeviceService supportDeviceService;
    @InjectMocks
    private SupportDeviceRestController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = TestUtils.getMockMvc(controller);
    }

    @Test
    public void findSupportDeviceById() throws Exception {
        SupportDeviceModel sd = new SupportDeviceModel();
        sd.setID(1);
        sd.setName("Wi-Fi");

        when(supportDeviceService.getSupportDeviceByID(1)).thenReturn(sd);

        mockMvc.perform(get("/support-device/{id}", 1))
                .andExpect(status().isOk())

        ;

        verify(supportDeviceService, times(1)).getSupportDeviceByID(1);
        verifyNoMoreInteractions(supportDeviceService);
    }


    @Test
    public void findBySupportDeviceIdWithInvalidId() throws Exception {

        when(supportDeviceService.getSupportDeviceByID(13)).thenThrow(new SupportDeviceNotFoundException());

        mockMvc.perform(get("/support-device/{id}", 13))
                .andExpect(status().isNotFound());

        verify(supportDeviceService, times(1)).getSupportDeviceByID(13);
        verifyNoMoreInteractions(supportDeviceService);
    }


    @Test
    public void saveSupportDevice() throws Exception {
        SupportDeviceModel sd = new SupportDeviceModel();

        sd.setID(1);
        sd.setName("Proiettore 4K");


        when(supportDeviceService.createSupportDevice(any(SupportDeviceModel.class)))
                .thenReturn(sd);

        mockMvc.perform(
                post("/support-device")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtils.toJson(sd))
        )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Proiettore 4K")))
        ;


        verify(supportDeviceService, times(1)).createSupportDevice(refEq(sd));
        verifyNoMoreInteractions(supportDeviceService);

    }

    @Test
    public void getAllSupportDevices() throws Exception {
        SupportDeviceModel sd1 = new SupportDeviceModel();
        sd1.setID(1);
        sd1.setName("Condizionatore");

        SupportDeviceModel sd2 = new SupportDeviceModel();
        sd2.setID(2);
        sd2.setName("Impianto Audio");

        List<SupportDeviceModel> models = new ArrayList<>();
        models.add(sd1);
        models.add(sd2);

        when(supportDeviceService.getAll()).thenReturn(models);

        mockMvc.perform(get("/support-device"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$[0].name", Matchers.is("Condizionatore")))

                .andExpect(jsonPath("$[1].id", Matchers.is(2)))
                .andExpect(jsonPath("$[1].name", Matchers.is("Impianto Audio")))
        ;

        verify(supportDeviceService, times(1)).getAll();
        verifyNoMoreInteractions(supportDeviceService);


    }


}



