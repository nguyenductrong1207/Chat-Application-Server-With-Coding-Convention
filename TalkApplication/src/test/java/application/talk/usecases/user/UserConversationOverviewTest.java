package application.talk.usecases.user;

import application.talk.domains.Conversation;
import application.talk.domains.PrivateGroup;
import application.talk.domains.PublicGroup;
import application.talk.domains.User;
import application.talk.enums.FinalResult;
import application.talk.infrastructure.data.InMemoryDataStorage;
import application.talk.usecases.adapters.DataStorage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserConversationOverviewTest {
    DataStorage _storage;
    UserConversationOverview _useCase;

    @Before
    public void setUp() throws Exception {
        _storage = InMemoryDataStorage.getInstance();
        _useCase = new UserConversationOverview(_storage);
    }

    @After
    public void tearDown() throws Exception {
        _storage.cleanAll();
    }

    @Test
    public void testSeeAllConversationOfUser() {
        User user = new User("Trong", "123456");
        User receiver = new User("test", "1910");
        User user2 = new User("test2", "1234");

        PrivateGroup privateGroup = new PrivateGroup("group 1", user2);
        privateGroup.addUser(user);

        PublicGroup publicGroup = new PublicGroup("group 2", "123456");
        publicGroup.addUser(user);
        publicGroup.addUser(receiver);

        Conversation conversation1 = new Conversation(user, receiver);
        Conversation conversation2 = new Conversation(user, user2);

        UserConversationOverview.InputValues input = new UserConversationOverview.InputValues(user.getId());
        UserConversationOverview.OutputValues output = _useCase.execute(input);

        assertEquals(FinalResult.SUCCESSFUL, output.getResult());
    }
}
