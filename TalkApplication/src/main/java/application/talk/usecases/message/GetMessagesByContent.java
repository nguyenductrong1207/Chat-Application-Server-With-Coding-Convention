package application.talk.usecases.message;

import application.talk.domains.ChatEntity;
import application.talk.domains.Message;
import application.talk.usecases.UseCase;
import application.talk.usecases.adapters.DataStorage;

import java.util.ArrayList;
import java.util.List;

public class GetMessagesByContent extends UseCase<GetMessagesByContent.InputValues, GetMessagesByContent.OutputValues> {
	private DataStorage _dataStorage;

	public GetMessagesByContent(DataStorage dataStorage) {
		super();
		_dataStorage = dataStorage;
	}

	@Override
	public OutputValues execute(InputValues input) {
		ChatEntity chatEntity = _dataStorage.getChatEntities().getById(input._chatEntityId);
		List<Message> messages = chatEntity.getMessages();
		List<Message> foundMessages = new ArrayList<>();

		for(Message message:messages){
			if(message.getContent().contains(input._keyword)){
				foundMessages.add(message);
			}
		}

		OutputValues output =  new OutputValues(GetTopLastestMessagesResult.SUCCESSFUL, "");
		output.setFoundMessages(foundMessages);

		return output;
	}

	public static class InputValues {
		private String _keyword;
		private String _chatEntityId;

		public InputValues( String keyword, String id) {
			_keyword = keyword;
			_chatEntityId = id;
		}
	}

	public static class OutputValues {
		private final GetTopLastestMessagesResult RESULT;
		private final String MESSAGE;
		private List<Message> _foundMessages;

		public OutputValues(GetTopLastestMessagesResult result, String message) {
			MESSAGE = message;
			RESULT = result;
		}

		public GetTopLastestMessagesResult getResult() {
			return RESULT;
		}

		public List<Message> getfoundMessages() {
			return _foundMessages;
		}

		public void setFoundMessages(List<Message> foundMessages) {
			_foundMessages = foundMessages;
		}

		public String getMessage() {
			return MESSAGE;
		}
	}

	public enum GetTopLastestMessagesResult {
		SUCCESSFUL, FAILED
	}
}
