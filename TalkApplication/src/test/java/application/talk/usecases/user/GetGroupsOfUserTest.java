package application.talk.usecases.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.talk.infrastructure.data.InMemoryDataStorage;
import application.talk.usecases.adapters.DataStorage;
import application.talk.usecases.user.GetGroupsOfUser.RegisterResult;

public class GetGroupsOfUserTest {

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
	public void testGetGroupsOfUser() {
		DataStorage storage = InMemoryDataStorage.getInstance();
		GetGroupsOfUser useCase = new GetGroupsOfUser(storage);

		GetGroupsOfUser.InputValues input = new GetGroupsOfUser.InputValues("trong");

		GetGroupsOfUser.OutputValues output = useCase.execute(input);
		assertEquals(RegisterResult.SUCCESSED, output.getResult());
		assertNotNull(output.getMessage());
	}

	@Test
	public void testGetGroupsOfUserWithoutName() {
		DataStorage storage = InMemoryDataStorage.getInstance();
		GetGroupsOfUser useCase = new GetGroupsOfUser(storage);

		GetGroupsOfUser.InputValues input = new GetGroupsOfUser.InputValues(null);

		GetGroupsOfUser.OutputValues output = useCase.execute(input);
		assertEquals(RegisterResult.FAILED, output.getResult());
		assertEquals("Please Enter A Name To Listing!!!", output.getMessage());
	}

}
