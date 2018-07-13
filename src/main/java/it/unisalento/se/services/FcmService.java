package it.unisalento.se.services;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import it.unisalento.se.iservices.IFcmService;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;

@Service
public class FcmService implements IFcmService {

    private static final String SERVICE_ACCOUNT_FILE_PATH = "university-be-firebase-adminsdk-wpbul-60dcb48ea1.json";
    private static Boolean appInitiated = false;

    private static void initApp() throws IOException {
        if (!FcmService.appInitiated) {
            String f = FcmService.class.getClassLoader().getResource(SERVICE_ACCOUNT_FILE_PATH).getFile();
            FileInputStream serviceAccount =
                    new FileInputStream(f);

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://university-be.firebaseio.com")
                    .build();

            FirebaseApp.initializeApp(options);
            FcmService.appInitiated = true;
        }
    }

    @Override
    public void sendMessageToTopic(String title, String body, String topicName) throws IOException, FirebaseMessagingException {
        FcmService.initApp();

        Message message = Message.builder()
                .setTopic(topicName)
                .putData("title", title)
                .putData("body", body)
                .build();

        FirebaseMessaging.getInstance().send(message);
    }

}
