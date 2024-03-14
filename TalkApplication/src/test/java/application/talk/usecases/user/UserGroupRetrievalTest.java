package application.talk.usecases.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import application.talk.domains.Group;
import application.talk.domains.PublicGroup;
import application.talk.domains.User;
import application.talk.enums.FinalResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.talk.infrastructure.data.InMemoryDataStorage;
import application.talk.usecases.adapters.DataStorage;

public class UserGroupRetrievalTest {
    private DataStorage _storage;
    private User testedUser;

    @Before
    public void setUp() throws Exception {
        _storage = InMemoryDataStorage.getInstance();

        testedUser = new User("trong", "123");
        _storage.getUsers().add(testedUser);

        Group testGroup = new PublicGroup("group hoc tap", "123");
        testGroup.addUser(testedUser);
        _storage.getGroups().add(testGroup);
    }

    @After
    public void tearDown() throws Exception {
        _storage.cleanAll();
    }

    @Test
    public void testGetGroupsOfUser() {
        UserGroupRetrieval useCase = new UserGroupRetrieval(_storage);

        UserGroupRetrieval.InputValues input = new UserGroupRetrieval.InputValues(testedUser.getId());

        UserGroupRetrieval.OutputValues output = useCase.execute(input);
        assertEquals(FinalResult.SUCCESSFUL, output.getResult());
    }

    @Test
    public void testGetGroupsOfUserWithoutName() {
        DataStorage storage = InMemoryDataStorage.getInstance();
        UserGroupRetrieval useCase = new UserGroupRetrieval(storage);

        UserGroupRetrieval.InputValues input = new UserGroupRetrieval.InputValues(null);

        UserGroupRetrieval.OutputValues output = useCase.execute(input);
        assertTrue(output.getFoundGroups().isEmpty());
    }
}