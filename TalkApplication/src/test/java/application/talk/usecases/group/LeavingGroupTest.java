package application.talk.usecases.group;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import application.talk.domains.ChatEntity;
import application.talk.domains.PublicGroup;
import application.talk.enums.FinalResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.talk.domains.Group;
import application.talk.domains.User;
import application.talk.infrastructure.data.InMemoryDataStorage;
import application.talk.usecases.adapters.DataStorage;

public class LeavingGroupTest {
    @Before
    public void setUp() throws Exception {
        DataStorage storage = InMemoryDataStorage.getInstance();
    }

    @After
    public void tearDown() throws Exception {
        DataStorage storage = InMemoryDataStorage.getInstance();
        storage.cleanAll();
    }

    @Test
    public void testLeavingGroup() {
        DataStorage storage = InMemoryDataStorage.getInstance();
        LeavingGroup useCase = new LeavingGroup(storage);

        ChatEntity group = new PublicGroup("Mygroup", "123456");
        User user = new User("trong", "1207");
        storage.getGroups().add((Group) group);

        LeavingGroup.InputValues input = new LeavingGroup.InputValues(group.getId(), user.getId());

        LeavingGroup.OutputValues output = useCase.execute(input);
        assertEquals(FinalResult.SUCCESSFUL, output.getResult());
        assertNotNull(output.getMessage());
    }

    @Test
    public void testLeavingGroupWithNullGroup() {
        DataStorage storage = InMemoryDataStorage.getInstance();
        LeavingGroup useCase = new LeavingGroup(storage);

        ChatEntity group = new PublicGroup("group123", "123456");
        User user = new User("kiet", "0710");

        LeavingGroup.InputValues input = new LeavingGroup.InputValues(group.getId(), user.getId());

        LeavingGroup.OutputValues output = useCase.execute(input);
        assertEquals(FinalResult.FAILED, output.getResult());
        assertNotNull(output.getMessage());
    }
}
