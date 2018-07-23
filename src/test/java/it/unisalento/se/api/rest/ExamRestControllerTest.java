package it.unisalento.se.api.rest;


import it.unisalento.se.common.CommonUtils;
import it.unisalento.se.common.Constants;
import it.unisalento.se.dao.UserType;
import it.unisalento.se.exceptions.ExamNotFoundException;
import it.unisalento.se.iservices.IExamService;
import it.unisalento.se.models.*;
import it.unisalento.se.test.utils.TestUtils;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class ExamRestControllerTest {

    private MockMvc mockMvc;
    @Mock
    private IExamService service;
    @InjectMocks
    private ExamRestController controller;
    @Captor
    private ArgumentCaptor<ExamModel> savedExam;
    @Captor
    private ArgumentCaptor<ExamFilterModel> examFilter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = TestUtils.getMockMvc(controller);
    }


    @Test
    public void getExamByID_Fail() throws Exception {
        when(service.getExamByID(any(Integer.class))).thenThrow(new ExamNotFoundException());

        mockMvc.perform(get("/exam/{id}", 19))
                .andExpect(status().isNotFound());

        verify(service, times(1)).getExamByID(19);
        verifyNoMoreInteractions(service);
    }


    @Test
    public void getExamByID_OK() throws Exception {
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

        Date startDate = new Date();
        startDate.setTime(startDate.getTime() - 100);
        Date endTime = new Date();

        TimeSlotModel ts = new TimeSlotModel();
        ts.setID(1);
        ts.setStartTime(startDate);
        ts.setEndTime(endTime);

        ClassroomModel cr = new ClassroomModel();
        cr.setID(1);
        cr.setName("Y1");
        cr.setLatitude(1.0);
        cr.setLongitude(1.0);


        ExamModel exam = new ExamModel();
        exam.setID(1);
        exam.setDescription("Appello 1");
        exam.setTimeslot(ts);
        exam.setClassroom(cr);
        exam.setSubject(sub);

        when(service.getExamByID(any(Integer.class))).thenReturn(exam);

        mockMvc.perform(get("/exam/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", Matchers.is(exam.getID())))
                .andExpect(jsonPath("$.classroom.name", Matchers.is(cr.getName())))
                .andExpect(jsonPath("$.subject.name", Matchers.is(sub.getName())))

        ;

        verify(service, times(1)).getExamByID(1);
        verifyNoMoreInteractions(service);


    }

    @Test
    public void getDaily_OK() throws Exception {
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

        Date startDate = new Date();
        startDate.setTime(startDate.getTime() - 100);
        Date endTime = new Date();

        TimeSlotModel ts = new TimeSlotModel();
        ts.setID(1);
        ts.setStartTime(startDate);
        ts.setEndTime(endTime);


        Date filter_before = new Date();
        Date filter_after = new Date();
        filter_before.setTime(startDate.getTime() -1000);
        filter_after.setTime(endTime.getTime() + 1000);

        TimeSlotModel ts_before = new TimeSlotModel();
        ts_before.setID(2);
        ts_before.setStartTime(filter_before);
        ts_before.setEndTime(filter_after);

        TimeSlotModel ts_after = new TimeSlotModel();
        ts_after.setID(3);
        ts_after.setStartTime(filter_before);
        ts_after.setEndTime(filter_after);

        ClassroomModel cr = new ClassroomModel();
        cr.setID(1);
        cr.setName("Y1");
        cr.setLatitude(1.0);
        cr.setLongitude(1.0);


        ExamModel exam = new ExamModel();
        exam.setID(1);
        exam.setDescription("Appello 1");
        exam.setTimeslot(ts);
        exam.setClassroom(cr);
        exam.setSubject(sub);

        ExamFilterModel filter = new ExamFilterModel();
        filter.setCourseOfStudy(cs);
        filter.setStartTime(ts_before);
        filter.setEndTime(ts_after);


        List<ExamModel> lista = new ArrayList<>();
        lista.add(exam);

        when(service.filterByTimeAndCourseOfStudy(any(ExamFilterModel.class))).thenReturn(lista);

        mockMvc.perform(post("/exam/daily").contentType(MediaType.APPLICATION_JSON_UTF8).content(CommonUtils.toJson(filter)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].id", Matchers.is(exam.getID())))
                .andExpect(jsonPath("$[0].classroom.name", Matchers.is(cr.getName())))
                .andExpect(jsonPath("$[0].subject.name", Matchers.is(sub.getName())))

        ;

        verify(service, times(1)).filterByTimeAndCourseOfStudy(examFilter.capture());
        verifyNoMoreInteractions(service);


    }


    @Test
    public void getDailyProfessor_OK() throws Exception {
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

        Date startDate = new Date();
        startDate.setTime(startDate.getTime() - 100);
        Date endTime = new Date();

        TimeSlotModel ts = new TimeSlotModel();
        ts.setID(1);
        ts.setStartTime(startDate);
        ts.setEndTime(endTime);


        Date filter_before = new Date();
        Date filter_after = new Date();
        filter_before.setTime(startDate.getTime() -1000);
        filter_after.setTime(endTime.getTime() + 1000);

        TimeSlotModel ts_before = new TimeSlotModel();
        ts_before.setID(2);
        ts_before.setStartTime(filter_before);
        ts_before.setEndTime(filter_after);

        TimeSlotModel ts_after = new TimeSlotModel();
        ts_after.setID(3);
        ts_after.setStartTime(filter_before);
        ts_after.setEndTime(filter_after);

        ClassroomModel cr = new ClassroomModel();
        cr.setID(1);
        cr.setName("Y1");
        cr.setLatitude(1.0);
        cr.setLongitude(1.0);


        ExamModel exam = new ExamModel();
        exam.setID(1);
        exam.setDescription("Appello 1");
        exam.setTimeslot(ts);
        exam.setClassroom(cr);
        exam.setSubject(sub);

        ExamFilterModel filter = new ExamFilterModel();
        filter.setProfessor(model);
        filter.setStartTime(ts_before);
        filter.setEndTime(ts_after);


        List<ExamModel> lista = new ArrayList<>();
        lista.add(exam);


        /*
                when(service.filterByTimeAndCourseOfStudy(any(ExamFilterModel.class))).thenReturn(lista);

        mockMvc.perform(post("/exam/daily").contentType(MediaType.APPLICATION_JSON_UTF8).content(CommonUtils.toJson(filter)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].id", Matchers.is(exam.getID())))
                .andExpect(jsonPath("$[0].classroom.name", Matchers.is(cr.getName())))
                .andExpect(jsonPath("$[0].subject.name", Matchers.is(sub.getName())))

        ;

        verify(service, times(1)).filterByTimeAndCourseOfStudy(examFilter.capture());
        verifyNoMoreInteractions(service);
         */


        when(service.filterByTimeAndProfessor(any(ExamFilterModel.class))).thenReturn(lista);

        mockMvc.perform(post("/exam/daily-professor").contentType(MediaType.APPLICATION_JSON_UTF8).content(CommonUtils.toJson(filter)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$[0].id", Matchers.is(exam.getID())))
                .andExpect(jsonPath("$[0].classroom.name", Matchers.is(cr.getName())))
                .andExpect(jsonPath("$[0].subject.name", Matchers.is(sub.getName())))

        ;

        verify(service, times(1)).filterByTimeAndProfessor(examFilter.capture());
        verifyNoMoreInteractions(service);


    }








    @Test
    public void saveExam() throws Exception {
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

        Date startDate = new Date();
        startDate.setTime(startDate.getTime() - 100);
        Date endTime = new Date();

        TimeSlotModel ts = new TimeSlotModel();
        ts.setID(1);
        ts.setStartTime(startDate);
        ts.setEndTime(endTime);

        ClassroomModel cr = new ClassroomModel();
        cr.setID(1);
        cr.setName("Y1");
        cr.setLatitude(1.0);
        cr.setLongitude(1.0);


        ExamModel exam = new ExamModel();
        exam.setID(1);
        exam.setDescription("Appello 1");
        exam.setTimeslot(ts);
        exam.setClassroom(cr);
        exam.setSubject(sub);

        when(service.saveExam(any(ExamModel.class))).thenReturn(exam);


        mockMvc.perform(
                post("/exam")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(TestUtils.toJson(exam)))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.id", Matchers.is(exam.getID())))
                .andExpect(jsonPath("$.classroom.name", Matchers.is(cr.getName())))
                .andExpect(jsonPath("$.subject.name", Matchers.is(sub.getName())))
        ;


        verify(service, times(1)).saveExam(savedExam.capture());
        verifyNoMoreInteractions(service);

    }

}
