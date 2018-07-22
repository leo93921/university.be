package it.unisalento.se.services;

import it.unisalento.se.common.Constants;
import it.unisalento.se.dao.*;
import it.unisalento.se.dto.DocumentDto;
import it.unisalento.se.dto.EvaluationDto;
import it.unisalento.se.exceptions.EvaluationNotFoundException;
import it.unisalento.se.exceptions.EvaluationRecipientNotSupported;
import it.unisalento.se.exceptions.ScoreNotValidException;
import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.*;
import it.unisalento.se.repositories.DocumentEvaluationRepository;
import it.unisalento.se.repositories.LessonEvaluationRepository;
import it.unisalento.se.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EvaluationServiceTest {

    @Mock
    private LessonEvaluationRepository lessonEvaluationRepository;
    @Mock
    private DocumentEvaluationRepository documentEvaluationRepository;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private EvaluationService evaluationService;
    @Mock
    private UserService userService;
    @Mock
    private FcmService fcmService;

    @Test
    public void getLessonEvaluationbyID() throws EvaluationRecipientNotSupported, UserTypeNotSupported, EvaluationNotFoundException, ScoreNotValidException {

        AcademicYear ay = getAcademicYear();
        CourseOfStudy cs = getCourseOfStudy(ay);
        UserType ut = getUserType(2, Constants.PROFESSOR);
        User u = getUser(ut);
        UserType ut2 = getUserType(1, Constants.STUDENT);
        User u2 = getStudent(ut2);
        Subject s = getSubject(cs, u);
        Timeslot ts = getTimeslot();
        Classroom cr = getClassroom();
        Lesson l = getLesson(s, ts, cr);
        LessonEvaluation le = getLessonEvaluation(u2, l);

        when(lessonEvaluationRepository.getOne(1)).thenReturn(le);
        EvaluationModel model = evaluationService.getLessonEvaluationbyID(1);
        assertEquals(new Integer(1), model.getID());
        assertEquals(new Integer(1), model.getScore());


    }

    private Timeslot getTimeslot() {
        Date startDate = new Date();
        startDate.setTime(startDate.getTime() - 100);
        Date endDate = new Date();
        Timeslot ts = new Timeslot();
        ts.setId(1);
        ts.setStartTime(startDate);
        ts.setEndTime(endDate);
        return ts;
    }

    private LessonEvaluation getLessonEvaluation(User u2, Lesson l) {
        LessonEvaluation le = new LessonEvaluation();
        le.setId(1);
        le.setLesson(l);
        le.setNote("Bello");
        le.setScore(1);
        le.setUser(u2);
        return le;
    }

    private Lesson getLesson(Subject s, Timeslot ts, Classroom cr) {
        Lesson l = new Lesson();
        l.setId(1);
        l.setClassroom(cr);
        l.setTimeslot(ts);
        l.setSubject(s);
        return l;
    }

    private Classroom getClassroom() {
        Classroom cr = new Classroom();
        cr.setId(1);
        cr.setName("Y1");
        cr.setLatitude(1.0);
        cr.setLongitude(1.0);
        return cr;
    }

    private Subject getSubject(CourseOfStudy cs, User u) {
        Subject s = new Subject();
        s.setId(1);
        s.setName("Software");
        s.setCfu(12);
        s.setUser(u);
        s.setCourseOfStudy(cs);
        s.setYear(2018);
        return s;
    }

    private User getUser(UserType ut) {
        User u = new User();
        u.setId(1);
        u.setName("Mario");
        u.setSurname("Giallo");
        u.setEmail("mario.giallo@email.it");
        u.setUserType(ut);
        u.setPassword("ciaociaociao");
        return u;
    }

    private UserType getUserType(Integer ID, String name) {
        UserType ut = new UserType();
        ut.setId(2);
        ut.setName("PROFESSOR");
        return ut;
    }

    private CourseOfStudy getCourseOfStudy(AcademicYear ay) {
        CourseOfStudy cs = new CourseOfStudy();
        cs.setId(1);
        cs.setName("Engineering");
        cs.setAcademicYear(ay);
        return cs;
    }

    private AcademicYear getAcademicYear() {
        AcademicYear ay = new AcademicYear();
        ay.setId(1);
        ay.setStartYear(2017);
        ay.setEndYear(2018);
        return ay;
    }

    @Test
    public void getDocumentEvaluationbyID() throws EvaluationRecipientNotSupported, UserTypeNotSupported, EvaluationNotFoundException, ScoreNotValidException {


        AcademicYear ay = getAcademicYear();
        CourseOfStudy cs = getCourseOfStudy(ay);
        UserType ut = getUserType(2, Constants.PROFESSOR);
        User u = getUser(ut);
        UserType ut2 = getUserType(1, Constants.STUDENT);
        User u2 = getStudent(ut2);
        Subject s = getSubject(cs, u);
        Timeslot ts = getTimeslot();
        Classroom cr = getClassroom();
        Lesson l = getLesson(s, ts, cr);
        Document d = getDocument(l);
        DocumentEvaluation de = getDocumentEvaluation(u2, d);

        when(documentEvaluationRepository.getOne(any(Integer.class))).thenReturn(de);
        EvaluationModel model = evaluationService.getDocumentEvaluationbyID(1);
        assertEquals(new Integer(1), model.getID());
        assertEquals(new Integer(1), model.getScore());

    }

    private DocumentEvaluation getDocumentEvaluation(User u2, Document d) {
        DocumentEvaluation de = new DocumentEvaluation();
        de.setId(1);
        de.setDocument(d);
        de.setNote("Bello");
        de.setScore(1);
        de.setUser(u2);
        return de;
    }

    private Document getDocument(Lesson l) {
        Document d = new Document();
        d.setId(1);
        d.setLesson(l);
        d.setLink("link");
        d.setName("Nome");
        d.setNote("Nota di prova");
        d.setPublishDate(new Date());
        return d;
    }

    @Test
    public void createDocumentEvaluation() throws ScoreNotValidException, EvaluationRecipientNotSupported, UserTypeNotSupported {

        AcademicYear ay = getAcademicYear();
        CourseOfStudy cs = getCourseOfStudy(ay);
        UserType ut = getUserType(2, Constants.PROFESSOR);
        User u = getUser(ut);
        UserType ut2 = getUserType(1, Constants.STUDENT);
        User u2 = getStudent(ut2);
        Subject s = getSubject(cs, u);
        Timeslot ts = getTimeslot();
        Classroom cr = getClassroom();
        Lesson l = getLesson(s, ts, cr);
        Document d = getDocument(l);
        DocumentEvaluation de = getDocumentEvaluation(u2, d);


        AcademicYearModel aym = getAcademicYearModel();
        CourseOfStudyModel csm = getCourseOfStudyModel(aym);
        UserModel um = getUserModel(1, "Mario", "Giallo", "mario.giallo@email.it", UserTypeModel.PROFESSOR, "ciaociaociao");
        UserModel u2m = getUserModel(2, "Luigi", "Marrone", "marrone.luigi@lot.it", UserTypeModel.STUDENT, "adios");
        SubjectModel sm = getSubjectModel(csm, um);
        Date startDate = new Date();
        startDate.setTime(startDate.getTime() - 100);
        Date endDatem = new Date();

        TimeSlotModel tsm = new TimeSlotModel();
        tsm.setID(1);
        tsm.setStartTime(startDate);
        tsm.setEndTime(endDatem);

        ClassroomModel crm = getClassroomModel();
        LessonModel lm = getLessonModel(sm, tsm, crm);
        DocumentDto dmdto = getDocumentDto(startDate, lm);
        EvaluationDto dedto = getEvaluationDto(u2m, dmdto);


        // Save a document
        when(userService.getFCMToken(any(UserModel.class))).thenReturn("1234");
        when(documentEvaluationRepository.save(any(DocumentEvaluation.class))).thenReturn(de);

        EvaluationModel model = evaluationService.createEvaluation(dedto);

        assertEquals(dedto.getId(), model.getID());
        assertEquals(dedto.getScore(), model.getScore());
        assertEquals(dedto.getNote(), model.getNote());
        assertEquals(dedto.getSender().getId(), model.getSender().getId());
        assertEquals(Constants.DOCUMENT, model.getRecipientType());
        assertThat(model.getRecipient(), instanceOf(DocumentModel.class));

        DocumentModel documentModel = (DocumentModel) model.getRecipient();
        assertEquals(de.getDocument().getName(), documentModel.getName());
        assertEquals(de.getDocument().getLink(), documentModel.getLink());
        assertEquals(de.getDocument().getPublishDate(), documentModel.getPublishDate());
        assertEquals(de.getDocument().getNote(), documentModel.getNote());
        assertEquals(de.getDocument().getId(), documentModel.getID());

    }

    @Test
    public void createLessonEvaluation() throws ScoreNotValidException, EvaluationRecipientNotSupported, UserTypeNotSupported {
        AcademicYear ay = getAcademicYear();
        CourseOfStudy cs = getCourseOfStudy(ay);
        UserType ut = getUserType(2, Constants.PROFESSOR);
        User u = getUser(ut);
        UserType ut2 = getUserType(1, Constants.STUDENT);
        User u2 = getStudent(ut2);
        Subject s = getSubject(cs, u);
        Timeslot ts = getTimeslot();
        Classroom cr = getClassroom();
        Lesson l = getLesson(s, ts, cr);


        when(userService.getFCMToken(any(UserModel.class))).thenReturn("1234");
        LessonEvaluation lessonEvaluation = new LessonEvaluation(l, u2, 3);
        lessonEvaluation.setId(12);
        when(lessonEvaluationRepository.save(any(LessonEvaluation.class))).thenReturn(lessonEvaluation);

        EvaluationDto evalDto = new EvaluationDto();
        evalDto.setId(12);
        evalDto.setNote("A note");
        evalDto.setRecipientL(getLessonModel(getSubjectModel(getCourseOfStudyModel(getAcademicYearModel()), getUserModel(21, "Mario", "Rossi", "m.r@test.it", UserTypeModel.PROFESSOR, "ciao")), new TimeSlotModel(), getClassroomModel()));
        evalDto.setRecipientType(Constants.LESSON);
        evalDto.setScore(4);
        evalDto.setSender(getUserModel(21, "Mario", "Rossi", "m.r@test.it", UserTypeModel.STUDENT, "ciao"));
        EvaluationModel model = evaluationService.createEvaluation(evalDto);

        assertEquals(evalDto.getId(), model.getID());
    }

    private EvaluationDto getEvaluationDto(UserModel u2m, DocumentDto dmdto) {
        EvaluationDto dedto = new EvaluationDto();
        dedto.setId(1);
        dedto.setRecipientD(dmdto);
        dedto.setNote("Bello");
        dedto.setScore(1);
        dedto.setSender(u2m);
        dedto.setRecipientType("DOCUMENT");
        return dedto;
    }

    private DocumentDto getDocumentDto(Date startDate, LessonModel lm) {
        DocumentDto dmdto = new DocumentDto();
        dmdto.setID(1);
        dmdto.setLesson(lm);
        dmdto.setLink("link");
        dmdto.setName("Nome");
        dmdto.setNote("Nota di prova");
        dmdto.setPublishDate(startDate);
        return dmdto;
    }

    private LessonModel getLessonModel(SubjectModel sm, TimeSlotModel tsm, ClassroomModel crm) {
        LessonModel lm = new LessonModel();
        lm.setID(1);
        lm.setClassroom(crm);
        lm.setTimeSlot(tsm);
        lm.setSubject(sm);
        return lm;
    }

    private ClassroomModel getClassroomModel() {
        ClassroomModel crm = new ClassroomModel();
        crm.setID(1);
        crm.setName("Y1");
        crm.setLatitude(1.0);
        crm.setLongitude(1.0);
        return crm;
    }

    private SubjectModel getSubjectModel(CourseOfStudyModel csm, UserModel um) {
        SubjectModel sm = new SubjectModel();
        sm.setID(1);
        sm.setName("Software");
        sm.setCFU(12);
        sm.setProfessor(um);
        sm.setCourseOfStudy(csm);
        sm.setTeachingYear(2018);
        return sm;
    }

    private UserModel getUserModel(Integer id, String name, String surname, String email, UserTypeModel userType, String password) {
        UserModel um = new UserModel();
        um.setId(id);
        um.setName(name);
        um.setSurname(surname);
        um.setEmail(email);
        um.setUserType(userType);
        um.setPassword(password);
        return um;
    }

    private CourseOfStudyModel getCourseOfStudyModel(AcademicYearModel aym) {
        CourseOfStudyModel csm = new CourseOfStudyModel();
        csm.setID(1);
        csm.setName("Engineering");
        csm.setAcademicYear(aym);
        return csm;
    }

    private AcademicYearModel getAcademicYearModel() {
        AcademicYearModel aym = new AcademicYearModel();
        aym.setID(1);
        aym.setStartYear(2017);
        aym.setEndYear(2018);
        return aym;
    }

    private User getStudent(UserType ut2) {
        User u2 = new User();
        u2.setId(2);
        u2.setName("Luigi");
        u2.setSurname("Marrone");
        u2.setEmail("marrone.luigi@lot.it");
        u2.setUserType(ut2);
        u2.setPassword("adios");
        return u2;
    }
}