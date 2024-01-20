package application.talk.domains;

import java.util.ArrayList;

public class PublicGroup implements Group {
	private ArrayList<User> _users;
	private String _groupName;

	public PublicGroup(ArrayList<User> users, String groupName) {
		_users = users;
		_groupName = groupName;
	}

	@Override
	public ArrayList<User> getUsers() {
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
