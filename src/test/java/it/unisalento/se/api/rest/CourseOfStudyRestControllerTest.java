package it.unisalento.se.api.rest;

import it.unisalento.se.exceptions.CourseOfStudyNotFound;
import it.unisalento.se.iservices.ICourseOfStudyService;
import it.unisalento.se.models.AcademicYearModel;
import it.unisalento.se.models.CourseOfStudyModel;
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

import java.util.ArrayList;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
public class CourseOfStudyRestControllerTest {

    private MockMvc mockMvc;
    @Mock
    private ICourseOfStudyService service;
    @InjectMocks
    private CourseOfStudyRestController controller;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = TestUtils.getMockMvc(controller);
    }

    @Test
    public void getCourseOfStudyByID_ShouldFail() throws Exception {

        when(service.getCourseOfStudyByID(any(Integer.class))).thenThrow(new CourseOfStudyNotFound());

        mockMvc.perform(get("/course-of-study/{id}", 123))
                .andExpect(status().isNotFound());

        verify(service, times(1)).getCourseOfStudyByID(123);
        verifyNoMoreInteractions(service);
    }

    @Test
    public void getCourseOfStudyByID_OK() throws Exception {

        CourseOfStudyModel model = new CourseOfStudyModel();
        model.setID(10);
        model.setName("Comp. Eng.");
        AcademicYearModel academicYear = new AcademicYearModel();
        academicYear.setID(123);
        academicYear.setStartYear(2017);
        academicYear.setEndYear(2018);
        model.setAcademicYear(academicYear);

        when(service.getCourseOfStudyByID(any(Integer.class))).thenReturn(model);

        mockMvc.perform(get("/course-of-study/{id}", 10))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", Matchers.is(model.getID())))
                .andExpect(jsonPath("$.name", Matchers.is(model.getName())))
                .andExpect(jsonPath("$.academicYear.id", Matchers.is(model.getAcademicYear().getID())))
                .andExpect(jsonPath("$.academicYear.startYear", Matchers.is(model.getAcademicYear().getStartYear())))
                .andExpect(jsonPath("$.academicYear.endYear", Matchers.is(model.getAcademicYear().getEndYear())));

        verify(service, times(1)).getCourseOfStudyByID(10);
        verifyNoMoreInteractions(service);

    }

    @Test
    public void saveCourseOfStudy() throws Exception {

        CourseOfStudyModel model = new CourseOfStudyModel();
        model.setID(10);
        model.setName("Comp. Eng.");
        AcademicYearModel academicYear = new AcademicYearModel();
        academicYear.setID(123);
        academicYear.setStartYear(2017);
        academicYear.setEndYear(2018);
        model.setAcademicYear(academicYear);

        when(service.saveCourseOfStudy(any(CourseOfStudyModel.class))).thenReturn(model);

        mockMvc.perform(post("/course-of-study")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(TestUtils.toJson(model)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", Matchers.is(model.getID())))
                .andExpect(jsonPath("$.name", Matchers.is(model.getName())))
                .andExpect(jsonPath("$.academicYear.id", Matchers.is(model.getAcademicYear().getID())))
                .andExpect(jsonPath("$.academicYear.startYear", Matchers.is(model.getAcademicYear().getStartYear())))
                .andExpect(jsonPath("$.academicYear.endYear", Matchers.is(model.getAcademicYear().getEndYear())));

        verify(service, times(1)).saveCourseOfStudy(refEq(model, "academicYear"));
        verifyNoMoreInteractions(service);
    }

    @Test
    public void getAllCoursesOfStudy() throws Exception {
        CourseOfStudyModel model1 = new CourseOfStudyModel();
        model1.setID(10);
        model1.setName("Comp. Eng.");
        AcademicYearModel academicYear1 = new AcademicYearModel();
        academicYear1.setID(123);
        academicYear1.setStartYear(2017);
        academicYear1.setEndYear(2018);
        model1.setAcademicYear(academicYear1);

        CourseOfStudyModel model2 = new CourseOfStudyModel();
        model2.setID(11);
        model2.setName("Mechanical Eng.");
        AcademicYearModel academicYear2 = new AcademicYearModel();
        academicYear2.setID(123);
        academicYear2.setStartYear(2017);
        academicYear2.setEndYear(2018);
        model2.setAcademicYear(academicYear2);

        ArrayList<CourseOfStudyModel> models = new ArrayList<>();
        models.add(model1);
        models.add(model2);
        when(service.getAllCourses()).thenReturn(models);

        mockMvc.perform(get("/course-of-study"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].id", Matchers.is(model1.getID())))
                .andExpect(jsonPath("$[0].name", Matchers.is(model1.getName())))
                .andExpect(jsonPath("$[0].academicYear.id", Matchers.is(model1.getAcademicYear().getID())))
                .andExpect(jsonPath("$[0].academicYear.startYear", Matchers.is(model1.getAcademicYear().getStartYear())))
                .andExpect(jsonPath("$[0].academicYear.endYear", Matchers.is(model1.getAcademicYear().getEndYear())))
                .andExpect(jsonPath("$[1].id", Matchers.is(model2.getID())))
                .andExpect(jsonPath("$[1].name", Matchers.is(model2.getName())))
                .andExpect(jsonPath("$[1].academicYear.id", Matchers.is(model2.getAcademicYear().getID())))
                .andExpect(jsonPath("$[1].academicYear.startYear", Matchers.is(model2.getAcademicYear().getStartYear())))
                .andExpect(jsonPath("$[1].academicYear.endYear", Matchers.is(model2.getAcademicYear().getEndYear())));

        verify(service, times(1)).getAllCourses();
        verifyNoMoreInteractions(service);
    }
}