package application.talk.domains;

import java.util.List;

public class PrivateGroup extends Group {
	private List<User> _users;
	private List<User> _admins;
	private String _name;

	public PrivateGroup(String name, User admin) {
		super(name);
		_name = name;
		this._admins.add(admin);
	}

	public void addAdmin(User user) {
		_admins.add(user);
	}

	public List<User> getAdmins() {
		return _admins;
	}
}
