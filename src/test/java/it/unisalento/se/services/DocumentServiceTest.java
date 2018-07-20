package it.unisalento.se.services;

import it.unisalento.se.common.Constants;
import it.unisalento.se.dao.*;
import it.unisalento.se.exceptions.DocumentNotFoundException;
import it.unisalento.se.exceptions.NodeNotSupportedException;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.*;
import it.unisalento.se.repositories.DocumentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DocumentServiceTest {

    @Mock
    private DocumentRepository repository;
    @InjectMocks
    private DocumentService service;

    @Test
    public void getDocumentByID() throws DocumentNotFoundException, UserTypeNotSupported {
        Document dao = new Document();
        dao.setId(1);
        dao.setNote("A note");
        dao.setName("A name");
        dao.setPublishDate(new Date());
        dao.setLink("A new linlk");

        Lesson lesson = new Lesson(
                new Classroom("01", 1.1, 2.3, null, null, null, null),
                new Subject(
                        new CourseOfStudy(
                                new AcademicYear(2017, 2018),
                                "Computer Engineering"
                        ),
                        new User(
                                new UserType(Constants.PROFESSOR),
                                "Mario",
                                "Rossi",
                                "mario.rossi@example.it",
                                "password"
                        ),
                        "Software Engineering",
                        9,
                        1
                ),
                new Timeslot(new Date(), new Date())
        );
        dao.setLesson(lesson);

        when(repository.getOne(any(Integer.class))).thenReturn(dao);

        DocumentModel model = service.getDocumentByID(1);

        assertEquals(dao.getId(), model.getID());
        assertEquals(dao.getName(), model.getName());
        assertEquals(dao.getNote(), model.getNote());
        assertEquals(dao.getPublishDate(), model.getPublishDate());
    }

    @Test(expected = DocumentNotFoundException.class)
    public void getDocumentByID_shouldFail() throws DocumentNotFoundException, UserTypeNotSupported {

        when(repository.getOne(any(Integer.class))).thenThrow(new EntityNotFoundException());

        DocumentModel model = service.getDocumentByID(1);

    }

    @Test
    public void saveDocument() throws UserTypeNotSupported, NodeNotSupportedException {
        Document saved = new Document();
        saved.setId(1);
        saved.setNote("A note");
        saved.setName("A name");
        saved.setPublishDate(new Date());
        saved.setLink("A new linlk");

        Classroom classroom = new Classroom("01", 1.1, 2.3, null, null, null, null);
        classroom.setId(2);
        Subject subject = new Subject(
                new CourseOfStudy(
                        new AcademicYear(2017, 2018),
                        "Computer Engineering"
                ),
                new User(
                        new UserType(Constants.PROFESSOR),
                        "Mario",
                        "Rossi",
                        "mario.rossi@example.it",
                        "password"
                ),
                "Software Engineering",
                9,
                1
        );
        subject.setId(23);
        Lesson lesson = new Lesson(
                classroom,
                subject,
                new Timeslot(new Date(), new Date())
        );
        lesson.setId(24);
        saved.setLesson(lesson);

        when(repository.save(any(Document.class))).thenReturn(saved);

        UserModel professor = new UserModel();
        professor.setId(1);
        professor.setEmail("mario.rossi@test.it");
        professor.setName("Mario");
        professor.setSurname("Rossi");
        professor.setUserType(UserTypeModel.PROFESSOR);
        UserType type = new UserType();
        type.setName(Constants.PROFESSOR);
        type.setId(3);

        AcademicYearModel academicYear = new AcademicYearModel();
        academicYear.setID(1);
        academicYear.setStartYear(2017);
        academicYear.setEndYear(2018);

        CourseOfStudyModel cs = new CourseOfStudyModel();
        cs.setName("Computer Engineering");
        cs.setID(25);
        cs.setAcademicYear(academicYear);


        LessonModel lesson1 = new LessonModel();
        lesson1.setID(1);
        lesson1.setTimeSlot(new TimeSlotModel());
        SubjectModel subject1 = new SubjectModel();
        subject1.setID(1);

        subject1.setProfessor(professor);
        subject1.setCourseOfStudy(cs);
        subject1.setCFU(8);
        subject1.setTeachingYear(1);
        lesson1.setSubject(subject1);
        lesson1.setClassroom(new ClassroomModel());

        DocumentModel model = new DocumentModel();
        model.setPublishDate(new Date());
        model.setNote("A note");
        model.setName("A name");
        model.setID(12);
        model.setLink("a.link");
        model.setLesson(lesson1);

        DocumentModel model1 = service.saveDocument(model);

        assertEquals(saved.getId(), model1.getID());
        assertEquals(saved.getName(), model1.getName());
        assertEquals(saved.getNote(), model1.getNote());
        assertEquals(saved.getPublishDate(), model1.getPublishDate());
    }
}