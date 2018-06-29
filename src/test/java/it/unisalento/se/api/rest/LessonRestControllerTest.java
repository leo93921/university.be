package it.unisalento.se.api.rest;

import it.unisalento.se.common.Constants;
import it.unisalento.se.dao.Classroom;
import it.unisalento.se.dao.UserType;
import it.unisalento.se.exceptions.LessonNotFoundException;
import it.unisalento.se.exceptions.SubjectNotFoundException;
import it.unisalento.se.iservices.ILessonService;
import it.unisalento.se.iservices.ISubjectService;
import it.unisalento.se.models.*;
import it.unisalento.se.test.utils.TestUtils;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class LessonRestControllerTest {


    private MockMvc mockMvc;
    @Mock
    private ILessonService service;
    @InjectMocks
    private LessonRestController controller;

    @Captor
    private ArgumentCaptor<LessonModel> savedLesson;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = TestUtils.getMockMvc(controller);
    }


    @Test
    public void getLessonByID_Fail() throws Exception {
        when(service.getLessonByID(any(Integer.class))).thenThrow(new LessonNotFoundException());

        mockMvc.perform(get("/lesson/{id}", 19))
                .andExpect(status().isNotFound());

        verify(service, times(1)).getLessonByID(19);
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


        LessonModel l = new LessonModel();
        l.setID(1);
        l.setClassroom(cr);
        l.setTimeSlot(ts);
        l.setSubject(sub);

        when(service.getLessonByID(any(Integer.class))).thenReturn(l);


        mockMvc.perform(get("/lesson/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", Matchers.is(l.getID())))
                .andExpect(jsonPath("$.classroom.name", Matchers.is(cr.getName())))
                .andExpect(jsonPath("$.subject.name", Matchers.is(sub.getName())))

        ;


        verify(service, times(1)).getLessonByID(1);
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


        LessonModel l = new LessonModel();
        l.setID(1);
        l.setClassroom(cr);
        l.setTimeSlot(ts);
        l.setSubject(sub);


        when(service.saveLesson(any(LessonModel.class))).thenReturn(l);

        mockMvc.perform(
                post("/lesson")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(TestUtils.toJson(l)))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.id", Matchers.is(l.getID())))
                .andExpect(jsonPath("$.classroom.name", Matchers.is(cr.getName())))
                .andExpect(jsonPath("$.subject.name", Matchers.is(sub.getName())))
                ;


        verify(service, times(1)).saveLesson(savedLesson.capture());
        verifyNoMoreInteractions(service);

    }


}