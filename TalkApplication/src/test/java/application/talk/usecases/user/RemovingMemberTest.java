package application.talk.usecases.user;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.talk.infastructure.data.InMemoryDataStorage;
import application.talk.usecases.adapters.DataStorage;
import application.talk.usecases.user.RemovingMember.CreatingResult;

public class RemovingMemberTest {

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
	public void testRemovingMember() {
		DataStorage storage = InMemoryDataStorage.getInstance();

		RemovingMember useCase = new RemovingMember(storage);
		RemovingMember.InputValues input = new RemovingMember.InputValues(false, "123456", "kiet");

		RemovingMember.OutputValues output = useCase.execute(input);
		assertNotNull(output.getMessage());
		assertEquals(CreatingResult.FAILED, output.getResult());
	}


}
