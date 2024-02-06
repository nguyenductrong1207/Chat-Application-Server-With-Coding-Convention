package application.talk.usecases.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import application.talk.infastructure.data.InMemoryDataStorage;
import application.talk.usecases.adapters.DataStorage;
import application.talk.usecases.user.FindingUser.FindingResult;
import application.talk.usecases.user.ListingGroup.RegisterResult;

public class ListingGroupTest {

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
	public void testListingGroup() {
		DataStorage storage = InMemoryDataStorage.getInstance();
		ListingGroup useCase = new ListingGroup(storage);

		ListingGroup.InputValues input = new ListingGroup.InputValues("trong");

		ListingGroup.OutputValues output = useCase.execute(input);
		assertEquals(RegisterResult.SUCCESSED, output.getResult());
		assertNotNull(output.getMessage());
	}

	@Test
	public void testListingGroupWithoutName() {
		DataStorage storage = InMemoryDataStorage.getInstance();
		ListingGroup useCase = new ListingGroup(storage);

		ListingGroup.InputValues input = new ListingGroup.InputValues(null);

		ListingGroup.OutputValues output = useCase.execute(input);
		assertEquals(RegisterResult.FAILED, output.getResult());
		assertEquals("Please Enter A Name To Listing!!!", output.getMessage());
	}

}
