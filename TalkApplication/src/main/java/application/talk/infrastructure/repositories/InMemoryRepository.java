package application.talk.infrastructure.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import application.talk.domains.BaseEntity;
import application.talk.usecases.adapters.Repository;

public class InMemoryRepository<T extends BaseEntity> implements Repository<T> {
	private List<T> entities;
	public static int idCounter = 1;

	public InMemoryRepository() {
		entities = new ArrayList<>();
	}

	public List<T> getEnities() {
		return entities;
	}

	@Override
	public T getById(String id) {
		Optional<T> entity = entities.stream().filter(e -> e.getId().equals(id)).findFirst();

		if (!entity.isPresent()) {
			return null;
		}
		return entity.get();
	}

	@Override
	public T getByName(String name) {
		Optional<T> entity = entities.stream().filter(e -> {
			String entityName = e.getName();
			return entityName != null && entityName.equalsIgnoreCase(name);
		}).findFirst();

		return entity.orElse(null);
	}

	@Override
	public boolean add(T entity) {
		if (entity == null) {
			return false;
		}

		entities.add(entity);

		return true;
	}

	@Override
	public void deleteAll() {
		entities.clear();
	}

	@Override
	public T getFirst(Predicate<T> predicate) {
		Optional<T> entity = entities.stream().filter(predicate).findFirst();

		return entity.isPresent() ? entity.get() : null;
	}

	@Override
	public List<T> getAll() {
		return entities;
	}

}
