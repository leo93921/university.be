/*package it.unisalento.se.api.rest;

import it.unisalento.se.api.rest.LessonRestController;
import it.unisalento.se.common.Constants;
import it.unisalento.se.dao.Classroom;
import it.unisalento.se.dao.UserType;
import it.unisalento.se.exceptions.LessonNotFoundException;

import it.unisalento.se.iservices.ILessonService;
import it.unisalento.se.models.*;
import it.unisalento.se.test.utils.TestUtils;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LessonRestControllerTest {
    private MockMvc mockMvc;
    @Mock
    private ILessonService service;
    @InjectMocks
    private LessonRestController controller;

    @Captor
    private ArgumentCaptor<LessonModel> savedLesson;

    @Test
    public void getLessonByID_Fail() throws Exception {
        when(service.getLessonByID(any(Integer.class))).thenThrow(new LessonNotFoundException());

        mockMvc.perform(get("/lesson/{id}", 11))
                .andExpect(status().isNotFound());

        verify(service, times(1)).getLessonByID(11);
        verifyNoMoreInteractions(service);
    }



    @Test
    public void getLessonByID_OK() throws Exception {
        ClassroomModel classroomModel = new ClassroomModel();
        classroomModel.setId(1);
        classroomModel.setName("Y1");
        classroomModel.setLatitude(1.0);
        classroomModel.setLongitude(2.0);

        TimeSlotModel timeSlotModel = new TimeSlotModel();
        timeSlotModel.setID(1);
        Date dateStart = new GregorianCalendar(2018, 1, 1).getTime();
        Date dateEnd = new GregorianCalendar(2018, 1, 2).getTime();
        timeSlotModel.setStartTime(dateStart);
        timeSlotModel.setEndTime(dateEnd);


        AcademicYearModel academicYear = new AcademicYearModel();
        academicYear.setID(1);
        academicYear.setStartYear(2017);
        academicYear.setEndYear(2018);

        CourseOfStudyModel cs = new CourseOfStudyModel();
        cs.setName("Computer Engineering");
        cs.setID(25);
        cs.setAcademicYear(academicYear);


        UserModel userModel = new UserModel();
        userModel.setId(1);
        userModel.setEmail("mario.rossi@test.it");
        userModel.setName("Mario");
        userModel.setSurname("Rossi");
        userModel.setUserType(UserTypeModel.PROFESSOR);
        UserType type = new UserType();
        type.setName(Constants.PROFESSOR);
        type.setId(2);


        SubjectModel sub = new SubjectModel();
        sub.setID(24);
        sub.setCFU(8);
        sub.setName("A new name");
        sub.setTeachingYear(3);
        sub.setProfessor(userModel);
        sub.setCourseOfStudy(cs);

        LessonModel lesson = new LessonModel();
        lesson.setID(1);
        lesson.setTimeslot(timeSlotModel);
        lesson.setClassroom(classroomModel);
        lesson.setSubject(sub);

        when ( service.getLessonByID(any(Integer.class))).thenReturn(lesson);

        mockMvc.perform(get("/lesson/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", Matchers.is(sub.getID())))
                .andExpect(jsonPath("$.cfu", Matchers.is(sub.getCFU())))
                .andExpect(jsonPath("$.name", Matchers.is(sub.getName())))
                .andExpect(jsonPath("$.teachingYear", Matchers.is(sub.getTeachingYear())))
                .andExpect(jsonPath("$.courseOfStudy.id", Matchers.is(cs.getID())))
                .andExpect(jsonPath("$.courseOfStudy.name", Matchers.is(cs.getName())))
                .andExpect(jsonPath("$.courseOfStudy.academicYear.id", Matchers.is(academicYear.getID())))
                .andExpect(jsonPath("$.courseOfStudy.academicYear.startYear", Matchers.is(academicYear.getStartYear())))
                .andExpect(jsonPath("$.courseOfStudy.academicYear.endYear", Matchers.is(academicYear.getEndYear())));

        verify(service, times(1)).getLessonByID(1);
        verifyNoMoreInteractions(service);




    }


}
*/