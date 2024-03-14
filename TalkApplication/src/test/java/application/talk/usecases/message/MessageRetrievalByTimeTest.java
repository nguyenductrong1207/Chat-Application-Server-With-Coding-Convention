package application.talk.usecases.message;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import application.talk.domains.Conversation;
import application.talk.domains.Message;
import application.talk.domains.User;
import application.talk.enums.FinalResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.talk.infrastructure.data.InMemoryDataStorage;
import application.talk.usecases.adapters.DataStorage;

public class MessageRetrievalByTimeTest {
    private DataStorage _storage;
    private MessageRetrievalByTime _useCase;
    private Conversation _newConversation;

    @Before
    public void setUp() throws Exception {
        _storage = InMemoryDataStorage.getInstance();
        _useCase = new MessageRetrievalByTime(_storage);

        User sender = new User("kiet", "123");
        User receiver = new User("duyen", "haha");

        _newConversation = new Conversation(sender, receiver);

        for (int i = 0; i < 5; i++) {
            _newConversation.addMessage(new Message(sender, LocalDateTime.now(), receiver, "hello"));
        }

        _storage.getConversations().add(_newConversation);
    }

    @After
    public void tearDown() throws Exception {
        _storage.cleanAll();
    }

    @Test
    public void testGetMessagesEmptyResult() {
        LocalDateTime currentTime = LocalDateTime.now();

        MessageRetrievalByTime.InputValues input = new MessageRetrievalByTime.InputValues(currentTime, "daucoiddau");
        MessageRetrievalByTime.OutputValues output = _useCase.execute(input);

        assertEquals(FinalResult.FAILED, output.getResult());
    }

    @Test
    public void testGetMessageValid() {
        LocalDateTime currentTime = LocalDateTime.now();

        MessageRetrievalByTime.InputValues input = new MessageRetrievalByTime.InputValues(currentTime, _newConversation.getId());
        MessageRetrievalByTime.OutputValues output = _useCase.execute(input);

        assertEquals("hello", output.getFoundMessages().get(0).getContent());
    }
}