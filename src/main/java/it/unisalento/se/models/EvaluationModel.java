package it.unisalento.se.models;

public class EvaluationModel implements CourseOfStudyNode {

    private Integer id;
    private Integer score;
    private String note;
    private UserModel sender;
    private String recipientType;
    private CourseOfStudyNode recipient;

    @Override
    public Integer getID() {
        return id;
    }

    @Override
    public void setID(Integer ID) {
        this.id = ID;
    }


    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public UserModel getSender() {
        return sender;
    }

    public void setSender(UserModel sender) {
        this.sender = sender;
    }

    public String getRecipientType() {
        return recipientType;
    }

    public void setRecipientType(String recipientType) {
        this.recipientType = recipientType;
    }

    public CourseOfStudyNode getRecipient() {
        return recipient;
    }

    public void setRecipient(CourseOfStudyNode recipient) {
        this.recipient = recipient;
    }
}
