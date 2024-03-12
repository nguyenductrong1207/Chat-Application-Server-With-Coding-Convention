package application.talk.usecases.user;

import java.time.LocalDateTime;

import application.talk.domains.ChatEntity;
import application.talk.domains.File;
import application.talk.domains.File.Type;
import application.talk.domains.Message;
import application.talk.domains.User;
import application.talk.usecases.UseCase;
import application.talk.usecases.adapters.DataStorage;

public class SendingMessage extends UseCase<SendingMessage.InputValues, SendingMessage.OutputValues> {
	private DataStorage _dataStorage;
	public SendingMessage(DataStorage dataStorage) {
		_dataStorage = dataStorage;
	}

	@Override
	public OutputValues execute(InputValues input) {
		Message message = new Message(input._sender, LocalDateTime.now(), input._receiver, input._content);

		if (input._attachment != null) {
			File attachment = new File(input._attachment.length, input._type);
			message.setAttachment(attachment);
		}

		_dataStorage.getMessages().add(message);

		return new OutputValues(SendingResult.SUCCESSED, "");
	}

	public static class InputValues {
		private ChatEntity _receiver;
		private User _sender;
		private String _content;
		private byte[] _attachment;
		private Type _type;

		public InputValues(ChatEntity receiver, User sender, String content, byte[] attachment, Type type) {
			_receiver = receiver;
			_sender = sender;
			_content = content;
			_attachment = attachment;
			_type = type;
		}
	}

	public static class OutputValues {
		private final SendingResult RESULT;
		private final String MESSAGE;

		public OutputValues(SendingResult result, String message) {
			MESSAGE = message;
			RESULT = result;
		}

		public SendingResult getResult() {
			return RESULT;
		}

		public String getMessage() {
			return MESSAGE;
		}
	}

	public static enum SendingResult {
		SUCCESSED, FAILED
	}
}
