package application.talk.usecases.user;


import application.talk.domains.Group;
import application.talk.usecases.UseCase;
import application.talk.usecases.adapters.DataStorage;

public class RemovingMember extends UseCase<RemovingMember.InputValues, RemovingMember.OutputValues> {
	private DataStorage _dataStorage;

	public RemovingMember(DataStorage dataStorage) {
		super();
		_dataStorage = dataStorage;
	}

	@Override
	public OutputValues execute(InputValues input) {
		String memberId = input._memberId;
		String groupName = input._groupName;
		Group group = _dataStorage.getGroups().getByName(groupName);

		if (group == null) {
			return new OutputValues(CreatingResult.FAILED, "Group does not exist");
		}

		if (input._isAdmin) {
			group.removeUserById(memberId);
		} else {
			return new OutputValues(CreatingResult.FAILED, "Only admin can remove member");
		}

		return new OutputValues(CreatingResult.SUCCESSFUL, "");
	}

	public static class InputValues {
		private boolean _isAdmin;
		private String _groupName;
		private String _memberId;

		public InputValues(boolean isAdmin, String groupName, String memberId) {
			super();
			_isAdmin = isAdmin;
			_groupName = groupName;
			_memberId = memberId;
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
