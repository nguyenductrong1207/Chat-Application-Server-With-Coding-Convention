package application.talk.usecases.message;

import java.util.List;
import application.talk.domains.Conversation;
import application.talk.domains.PrivateGroup;
import application.talk.domains.User;
import application.talk.usecases.UseCase;
import application.talk.usecases.adapters.DataStorage;

public class AdminDeleteMessage extends UseCase<AdminDeleteMessage.InputValues, AdminDeleteMessage.OutputValues> {
	private DataStorage _dataStorage;
	private DeleteMessage _deleteMessage;

	public AdminDeleteMessage(DataStorage dataStorage, DeleteMessage deleteMessage) {
		super();
		_dataStorage = dataStorage;
		_deleteMessage = deleteMessage;
	}

	@Override
	public OutputValues execute(InputValues input) {
		PrivateGroup group = (PrivateGroup) _dataStorage.getGroups().getById(input._groupId);
        Conversation conversation = _dataStorage.getConversations().getById(input._conversationId);
		List<User> admins = group.getAdmins();

		for (User admin : admins) {
			if (admin.getId().equals(input._userId)) {
	            _deleteMessage.removeMessageById(conversation, input._messageId);
				return new OutputValues(CreatingResult.SUCCESSFUL, "");
			}
		}
	
		return new OutputValues(CreatingResult.FAILED, "");
	}

	public static class InputValues {
		private String _userId;
		private String _conversationId;
		private String _groupId;
		private String _messageId;

		public InputValues(String userId, String conversationId, String groupId, String messageId) {
			super();
			_userId = userId;
			_conversationId = conversationId;
			_groupId = groupId;
			_messageId = messageId;
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
