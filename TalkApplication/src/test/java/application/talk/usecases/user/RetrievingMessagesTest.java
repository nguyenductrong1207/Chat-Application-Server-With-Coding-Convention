package application.talk.usecases.user;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.talk.domains.ChatEntity;
import application.talk.domains.Message;
import application.talk.domains.User;
import application.talk.infrastructure.data.InMemoryDataStorage;
import application.talk.usecases.adapters.DataStorage;
import application.talk.usecases.user.RetrievingMessages.RetrievingMessagesResult;

public class RetrievingMessagesTest {

	private DataStorage _storage;
	private RetrievingMessages _useCase;

	@Before
	public void setUp() throws Exception {
		_storage = InMemoryDataStorage.getInstance();
		GetTopLastestMessages getTopMessage = new GetTopLastestMessages(_storage);
		_useCase = new RetrievingMessages(_storage, getTopMessage);
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
		for (int i = 0; i < 10;i ++) {
			messages.add(new Message(sender, LocalDateTime.now(), receiverUser, "hello"));
		}
		
		_storage.getMessages().getAll().addAll(messages);
		
	    RetrievingMessages.InputValues input = new RetrievingMessages.InputValues(5); 
	    RetrievingMessages.OutputValues output = _useCase.execute(input);
	    
	    assertEquals(RetrievingMessagesResult.SUCCESSFUL, output.getResult());
	}

}
