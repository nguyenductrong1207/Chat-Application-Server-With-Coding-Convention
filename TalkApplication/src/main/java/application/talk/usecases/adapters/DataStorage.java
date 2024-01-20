package application.talk.usecases.adapters;

import application.talk.domains.User;

public interface DataStorage {
    Repository<User> getUsers();

    void cleanAll();
}
