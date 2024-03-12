package application.talk.usecases.message;

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

			return new OutputValues(EditMessageResult.SUCCESSED, "");
		} else {
			return new OutputValues(EditMessageResult.FAILED, "Unable to edit the message.");
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
		private final EditMessageResult RESULT;
		private final String MESSAGE;

		public OutputValues(EditMessageResult result, String message) {
			MESSAGE = message;
			RESULT = result;
		}

		public EditMessageResult getResult() {
			return RESULT;
		}

		public String getMessage() {
			return MESSAGE;
		}
	}

	public enum EditMessageResult {
		SUCCESSED, FAILED
	}
}
