package application.talk.usecases.group;

import application.talk.domains.User;
import application.talk.enums.FinalResult;
import application.talk.infrastructure.data.InMemoryDataStorage;
import application.talk.usecases.adapters.DataStorage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JoiningPrivateGroupTest {

    DataStorage _storage;
    JoiningPrivateGroup _useCase;

    @Before
    public void setUp() throws Exception {
        _storage = InMemoryDataStorage.getInstance();
        _useCase = new JoiningPrivateGroup(_storage);
    }

    @After
    public void tearDown() throws Exception {
        _storage.cleanAll();
    }

    @Test
    public void testJoiningPrivateGroup() {
        User inviter = new User("trong", "1207");
        User receiver = new User("trong2", "1910");

        JoiningPrivateGroup.InputValues input = new JoiningPrivateGroup.InputValues(inviter, receiver);
        JoiningPrivateGroup.OutputValues output = _useCase.execute(input);

        assertEquals(FinalResult.SUCCESSFUL, output.getResult());
    }


}
