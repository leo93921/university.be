package it.unisalento.se.models;

import it.unisalento.se.dao.Document;
import it.unisalento.se.dao.Lesson;
import it.unisalento.se.dao.User;

public class EvaluationFilterModel {

    private User user;
    private Document document;
    private Lesson lesson;
    private String objectType;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }
}
