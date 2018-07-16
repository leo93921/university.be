package it.unisalento.se.models;

public class PublicChatMessageModel extends ChatMessageModel {

    private SubjectModel recipient;

    public SubjectModel getRecipient() {
        return recipient;
    }

    public void setRecipient(SubjectModel recipient) {
        this.recipient = recipient;
    }

}
