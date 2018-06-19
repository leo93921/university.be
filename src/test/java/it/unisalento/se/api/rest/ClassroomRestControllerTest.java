package it.unisalento.se.api.rest;


import it.unisalento.se.common.CommonUtils;
import it.unisalento.se.dto.ClassroomModel;
import it.unisalento.se.exceptions.ClassroomNotFoundException;
import it.unisalento.se.iservice.IClassroomService;
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
public class ClassroomRestControllerTest {
    private MockMvc mockMvc;

    @Mock
    private IClassroomService classroomServiceMock;
    @InjectMocks private ClassroomRestController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .setViewResolvers(CommonUtils.getCommonUtils().getCommonResolver())
                .build();
    }

    @Test
    public void findClassroomById() throws Exception {
        ClassroomModel c = new ClassroomModel();

        c.setId(1);
        c.setName("Y1");
        c.setLatitude(100.5);
        c.setLongitude(200.88);

        when(classroomServiceMock.getClassroomByID(1)).thenReturn(c);

        // Do call and test
        mockMvc.perform(get("/classroom/{id}", 1))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Y1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.latitude", Matchers.is(100.5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.longitude", Matchers.is(200.88)));

        verify(classroomServiceMock, times(1)).getClassroomByID(1);
        verifyNoMoreInteractions(classroomServiceMock);
    }

    @Test
    public void findByClassroomIdWithInvalidId() throws Exception {

        when(classroomServiceMock.getClassroomByID(10)).thenThrow(new ClassroomNotFoundException());

        mockMvc.perform(get("/classroom/{id}", 10))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        verify(classroomServiceMock, times(1)).getClassroomByID(10);
        verifyNoMoreInteractions(classroomServiceMock);
    }

    @Test
    public void saveClassroom() throws Exception {
        ClassroomModel c = new ClassroomModel();

        c.setId(1);
        c.setName("Y2");
        c.setLatitude(150.86);
        c.setLongitude(300.36);

        when(classroomServiceMock.createClassroom(any(ClassroomModel.class)))
                .thenReturn(c);

        mockMvc.perform(
                post("/classroom")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(TestUtils.toJson(c))
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.is("Y2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.latitude", Matchers.is(150.86)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.longitude", Matchers.is(300.36)));


        verify(classroomServiceMock, times(1)).createClassroom(refEq(c));
        verifyNoMoreInteractions(classroomServiceMock);

    }

}
