package application.talk.infastructure.data;

import application.talk.domains.Group;
import application.talk.domains.Message;
import application.talk.domains.User;
import application.talk.infrastructure.repositories.InMemoryRepository;
import application.talk.usecases.adapters.DataStorage;
import application.talk.usecases.adapters.Repository;

public class InMemoryDataStorage implements DataStorage {
	private Repository<User> _users;
	private Repository<Group> _groups;
	private Repository<Message> _messages;
    private static InMemoryDataStorage storage;
	
	private InMemoryDataStorage() {
		_users =  new InMemoryRepository<User>();
		_groups = new InMemoryRepository<Group>();
		_messages = new InMemoryRepository<Message>();
	}
	
	public static synchronized InMemoryDataStorage getInstance() {
        if (storage == null) {
            storage = new InMemoryDataStorage();
        }
        
        return storage;
    }

	@Override
	public Repository<User> getUsers() {
		return _users;
	}

	@Override
	public Repository<Group> getGroups() {
		return _groups;
	}
	
	@Override
	public Repository<Message> getMessages() {
		return _messages;
	}
	
	@Override
	public void cleanAll() {
		_users.deleteAll();
		_groups.deleteAll();
		_messages.deleteAll();
	}

	@Override
	public boolean remove(Message message) {
		// TODO Auto-generated method stub
		return false;
	}

}
