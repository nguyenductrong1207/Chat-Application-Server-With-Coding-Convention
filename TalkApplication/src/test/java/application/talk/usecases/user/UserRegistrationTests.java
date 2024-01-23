package application.talk.usecases.user;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.chat.infrastructure.services.MD5Hasher;

import application.talk.domains.User;
import application.talk.infastructure.data.InMemoryDataStorage;
import application.talk.usecases.adapters.DataStorage;
import application.talk.usecases.adapters.Hasher;
import application.talk.usecases.user.UserRegistration.RegisterResult;

public class UserRegistrationTests {

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
	public void testRegistration() {
		UserRegistration.InputValues input = new UserRegistration.InputValues("kiet", "0710");
		DataStorage storage = InMemoryDataStorage.getInstance();

		UserRegistration registration = new UserRegistration(storage, new Hasher() {

			@Override
			public String hash(String orginal) {
				return null;
			}
		});

		UserRegistration.OutputValues output = registration.execute(input);

		assertEquals(output.getResult(), RegisterResult.SUCCESSED);
	}

}
