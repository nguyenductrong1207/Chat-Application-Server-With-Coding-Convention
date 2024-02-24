package application.talk.usecases.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import application.talk.domains.ChatEntity;
import application.talk.domains.Message;
import application.talk.domains.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.talk.infrastructure.data.InMemoryDataStorage;
import application.talk.usecases.adapters.DataStorage;

public class GetMessagesByTimeTest {
    private DataStorage _storage;
    private GetMessagesByTime _useCase;

    @Before
    public void setUp() throws Exception {
        _storage = InMemoryDataStorage.getInstance();
        _useCase = new GetMessagesByTime(_storage);
    }

    @After
    public void tearDown() throws Exception {
        _storage.cleanAll();
    }

    @Test
    public void testGetMessagesEmptyResult() {
        LocalDateTime currentTime = LocalDateTime.now();

        GetMessagesByTime.InputValues input = new GetMessagesByTime.InputValues(currentTime);
        GetMessagesByTime.OutputValues output = _useCase.execute(input);

        assertEquals(GetMessagesByTime.GetLastestMessagesResult.FAILED, output.getResult());
    }

    @Test
    public void testGetMessageValid() {
        LocalDateTime currentTime = LocalDateTime.now();

        User sender = new User("abc", "abc");
        ChatEntity receiverUser = new User("def", "456");
        Message newMessage = new Message(sender, LocalDateTime.now(), receiverUser, "hello");

        _storage.getMessages().add(newMessage);

        GetMessagesByTime.InputValues input = new GetMessagesByTime.InputValues(currentTime);
        GetMessagesByTime.OutputValues output = _useCase.execute(input);

        assertEquals("hello", output.getFoundMessages().get(0).getContent());
    }
}
