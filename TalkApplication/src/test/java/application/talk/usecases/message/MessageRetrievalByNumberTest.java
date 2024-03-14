package application.talk.usecases.message;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import application.talk.domains.Conversation;
import application.talk.enums.FinalResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.talk.domains.ChatEntity;
import application.talk.domains.Message;
import application.talk.domains.User;
import application.talk.infrastructure.data.InMemoryDataStorage;
import application.talk.usecases.adapters.DataStorage;

public class MessageRetrievalByNumberTest {
    private DataStorage _storage;
    private MessageRetrievalByNumber _useCase;

    @Before
    public void setUp() throws Exception {
        _storage = InMemoryDataStorage.getInstance();
        TopLatestMessageRetrieval getTopMessage = new TopLatestMessageRetrieval(_storage);
        _useCase = new MessageRetrievalByNumber(_storage, getTopMessage);
    }

    @After
    public void tearDown() throws Exception {
        DataStorage storage = InMemoryDataStorage.getInstance();
        storage.cleanAll();
    }

    @Test
    public void testRetrievingMessages() {
        User sender = new User("abc", "abc");
        ChatEntity receiverUser = new User("def", "456");
        List<Message> messages = new ArrayList<>();
        Conversation newConversation = new Conversation(sender, receiverUser);

        for (int i = 0; i < 10; i++) {
            messages.add(new Message(sender, LocalDateTime.now(), receiverUser, "hello"));
        }

        newConversation.setMessages(messages);
        _storage.getConversations().add(newConversation);

        MessageRetrievalByNumber.InputValues input = new MessageRetrievalByNumber.InputValues(5, newConversation.getId());
        MessageRetrievalByNumber.OutputValues output = _useCase.execute(input);

        assertEquals(FinalResult.SUCCESSFUL, output.getResult());
    }
}