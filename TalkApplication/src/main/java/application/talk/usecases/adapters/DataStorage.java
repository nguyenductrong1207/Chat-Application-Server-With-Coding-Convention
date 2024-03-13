package application.talk.usecases.adapters;

import application.talk.domains.*;

public interface DataStorage {
    Repository<User> getUsers();
    
    Repository<Group> getGroups();

    Repository<Conversation> getConversations();

    Repository<Request> getRequests();

    Repository<Message> getMessages();

    Repository<RecordedMessage> getRecordMessages();
    
    void cleanAll();
}
