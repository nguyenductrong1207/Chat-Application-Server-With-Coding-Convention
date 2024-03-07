package application.talk.usecases.adapters;

import application.talk.domains.*;

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
