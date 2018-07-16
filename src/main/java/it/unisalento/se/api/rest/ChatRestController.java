package it.unisalento.se.api.rest;

import it.unisalento.se.exceptions.UserTypeNotSupported;
import it.unisalento.se.iservices.IChatService;
import it.unisalento.se.models.ChatMessageRequest;
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

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public PublicChatMessageModel saveMessage(@RequestBody PublicChatMessageModel message) throws IOException, UserTypeNotSupported {
        return service.sendPublicMessage(message);
    }

    @PostMapping(value = "/find", produces = MediaType.TEXT_PLAIN_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String getPublicMessage(@RequestBody ChatMessageRequest request) {
        return service.getPublicMessage(request);
    }
}
