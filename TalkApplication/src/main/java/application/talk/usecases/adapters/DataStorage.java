package application.talk.usecases.adapters;

import application.talk.domains.Group;
import application.talk.domains.Message;
import application.talk.domains.User;

public interface DataStorage {
    Repository<User> getUsers();
    
    Repository<Group> getGroups();
    
    Repository<Message> getMessages();
    
    void cleanAll();

	boolean remove(Message message);
}
