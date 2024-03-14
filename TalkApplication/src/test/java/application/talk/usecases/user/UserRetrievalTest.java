package application.talk.usecases.user;

import static org.junit.Assert.assertEquals;

import application.talk.enums.FinalResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.talk.domains.User;
import application.talk.infrastructure.data.InMemoryDataStorage;
import application.talk.usecases.adapters.DataStorage;

public class UserRetrievalTest {
    private DataStorage _storage;
    private UserRetrieval _useCase;

    @Before
    public void setUp() throws Exception {
        _storage = InMemoryDataStorage.getInstance();
        _useCase = new UserRetrieval(_storage);
    }

    @After
    public void tearDown() throws Exception {
        DataStorage storage = InMemoryDataStorage.getInstance();
        storage.cleanAll();
    }

    @Test
    public void testFindingUser() {
        User user1 = new User("kiet", "123");
        user1.setFirstName("anh");
        user1.setLastName("kiet");

        User user2 = new User("kiet123", "456");
        user2.setFirstName("kiet");
        user2.setLastName("luong");

        _storage.getUsers().add(user1);
        _storage.getUsers().add(user2);

        UserRetrieval.InputValues input = new UserRetrieval.InputValues("kiet");
        UserRetrieval.OutputValues output = _useCase.execute(input);

        assertEquals(FinalResult.SUCCESSFUL, output.getResult());
    }
}