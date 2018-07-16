package it.unisalento.se.iservices;

import it.unisalento.se.models.FirebasePublicChatMessageModel;
import it.unisalento.se.models.SubjectModel;

import java.io.IOException;

public interface IFrdService {

    void savePublicMessage(SubjectModel subject, String path, FirebasePublicChatMessageModel toSave) throws IOException;
}
