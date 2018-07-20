package it.unisalento.se.services;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import it.unisalento.se.common.FirebaseAppSingleton;
import it.unisalento.se.iservices.IFcmService;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FcmService implements IFcmService {

    @Override
    public void sendMessageToTopic(
            String title,
            String body,
            String topicName,
            String additionalData) throws IOException, FirebaseMessagingException {
        FirebaseAppSingleton.initApp();

        Message message = Message.builder()
                .setTopic(topicName)
                .putData("title", title)
                .putData("body", body)
                .putData("additionalData", additionalData)
                .build();

        FirebaseMessaging.getInstance().send(message);
    }

    @Override
    public void sendMessageToUser(
            String title,
            String body,
            String token,
            String additionalData) throws FirebaseMessagingException, IOException {
        FirebaseAppSingleton.initApp();

        Message message = Message.builder()
                .putData("title", title)
                .putData("body", body)
                .putData("additionalData", additionalData)
                .setToken(token)
                .build();

        FirebaseMessaging.getInstance().send(message);
    }
}
