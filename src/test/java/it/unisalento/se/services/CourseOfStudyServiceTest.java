package it.unisalento.se.services;

import it.unisalento.se.dao.AcademicYear;
import it.unisalento.se.dao.CourseOfStudy;
import it.unisalento.se.exceptions.CourseOfStudyNotFoundException;
import it.unisalento.se.models.AcademicYearModel;
import it.unisalento.se.models.CourseOfStudyModel;
import it.unisalento.se.repositories.CourseOfStudyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CourseOfStudyServiceTest {


    @Mock
    private CourseOfStudyRepository courseOfStudyRepository;
    @InjectMocks
    private CourseOfStudyService courseOfStudyService;


    @Test(expected = CourseOfStudyNotFoundException.class)
    public void getcos_shouldFail() throws CourseOfStudyNotFoundException {
        when(courseOfStudyRepository.getOne(10)).thenThrow(new EntityNotFoundException());

        CourseOfStudyModel model = courseOfStudyService.getCourseOfStudyByID(10);
    }


    @Test
    public void getCourseOfStudyByID() throws CourseOfStudyNotFoundException {

        AcademicYear ay = new AcademicYear();
        ay.setId(1);
        ay.setStartYear(2017);
        ay.setEndYear(2018);

        CourseOfStudy cos = new CourseOfStudy();
        cos.setId(1);
        cos.setAcademicYear(ay);
        cos.setName("Computer");


        when(courseOfStudyRepository.getOne(1)).thenReturn(cos);
        CourseOfStudyModel model = courseOfStudyService.getCourseOfStudyByID(1);
        assertEquals(new Integer(1), model.getID());
        assertEquals(cos.getName(), model.getName());
    }

    @Test
    public void saveCourseOfStudy() {


        AcademicYear ay = new AcademicYear();
        ay.setId(1);
        ay.setStartYear(2017);
        ay.setEndYear(2018);

        CourseOfStudy cos = new CourseOfStudy();
        cos.setId(1);
        cos.setAcademicYear(ay);
        cos.setName("Computer");


        AcademicYearModel ayM = new AcademicYearModel();
        ayM.setID(1);
        ayM.setStartYear(2017);
        ayM.setEndYear(2018);

        CourseOfStudyModel cosM = new CourseOfStudyModel();
        cosM.setID(1);
        cosM.setAcademicYear(ayM);
        cosM.setName("Computer");


        when(courseOfStudyRepository.save(any(CourseOfStudy.class))).thenReturn(cos);

        CourseOfStudyModel model1 = courseOfStudyService.saveCourseOfStudy(cosM);


        assertEquals(cos.getId(), model1.getID());
        assertEquals(cos.getName(), model1.getName());


    }

    @Test
    public void getAllCourses() {

        AcademicYear ay = new AcademicYear();
        ay.setId(1);
        ay.setStartYear(2017);
        ay.setEndYear(2018);

        CourseOfStudy cos = new CourseOfStudy();
        cos.setId(1);
        cos.setAcademicYear(ay);
        cos.setName("Computer");

        CourseOfStudy cos2 = new CourseOfStudy();
        cos2.setId(2);
        cos2.setAcademicYear(ay);
        cos2.setName("Matematica");


        List<CourseOfStudy> list = new ArrayList<>();
        list.add(cos);
        list.add(cos2);

        when(courseOfStudyRepository.findAll()).thenReturn(list);

        List<CourseOfStudyModel> model = courseOfStudyService.getAllCourses();

        assertEquals(Integer.valueOf(1), model.get(0).getID());
        assertEquals(cos.getName(), model.get(0).getName());
        assertEquals(ay.getId(), model.get(0).getAcademicYear().getID());
        assertEquals(Integer.valueOf(ay.getStartYear()), model.get(0).getAcademicYear().getStartYear());
        assertEquals(Integer.valueOf(ay.getEndYear()), model.get(0).getAcademicYear().getEndYear());

        assertEquals(cos2.getName(), model.get(1).getName());


    }
}