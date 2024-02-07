package application.talk.usecases.user;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.talk.infastructure.data.InMemoryDataStorage;
import application.talk.usecases.adapters.DataStorage;
import application.talk.usecases.user.SeeingFile.CreatingResult;

public class SeeingFileTest {

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

		SeeingFile useCase = new SeeingFile(storage);
		SeeingFile.InputValues input = new SeeingFile.InputValues("123456");

		SeeingFile.OutputValues output = useCase.execute(input);
		assertNotNull(output.getMessage());
		assertEquals(CreatingResult.FAILED, output.getResult());
	}

}
