package application.talk.usecases.group;

import application.talk.domains.Group;
import application.talk.domains.PrivateGroup;
import application.talk.domains.User;
import application.talk.enums.FinalResult;
import application.talk.infrastructure.data.InMemoryDataStorage;
import application.talk.usecases.adapters.DataStorage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PrivateGroupJoiningTest {
    private DataStorage _storage;
    private PrivateGroupJoining _useCase;
    private Group _newGroup;
    private User _admin;

    @Before
    public void setUp() throws Exception {
        _storage = InMemoryDataStorage.getInstance();
        _useCase = new PrivateGroupJoining(_storage);

        _admin = new User("kietluong", "hehe");
        _newGroup = new PrivateGroup("groupkin123", _admin);
        _storage.getGroups().add(_newGroup);
    }

    @After
    public void tearDown() throws Exception {
        _storage.cleanAll();
    }

    @Test
    public void testWrongAdminJoiningPrivateGroup() {
        User inviter = new User("trong", "1207");
        User receiver = new User("trong2", "1910");

        PrivateGroupJoining.InputValues input = new PrivateGroupJoining.InputValues(inviter.getId(), receiver.getId(), _newGroup.getId());
        PrivateGroupJoining.OutputValues output = _useCase.execute(input);

        assertEquals(FinalResult.FAILED, output.getResult());
    }

    @Test
    public void testJoiningPrivateGroup() {
        User receiver = new User("trong2", "1910");

        PrivateGroupJoining.InputValues input = new PrivateGroupJoining.InputValues(_admin.getId(), receiver.getId(), _newGroup.getId());
        PrivateGroupJoining.OutputValues output = _useCase.execute(input);

        assertEquals(FinalResult.SUCCESSFUL, output.getResult());
    }
}