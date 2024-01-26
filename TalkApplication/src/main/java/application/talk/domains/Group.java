package application.talk.domains;

import java.util.List;

public abstract class Group {
	private List<User> _users;
	private String _name;

	
	public Group(List<User> users, String name) {
		_users = users;
		_name = name;
	}

	public void addUser(User user) {
		_users.add(user);
	}
	
	public List<User> getUsers(){
		return _users;
	}
	
	public void setName(String name) {
		_name = name;
	}
	
	public String getName() {
		return _name;
	}
}
