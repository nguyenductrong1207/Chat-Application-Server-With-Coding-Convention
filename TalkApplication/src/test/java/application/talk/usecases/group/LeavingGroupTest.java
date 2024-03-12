package application.talk.usecases.group;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.talk.domains.Group;
import application.talk.domains.User;
import application.talk.infrastructure.data.InMemoryDataStorage;
import application.talk.usecases.adapters.DataStorage;
import application.talk.usecases.user.LeavingGroup.RegisterResult;

public class LeavingGroupTest {
	
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
	public void testLeavingGroup() {
		DataStorage storage = InMemoryDataStorage.getInstance();
		LeavingGroup useCase = new LeavingGroup(storage);
		
		User user = new User("trong", "1207");

		LeavingGroup.InputValues input = new LeavingGroup.InputValues(null, user);

		LeavingGroup.OutputValues output = useCase.execute(input);
		assertEquals(RegisterResult.SUCCESSED, output.getResult());
		assertNotNull(output.getMessage());
	}
	@Test
	public void testLeavingGroupWithNullGroup() {
		DataStorage storage = InMemoryDataStorage.getInstance();
		LeavingGroup useCase = new LeavingGroup(storage);
		Group group = new Group(null) {};
		User user = new User("kiet", "0710");

		LeavingGroup.InputValues input = new LeavingGroup.InputValues(group, user);

		LeavingGroup.OutputValues output = useCase.execute(input);
		assertEquals(RegisterResult.FAILED, output.getResult());
		assertNotNull(output.getMessage());
	}

}
