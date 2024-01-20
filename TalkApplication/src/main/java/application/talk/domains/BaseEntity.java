package application.talk.domains;

import java.util.UUID;

public abstract class BaseEntity {
	private String id;

	public String getId() {
		return id;
	}

	protected BaseEntity() {
		id = UUID.randomUUID().toString();
	}
}
