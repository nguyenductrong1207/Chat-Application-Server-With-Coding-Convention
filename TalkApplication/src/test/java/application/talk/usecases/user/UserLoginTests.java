package application.talk.usecases.user;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import application.talk.domains.User;
import application.talk.infastructure.data.InMemoryDataStorage;
import application.talk.usecases.adapters.DataStorage;
import application.talk.usecases.adapters.Hasher;
import application.talk.usecases.user.LoginUseCase.InputValues;
import application.talk.usecases.user.LoginUseCase.LoginResult;
import application.talk.usecases.user.UserRegistration.RegisterResult;

public class UserLoginTests {

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
	public void testLogin() {
		LoginUseCase.InputValues input = new InputValues("kiet","0710");
		DataStorage storage = InMemoryDataStorage.getInstance();

		LoginUseCase login = new LoginUseCase(storage, new Hasher() {
			
			@Override
			public String hash(String orginal) {
				return null;
			}
		});
		
		LoginUseCase.OutputValues output = login.execute(input);

		assertEquals(output.getResult(), LoginResult.Successed);
	}

}