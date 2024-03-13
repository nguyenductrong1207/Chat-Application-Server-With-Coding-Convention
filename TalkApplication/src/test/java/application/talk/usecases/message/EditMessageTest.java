package application.talk.usecases.message;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import application.talk.enums.FinalResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.talk.domains.User;
import application.talk.infrastructure.data.InMemoryDataStorage;
import application.talk.usecases.adapters.DataStorage;

public class EditMessageTest {
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
	public void testEditMessage() {
		DataStorage storage = InMemoryDataStorage.getInstance();
		User sender = new User("trong", "1207");

		EditMessage useCase = new EditMessage(storage);
		EditMessage.InputValues input = new EditMessage.InputValues("001", sender, "He lu");

		EditMessage.OutputValues output = useCase.execute(input);
		assertNotNull(output.getMessage());
		assertEquals(FinalResult.SUCCESSFUL, output.getResult());
	}
}
