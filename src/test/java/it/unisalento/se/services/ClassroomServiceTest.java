package it.unisalento.se.services;

import it.unisalento.se.dao.Classroom;
import it.unisalento.se.exceptions.ClassroomNotFoundException;
import it.unisalento.se.exceptions.EntityNotDeletableException;
import it.unisalento.se.models.ClassroomModel;
import it.unisalento.se.repositories.ClassroomRepository;
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
public class ClassroomServiceTest {


    @Mock
    private ClassroomRepository classroomRepository;
    @InjectMocks
    private ClassroomService classroomService;


    @Test
    public void createClassroom() {
        Classroom cr = new Classroom();
        cr.setId(1);
        cr.setName("Aula");
        cr.setLongitude(1.0);
        cr.setLatitude(1.0);

        ClassroomModel crm = new ClassroomModel();
        crm.setID(1);
        crm.setName("Aula");
        crm.setLongitude(1.0);
        crm.setLatitude(1.0);

        when(classroomRepository.save(any(Classroom.class))).thenReturn(cr);

        ClassroomModel model1 = classroomService.createClassroom(crm);
        assertEquals(new Integer(1), model1.getID());
        assertEquals(cr.getName(), model1.getName());
        assertEquals(1.0, model1.getLatitude(), 0);
        assertEquals(1.0, model1.getLongitude(), 0);

    }

    @Test
    public void getClassroomByID() throws ClassroomNotFoundException {


        Classroom cr = new Classroom();
        cr.setId(1);
        cr.setName("Y1");
        cr.setLatitude(1.0);
        cr.setLongitude(1.0);


        when(classroomRepository.getOne(1)).thenReturn(cr);

        ClassroomModel model = classroomService.getClassroomByID(1);

        assertEquals(new Integer(1), model.getID());
        assertEquals(cr.getName(), model.getName());
        assertEquals(1.0, model.getLatitude(), 0);
        assertEquals(1.0, model.getLongitude(), 0);

    }

    @Test
    public void getAllClassrooms() {

        Classroom cr = new Classroom();
        cr.setId(1);
        cr.setName("Y1");
        cr.setLatitude(1.0);
        cr.setLongitude(1.0);

        Classroom cr2 = new Classroom();
        cr2.setId(2);
        cr2.setName("Y2");
        cr2.setLatitude(2.0);
        cr2.setLongitude(2.0);


        List<Classroom> list = new ArrayList<>();
        list.add(cr);
        list.add(cr2);

        when(classroomRepository.findAll()).thenReturn(list);

        List<ClassroomModel> models = classroomService.getAllClassrooms();

        assertEquals(Integer.valueOf(1), models.get(0).getID());
        assertEquals("Y1", models.get(0).getName());
        assertEquals(1.0, models.get(0).getLatitude(), 0);
        assertEquals(1.0, models.get(0).getLongitude(), 0);
        assertEquals(Integer.valueOf(2), models.get(1).getID());
        assertEquals("Y2", models.get(1).getName());
        assertEquals(2.0, models.get(1).getLatitude(), 0);
        assertEquals(2.0, models.get(1).getLongitude(), 0);


    }

    @Test(expected = ClassroomNotFoundException.class)
    public void getClassroom_shouldFail() throws ClassroomNotFoundException {
        when(classroomRepository.getOne(10)).thenThrow(new EntityNotFoundException());

        ClassroomModel model = classroomService.getClassroomByID(10);
    }

    @Test(expected = ClassroomNotFoundException.class)
    public void deleteClassroom() throws ClassroomNotFoundException, EntityNotDeletableException {
        Classroom cr = new Classroom();
        cr.setId(1);
        cr.setName("Aula");
        cr.setLongitude(1.0);
        cr.setLatitude(1.0);

        ClassroomModel crm = new ClassroomModel();
        crm.setID(1);
        crm.setName("Aula");
        crm.setLongitude(1.0);
        crm.setLatitude(1.0);

        when(classroomRepository.save(any(Classroom.class))).thenReturn(cr);

        ClassroomModel model1 = classroomService.createClassroom(crm);
        classroomService.deleteClassroom(1);
        when(classroomRepository.getOne(1)).thenThrow(new EntityNotFoundException());
        ClassroomModel model = classroomService.getClassroomByID(1);


    }
}