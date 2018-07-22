package it.unisalento.se.services;

import com.google.firebase.messaging.FirebaseMessagingException;
import it.unisalento.se.dao.User;
import it.unisalento.se.models.*;
import it.unisalento.se.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ChatServiceTest {

    @Mock
    private FrdService realtimeDbService;
    @Mock
    private FcmService fcmService;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private ChatService chatService;

    @Captor
    private ArgumentCaptor<SubjectModel> subjectToSave;
    @Captor
    private ArgumentCaptor<String> UUIDToSave;
    @Captor
    private ArgumentCaptor<FirebaseChatMessageModel> firebaseMessageToSave;
    @Captor
    private ArgumentCaptor<String> notificationTitle;
    @Captor
    private ArgumentCaptor<String> notificationBody;
    @Captor
    private ArgumentCaptor<String> topicName;
    @Captor
    private ArgumentCaptor<String> additionalData;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        User user = new User();
        user.setFcmToken("A_token");
        user.setId(24);
        user.setName("Mario");
        user.setSurname("Rossi");
        when(userRepository.getOne(any(Integer.class))).thenReturn(user);
    }

    @Test
    public void sendPublicMessage_OK() throws IOException, FirebaseMessagingException {

        UserModel sender = getSenderModel();
        SubjectModel recipient = new SubjectModel();
        recipient.setID(21);
        recipient.setName("Software Engineering");

        PublicChatMessageModel messageModel = getPublicChatMessageModel(sender, recipient);

        Boolean res = chatService.sendPublicMessage(messageModel);

        assertEquals(true, res);
        verify(realtimeDbService, times(1)).savePublicMessage(
                subjectToSave.capture(),
                UUIDToSave.capture(),
                firebaseMessageToSave.capture()
        );
        assertEquals(recipient.getName(), subjectToSave.getValue().getName());
        assertNotNull(UUIDToSave.getValue());
        assertNotEquals(0, UUIDToSave.getValue().length());
        assertEquals(messageModel.getContent(), firebaseMessageToSave.getValue().getContent());
        assertEquals(UUIDToSave.getValue(), firebaseMessageToSave.getValue().getUUID());
        assertEquals(messageModel.getRecipient().getID(), firebaseMessageToSave.getValue().getRecipientID());
        assertEquals(messageModel.getRecipient().getName(), firebaseMessageToSave.getValue().getRecipientFullName());
        assertEquals(messageModel.getSender().getId(), firebaseMessageToSave.getValue().getSenderID());
        assertEquals(messageModel.getSender().getName() + " " + messageModel.getSender().getSurname(), firebaseMessageToSave.getValue().getSenderFullName());
        assertEquals(messageModel.getSendDate().getTime(), firebaseMessageToSave.getValue().getSendDate().getTime());

        verify(fcmService, times(1)).sendMessageToTopic(
                notificationTitle.capture(),
                notificationBody.capture(),
                topicName.capture(),
                additionalData.capture()
        );
        assertEquals("New message in " + recipient.getName(), notificationTitle.getValue());
        assertEquals("A new message has been left in " + recipient.getName(), notificationBody.getValue());
        assertEquals(recipient.getName().replaceAll(" ", ""), topicName.getValue());
        assertNotEquals(0, additionalData.getValue().length());
        assertTrue(additionalData.getValue().contains("type"));
        assertTrue(additionalData.getValue().contains("public-chat"));
        assertTrue(additionalData.getValue().contains("recipient"));
        assertTrue(additionalData.getValue().contains(messageModel.getRecipient().getName()));
    }

    private PublicChatMessageModel getPublicChatMessageModel(UserModel sender, SubjectModel recipient) {
        PublicChatMessageModel messageModel = new PublicChatMessageModel();
        messageModel.setID(2);
        messageModel.setContent("Hi, everyone.");
        messageModel.setRecipient(recipient);
        messageModel.setSender(sender);
        messageModel.setSendDate(new Date());
        return messageModel;
    }

    @Test
    public void sendPrivateMessage_OK() throws IOException, FirebaseMessagingException {

        UserModel sender = getSenderModel();
        UserModel recipient = getRecipientModel(21, "Franco", "Verdi");

        PrivateChatMessageModel messageModel = getPrivateChatMessageModel(sender, recipient);

        Boolean res = chatService.sendPrivateMessage(messageModel);

        assertEquals(true, res);
        verify(realtimeDbService, times(1)).savePrivateMessage(
                firebaseMessageToSave.capture()
        );
        assertEquals(messageModel.getContent(), firebaseMessageToSave.getValue().getContent());
        assertEquals(recipient.getId(), firebaseMessageToSave.getValue().getRecipientID());
        assertEquals(recipient.getName() + " " + recipient.getSurname(), firebaseMessageToSave.getValue().getRecipientFullName());
        assertEquals(sender.getId(), firebaseMessageToSave.getValue().getSenderID());
        assertEquals(sender.getName() + " " + sender.getSurname(), firebaseMessageToSave.getValue().getSenderFullName());
        assertEquals(messageModel.getSendDate().getTime(), firebaseMessageToSave.getValue().getSendDate().getTime());

        verify(fcmService, times(1)).sendMessageToUser(
                notificationTitle.capture(),
                notificationBody.capture(),
                topicName.capture(),
                additionalData.capture()
        );
        assertEquals(sender.getName() + " sent you a message.", notificationTitle.getValue());
        assertNotEquals(0, additionalData.getValue().length());
        assertTrue(additionalData.getValue().contains("type"));
        assertTrue(additionalData.getValue().contains("private-chat"));
        assertTrue(additionalData.getValue().contains("recipient"));
        assertTrue(additionalData.getValue().contains(messageModel.getSender().getName()));

    }

    private PrivateChatMessageModel getPrivateChatMessageModel(UserModel sender, UserModel recipient) {
        PrivateChatMessageModel messageModel = new PrivateChatMessageModel();
        messageModel.setID(2);
        messageModel.setContent("Hi, everyone.");
        messageModel.setSender(sender);
        messageModel.setRecipient(recipient);
        messageModel.setSendDate(new Date());
        return messageModel;
    }

    private UserModel getRecipientModel(int i, String name, String surname) {
        UserModel recipient = new UserModel();
        recipient.setId(i);
        recipient.setName(name);
        recipient.setSurname(surname);
        return recipient;
    }


    @Test(expected = Exception.class)
    public void sendPrivateMessage_Exception() throws FirebaseMessagingException, IOException {
        doThrow(new Exception()).when(fcmService).sendMessageToUser(any(String.class), any(String.class), any(String.class), any(String.class));

        chatService.sendPrivateMessage(getPrivateChatMessageModel(getSenderModel(), getRecipientModel(21, "Franco", "Verdi")));


    }


    @Test(expected = Exception.class)
    public void sendPublicMessage_Exception() throws FirebaseMessagingException, IOException {
        doThrow(new Exception()).when(fcmService).sendMessageToTopic(any(String.class), any(String.class), any(String.class), any(String.class));

        UserModel sender = getSenderModel();
        SubjectModel recipient = new SubjectModel();
        recipient.setID(21);
        recipient.setName("Software Engineering");

        PublicChatMessageModel messageModel = getPublicChatMessageModel(sender, recipient);

        chatService.sendPublicMessage(messageModel);
    }

    private UserModel getSenderModel() {
        UserModel sender = getRecipientModel(24, "Mario", "Rossi");
        return sender;
    }
}