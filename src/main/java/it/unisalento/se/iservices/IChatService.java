package it.unisalento.se.iservices;

import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.ChatMessageRequest;
import it.unisalento.se.models.PublicChatMessageModel;

import java.io.IOException;

public interface IChatService {
    PublicChatMessageModel sendPublicMessage(PublicChatMessageModel message) throws IOException, UserTypeNotSupported;

    String getPublicMessage(ChatMessageRequest request);
}
