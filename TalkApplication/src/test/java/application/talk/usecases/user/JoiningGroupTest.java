package application.talk.usecases.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import application.talk.domains.User;
import application.talk.infastructure.data.InMemoryDataStorage;
import application.talk.usecases.adapters.DataStorage;
import application.talk.usecases.user.FindingUser.FindingResult;

public class JoiningGroupTest {

	@Before
	public void setUp() throws Exception {
		DataStorage storage = InMemoryDataStorage.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		DataStorage storage = InMemoryDataStorage.getInstance();
		storage.cleanAll();
	}

	@Test
	public void testJoiningPrivateGroup() {
		DataStorage storage = InMemoryDataStorage.getInstance();
		JoiningGroup useCase = new JoiningGroup(storage);
		User user1 = new User("trong", "1207");
		User user2 = new User("kiet", "0710");

		JoiningGroup.InputValues input = new JoiningGroup.InputValues(user1, user2);

		JoiningGroup.OutputValues output = useCase.execute(input);
		assertEquals(FindingResult.SUCCESSFUL, output.getResult());
		assertNotNull(output.getMessage());
	}

	@Test
	public void testJoiningPublicGroup() {
		DataStorage storage = InMemoryDataStorage.getInstance();
		JoiningGroup useCase = new JoiningGroup(storage);
		User user = new User("trong", "1207");

		JoiningGroup.InputValues input = new JoiningGroup.InputValues("123456", user);

		JoiningGroup.OutputValues output = useCase.execute(input);
		assertEquals(FindingResult.SUCCESSFUL, output.getResult());
		assertNotNull(output.getMessage());
	}

}
