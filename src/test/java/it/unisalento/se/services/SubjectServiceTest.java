package it.unisalento.se.services;

import it.unisalento.se.dao.*;
import it.unisalento.se.exceptions.SubjectNotFoundException;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.CourseOfStudyModel;
import it.unisalento.se.models.SubjectModel;
import it.unisalento.se.models.UserModel;
import it.unisalento.se.models.UserTypeModel;
import it.unisalento.se.repositories.SubjectRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SubjectServiceTest {

    @Mock
    private SubjectRepository subjectRepository;
    @InjectMocks
    private SubjectService service;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getSubjectByID_OK() throws UserTypeNotSupported, SubjectNotFoundException {
        Subject subject = getSubject();

        when(subjectRepository.getOne(any(Integer.class))).thenReturn(subject);

        SubjectModel returned = service.getSubjectByID(2);

        assertEquals(Integer.valueOf(2), returned.getID());
        assertEquals(subject.getName(), returned.getName());
        assertEquals(Integer.valueOf(subject.getCfu()), returned.getCFU());
        assertEquals(Integer.valueOf(subject.getYear()), returned.getTeachingYear());
    }

    private Subject getSubject() {
        Subject subject = new Subject();
        subject.setId(2);
        subject.setName("Subject");
        subject.setCfu(34);
        subject.setYear(3);
        CourseOfStudy courseOfStudy = new CourseOfStudy();
        courseOfStudy.setAcademicYear(new AcademicYear(2017, 2018));
        subject.setCourseOfStudy(courseOfStudy);
        User user = new User();
        user.setUserType(new UserType("PROFESSOR"));
        subject.setUser(user);
        return subject;
    }

    @Test(expected = SubjectNotFoundException.class)
    public void getSubjectByID_KO() throws UserTypeNotSupported, SubjectNotFoundException {
        when(subjectRepository.getOne(any(Integer.class))).thenThrow(new EntityNotFoundException());
        service.getSubjectByID(2);
    }

    @Test(expected = UserTypeNotSupported.class)
    public void saveSubject_Fail() throws UserTypeNotSupported {
        SubjectModel model = new SubjectModel();
        UserModel professor = new UserModel();
        professor.setUserType(UserTypeModel.STUDENT);
        model.setProfessor(professor);
        service.saveSubject(model);
    }


    @Test
    public void getAllSubjectsByCourseOfStudy() throws UserTypeNotSupported {
        List<Subject> daos = getSubjects();

        when(subjectRepository.findByCourseOfStudy(any(Integer.class))).thenReturn(daos);

        CourseOfStudyModel courseOfStudyModel = new CourseOfStudyModel();
        courseOfStudyModel.setID(23);
        List<SubjectModel> returned = service.getAllSubjectsByCourseOfStudy(courseOfStudyModel);

        assertEquals(daos.get(0).getId(), returned.get(0).getID());
        assertEquals(daos.get(0).getName(), returned.get(0).getName());
        assertEquals(Integer.valueOf(daos.get(0).getCfu()), returned.get(0).getCFU());
        assertEquals(Integer.valueOf(daos.get(0).getYear()), returned.get(0).getTeachingYear());
        assertEquals(daos.get(1).getId(), returned.get(1).getID());
        assertEquals(daos.get(1).getName(), returned.get(1).getName());
        assertEquals(Integer.valueOf(daos.get(1).getCfu()), returned.get(1).getCFU());
        assertEquals(Integer.valueOf(daos.get(1).getYear()), returned.get(1).getTeachingYear());
    }

    private List<Subject> getSubjects() {
        List<Subject> daos = new ArrayList<>();
        daos.add(getSubject());
        Subject subject2 = getSubject();
        subject2.setId(3);
        subject2.setCfu(2);
        subject2.setYear(5);
        subject2.setName("An other string");
        daos.add(subject2);
        return daos;
    }

    @Test
    public void getAllSubjectsByProfessor() throws UserTypeNotSupported {
        List<Subject> daos = getSubjects();

        when(subjectRepository.findByProfessor(any(Integer.class))).thenReturn(daos);

        UserModel prof = new UserModel();
        prof.setId(2);
        List<SubjectModel> returned = service.getAllSubjectsByProfessor(prof);

        assertEquals(daos.get(0).getId(), returned.get(0).getID());
        assertEquals(daos.get(0).getName(), returned.get(0).getName());
        assertEquals(Integer.valueOf(daos.get(0).getCfu()), returned.get(0).getCFU());
        assertEquals(Integer.valueOf(daos.get(0).getYear()), returned.get(0).getTeachingYear());
        assertEquals(daos.get(1).getId(), returned.get(1).getID());
        assertEquals(daos.get(1).getName(), returned.get(1).getName());
        assertEquals(Integer.valueOf(daos.get(1).getCfu()), returned.get(1).getCFU());
        assertEquals(Integer.valueOf(daos.get(1).getYear()), returned.get(1).getTeachingYear());
    }
}