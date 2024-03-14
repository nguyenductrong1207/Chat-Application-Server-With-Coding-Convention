package application.talk.usecases.group;

import application.talk.domains.Group;
import application.talk.domains.User;
import application.talk.enums.FinalResult;
import application.talk.usecases.UseCase;
import application.talk.usecases.adapters.DataStorage;

public class GroupExit extends UseCase<GroupExit.InputValues, GroupExit.OutputValues> {
	private DataStorage _dataStorage;

	public GroupExit(DataStorage dataStorage) {
		_dataStorage = dataStorage;
	}

	@Override
	public OutputValues execute(InputValues input) {
		Group group = _dataStorage.getGroups().getById(input._groupId);

		if (group == null) {
			return new OutputValues(FinalResult.FAILED, "");
		}

		group.removeUserById(input._userId);

		return new OutputValues(FinalResult.SUCCESSFUL, "");
	}

	public static class InputValues {
		private String _groupId;
		private String _userId;

		public InputValues(String groupId, String userId) {
			_userId = userId;
			_groupId = groupId;
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
