package application.talk.usecases.user;

import static org.junit.Assert.assertEquals;

import application.talk.enums.FinalResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.talk.domains.User;
import application.talk.infrastructure.data.InMemoryDataStorage;
import application.talk.usecases.adapters.DataStorage;
import application.talk.usecases.adapters.Hasher;
import application.talk.usecases.user.UserLogin.InputValues;

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
        UserLogin.InputValues input = new InputValues("kiet", "0710");
        DataStorage storage = InMemoryDataStorage.getInstance();

        UserLogin login = new UserLogin(storage, new Hasher() {
            @Override
            public String hash(String orginal) {
                return null;
            }
        });

        UserLogin.OutputValues output = login.execute(input);
        assertEquals(output.getResult(), FinalResult.SUCCESSFUL);
    }
}
