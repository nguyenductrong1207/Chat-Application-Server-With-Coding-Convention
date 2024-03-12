package application.talk.usecases.message;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.talk.domains.File.Type;
import application.talk.domains.User;
import application.talk.infrastructure.data.InMemoryDataStorage;
import application.talk.usecases.adapters.DataStorage;
import application.talk.usecases.user.CreatingGroup.CreatingResult;

public class SendingMessageTest {

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
	public void testSendingMessage() {
		DataStorage storage = InMemoryDataStorage.getInstance();
		User user = new User("kiet", "0710");

		SendingMessage useCase = new SendingMessage(storage);
		SendingMessage.InputValues input = new SendingMessage.InputValues(null, user, null, null, null);

		SendingMessage.OutputValues output = useCase.execute(input);
		assertNotNull(output.getMessage());
		assertEquals(FinalResult.SUCCESSFUL, output.getResult());
	}
}
