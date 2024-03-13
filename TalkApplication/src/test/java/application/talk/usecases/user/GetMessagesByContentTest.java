//package application.talk.usecases.user;
//
//import application.talk.domains.ChatEntity;
//import application.talk.domains.Message;
//import application.talk.domains.User;
//import application.talk.infrastructure.data.InMemoryDataStorage;
//import application.talk.usecases.adapters.DataStorage;
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//
//import java.time.LocalDateTime;
//
//import static org.junit.Assert.assertEquals;
//
//public class GetMessagesByContentTest {
//    private DataStorage _storage;
//    private GetMessagesByContent _useCase;
//
//    @Before
//    public void setUp() throws Exception {
//        _storage = InMemoryDataStorage.getInstance();
//        _useCase = new GetMessagesByContent(_storage);
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        _storage.cleanAll();
//    }
//
//    @Test
//    public void testGetMessagesEmptyResult() {
//        GetMessagesByContent.InputValues input = new GetMessagesByContent.InputValues("","0");
//        GetMessagesByContent.OutputValues output = _useCase.execute(input);
//
//        assertEquals(GetMessagesByTime.GetLastestMessagesResult.FAILED, output.getResult());
//    }
//
//    @Test
//    public void testGetMessageValid() {
//        User sender = new User("abc", "abc");
//        ChatEntity receiverUser = new User("def", "456");
//        Message newMessage = new Message(sender, LocalDateTime.now(), receiverUser, "hello");
////        receiverUser.addMessage(newMessage);
//
//        _storage.getMessages().add(newMessage);
//
//        GetMessagesByContent.InputValues input = new GetMessagesByContent.InputValues("he",receiverUser.getId());
//        GetMessagesByContent.OutputValues output = _useCase.execute(input);
//
//        assertEquals("hello", output.getfoundMessages().get(0).getContent());
//    }
//}
