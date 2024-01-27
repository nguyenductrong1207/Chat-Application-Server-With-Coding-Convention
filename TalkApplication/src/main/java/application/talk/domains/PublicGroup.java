package application.talk.domains;

import java.util.ArrayList;
import java.util.List;

public class PublicGroup extends Group {
	private List<User> _users = new ArrayList<>();
	private String _name;
	private String _joinCode;
	
	public PublicGroup( String name, String joinCode) {
		super(name);
		_name = name;
		_joinCode = joinCode;
	}

	public String getJoinCode() {
		return _joinCode;
	}
}
