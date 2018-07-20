package it.unisalento.se.common;

import it.unisalento.se.exceptions.EvaluationRecipientNotSupported;
import it.unisalento.se.exceptions.ScoreNotValidException;
import it.unisalento.se.models.*;

public class EvaluationBuilder {

    private static EvaluationBuilder instance;

    private Integer ID;
    private Integer score;
    private String note;
    private UserModel sender;
    private CourseOfStudyNode evaluatedNode;

    private EvaluationBuilder() {
    }

    public static EvaluationBuilder getInstance() {
        if (instance == null) {
            instance = new EvaluationBuilder();
        }
        return instance;
    }

    public EvaluationBuilder setID(Integer ID) {
        this.ID = ID;
        return this;
    }

    public EvaluationBuilder setScore(Integer score) throws ScoreNotValidException {
        if (score < 0 || score > 5) {
            throw new ScoreNotValidException("Score can be between 0 and 5");
        }
        this.score = score;
        return this;
    }

    public EvaluationBuilder setNote(String note) {
        this.note = note;
        return this;
    }

    public EvaluationBuilder setSender(UserModel user) {
        this.sender = user;
        return this;
    }

    public EvaluationBuilder setTarget(CourseOfStudyNode evaluatedTarget) throws EvaluationRecipientNotSupported {
        if (evaluatedTarget instanceof DocumentModel || evaluatedTarget instanceof LessonModel) {
            this.evaluatedNode = evaluatedTarget;
            return this;
        } else {
            throw new EvaluationRecipientNotSupported("Only documents and lessons can be evaluated");
        }

    }

    public EvaluationModel build() {
        EvaluationModel model = new EvaluationModel();
        model.setId(this.ID);
        model.setNote(this.note);
        model.setScore(this.score);
        model.setSender(this.sender);
        if (evaluatedNode instanceof DocumentModel) {
            model.setRecipientType(Constants.DOCUMENT);
            model.setRecipientD((DocumentModel) evaluatedNode);
        } else {
            model.setRecipientType(Constants.LESSON);
            model.setRecipientL((LessonModel) evaluatedNode);
        }
        return model;
    }


}
