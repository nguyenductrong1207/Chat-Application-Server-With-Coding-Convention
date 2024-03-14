package application.talk.usecases.message;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import application.talk.enums.FinalResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.talk.domains.User;
import application.talk.infrastructure.data.InMemoryDataStorage;
import application.talk.usecases.adapters.DataStorage;

public class MessageEditingTest {
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
    public void testEditMessageWithMessage() {
        DataStorage storage = InMemoryDataStorage.getInstance();
        User sender = new User("trong", "1207");

        MessageEditing useCase = new MessageEditing(storage);
        MessageEditing.InputValues input = new MessageEditing.InputValues(null, sender, "He lu");

        MessageEditing.OutputValues output = useCase.execute(input);
        assertNotNull(output.getMessage());
        assertEquals(FinalResult.FAILED, output.getResult());
    }
}