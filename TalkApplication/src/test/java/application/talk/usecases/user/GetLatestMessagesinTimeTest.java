package application.talk.usecases.user;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.talk.domains.Message;
import application.talk.infrastructure.data.InMemoryDataStorage;
import application.talk.usecases.adapters.DataStorage;
import application.talk.usecases.user.GetLastestMessagesInTime.GetLastestMessagesResult;

public class GetLatestMessagesinTimeTest {

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
	public void testGetMessages_nullResult() {
		DataStorage storage = InMemoryDataStorage.getInstance();

		GetLastestMessagesInTime useCase = new GetLastestMessagesInTime(storage);
		List<Message> messages = new ArrayList<Message>();
		LocalDateTime currentTime = LocalDateTime.now();

		GetLastestMessagesInTime.InputValues input = new GetLastestMessagesInTime.InputValues(messages, currentTime);
		GetLastestMessagesInTime.OutputValues output = useCase.execute(input);

		assertEquals(GetLastestMessagesInTime.GetLastestMessagesResult.FAILED, output.getResult());

	}
}
