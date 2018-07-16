package it.unisalento.se.common;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import it.unisalento.se.services.FcmService;

import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseAppSingleton {

    private static final String SERVICE_ACCOUNT_FILE_PATH = "university-be-firebase-adminsdk-wpbul-60dcb48ea1.json";
    private static FirebaseApp firebaseApp = null;

    public static void initApp() throws IOException {
        if (firebaseApp == null) {
            String f = FcmService.class.getClassLoader().getResource(SERVICE_ACCOUNT_FILE_PATH).getFile();
            FileInputStream serviceAccount =
                    new FileInputStream(f);

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://university-be.firebaseio.com")
                    .setProjectId("university-be")
                    .build();

            firebaseApp = FirebaseApp.initializeApp(options);
        }
    }
}
