package application.talk.usecases.user;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.talk.domains.ChatEntity;
import application.talk.domains.PrivateGroup;
import application.talk.domains.User;
import application.talk.infrastructure.data.InMemoryDataStorage;
import application.talk.usecases.adapters.DataStorage;
import application.talk.usecases.user.FindingUser.FindingResult;

public class FindingUserTest {

	private DataStorage _storage;
	private FindingUser _useCase;

	@Before
	public void setUp() throws Exception {
		_storage = InMemoryDataStorage.getInstance();
		_useCase = new FindingUser(_storage);
	}

	@After
	public void tearDown() throws Exception {
		DataStorage storage = InMemoryDataStorage.getInstance();
		storage.cleanAll();
	}

	@Test
	public void testFindingUser() {
		//setup
		User user1 = new User("kiet", "123");
		user1.setName("anh kiet");

		User user2 = new User("kiet123", "456");
		user2.setName("kietluong");
		
		_storage.getUsers().add(user1);
		_storage.getUsers().add(user2);

		
		FindingUser.InputValues input = new FindingUser.InputValues("kiet");
		FindingUser.OutputValues output = _useCase.execute(input);

		assertEquals(FindingResult.SUCCESSFUL, output.getResult());
	}

}
