package application.talk.usecases.adapters;

import application.talk.domains.Conversation;
import application.talk.domains.Group;
import application.talk.domains.Message;
import application.talk.domains.RecordedMessage;
import application.talk.domains.Request;
import application.talk.domains.User;

public interface DataStorage {
    Repository<User> getUsers();

    Repository<Group> getGroups();

    Repository<Conversation> getConversations();

    Repository<Request> getRequests();

    Repository<Message> getMessages();

    Repository<RecordedMessage> getRecordMessages();

    void cleanAll();
}
