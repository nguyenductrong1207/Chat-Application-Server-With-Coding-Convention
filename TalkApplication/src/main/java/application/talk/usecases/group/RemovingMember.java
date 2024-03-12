package application.talk.usecases.group;

import java.util.List;

import application.talk.domains.PrivateGroup;
import application.talk.domains.User;
import application.talk.enums.FinalResult;
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
		PrivateGroup group = (PrivateGroup) _dataStorage.getGroups().getById(input._groupId);
		List<User> admins = group.getAdmins();

		for (User admin : admins) {
			if (admin.getId().equals(input._userId)) {
				group.removeUserById(input._memberId);
				return new OutputValues(FinalResult.SUCCESSFUL, "");
			}
		}
		return new OutputValues(FinalResult.FAILED, "");
	}

	public static class InputValues {
		private String _userId;
		private String _groupId;
		private String _memberId;

		public InputValues(String userId, String groupId, String memberId) {
			super();
			_userId = userId;
			_groupId = groupId;
			_memberId = memberId;
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
