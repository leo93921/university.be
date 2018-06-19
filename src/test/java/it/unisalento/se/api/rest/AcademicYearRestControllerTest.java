package it.unisalento.se.api.rest;


import it.unisalento.se.common.CommonUtils;
import it.unisalento.se.dto.AcademicYearModel;
import it.unisalento.se.exceptions.AcademicYearNotFoundException;
import it.unisalento.se.iservice.IAcademicYearService;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;



@RunWith(MockitoJUnitRunner.class)
public class AcademicYearRestControllerTest {
    private MockMvc mockMvc;

    @Mock
    private IAcademicYearService academicYearServiceMock;
    @InjectMocks private AcademicYearRestController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setViewResolvers(CommonUtils.getCommonUtils().getCommonResolver())
                .build();
    }

    @Test
    public void findAcademicYearById() throws Exception {
        AcademicYearModel y = new AcademicYearModel();

        y.setId(1);
        y.setStartYear(2016);
        y.setEndYear(2017);


        when(academicYearServiceMock.getAcademicYearByID(1)).thenReturn(y);

        // Do call and test
        mockMvc.perform(get("/academicyear/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
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

        mockMvc.perform(get("/academicyear/{id}", 13))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        verify(academicYearServiceMock, times(1)).getAcademicYearByID(13);
        verifyNoMoreInteractions(academicYearServiceMock);
    }

    @Test
    public void saveAcademicYear() throws Exception {
        AcademicYearModel y = new AcademicYearModel();

        y.setId(1);
        y.setStartYear(1998);
        y.setEndYear(1999);

        when(academicYearServiceMock.createAcademicYear(any(AcademicYearModel.class)))
                .thenReturn(y);

        mockMvc.perform(
                post("/academicyear")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtils.toJson(y))
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.startYear", Matchers.is(1998)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.endYear", Matchers.is(1999)));


        verify(academicYearServiceMock, times(1)).createAcademicYear(refEq(y));
        verifyNoMoreInteractions(academicYearServiceMock);

    }



}
