package it.unisalento.se.api.rest;

import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.iservices.IChatService;
import it.unisalento.se.models.PrivateChatMessageModel;
import it.unisalento.se.models.PublicChatMessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/chat")
public class ChatRestController {

    @Autowired
    private IChatService service;

    @PostMapping(value = "/send-public-message", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Boolean sendPublicMessage(@RequestBody PublicChatMessageModel message) throws IOException, UserTypeNotSupported {
        return service.sendPublicMessage(message);
    }

    @PostMapping(value = "/send-private-message", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Boolean sendPrivateMessage(@RequestBody PrivateChatMessageModel message) throws IOException {
        return service.sendPrivateMessage(message);
    }
}
