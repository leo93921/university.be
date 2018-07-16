package it.unisalento.se.models;

public class ChatMessageRequest {

    private String UUID;
    private ChatMessageType type;

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public ChatMessageType getType() {
        return type;
    }

    public void setType(ChatMessageType type) {
        this.type = type;
    }
}
