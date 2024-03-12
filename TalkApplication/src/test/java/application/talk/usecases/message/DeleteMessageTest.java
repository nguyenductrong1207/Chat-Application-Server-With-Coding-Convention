package application.talk.usecases.message;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import application.talk.domains.ChatEntity;
import application.talk.domains.Conversation;
import application.talk.domains.Message;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.talk.domains.User;
import application.talk.infrastructure.data.InMemoryDataStorage;
import application.talk.usecases.adapters.DataStorage;
import application.talk.usecases.user.DeleteMessage.DeleteMessageResult;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DeleteMessageTest {
    private DataStorage _storage;
    private DeleteMessage _useCase;

    @Before
    public void setUp() throws Exception {
        _storage = InMemoryDataStorage.getInstance();
        _useCase = new DeleteMessage(_storage);
    }

    @After
    public void tearDown() throws Exception {
        _storage.cleanAll();
    }

    @Test
    public void testDeleteMessage() {
        User sender = new User("trong1", "1207");
        ChatEntity receiver = new User("trong2", "1910");
        Conversation conversation = new Conversation(sender, receiver);
        Message message = new Message(sender, LocalDateTime.now(), receiver, "nhuc dau quai");

        List<Message> messages = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            messages.add(new Message(sender, LocalDateTime.now(), receiver, "nhuc dau qua"));
        }
        conversation.setMessages(messages);

        _storage.getConversations().add(conversation);
        _storage.getMessages().add(message);

        DeleteMessage.InputValues input = new DeleteMessage.InputValues(conversation.getId(), message.getId(), sender.getId());
        DeleteMessage.OutputValues output = _useCase.execute(input);

        assertEquals(DeleteMessageResult.SUCCESSED, output.getResult());
    }

}
