package application.talk.infrastructure.data;


import application.talk.domains.*;
import application.talk.infrastructure.repositories.InMemoryRepository;
import application.talk.usecases.adapters.DataStorage;
import application.talk.usecases.adapters.Repository;

public class InMemoryDataStorage implements DataStorage {
    private Repository<User> _users;
    private Repository<Group> _groups;
    private Repository<Message> _messages;
    private Repository<Conversation> _conversation;
    private Repository<Request> _request;
    private Repository<RecordedMessage> _recordMessages;
    private static InMemoryDataStorage storage;

    private InMemoryDataStorage() {
        _users = new InMemoryRepository<User>();
        _groups = new InMemoryRepository<Group>();
        _messages = new InMemoryRepository<Message>();
        _conversation = new InMemoryRepository<Conversation>();
        _request = new InMemoryRepository<Request>();
        _recordMessages = new InMemoryRepository<RecordedMessage>();
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
    public Repository<Conversation> getConversations() {
        return _conversation;
    }

    @Override
    public Repository<Request> getRequests() {
        return _request;
    }

    @Override
    public Repository<Message> getMessages() {
        return _messages;
    }

    @Override
    public Repository<RecordedMessage> getRecordMessages() {
        return _recordMessages;
    }

    @Override
    public void cleanAll() {
        _users.deleteAll();
        _groups.deleteAll();
        _messages.deleteAll();
        _conversation.deleteAll();
        _request.deleteAll();
    }

    @Override
    public boolean remove(Message message) {
        // TODO Auto-generated method stub
        return false;
    }
}
