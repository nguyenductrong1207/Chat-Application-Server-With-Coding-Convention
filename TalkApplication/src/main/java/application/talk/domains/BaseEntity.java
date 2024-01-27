package application.talk.domains;

import java.util.UUID;

public abstract class BaseEntity {
	private String _id;
	private String _name;

	public String getId() {
		return _id;
	}
	
	public String getName() {
		return _name;
	}
	
	public void setName(String name) {
		_name = name;
	}

	protected BaseEntity() {
		_id = UUID.randomUUID().toString();
	}
}
