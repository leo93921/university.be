package it.unisalento.se.services;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import it.unisalento.se.common.FirebaseAppSingleton;
import it.unisalento.se.iservices.IFrdService;
import it.unisalento.se.models.FirebaseChatMessageModel;
import it.unisalento.se.models.SubjectModel;
import org.springframework.stereotype.Service;

import java.io.IOException;

// Firebase real-time Database service
@Service
public class FrdService implements IFrdService {

    @Override
    public void savePublicMessage(SubjectModel subject, String UUID, FirebaseChatMessageModel toSave) throws IOException {
        FirebaseAppSingleton.initApp();

        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference("/public-message/" + String.valueOf(subject.getID()) + "_" + subject.getName());

        DatabaseReference childRef = ref.child(UUID);
        childRef.setValueAsync(toSave);
    }

    @Override
    public void savePrivateMessage(FirebaseChatMessageModel message) throws IOException {
        FirebaseAppSingleton.initApp();

        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference("/private-message");
        DatabaseReference childRef = ref.push();
        childRef.setValueAsync(message);
    }
}
