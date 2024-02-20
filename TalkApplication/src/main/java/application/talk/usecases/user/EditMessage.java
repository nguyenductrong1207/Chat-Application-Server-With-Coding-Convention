package application.talk.usecases.user;

import java.time.LocalDateTime;

import application.talk.domains.File;
import application.talk.domains.Message;
import application.talk.domains.User;
import application.talk.usecases.UseCase;
import application.talk.usecases.adapters.DataStorage;

public class EditMessage extends UseCase<EditMessage.InputValues, EditMessage.OutputValues> {
	private DataStorage _dataStorage;

	public EditMessage(DataStorage dataStorage) {
		_dataStorage = dataStorage;
	}

	@Override
	public OutputValues execute(InputValues input) {
		Message message = _dataStorage.getMessages().getById(input._messageId);
		
		if (message != null && message.getSender().equals(input._sender)) {

			message.setContent(input._newContent);

			return new OutputValues(EditResult.SUCCESSED, "");
		} else {
			return new OutputValues(EditResult.FAILED, "Unable to edit the message.");
		}
	}

	public static class InputValues {
		private String _messageId;
		private User _sender;
		private String _newContent;

		public InputValues(String messageId, User sender, String newContent) {
			_messageId = messageId;
			_sender = sender;
			_newContent = newContent;
		}
	}

	public static class OutputValues {
		private final EditResult RESULT;
		private final String MESSAGE;

		public OutputValues(EditResult result, String message) {
			MESSAGE = message;
			RESULT = result;
		}

		public EditResult getResult() {
			return RESULT;
		}

		public String getMessage() {
			return MESSAGE;
		}
	}

	public enum EditResult {
		SUCCESSED, FAILED
	}
}
