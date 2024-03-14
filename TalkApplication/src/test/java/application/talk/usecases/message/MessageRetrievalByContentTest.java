package application.talk.usecases.message;

import application.talk.domains.Conversation;
import application.talk.domains.Message;
import application.talk.domains.User;
import application.talk.enums.FinalResult;
import application.talk.infrastructure.data.InMemoryDataStorage;
import application.talk.usecases.adapters.DataStorage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MessageRetrievalByContentTest {
    private DataStorage _storage;
    private MessageRetrievalByContent _useCase;
    private Conversation _conversation;

    @Before
    public void setUp() throws Exception {
        _storage = InMemoryDataStorage.getInstance();
        _useCase = new MessageRetrievalByContent(_storage);

        User sender = new User("kiet", "123");
        User receiver = new User("trong", "123");
        Message newMessage = new Message(sender, LocalDateTime.now(), receiver, "hello");
        _conversation = new Conversation(sender, receiver);
        _conversation.addMessage(newMessage);

        _storage.getConversations().add(_conversation);
    }

    @After
    public void tearDown() throws Exception {
        _storage.cleanAll();
    }

    @Test
    public void testGetMessagesEmptyResult() {
        MessageRetrievalByContent.InputValues input = new MessageRetrievalByContent.InputValues("not", _conversation.getId());
        MessageRetrievalByContent.OutputValues output = _useCase.execute(input);

        assertTrue(output.getFoundMessages().isEmpty());
    }

    @Test
    public void testGetMessageValid() {
        MessageRetrievalByContent.InputValues input = new MessageRetrievalByContent.InputValues("he", _conversation.getId());
        MessageRetrievalByContent.OutputValues output = _useCase.execute(input);

        assertEquals("hello", output.getFoundMessages().get(0).getContent());
        assertEquals(FinalResult.SUCCESSFUL, output.getResult());
    }
}