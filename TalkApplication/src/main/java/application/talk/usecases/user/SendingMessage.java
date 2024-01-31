package application.talk.usecases.user;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDateTime;

import application.talk.domains.ChatEntity;
import application.talk.domains.File;
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

		if (input._attachments.length > 0) {
			File attachment = null;
			
			try {
				attachment = (File) new ObjectInputStream(new ByteArrayInputStream(input._attachments)).readObject();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
			
			message.setAttachment(attachment);
		}
		
		_dataStorage.getMessages().add(message);

		return new OutputValues(RegisterResult.SUCCESSED, "");
	}

	public static class InputValues {
		private ChatEntity _receiver;
		private User _sender;
		private String _content;
		private byte[] _attachments;

		public InputValues(ChatEntity desChat, User srcChat, String content, byte[] bytes) {
			_receiver = desChat;
			_sender = srcChat;
			_content = content;
			_attachments = bytes;
		}
	}

	public static class OutputValues {
		private final RegisterResult RESULT;
		private final String MESSAGE;

		public OutputValues(RegisterResult result, String message) {
			MESSAGE = message;
			RESULT = result;
		}

		public RegisterResult getResult() {
			return RESULT;
		}

		public String getMessage() {
			return MESSAGE;
		}
	}

	public enum RegisterResult {
		SUCCESSED, FAILED
	}
}
