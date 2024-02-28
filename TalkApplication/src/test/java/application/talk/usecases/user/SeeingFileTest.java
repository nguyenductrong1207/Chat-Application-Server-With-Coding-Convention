package application.talk.usecases.user;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.talk.domains.File;
import application.talk.domains.File.Type;
import application.talk.infrastructure.data.InMemoryDataStorage;
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
	public void testSeeingFile() {
		DataStorage storage = InMemoryDataStorage.getInstance();

		SeeingFile useCase = new SeeingFile(storage);
		List<File> files = new ArrayList<File>();
		File images = new File(10, Type.IMAGE);
		files.add(images);
		SeeingFile.InputValues input = new SeeingFile.InputValues("123456", files);

		SeeingFile.OutputValues output = useCase.execute(input);
		assertEquals(CreatingResult.FAILED, output.getResult());
	}

}
