package it.unisalento.se.services;

import it.unisalento.se.dao.AcademicYear;
import it.unisalento.se.dao.CourseOfStudy;
import it.unisalento.se.dao.User;
import it.unisalento.se.dao.UserType;
import it.unisalento.se.exceptions.UserNotFoundException;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.AcademicYearModel;
import it.unisalento.se.models.CourseOfStudyModel;
import it.unisalento.se.models.UserModel;
import it.unisalento.se.models.UserTypeModel;
import it.unisalento.se.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {


    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    @Test
    public void createUser() throws UserTypeNotSupported {


        UserType ut = new UserType();
        ut.setId(1);
        ut.setName("PROFESSOR");

        User u = new User();
        u.setId(1);
        u.setName("Filippo");
        u.setSurname("Emanuele");
        u.setEmail("emanuele.filippo@email.it");
        u.setUserType(ut);
        u.setPassword("ciaociaociao");



        UserModel um = new UserModel();
        um.setId(1);
        um.setName("Filippo");
        um.setSurname("Emanuele");
        um.setEmail("emanuele.filippo@email.it");
        um.setUserType(UserTypeModel.PROFESSOR);
        um.setPassword("ciaociaociao");


        when(userRepository.save(any(User.class))).thenReturn(u);

        UserModel model1 = userService.createUser(um);


        assertEquals(u.getId(), model1.getId());
        assertEquals(u.getName(), model1.getName());
        assertEquals(u.getSurname(), model1.getSurname());



    }

    @Test
    public void getUserByID() throws UserTypeNotSupported, UserNotFoundException {


        UserType ut = new UserType();
        ut.setId(1);
        ut.setName("PROFESSOR");

        User u = new User();
        u.setId(1);
        u.setName("Filippo");
        u.setSurname("Emanuele");
        u.setEmail("emanuele.filippo@email.it");
        u.setUserType(ut);
        u.setPassword("ciaociaociao");


        when(userRepository.getOne(1)).thenReturn(u);

        UserModel model = userService.getUserByID(1);
        assertEquals(new Integer(1), model.getId());
        assertEquals(u.getName(), model.getName());
        assertEquals(u.getSurname(), model.getSurname());


    }


    @Test
    public void getAllProfessors() throws UserTypeNotSupported {
        UserType ut = new UserType();
        ut.setId(1);
        ut.setName("PROFESSOR");

        User u = new User();
        u.setId(1);
        u.setName("Filippo");
        u.setSurname("Emanuele");
        u.setEmail("emanuele.filippo@email.it");
        u.setUserType(ut);
        u.setPassword("ciaociaociao");

        User u2 = new User();
        u2.setId(2);
        u2.setName("Filippa");
        u2.setSurname("Emanuela");
        u2.setEmail("emanuela.filippa@email.it");
        u2.setUserType(ut);
        u2.setPassword("ciaociaociao");


        List<User> list = new ArrayList<>();
        list.add(u);
        list.add(u2);

        when(userRepository.findByUserType(2)).thenReturn(list);

        List<UserModel> models = userService.getAllProfessors();
        assertEquals(new Integer(1), models.get(0).getId());
        assertEquals(u.getName(), models.get(0).getName());
        assertEquals(u.getSurname(), models.get(0).getSurname());
        assertEquals(u.getEmail(), models.get(0).getEmail());
        assertEquals(u.getUserType().getName(), models.get(0).getUserType().name());
        assertNull(models.get(0).getPassword());

        assertEquals(new Integer(2), models.get(1).getId());
        assertEquals(u2.getName(), models.get(1).getName());
        assertEquals(u2.getSurname(), models.get(1).getSurname());
        assertEquals(u2.getEmail(), models.get(1).getEmail());
        assertEquals(u2.getUserType().getName(), models.get(1).getUserType().name());
        assertNull(models.get(1).getPassword());

    }

    @Test
    public void getStudentsByCourseOfStudy() throws UserTypeNotSupported {
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


        UserType ut = new UserType();
        ut.setId(1);
        ut.setName("STUDENT");

        User u = getUser(cos, ut, 1, "Filippo", "Emanuele", "emanuele.filippo@email.it");


        User u2 = getUser(cos, ut, 2, "Filippa", "Emanuela", "emanuela.filippa@email.it");


        List<User> list = new ArrayList<>();
        list.add(u);
        list.add(u2);


        when(userRepository.findByCourseOfStudy(any(CourseOfStudy.class))).thenReturn(list);

        List<UserModel> model = userService.getStudentsByCourseOfStudy(cosM);

      assertEquals(new Integer(1), model.get(0).getId());
        assertEquals(u.getName(), model.get(0).getName());
        assertEquals(u2.getSurname(), model.get(1).getSurname());


    }

    private User getUser(CourseOfStudy cos, UserType ut, int i, String filippa, String emanuela, String s) {
        User u2 = new User();
        u2.setId(i);
        u2.setName(filippa);
        u2.setSurname(emanuela);
        u2.setEmail(s);
        u2.setUserType(ut);
        u2.setPassword("ciaociaociao");
        u2.setCourseOfStudy(cos);
        return u2;
    }


}