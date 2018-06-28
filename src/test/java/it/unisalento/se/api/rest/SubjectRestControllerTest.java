package it.unisalento.se.api.rest;

import it.unisalento.se.common.Constants;
import it.unisalento.se.dao.UserType;
import it.unisalento.se.exceptions.SubjectNotFoundException;
import it.unisalento.se.iservices.ISubjectService;
import it.unisalento.se.models.*;
import it.unisalento.se.test.utils.TestUtils;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class SubjectRestControllerTest {

    private MockMvc mockMvc;
    @Mock
    private ISubjectService service;
    @InjectMocks
    private SubjectRestController controller;

    @Captor
    private ArgumentCaptor<SubjectModel> savedSubject;
    @Captor
    private ArgumentCaptor<CourseOfStudyModel> courseOfStudy;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = TestUtils.getMockMvc(controller);
    }

    @Test
    public void getSubjectByID_Fail() throws Exception {
        when(service.getSubjectByID(any(Integer.class))).thenThrow(new SubjectNotFoundException());

        mockMvc.perform(get("/subject/{id}", 19))
                .andExpect(status().isNotFound());

        verify(service, times(1)).getSubjectByID(19);
        verifyNoMoreInteractions(service);
    }

    @Test
    public void getSubjectByID_OK() throws Exception {
        UserModel model = new UserModel();
        model.setId(1);
        model.setEmail("mario.rossi@test.it");
        model.setName("Mario");
        model.setSurname("Rossi");
        model.setUserType(UserTypeModel.PROFESSOR);
        UserType type = new UserType();
        type.setName(Constants.PROFESSOR);
        type.setId(2);

        AcademicYearModel academicYear = new AcademicYearModel();
        academicYear.setID(1);
        academicYear.setStartYear(2017);
        academicYear.setEndYear(2018);

        CourseOfStudyModel cs = new CourseOfStudyModel();
        cs.setName("Computer Engineering");
        cs.setID(25);
        cs.setAcademicYear(academicYear);

        SubjectModel sub = new SubjectModel();
        sub.setID(24);
        sub.setCFU(8);
        sub.setName("A new name");
        sub.setTeachingYear(3);
        sub.setProfessor(model);
        sub.setCourseOfStudy(cs);

        when(service.getSubjectByID(any(Integer.class))).thenReturn(sub);

        mockMvc.perform(get("/subject/{id}", 24))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", Matchers.is(sub.getID())))
                .andExpect(jsonPath("$.cfu", Matchers.is(sub.getCFU())))
                .andExpect(jsonPath("$.name", Matchers.is(sub.getName())))
                .andExpect(jsonPath("$.teachingYear", Matchers.is(sub.getTeachingYear())))
                .andExpect(jsonPath("$.professor.id", Matchers.is(model.getId())))
                .andExpect(jsonPath("$.professor.email", Matchers.is(model.getEmail())))
                .andExpect(jsonPath("$.professor.name", Matchers.is(model.getName())))
                .andExpect(jsonPath("$.professor.surname", Matchers.is(model.getSurname())))
                .andExpect(jsonPath("$.professor.userType", Matchers.is(Constants.PROFESSOR)))
                .andExpect(jsonPath("$.courseOfStudy.id", Matchers.is(cs.getID())))
                .andExpect(jsonPath("$.courseOfStudy.name", Matchers.is(cs.getName())))
                .andExpect(jsonPath("$.courseOfStudy.academicYear.id", Matchers.is(academicYear.getID())))
                .andExpect(jsonPath("$.courseOfStudy.academicYear.startYear", Matchers.is(academicYear.getStartYear())))
                .andExpect(jsonPath("$.courseOfStudy.academicYear.endYear", Matchers.is(academicYear.getEndYear())));

        verify(service, times(1)).getSubjectByID(24);
        verifyNoMoreInteractions(service);
    }

    @Test
    public void saveSubject() throws Exception {
        UserModel model = new UserModel();
        model.setId(1);
        model.setEmail("mario.rossi@test.it");
        model.setName("Mario");
        model.setSurname("Rossi");
        model.setUserType(UserTypeModel.PROFESSOR);
        UserType type = new UserType();
        type.setName(Constants.PROFESSOR);
        type.setId(2);

        AcademicYearModel academicYear = new AcademicYearModel();
        academicYear.setID(1);
        academicYear.setStartYear(2017);
        academicYear.setEndYear(2018);

        CourseOfStudyModel cs = new CourseOfStudyModel();
        cs.setName("Computer Engineering");
        cs.setID(25);
        cs.setAcademicYear(academicYear);

        SubjectModel sub = new SubjectModel();
        sub.setID(24);
        sub.setCFU(8);
        sub.setName("A new name");
        sub.setTeachingYear(3);
        sub.setProfessor(model);
        sub.setCourseOfStudy(cs);

        when(service.saveSubject(any(SubjectModel.class))).thenReturn(sub);

        mockMvc.perform(
                post("/subject")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(TestUtils.toJson(sub)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", Matchers.is(sub.getID())))
                .andExpect(jsonPath("$.cfu", Matchers.is(sub.getCFU())))
                .andExpect(jsonPath("$.name", Matchers.is(sub.getName())))
                .andExpect(jsonPath("$.teachingYear", Matchers.is(sub.getTeachingYear())))
                .andExpect(jsonPath("$.professor.id", Matchers.is(model.getId())))
                .andExpect(jsonPath("$.professor.email", Matchers.is(model.getEmail())))
                .andExpect(jsonPath("$.professor.name", Matchers.is(model.getName())))
                .andExpect(jsonPath("$.professor.surname", Matchers.is(model.getSurname())))
                .andExpect(jsonPath("$.professor.userType", Matchers.is(Constants.PROFESSOR)))
                .andExpect(jsonPath("$.courseOfStudy.id", Matchers.is(cs.getID())))
                .andExpect(jsonPath("$.courseOfStudy.name", Matchers.is(cs.getName())))
                .andExpect(jsonPath("$.courseOfStudy.academicYear.id", Matchers.is(academicYear.getID())))
                .andExpect(jsonPath("$.courseOfStudy.academicYear.startYear", Matchers.is(academicYear.getStartYear())))
                .andExpect(jsonPath("$.courseOfStudy.academicYear.endYear", Matchers.is(academicYear.getEndYear())));

        verify(service, times(1)).saveSubject(savedSubject.capture());
        verifyNoMoreInteractions(service);
    }

    @Test
    public void getAllSubjectsByCourseOfStudy() throws Exception {
        ArrayList<SubjectModel> models = new ArrayList<>();
        SubjectModel m = new SubjectModel();
        UserModel model = new UserModel();
        model.setId(1);
        model.setEmail("mario.rossi@test.it");
        model.setName("Mario");
        model.setSurname("Rossi");
        model.setUserType(UserTypeModel.PROFESSOR);
        UserType type = new UserType();
        type.setName(Constants.PROFESSOR);
        type.setId(2);

        AcademicYearModel academicYear = new AcademicYearModel();
        academicYear.setID(1);
        academicYear.setStartYear(2017);
        academicYear.setEndYear(2018);

        CourseOfStudyModel cs = new CourseOfStudyModel();
        cs.setName("Computer Engineering");
        cs.setID(25);
        cs.setAcademicYear(academicYear);

        SubjectModel sub = new SubjectModel();
        sub.setID(24);
        sub.setCFU(8);
        sub.setName("A new name");
        sub.setTeachingYear(3);
        sub.setProfessor(model);
        sub.setCourseOfStudy(cs);
        models.add(sub);

        when(service.getAllSubjectsByCourseOfStudy(any(CourseOfStudyModel.class))).thenReturn(models);

        mockMvc.perform(
                post("/subject/find-by-course")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(TestUtils.toJson(cs)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].id", Matchers.is(sub.getID())))
                .andExpect(jsonPath("$[0].cfu", Matchers.is(sub.getCFU())))
                .andExpect(jsonPath("$[0].name", Matchers.is(sub.getName())))
                .andExpect(jsonPath("$[0].teachingYear", Matchers.is(sub.getTeachingYear())))
                .andExpect(jsonPath("$[0].professor.id", Matchers.is(model.getId())))
                .andExpect(jsonPath("$[0].professor.email", Matchers.is(model.getEmail())))
                .andExpect(jsonPath("$[0].professor.name", Matchers.is(model.getName())))
                .andExpect(jsonPath("$[0].professor.surname", Matchers.is(model.getSurname())))
                .andExpect(jsonPath("$[0].professor.userType", Matchers.is(Constants.PROFESSOR)))
                .andExpect(jsonPath("$[0].courseOfStudy.id", Matchers.is(cs.getID())))
                .andExpect(jsonPath("$[0].courseOfStudy.name", Matchers.is(cs.getName())))
                .andExpect(jsonPath("$[0].courseOfStudy.academicYear.id", Matchers.is(academicYear.getID())))
                .andExpect(jsonPath("$[0].courseOfStudy.academicYear.startYear", Matchers.is(academicYear.getStartYear())))
                .andExpect(jsonPath("$[0].courseOfStudy.academicYear.endYear", Matchers.is(academicYear.getEndYear())));

        verify(service, times(1)).getAllSubjectsByCourseOfStudy(courseOfStudy.capture());
        verifyNoMoreInteractions(service);
    }
}