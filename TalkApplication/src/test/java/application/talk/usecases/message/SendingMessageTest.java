package application.talk.usecases.message;

import application.talk.domains.Conversation;
import application.talk.enums.FinalResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.talk.domains.User;
import application.talk.infrastructure.data.InMemoryDataStorage;
import application.talk.usecases.adapters.DataStorage;

import java.sql.Connection;

import static org.junit.Assert.*;

public class SendingMessageTest {
    private DataStorage _storage;
    private SendingMessage _useCase;
    @Before
    public void setUp() throws Exception {
        _storage = InMemoryDataStorage.getInstance();
        _useCase =new SendingMessage(_storage);
    }

    @After
    public void tearDown() throws Exception {
        _storage.cleanAll();
    }

    @Test
    public void testSendingMessageNull() {
        User user = new User("kiet", "0710");

        SendingMessage.InputValues input = new SendingMessage.InputValues(null, user, null, null, null, "asd");

        SendingMessage.OutputValues output = _useCase.execute(input);
        assertTrue(output.getMessage().isEmpty());
        assertEquals(FinalResult.FAILED, output.getResult());
    }

    @Test
    public void testValidSendingMessage() {
        User sender = new User("kiet", "0710");
        User receiver = new User("kiet", "0710");
        Conversation conversation = new Conversation(sender,receiver);
        _storage.getConversations().add(conversation);

        SendingMessage.InputValues input = new SendingMessage.InputValues(sender, receiver, "khum", null, null, conversation.getId());

        SendingMessage.OutputValues output = _useCase.execute(input);
        assertEquals(FinalResult.SUCCESSFUL, output.getResult());
    }
}
