package it.unisalento.se.models;

public class EvaluationModel {

    private Integer id;
    private Integer score;
    private String note;
    private UserModel sender;
    private String recipientType;
    private LessonModel recipientL;
    private DocumentModel recipientD;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public LessonModel getRecipientL() {
        return recipientL;
    }

    public void setRecipientL(LessonModel recipientL) {
        this.recipientL = recipientL;
    }

    public DocumentModel getRecipientD() {
        return recipientD;
    }

    public void setRecipientD(DocumentModel recipientD) {
        this.recipientD = recipientD;
    }
}
