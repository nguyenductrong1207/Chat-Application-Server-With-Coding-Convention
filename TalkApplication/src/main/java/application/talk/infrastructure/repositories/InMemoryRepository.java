package application.talk.infrastructure.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import application.talk.domains.BaseEntity;
import application.talk.usecases.adapters.Repository;

public class InMemoryRepository<T extends BaseEntity> implements Repository<T> {
	private List<T> enities;
	public static int idCounter = 1;

	public InMemoryRepository() {
		enities = new ArrayList<>();
	}

	@Override
	public T getById(String id) {
		Optional<T> entity = enities.stream().filter(e -> e.getId().equals(id)).findFirst();

		return entity.get();
	}

	@Override
	public boolean add(T entity) {
		if (entity == null) {
			return false;
		}

		enities.add(entity);

		return true;
	}

	@Override
	public void deleteAll() {
		enities.clear();
	}

	@Override
	public T getFirst(Predicate<T> predicate) {
		Optional<T> entity = enities.stream().filter(predicate).findFirst();

		return entity.isPresent() ? entity.get() : null;
	}
}
