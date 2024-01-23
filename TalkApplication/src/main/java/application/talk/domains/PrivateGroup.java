package application.talk.domains;

import java.util.List;

public class PrivateGroup implements Group {
	private List<User> _admins;
	private List<User> _members;
	private String _groupName;

	public PrivateGroup(List<User> admins, List<User> members, String groupName) {
		super();
		_admins = admins;
		this._members = members;
		this._groupName = groupName;
	}

	@Override
	public List<User> getUsers() {
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
