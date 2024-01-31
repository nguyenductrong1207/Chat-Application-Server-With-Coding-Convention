package application.talk.usecases.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import application.talk.domains.Group;
import application.talk.domains.PublicGroup;
import application.talk.domains.User;
import application.talk.infastructure.data.InMemoryDataStorage;
import application.talk.usecases.adapters.DataStorage;
import application.talk.usecases.user.CreatingGroup.CreatingResult;

public class CreatingGroupTest {
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
	public void testCreatingPublicGroup() {
		DataStorage storage = InMemoryDataStorage.getInstance();
		User user1 = new User("kiet", "0710");

		CreatingGroup useCase = new CreatingGroup(storage);
		CreatingGroup.InputValues input = new CreatingGroup.InputValues(true, user1, "Public group");

		CreatingGroup.OutputValues output = useCase.execute(input);
		assertNotNull(output.getMessage());
		assertEquals(CreatingResult.SUCCESSFUL, output.getResult());
	}

	@Test
	public void testCreatingPrivateGroup() {
		DataStorage storage = InMemoryDataStorage.getInstance();
		User user1 = new User("kiet", "0710");

		CreatingGroup useCase = new CreatingGroup(storage);
		CreatingGroup.InputValues input = new CreatingGroup.InputValues(false, user1, "Private group");

		CreatingGroup.OutputValues output = useCase.execute(input);
		assertNotNull(output.getMessage());
		assertEquals(CreatingResult.SUCCESSFUL, output.getResult());
	}

	@Test
	public void testCreateGroupWithNullAdmin() {
		DataStorage storage = InMemoryDataStorage.getInstance();
		CreatingGroup useCase = new CreatingGroup(storage);

		CreatingGroup.InputValues input = new CreatingGroup.InputValues(false, null, "NullAdminGroup");
		CreatingGroup.OutputValues output = useCase.execute(input);

		assertEquals(CreatingGroup.CreatingResult.FAILED, output.getResult());
		assertEquals("Admin cannot be null", output.getMessage());
	}

}
