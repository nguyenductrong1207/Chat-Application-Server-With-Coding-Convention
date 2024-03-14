package application.talk.usecases.group;

import static org.junit.Assert.assertEquals;

import application.talk.enums.FinalResult;
import application.talk.enums.GroupType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.talk.domains.User;
import application.talk.infrastructure.data.InMemoryDataStorage;
import application.talk.usecases.adapters.DataStorage;

public class GroupCreationTest {
    private DataStorage _storage;
    private GroupCreation _useCase;

    @Before
    public void setUp() throws Exception {
        _storage = InMemoryDataStorage.getInstance();
        _useCase = new GroupCreation(_storage);
    }

    @After
    public void tearDown() throws Exception {
        DataStorage storage = InMemoryDataStorage.getInstance();
        storage.cleanAll();
    }

    @Test
    public void testCreatingPublicGroup() {
        User user1 = new User("kiet", "0710");

        GroupCreation.InputValues input = new GroupCreation.InputValues(GroupType.PUBLICGROUP, user1, "Public group");

        GroupCreation.OutputValues output = _useCase.execute(input);
        assertEquals(FinalResult.SUCCESSFUL, output.getResult());
    }

    @Test
    public void testCreatingPrivateGroup() {
        User user1 = new User("kiet", "0710");

        GroupCreation.InputValues input = new GroupCreation.InputValues(GroupType.PRIVATEGROUP, user1, "Private group");

        GroupCreation.OutputValues output = _useCase.execute(input);
        assertEquals(FinalResult.SUCCESSFUL, output.getResult());
    }
}