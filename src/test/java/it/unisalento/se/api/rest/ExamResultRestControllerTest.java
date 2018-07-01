
package it.unisalento.se.api.rest;
/*
import it.unisalento.se.common.Constants;
import it.unisalento.se.dao.UserType;
import it.unisalento.se.exceptions.ExamResultNotFoundException;
import it.unisalento.se.iservices.IExamResultService;
import it.unisalento.se.models.*;
import it.unisalento.se.test.utils.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class ExamResultRestControllerTest {

    private MockMvc mockMvc;
    @Mock
    private IExamResultService service;
    @InjectMocks
    private ExamResultRestControllerTest controller;

    @Captor
    private ArgumentCaptor<ExamResultModel> savedExamResult;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = TestUtils.getMockMvc(controller);
    }

    @Test
    public void getExamResultByID_Fail() throws Exception {
        when(service.getExamResultByID(any(Integer.class))).thenThrow(new ExamResultNotFoundException());
        mockMvc.perform(get("/examresult/{id}", 20)).andExpect(status().isNotFound());

        verify(service, times(1)).getExamResultByID(20);
        verifyNoMoreInteractions(service);
    }

    @Test
    public void getExamResultByID_OK() throws Exception{

        UserModel model = new UserModel();
        model.setId(1);
        model.setEmail("mario.rossi@test.it");
        model.setName("Mario");
        model.setSurname("Rossi");
        model.setUserType(UserTypeModel.PROFESSOR);
        UserType type = new UserType();
        type.setName(Constants.PROFESSOR);
        type.setId(2);


        UserModel model2 = new UserModel();
        model2.setId(2);
        model2.setEmail("mario.rossi@test.it");
        model2.setName("Mario");
        model2.setSurname("Rossi");
        model2.setUserType(UserTypeModel.STUDENT);
        UserType type2 = new UserType();
        type2.setName(Constants.STUDENT);
        type2.setId(3);

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

        Date startDate = new Date();
        startDate.setTime(startDate.getTime() - 100);
        Date endTime = new Date();

        TimeSlotModel ts = new TimeSlotModel();
        ts.setID(1);
        ts.setStartTime(startDate);
        ts.setEndTime(endTime);

        ClassroomModel cr = new ClassroomModel();
        cr.setId(1);
        cr.setName("Y1");
        cr.setLatitude(1.0);
        cr.setLongitude(1.0);


        ExamModel exam = new ExamModel();
        exam.setID(1);
        exam.setDescription("Appello 1");
        exam.setTimeslot(ts);
        exam.setClassroom(cr);
        exam.setSubject(sub);

        Date examDate = new Date();

        ExamResultModel examResult = new ExamResultModel();
        examResult.setID(1);
        examResult.setVote(18);
        examResult.setStudent(model2);
        examResult.setDate(examDate);
        examResult.setExam(exam);

        when(service.getExamResultByID(any(Integer.class))).thenReturn(examResult);

        mockMvc.perform(get("/examresult/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", org.hamcrest.Matchers.is(examResult.getID())))
                .andExpect(jsonPath("$.vote", org.hamcrest.Matchers.is(examResult.getVote())))
                .andExpect(jsonPath("$.date", org.hamcrest.Matchers.is(examResult.getDate())))

        ;

        verify(service, times(1)).getExamResultByID(1);
        verifyNoMoreInteractions(service);

    }
    @Test
    public void saveExamResult() throws Exception {
        UserModel model = new UserModel();
        model.setId(1);
        model.setEmail("mario.rossi@test.it");
        model.setName("Mario");
        model.setSurname("Rossi");
        model.setUserType(UserTypeModel.PROFESSOR);
        UserType type = new UserType();
        type.setName(Constants.PROFESSOR);
        type.setId(2);

        UserModel model2 = new UserModel();
        model2.setId(2);
        model2.setEmail("mario.rossi@test.it");
        model2.setName("Mario");
        model2.setSurname("Rossi");
        model2.setUserType(UserTypeModel.STUDENT);
        UserType type2 = new UserType();
        type2.setName(Constants.STUDENT);
        type2.setId(3);

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

        Date startDate = new Date();
        startDate.setTime(startDate.getTime() - 100);
        Date endTime = new Date();

        TimeSlotModel ts = new TimeSlotModel();
        ts.setID(1);
        ts.setStartTime(startDate);
        ts.setEndTime(endTime);

        ClassroomModel cr = new ClassroomModel();
        cr.setId(1);
        cr.setName("Y1");
        cr.setLatitude(1.0);
        cr.setLongitude(1.0);


        ExamModel exam = new ExamModel();
        exam.setID(1);
        exam.setDescription("Appello 1");
        exam.setTimeslot(ts);
        exam.setClassroom(cr);
        exam.setSubject(sub);

        Date examDate = new Date();


        ExamResultModel examResult = new ExamResultModel();
        examResult.setID(1);
        examResult.setVote(18);
        examResult.setStudent(model2);
        examResult.setDate(examDate);
        examResult.setExam(exam);

        when(service.saveExamResult(any(ExamResultModel.class))).thenReturn(examResult);


        mockMvc.perform(
                post("/examresult")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(TestUtils.toJson(exam)))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.id", org.hamcrest.Matchers.is(exam.getID())))
                .andExpect(jsonPath("$.vote", org.hamcrest.Matchers.is(examResult.getVote())))
                .andExpect(jsonPath("$.date", org.hamcrest.Matchers.is(examResult.getDate())))
        ;


        verify(service, times(1)).saveExamResult(savedExamResult.capture());
        verifyNoMoreInteractions(service);

    }
}


*/