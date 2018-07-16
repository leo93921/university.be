package it.unisalento.se.iservices;

import it.unisalento.se.models.FirebaseChatMessageModel;
import it.unisalento.se.models.SubjectModel;

import java.io.IOException;

public interface IFrdService {

    void savePublicMessage(SubjectModel subject, String path, FirebaseChatMessageModel toSave) throws IOException;

    void savePrivateMessage(FirebaseChatMessageModel msg) throws IOException;
}
