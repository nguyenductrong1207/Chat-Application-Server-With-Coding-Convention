package application.talk.usecases.user;

import java.util.UUID;

import application.talk.domains.Group;
import application.talk.domains.PrivateGroup;
import application.talk.domains.PublicGroup;
import application.talk.domains.User;
import application.talk.usecases.UseCase;
import application.talk.usecases.adapters.DataStorage;

public class JoiningGroup extends UseCase<JoiningGroup.InputValues, JoiningGroup.OutputValues> {
	private DataStorage _dataStorage;

	public JoiningGroup(DataStorage dataStorage) {
		super();
		_dataStorage = dataStorage;
	}

	@Override
	public OutputValues execute(InputValues input) {
		Group group;

		if (input._isPublic) {

			PublicGroup publicGroup = null;
			if (input._joinCode.equals(publicGroup.getJoinCode())) {
				publicGroup.addUser(input._user);
			}

		} else {
			PrivateGroup privateGroup = null;

			if (equals(privateGroup.getAdmins().contains(input._inviteUser.getName()))) {
				privateGroup.addUser(input._receivedUser);
			}

		}

		return new OutputValues(CreatingResult.SUCCESSFUL, "");
	}

	public static class InputValues {
		private boolean _isPublic;
		private String _joinCode;
		private User _inviteUser;
		private User _receivedUser;
		private User _user;

		public InputValues(String joinCode, User user) {
			super();
			_joinCode = joinCode;
			_user = user;
			_isPublic = true;
		}

		public InputValues(User inviteUser, User receivedUser) {
			super();
			_inviteUser = inviteUser;
			_receivedUser = receivedUser;
			_isPublic = false;
		}
	}

	public static class OutputValues {
		private final CreatingResult RESULT;
		private final String MESSAGE;

		public OutputValues(CreatingResult result, String message) {
			MESSAGE = message;
			RESULT = result;
		}

		public CreatingResult getResult() {
			return RESULT;
		}

		public String getMessage() {
			return MESSAGE;
		}
	}

	public static enum CreatingResult {
		SUCCESSFUL, FAILED
	}
}
