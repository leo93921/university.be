package it.unisalento.se.models;

public class PrivateChatMessageModel extends ChatMessageModel {
    private UserModel recipient;

    public UserModel getRecipient() {
        return recipient;
    }

    public void setRecipient(UserModel recipient) {
        this.recipient = recipient;
    }
}
