package application.talk.domains;

import java.time.LocalDate;

public class File extends BaseEntity {
	private String _name;
	private long _size;
	private LocalDate _createdDate;
	private Type _type;
	
	public enum Type{
		IMAGE, VIDEO, AUDIO;
	}

	public File(String name, long size, Type type) {
		super();
		_name = name;
		_size = size;
		_type = type;
		_createdDate = LocalDate.now();
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public long getSize() {
		return _size;
	}

	public LocalDate getDate() {
		return _createdDate;
	}

	public Type getType() {
		return _type;
	}
	
	
}
