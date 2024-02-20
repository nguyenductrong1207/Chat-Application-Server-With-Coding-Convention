package application.talk.domains;

import java.time.LocalDate;

public class File extends BaseEntity {
	private String _name;
	private long _size;
	private LocalDate _createdDate;
	private Type _type;
	private String _filePath;

	public enum Type {
		IMAGE, VIDEO, AUDIO;
	}

	public File(String name, long size, Type type) {
		super();
		_name = name;
		_size = size;
		_type = type;
		_createdDate = LocalDate.now();
		_filePath = generateFilePath();
	}

	private String generateFilePath() {
		String baseDirectory = "/Uploads/";
		String typeFolder;

		switch (_type) {
		case IMAGE: {
			typeFolder = "images";
			break;
		}
		case VIDEO: {
			typeFolder = "videos";
			break;
		}
		case AUDIO: {
			typeFolder = "audios";
			break;
		}
		default: {
			throw new IllegalArgumentException("Incorrect File Type!");
		}
		}

		return baseDirectory + typeFolder + "/" + _name;
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

	public String getFilePath() {
		return _filePath;
	}

}
