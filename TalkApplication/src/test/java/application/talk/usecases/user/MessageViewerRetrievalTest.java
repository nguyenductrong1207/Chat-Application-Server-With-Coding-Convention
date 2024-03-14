package application.talk.usecases.user;

import application.talk.domains.*;
import application.talk.enums.FinalResult;
import application.talk.infrastructure.data.InMemoryDataStorage;
import application.talk.usecases.adapters.DataStorage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDateTime;
import java.util.Arrays;

public class MessageViewerRetrievalTest {
    private DataStorage _storage;
    private User _sender;
    private ChatEntity _receiver;
    private Message _message;

    @Before
    public void setUp() throws Exception {
        _storage = InMemoryDataStorage.getInstance();
        _sender = new User("trong", "1207");
        User userA = new User("kiet", "071002");
        User userB = new User("duyen", "123");
        User userC = new User("hao", "456");

        _receiver = new PublicGroup("group hoc tap", "abc");

        ((Group)_receiver).addAllUser(Arrays.asList(userA, userB, userC));
        _storage.getGroups().add((Group) _receiver);

        _message = new Message(_sender, LocalDateTime.now(), _receiver, "helo");

        Conversation newConversation = new Conversation(_sender, _receiver);
        newConversation.addMessage(_message);

        _storage.getConversations().add(newConversation);
        _storage.getRecordMessages().add(new RecordedMessage(_receiver, _message));
    }

    @After
    public void tearDown() throws Exception {
        DataStorage storage = InMemoryDataStorage.getInstance();
        storage.cleanAll();
    }

    @Test
    public void testRecordingMessage() {
        String userID = _sender.getId();
        String messageID = _message.getId();

        LastMessageRecording recordTest = new LastMessageRecording(_storage);
        MessageViewerRetrieval.InputValues input = new MessageViewerRetrieval.InputValues(userID, messageID);
        MessageViewerRetrieval gettingMessageViewers = new MessageViewerRetrieval(_storage, recordTest);
        MessageViewerRetrieval.OutputValues output = gettingMessageViewers.execute(input);

        Assertions.assertEquals(FinalResult.SUCCESSFUL, output.getResult());
        Assertions.assertNotNull(output.getViewers());
    }
}
