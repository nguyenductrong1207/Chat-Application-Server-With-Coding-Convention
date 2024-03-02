package application.talk.usecases.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.talk.domains.User;
import application.talk.infrastructure.data.InMemoryDataStorage;
import application.talk.usecases.adapters.DataStorage;
import application.talk.usecases.user.DeleteMessage.DeleteMessageResult;

public class DeleteMessageTest {

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
	public void testDeleteMessage() {
		DataStorage storage = InMemoryDataStorage.getInstance();
		User sender = new User("trong", "1207");

		DeleteMessage useCase = new DeleteMessage(storage);
		DeleteMessage.InputValues input = new DeleteMessage.InputValues("001", sender);

		DeleteMessage.OutputValues output = useCase.execute(input);
		assertNotNull(output.getMessage());
		assertEquals(DeleteMessageResult.FAILED, output.getResult());
	}

}
