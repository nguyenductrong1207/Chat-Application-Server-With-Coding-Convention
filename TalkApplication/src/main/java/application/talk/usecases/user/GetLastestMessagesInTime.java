package application.talk.usecases.user;

import application.talk.domains.Message;
import application.talk.usecases.UseCase;
import application.talk.usecases.adapters.DataStorage;
import application.talk.usecases.adapters.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GetLastestMessagesInTime extends UseCase<GetLastestMessagesInTime.InputValues, GetLastestMessagesInTime.OutputValues> {
	private DataStorage _dataStorage;

	public GetLastestMessagesInTime(DataStorage dataStorage) {
		super();
		_dataStorage = dataStorage;
	}

	@Override
	public OutputValues execute(InputValues input) {
		List<Message> messages = (List<Message>) _dataStorage.getMessages();
		List<Message> foundMessages = new ArrayList<>();

		for(Message message :messages){
			if(message.getTimestamp().compareTo(input._time)>0){
				break;
			}

			foundMessages.add(message);
		}

		OutputValues output =  new OutputValues(GetLastestMessagesResult.SUCCESSFUL, "");
		output.setFoundMessages(foundMessages);

		return output;
	}

	public static class InputValues {
		private LocalDateTime _time;

		public InputValues(List<Message> messages, LocalDateTime time) {
			_time = time;
		}
	}

	public static class OutputValues {
		private final GetLastestMessagesResult RESULT;
		private final String MESSAGE;
		private List<Message> _foundMessages;

		public OutputValues(GetLastestMessagesResult result, String message) {
			MESSAGE = message;
			RESULT = result;
		}

		public GetLastestMessagesResult getResult() {
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

	public static enum GetLastestMessagesResult {
		SUCCESSFUL, FAILED
	}
}
