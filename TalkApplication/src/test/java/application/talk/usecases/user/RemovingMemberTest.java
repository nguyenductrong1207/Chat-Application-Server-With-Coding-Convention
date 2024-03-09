package application.talk.usecases.user;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.talk.domains.PrivateGroup;
import application.talk.domains.User;
import application.talk.infrastructure.data.InMemoryDataStorage;
import application.talk.usecases.adapters.DataStorage;
import application.talk.usecases.user.RemovingMember.CreatingResult;

public class RemovingMemberTest {

	private DataStorage _storage;
	private RemovingMember _useCase;

	@Before
	public void setUp() throws Exception {
		_storage = InMemoryDataStorage.getInstance();
		_useCase = new RemovingMember(_storage);
	}

	@After
	public void tearDown() throws Exception {
		DataStorage storage = InMemoryDataStorage.getInstance();
		storage.cleanAll();
	}


	@Test
	public void testRemovingMember() {
		User user1 = new User("duyen", "123");
		User user2 = new User("kiet", "456");
		_storage.getUsers().add(user1);
		_storage.getUsers().add(user2);
		
		PrivateGroup privateGroup = new PrivateGroup("group1", user1);
		privateGroup.addUser(user2);;
		
		_storage.getGroups().add(privateGroup);
		
		RemovingMember.InputValues input = new RemovingMember.InputValues(user1.getId(),privateGroup.getId(), user2.getId());
		RemovingMember.OutputValues output = _useCase.execute(input);
		assertEquals(CreatingResult.SUCCESSFUL, output.getResult());
	}


}
