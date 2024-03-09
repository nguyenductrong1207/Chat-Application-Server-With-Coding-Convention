package application.talk.usecases.user;

import java.util.ArrayList;
import java.util.List;

import application.talk.domains.Message;
import application.talk.domains.PrivateGroup;
import application.talk.domains.User;
import application.talk.usecases.UseCase;
import application.talk.usecases.adapters.DataStorage;

public class RemovingMessages extends UseCase<RemovingMessages.InputValues, RemovingMessages.OutputValues> {
	private DataStorage _dataStorage;

	public RemovingMessages(DataStorage dataStorage) {
		super();
		_dataStorage = dataStorage;
	}

	@Override
	public OutputValues execute(InputValues input) {
		PrivateGroup group = (PrivateGroup) _dataStorage.getGroups().getById(input._groupId);

		Message message = _dataStorage.getMessages().getById(input._messageId);

		List<User> admins = group.getAdmins();
		if (admins == null) {
			admins = new ArrayList<User>();
		}
		admins.add(new User("", ""));
		
		System.out.println("Group: " + _dataStorage.getGroups());
		for (User user : admins) {
			if (user.getId().equals(input._userId)) {
				message.removeMessageById(input._messageId);
				return new OutputValues(CreatingResult.SUCCESSFUL, "");
			}
		}
		if (message != null && message.getSender().getId().equals(input._userId)) {
			message.removeMessageById(input._messageId);
			return new OutputValues(CreatingResult.SUCCESSFUL, "");
		}

		return new OutputValues(CreatingResult.FAILED, "");
	}

	public static class InputValues {
		private String _userId;
		private String _messageId;
		private String _groupId;

		public InputValues(String userId, String messageId, String groupId) {
			super();
			_userId = userId;
			_messageId = messageId;
			_groupId = groupId;
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
