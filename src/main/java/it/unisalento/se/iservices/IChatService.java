package it.unisalento.se.iservices;

import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.models.PrivateChatMessageModel;
import it.unisalento.se.models.PublicChatMessageModel;

import java.io.IOException;

public interface IChatService {
    Boolean sendPublicMessage(PublicChatMessageModel message) throws IOException, UserTypeNotSupported;

    Boolean sendPrivateMessage(PrivateChatMessageModel message) throws IOException;
}
