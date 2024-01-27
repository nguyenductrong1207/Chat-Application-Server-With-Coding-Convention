package application.talk.usecases.user;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import application.talk.domains.User;

public class CreatingGroupTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void testGeneratingCode() {
		CreatingGroup joinCode = new CreatingGroup(true, null, null);
		joinCode.generateCode();
		assertNotNull(joinCode);
	}

	@Test
	public void testCreateAdmin() {
		User user1 = new User("kiet", "123456");
		CreatingGroup createAdmin = new CreatingGroup(false, null, user1 );
		assertNotNull(createAdmin);
	}
}
