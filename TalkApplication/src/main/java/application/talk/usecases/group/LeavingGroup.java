package application.talk.usecases.group;

import application.talk.domains.Group;
import application.talk.domains.User;
import application.talk.enums.FinalResult;
import application.talk.usecases.UseCase;
import application.talk.usecases.adapters.DataStorage;

public class LeavingGroup extends UseCase<LeavingGroup.InputValues, LeavingGroup.OutputValues> {
	private DataStorage _dataStorage;

	public LeavingGroup(DataStorage dataStorage) {
		_dataStorage = dataStorage;
	}

	@Override
	public OutputValues execute(InputValues input) {
		String userId = input._user.getId();
		String groupId = input._group.getId();
		Group group = _dataStorage.getGroups().getById(groupId);

		if (group == null) {
			return new OutputValues(FinalResult.FAILED, "");
		}
		group.removeUserById(userId);

		return new OutputValues(FinalResult.SUCCESSFUL, "");
	}

	public static class InputValues {
		private Group _group;
		private User _user;

		public InputValues(Group group, User user) {
			_user = user;
			_group = group;
		}
	}

	public static class OutputValues {
		private final FinalResult RESULT;
		private final String MESSAGE;

		public OutputValues(FinalResult result, String message) {
			MESSAGE = message;
			RESULT = result;
		}

		public FinalResult getResult() {
			return RESULT;
		}

		public String getMessage() {
			return MESSAGE;
		}
	}
}
