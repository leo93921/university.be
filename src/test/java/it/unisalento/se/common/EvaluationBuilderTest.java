package it.unisalento.se.common;

import it.unisalento.se.exceptions.EvaluationRecipientNotSupported;
import it.unisalento.se.exceptions.ScoreNotValidException;
import it.unisalento.se.models.*;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class EvaluationBuilderTest {

    @Test
    public void convertLesson_OK() throws ScoreNotValidException, EvaluationRecipientNotSupported {
        LessonModel lessonModel = getLessonModel();

        UserModel user = getUserModel();

        EvaluationModel model = EvaluationBuilder.getInstance()
                .setID(1)
                .setNote("A note")
                .setScore(3)
                .setSender(user)
                .setTarget(lessonModel)
                .build();

        assertEquals(Integer.valueOf(1), model.getID());
        assertEquals("A note", model.getNote());
        assertEquals(Integer.valueOf(3), model.getScore());
        assertEquals(user.getId(), model.getSender().getId());
        assertEquals(user.getName(), model.getSender().getName());
        assertThat(model.getRecipient(), instanceOf(LessonModel.class));
        assertEquals(lessonModel.getID(), model.getRecipient().getID());
        assertEquals(Constants.LESSON, model.getRecipientType());
    }

    private LessonModel getLessonModel() {
        LessonModel lessonModel = new LessonModel();
        lessonModel.setID(1);
        return lessonModel;
    }

    private UserModel getUserModel() {
        UserModel user = new UserModel();
        user.setName("Mario");
        user.setUserType(UserTypeModel.STUDENT);
        user.setId(3);
        return user;
    }

    @Test(expected = ScoreNotValidException.class)
    public void convertLesson_shouldFail() throws ScoreNotValidException, EvaluationRecipientNotSupported {
        LessonModel lessonModel = getLessonModel();
        UserModel user = getUserModel();

        EvaluationModel model = EvaluationBuilder.getInstance()
                .setID(1)
                .setNote("A note")
                .setScore(14)
                .setSender(user)
                .setTarget(lessonModel)
                .build();
    }

    @Test
    public void convertDocument_OK() throws ScoreNotValidException, EvaluationRecipientNotSupported {
        DocumentModel documentModel = new DocumentModel();
        documentModel.setID(10);
        documentModel.setLesson(getLessonModel());
        UserModel user = getUserModel();

        EvaluationModel model = EvaluationBuilder.getInstance()
                .setID(1)
                .setNote("A note")
                .setScore(3)
                .setSender(user)
                .setTarget(documentModel)
                .build();

        assertEquals(Integer.valueOf(1), model.getID());
        assertEquals("A note", model.getNote());
        assertEquals(Integer.valueOf(3), model.getScore());
        assertEquals(user.getId(), model.getSender().getId());
        assertEquals(user.getName(), model.getSender().getName());
        assertThat(model.getRecipient(), instanceOf(DocumentModel.class));
        assertEquals(documentModel.getID(), model.getRecipient().getID());
        assertEquals(Constants.DOCUMENT, model.getRecipientType());
    }

    @Test(expected = EvaluationRecipientNotSupported.class)
    public void convert_RecipientNotValid() throws ScoreNotValidException, EvaluationRecipientNotSupported {
        EvaluationModel model = EvaluationBuilder.getInstance()
                .setID(1)
                .setNote("A note")
                .setScore(3)
                .setSender(getUserModel())
                .setTarget(new CourseOfStudyModel())
                .build();
    }

}