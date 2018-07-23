package it.unisalento.se.services;

import it.unisalento.se.common.Constants;
import it.unisalento.se.dao.*;
import it.unisalento.se.exceptions.*;
import it.unisalento.se.models.*;
import it.unisalento.se.repositories.DocumentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DocumentServiceTest {

    @Mock
    private DocumentRepository repository;
    @InjectMocks
    private DocumentService service;
    @Mock
    private StorageService storageService;
    @Mock
    private LessonService lessonService;
    @Mock
    private FcmService fcmService;

    @Test
    public void getDocumentByID() throws DocumentNotFoundException, UserTypeNotSupported {
        Document dao = getDocument();

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
        Document saved = getDocument();

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

        UserModel professor = getUserModel();
        UserType type = new UserType();
        type.setName(Constants.PROFESSOR);
        type.setId(3);

        CourseOfStudyModel cs = getCourseOfStudyModel();


        LessonModel lesson1 = getLessonModel(professor, cs);

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

    private CourseOfStudyModel getCourseOfStudyModel() {
        AcademicYearModel academicYear = new AcademicYearModel();
        academicYear.setID(1);
        academicYear.setStartYear(2017);
        academicYear.setEndYear(2018);

        CourseOfStudyModel cs = new CourseOfStudyModel();
        cs.setName("Computer Engineering");
        cs.setID(25);
        cs.setAcademicYear(academicYear);
        return cs;
    }

    private UserModel getUserModel() {
        UserModel professor = new UserModel();
        professor.setId(1);
        professor.setEmail("mario.rossi@test.it");
        professor.setName("Mario");
        professor.setSurname("Rossi");
        professor.setUserType(UserTypeModel.PROFESSOR);
        return professor;
    }

    private LessonModel getLessonModel(UserModel professor, CourseOfStudyModel cs) {
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
        return lesson1;
    }

    private Document getDocument() {
        Document saved = new Document();
        saved.setId(1);
        saved.setNote("A note");
        saved.setName("A name");
        saved.setPublishDate(new Date());
        saved.setLink("A new linlk");
        saved.setLesson(getLesson());
        return saved;
    }

    private Lesson getLesson() {
        Lesson lesson = new Lesson();
        Subject subject = new Subject();
        subject.setName("Subject");
        subject.setId(5);
        User user = new User();
        user.setUserType(new UserType("PROFESSOR"));
        subject.setUser(user);
        CourseOfStudy courseOfStudy = new CourseOfStudy();
        courseOfStudy.setAcademicYear(new AcademicYear(2018, 2019));
        subject.setCourseOfStudy(courseOfStudy);
        lesson.setSubject(subject);
        Classroom classroom = new Classroom();
        classroom.setLatitude(1.4);
        classroom.setLongitude(1.3);
        lesson.setClassroom(classroom);
        lesson.setTimeslot(new Timeslot());
        return lesson;
    }

    @Test
    public void deleteDocument_OK() throws EntityNotDeletableException {

        when(repository.getOne(any(Integer.class))).thenReturn(getDocument());
        when(storageService.removeFile(any(String.class))).thenReturn(true);

        Boolean res = service.deleteDocument(1);
        assertTrue(res);
    }

    @Test
    public void saveDocument_OK() throws StorageException, LessonNotFoundException, NodeNotSupportedException, UserTypeNotSupported, ParseException {
        when(storageService.store(any(MultipartFile.class))).thenReturn("file_path");
        when(repository.save(any(Document.class))).thenReturn(getDocument());
        LessonModel lesson = new LessonModel();
        lesson.setClassroom(new ClassroomModel());
        lesson.setTimeSlot(new TimeSlotModel());
        SubjectModel subject = new SubjectModel();
        subject.setCFU(2);
        subject.setTeachingYear(2);
        lesson.setSubject(subject);
        UserModel professor = new UserModel();
        professor.setUserType(UserTypeModel.PROFESSOR);
        subject.setProfessor(professor);
        CourseOfStudyModel courseOfStudy = new CourseOfStudyModel();
        AcademicYearModel academicYear = new AcademicYearModel();
        academicYear.setStartYear(3);
        academicYear.setEndYear(4);
        academicYear.setID(3);
        courseOfStudy.setAcademicYear(academicYear);
        subject.setCourseOfStudy(courseOfStudy);
        when(lessonService.getLessonByID(any(Integer.class))).thenReturn(lesson);

        DocumentModel model = service.saveDocument(new MockMultipartFile("fileContent", "hi".getBytes()), "A name", "Note", "2018-02-03T12:32:12.111Z", "2");

        assertEquals("A name", model.getName());
    }

    @Test
    public void getDocumentsByLesson() throws UserTypeNotSupported {
        ArrayList<Document> documents = new ArrayList<>();
        Document document = getDocument();
        documents.add(document);
        when(repository.findByLesson(any(Lesson.class))).thenReturn(documents);

        List<DocumentModel> documentsByLesson = service.getDocumentsByLesson(getLessonModel(getUserModel(), getCourseOfStudyModel()));

        assertNotEquals(0, documentsByLesson.size());
        assertEquals(document.getId(), documentsByLesson.get(0).getID());
        assertEquals(document.getName(), documentsByLesson.get(0).getName());
        assertEquals(document.getNote(), documentsByLesson.get(0).getNote());
        assertEquals(document.getPublishDate(), documentsByLesson.get(0).getPublishDate());
    }
}