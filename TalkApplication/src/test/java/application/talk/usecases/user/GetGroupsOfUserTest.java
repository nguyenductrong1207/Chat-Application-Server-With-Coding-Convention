package application.talk.usecases.user;

import static org.junit.Assert.assertEquals;

import application.talk.enums.FinalResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.talk.infrastructure.data.InMemoryDataStorage;
import application.talk.usecases.adapters.DataStorage;

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
		assertEquals(FinalResult.SUCCESSFUL, output.getResult());
	}

	@Test
	public void testGetGroupsOfUserWithoutName() {
		DataStorage storage = InMemoryDataStorage.getInstance();
		GetGroupsOfUser useCase = new GetGroupsOfUser(storage);

		GetGroupsOfUser.InputValues input = new GetGroupsOfUser.InputValues(null);

		GetGroupsOfUser.OutputValues output = useCase.execute(input);
		assertEquals(FinalResult.FAILED, output.getResult());
	}
}
