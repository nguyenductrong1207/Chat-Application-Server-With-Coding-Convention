package application.talk.usecases.group;

import application.talk.domains.User;
import application.talk.enums.FinalResult;
import application.talk.infrastructure.data.InMemoryDataStorage;
import application.talk.usecases.adapters.DataStorage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JoiningPublicGroupTest {

    DataStorage _storage;
    JoiningPublicGroup _useCase;

    @Before
    public void setUp() throws Exception {
        _storage = InMemoryDataStorage.getInstance();
        _useCase = new JoiningPublicGroup(_storage);
    }

    @After
    public void tearDown() throws Exception {
        _storage.cleanAll();
    }

    @Test
    public void testJoiningPublicGroup() {
        User user = new User("trong", "1207");

        JoiningPublicGroup.InputValues input = new JoiningPublicGroup.InputValues(user, "1910");
        JoiningPublicGroup.OutputValues output = _useCase.execute(input);

        assertEquals(FinalResult.SUCCESSFUL, output.getResult());
    }


}
