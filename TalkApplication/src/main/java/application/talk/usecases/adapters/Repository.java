package application.talk.usecases.adapters;

import java.util.List;
import java.util.function.Predicate;

import application.talk.domains.BaseEntity;

public interface Repository<T extends BaseEntity> {
    T getById(String id);

    boolean add(T addingEntity);

    void deleteAll();

    T getFirst(Predicate<T> predicate);

    List<T> getAll();

    void removeByID(String id);
}
