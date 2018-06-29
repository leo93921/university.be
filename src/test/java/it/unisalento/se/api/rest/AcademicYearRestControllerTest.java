package it.unisalento.se.api.rest;


import it.unisalento.se.exceptions.AcademicYearNotFoundException;
import it.unisalento.se.iservices.IAcademicYearService;
import it.unisalento.se.models.AcademicYearModel;
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
public class AcademicYearRestControllerTest {
    private MockMvc mockMvc;

    @Mock
    private IAcademicYearService academicYearServiceMock;
    @InjectMocks
    private AcademicYearRestController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = TestUtils.getMockMvc(controller);
    }

    @Test
    public void findAcademicYearById() throws Exception {
        AcademicYearModel y = new AcademicYearModel();

        y.setID(1);
        y.setStartYear(2016);
        y.setEndYear(2017);


        when(academicYearServiceMock.getAcademicYearByID(1)).thenReturn(y);

        // Do call and test
        mockMvc.perform(get("/academic-year/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.startYear", Matchers.is(2016)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.endYear", Matchers.is(2017)));

        verify(academicYearServiceMock, times(1)).getAcademicYearByID(1);
        verifyNoMoreInteractions(academicYearServiceMock);
    }

    @Test
    public void findByAcademicYearIdWithInvalidId() throws Exception {

        when(academicYearServiceMock.getAcademicYearByID(13)).thenThrow(new AcademicYearNotFoundException());

        mockMvc.perform(get("/academic-year/{id}", 13))
                .andExpect(status().isNotFound());

        verify(academicYearServiceMock, times(1)).getAcademicYearByID(13);
        verifyNoMoreInteractions(academicYearServiceMock);
    }

    @Test
    public void saveAcademicYear() throws Exception {
        AcademicYearModel y = new AcademicYearModel();

        y.setID(1);
        y.setStartYear(1998);
        y.setEndYear(1999);

        when(academicYearServiceMock.createAcademicYear(any(AcademicYearModel.class)))
                .thenReturn(y);

        mockMvc.perform(
                post("/academic-year")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtils.toJson(y))
        )
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.startYear", Matchers.is(1998)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.endYear", Matchers.is(1999)));


        verify(academicYearServiceMock, times(1)).createAcademicYear(refEq(y));
        verifyNoMoreInteractions(academicYearServiceMock);

    }

    @Test
    public void getAllAcademicYears() throws Exception {
        AcademicYearModel y1 = new AcademicYearModel();
        y1.setID(1);
        y1.setStartYear(1998);
        y1.setEndYear(1999);
        AcademicYearModel y2 = new AcademicYearModel();
        y2.setID(2);
        y2.setStartYear(1999);
        y2.setEndYear(2000);
        List<AcademicYearModel> models = new ArrayList<>();
        models.add(y1);
        models.add(y2);

        when(academicYearServiceMock.getAll()).thenReturn(models);

        mockMvc.perform(get("/academic-year"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$[0].startYear", Matchers.is(1998)))
                .andExpect(jsonPath("$[0].endYear", Matchers.is(1999)))
                .andExpect(jsonPath("$[1].id", Matchers.is(2)))
                .andExpect(jsonPath("$[1].startYear", Matchers.is(1999)))
                .andExpect(jsonPath("$[1].endYear", Matchers.is(2000)));

        verify(academicYearServiceMock, times(1)).getAll();
        verifyNoMoreInteractions(academicYearServiceMock);


    }
}
