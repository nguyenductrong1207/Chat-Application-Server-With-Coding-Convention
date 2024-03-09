package application.talk.usecases.user;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.talk.domains.ChatEntity;
import application.talk.domains.Message;
import application.talk.domains.PrivateGroup;
import application.talk.domains.User;
import application.talk.infrastructure.data.InMemoryDataStorage;
import application.talk.usecases.adapters.DataStorage;
import application.talk.usecases.user.RemovingMessages.CreatingResult;

public class RemovingMessagesTest {
	private DataStorage _storage;
	private RemovingMessages _useCase;

	@Before
	public void setUp() throws Exception {
		_storage = InMemoryDataStorage.getInstance();
		_useCase = new RemovingMessages(_storage);
	}

	@After
	public void tearDown() throws Exception {
		DataStorage storage = InMemoryDataStorage.getInstance();
		storage.cleanAll();
	}

	@Test
	public void testRemovingMessgage() {

//		User sender = new User("abc", "abc");
//		sender.setId("001");
//		ChatEntity receiverUser = new User("def", "456");
//
//		PrivateGroup group = new PrivateGroup("privateGroup", sender);
//		group.setId("111");
//		group.addAdmin(sender);
//
//		_storage.getUsers().add(sender);
//		_storage.getGroups().add(group);
//
//		Message message = new Message(sender, LocalDateTime.now(), receiverUser, "hello");
//		message.setId("123");
//
//		_storage.getMessages().add(message);
//
//		RemovingMessages.InputValues input = new RemovingMessages.InputValues("001", "123", "111");
//		RemovingMessages.OutputValues output = _useCase.execute(input);
//
//		assertEquals(CreatingResult.SUCCESSFUL, output.getResult());

	}

}