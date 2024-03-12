package application.talk.usecases.group;

import java.util.UUID;

import application.talk.domains.Group;
import application.talk.domains.PrivateGroup;
import application.talk.domains.PublicGroup;
import application.talk.domains.User;
import application.talk.usecases.UseCase;
import application.talk.usecases.adapters.DataStorage;

public class CreatingGroup extends UseCase<CreatingGroup.InputValues, CreatingGroup.OutputValues> {
	private DataStorage _dataStorage;

	public CreatingGroup(DataStorage dataStorage) {
		super();
		_dataStorage = dataStorage;
	}

	@Override
	public OutputValues execute(InputValues input) {
		Group group;

		if (input._isPublic) {
			group = new PublicGroup(input._name, input._joinCode);
		} else {
			group = new PrivateGroup(input._name, input._admin);
		}

		_dataStorage.getGroups().add(group);

		return new OutputValues(CreatingResult.SUCCESSFUL, "");
	}

	public static class InputValues {
		private boolean _isPublic;
		private String _joinCode;
		private User _admin;
		private String _name;

		public InputValues(boolean isPublic, User admin, String name) {
			super();
			_isPublic = isPublic;
			_joinCode = UUID.randomUUID().toString().substring(0, 6);
			_admin = admin;
			_name = name;

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

	public enum CreatingResult {
		SUCCESSFUL, FAILED
	}
}
