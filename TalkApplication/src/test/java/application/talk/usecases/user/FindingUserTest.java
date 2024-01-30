package application.talk.usecases.user;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import application.talk.domains.User;
import application.talk.infastructure.data.InMemoryDataStorage;
import application.talk.usecases.adapters.DataStorage;
import application.talk.usecases.user.FindingUser.FindingResult;

public class FindingUserTest {

	@Before
	public void setUp() throws Exception {
		DataStorage storage = InMemoryDataStorage.getInstance();
		storage.getUsers().add(new User("kiet", "0710"));
	}

	@After
	public void tearDown() throws Exception {
		DataStorage storage = InMemoryDataStorage.getInstance();
		storage.cleanAll();
	}

	@Test
	public void testFindingNonExistUser() {
		DataStorage storage = InMemoryDataStorage.getInstance();
		FindingUser useCase = new FindingUser(storage);
		
		FindingUser.InputValues input = new FindingUser.InputValues("kiet");
		FindingUser.OutputValues output = useCase.execute(input);
		
		assertEquals(FindingResult.FAILED, output.getResult());
		assertNotNull(output.getMessage());
	}
	
	@Test
	public void testFindingExistUser() {
		DataStorage storage = InMemoryDataStorage.getInstance();
		FindingUser useCase = new FindingUser(storage);
		
		FindingUser.InputValues input = new FindingUser.InputValues("kiet");
		FindingUser.OutputValues output = useCase.execute(input);
		
		assertEquals(FindingResult.SUCCESSFUL, output.getResult());
		assertNotNull(output.getMessage());
	}

}
