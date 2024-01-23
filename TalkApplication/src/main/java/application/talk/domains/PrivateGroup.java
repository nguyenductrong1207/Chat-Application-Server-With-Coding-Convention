package application.talk.domains;

import java.util.ArrayList;

public class PrivateGroup implements Group {
	private ArrayList<User> _admins;
	private ArrayList<User> _members;
	private String _groupName;

	public PrivateGroup(ArrayList<User> admins, ArrayList<User> members, String groupName) {
		super();
		_admins = admins;
		this._members = members;
		this._groupName = groupName;
	}

	@Override
	public ArrayList<User> getUsers() {
		return _members;
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
