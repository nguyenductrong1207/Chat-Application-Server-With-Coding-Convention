package application.talk.domains;

import java.util.List;

public class PrivateGroup extends Group {
	private List<User> _users;
	private List<User> _admins;
	private String _name;
	
	public PrivateGroup( List<User> users, String name) {
		super(users, name);
		_users = users;
		_name = name;
	}
	
	public void addAdmin(User user) {
		_admins.add(user);
	}
}
