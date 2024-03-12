package application.talk.usecases.message;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import application.talk.enums.FinalResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.talk.domains.ChatEntity;
import application.talk.domains.Conversation;
import application.talk.domains.Message;
import application.talk.domains.PrivateGroup;
import application.talk.domains.User;
import application.talk.infrastructure.data.InMemoryDataStorage;
import application.talk.usecases.adapters.DataStorage;

public class AdminDeleteMessageTest {
	private DataStorage _storage;
	private AdminDeleteMessage _useCase;

	@Before
	public void setUp() throws Exception {
		_storage = InMemoryDataStorage.getInstance();
		DeleteMessage deleteMessage = new DeleteMessage(_storage);
		_useCase = new AdminDeleteMessage(_storage, deleteMessage);
	}

	@After
	public void tearDown() throws Exception {
		DataStorage storage = InMemoryDataStorage.getInstance();
		storage.cleanAll();
	}

	@Test
	public void testRemovingMessgage() {
		//set up
		User sender = new User("kiet", "0710");
        ChatEntity receiver = new User("trong", "1207");
        Conversation conversation = new Conversation(sender, receiver);
        Message message = new Message(sender, LocalDateTime.now(), receiver, "hello");
        PrivateGroup privateGroup = new PrivateGroup("group1", sender);
		
        List<Message> messages = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            messages.add(new Message(sender, LocalDateTime.now(), receiver, "hello!"));
        }
        conversation.setMessages(messages);
        
        _storage.getConversations().add(conversation);
        _storage.getMessages().add(message);
		_storage.getGroups().add(privateGroup);
        
        AdminDeleteMessage.InputValues input = new AdminDeleteMessage.InputValues(sender.getId(), 
        		conversation.getId(), privateGroup.getId(), message.getId());
        AdminDeleteMessage.OutputValues output = _useCase.execute(input);
        
        assertEquals(FinalResult.SUCCESSFUL, output.getResult());
	}

}
