package application.talk.domains;

import java.util.List;

public class PublicGroup implements Group {
	private List<User> _users;
	private String _groupName;

	public PublicGroup(List<User> users, String groupName) {
		super();
		_users = users;
		_groupName = groupName;
	}

	@Override
	public List<User> getUsers() {
		return _users;
	}

	@Override
	public void setName(String name) {
		_groupName = name;
	}

	@Override
	public String getName() {
		return _groupName;
	}

}
