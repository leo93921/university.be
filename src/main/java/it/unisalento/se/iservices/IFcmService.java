package it.unisalento.se.iservices;

import com.google.firebase.messaging.FirebaseMessagingException;

import java.io.IOException;

public interface IFcmService {

    void sendMessageToTopic(String title, String body, String topicName, String pageToOpen) throws IOException, FirebaseMessagingException;

    void sendMessageToUser(String title, String body, String token, String additionalData) throws FirebaseMessagingException, IOException;

}
