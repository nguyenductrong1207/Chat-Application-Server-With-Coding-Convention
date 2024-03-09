package application.talk.usecases.adapters;

import application.talk.domains.ChatEntity;
import application.talk.domains.Conversation;
import application.talk.domains.Group;
import application.talk.domains.Message;
import application.talk.domains.Request;
import application.talk.domains.User;

public interface DataStorage {
    Repository<User> getUsers();
    
    Repository<Group> getGroups();

//    delete 2 classes
    Repository<ChatEntity> getChatEntities();
    
    Repository<Message> getMessages();

    Repository<Conversation> getConversations();

    Repository<Request> getRequests();
    
    void cleanAll();

	boolean remove(Message message);

}
