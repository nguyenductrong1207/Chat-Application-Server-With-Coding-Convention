package application.talk.domains;

import java.util.ArrayList;
import java.util.List;

public abstract class Group extends BaseEntity {
	private List<User> _users;
	private String _name;

	public Group(String name) {
		super();
		_users = new ArrayList<>();
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

	public List<User> getUsers() {
		return _users;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getName() {
		return _name;
	}
}
