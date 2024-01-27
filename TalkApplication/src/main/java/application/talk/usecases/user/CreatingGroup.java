package application.talk.usecases.user;

import java.util.UUID;

import application.talk.domains.User;

public class CreatingGroup {
	private boolean _isPublic;
	private String _joinCode;
	private User _admin;

	public CreatingGroup(boolean isPublic, String joinCode, User admin) {
		super();
		_isPublic = isPublic;
		_joinCode = joinCode;
		_admin = admin;

		if (isPublic) {
			generateCode();
		} else {
			createAdmin(admin);
		}
	}

	public void generateCode() {
		_joinCode = UUID.randomUUID().toString().substring(0, 6);
	}

	public void createAdmin(User user) {
		_admin = user;
	}
}
