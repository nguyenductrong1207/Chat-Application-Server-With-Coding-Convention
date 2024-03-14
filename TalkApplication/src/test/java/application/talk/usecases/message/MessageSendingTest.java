package application.talk.usecases.message;

import application.talk.domains.Conversation;
import application.talk.enums.FinalResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.talk.domains.User;
import application.talk.infrastructure.data.InMemoryDataStorage;
import application.talk.usecases.adapters.DataStorage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MessageSendingTest {
    private DataStorage _storage;
    private MessageSending _useCase;

    @Before
    public void setUp() throws Exception {
        _storage = InMemoryDataStorage.getInstance();
        _useCase = new MessageSending(_storage);
    }

    @After
    public void tearDown() throws Exception {
        _storage.cleanAll();
    }

    @Test
    public void testSendingMessageNull() {
        User user = new User("kiet", "0710");

        MessageSending.InputValues input = new MessageSending.InputValues(null, user, null, null, null, "asd");

        MessageSending.OutputValues output = _useCase.execute(input);
        assertTrue(output.getMessage().isEmpty());
        assertEquals(FinalResult.FAILED, output.getResult());
    }

    @Test
    public void testValidSendingMessage() {
        User sender = new User("kiet", "0710");
        User receiver = new User("kiet", "0710");
        Conversation conversation = new Conversation(sender, receiver);
        _storage.getConversations().add(conversation);

        MessageSending.InputValues input = new MessageSending.InputValues(sender, receiver, "khum", null, null, conversation.getId());

        MessageSending.OutputValues output = _useCase.execute(input);
        assertEquals(FinalResult.SUCCESSFUL, output.getResult());
    }
}