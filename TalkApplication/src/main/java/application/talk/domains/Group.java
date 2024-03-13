package application.talk.domains;

import application.talk.enums.GroupType;

import java.util.ArrayList;
import java.util.List;

public abstract class Group extends ChatEntity {
	private List<User> _users;
	private List<File> _files;
	private String _name;
	private GroupType _groupType;

	public Group(String name, GroupType groupType) {
		super();
		_users = new ArrayList<>();
		_files = new ArrayList<>();
		_name = name;
		_groupType = groupType;
	}

	public void addUser(User user) {
		_users.add(user);
	}

	public void addAllUser(List<User> users){
		_users.addAll(users);
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

	public GroupType getGroupType() {
		return _groupType;
	}

	public void setGroupType(GroupType groupType) {
		_groupType = groupType;
	}
}
