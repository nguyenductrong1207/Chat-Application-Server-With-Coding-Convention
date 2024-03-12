package application.talk.domains;

import java.util.ArrayList;
import java.util.List;

public abstract class Group extends ChatEntity {
	private List<User> _users;
	private List<File> _files;
	private String _name;

	public Group(String name) {
		super();
		_users = new ArrayList<>();
		_files = new ArrayList<File>();
		_name = name;
	}

	public void addUser(User user) {
		_users.add(user);
	}

	public void removeUserById(String id) {
		for (User user : _users) {
			if (user.getId().equals(id)) {
				_users.remove(user);

				return;
			}
		}
	}

	public User findUserByID(String id) {
		for (User user : _users) {
			if (user.getId().equals(id)) {
				return user;
			}
		}
		return null;
	}

	public List<User> getUsers() {
		return _users;
	}

	public List<File> getFiles() {
		return _files;
	}

	public String getName() {
		return _name;
	}
}
