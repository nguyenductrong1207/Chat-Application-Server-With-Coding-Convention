package application.talk.infrastructure.data;


import application.talk.domains.ChatEntity;
import application.talk.domains.Conversation;
import application.talk.domains.File;
import application.talk.domains.Group;
import application.talk.domains.Message;
import application.talk.domains.Request;
import application.talk.domains.User;
import application.talk.infrastructure.repositories.InMemoryRepository;
import application.talk.usecases.adapters.DataStorage;
import application.talk.usecases.adapters.Repository;

public class InMemoryDataStorage implements DataStorage {
    private Repository<User> _users;
    private Repository<Group> _groups;
    private Repository<Message> _messages;
    private Repository<Conversation> _conversation;
    private Repository<Request> _request;
    private static InMemoryDataStorage storage;

    private InMemoryDataStorage() {
        _users = new InMemoryRepository<User>();
        _groups = new InMemoryRepository<Group>();
        _messages = new InMemoryRepository<Message>();
        _conversation = new InMemoryRepository<Conversation>();
        _request = new InMemoryRepository<Request>();
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
    public Repository<Conversation> getConversations() {
        return _conversation;
    }

    @Override
    public Repository<Request> getRequests() {
        return _request;
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

    @Override
    public Repository<ChatEntity> getChatEntities() {
        // TODO Auto-generated method stub
        return null;
    }

}
