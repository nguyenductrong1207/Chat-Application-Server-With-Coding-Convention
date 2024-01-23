package application.talk.domains;

import java.util.List;

public class PublicGroup extends Group {
	private List<User> _users;
	private String _name;
	
	public PublicGroup( List<User> users, String name) {
		super(users, name);
		_users = users;
		_name = name;
	}
	
}
