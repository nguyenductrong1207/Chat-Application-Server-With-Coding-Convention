package application.talk.usecases.adapters;

import java.util.function.Predicate;
import application.talk.domains.BaseEntity;

public interface Repository<T extends BaseEntity> {
    T getById(String id);
    
    T getByName(String name);

    boolean add(T addingEntity);

    void deleteAll();

    T getFirst(Predicate<T> predicate);
}
