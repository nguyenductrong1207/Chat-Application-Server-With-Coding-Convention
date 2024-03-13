package application.talk.usecases.message;

import application.talk.domains.*;
import application.talk.enums.FinalResult;
import application.talk.usecases.UseCase;
import application.talk.usecases.adapters.DataStorage;
import java.util.List;

public class RetrievingMessages extends UseCase<RetrievingMessages.InputValues, RetrievingMessages.OutputValues> {
	private DataStorage _dataStorage;
	private GetTopLastestMessages _getTopMessages;

	public RetrievingMessages(DataStorage dataStorage, GetTopLastestMessages getTopMessages) {
		super();
		_dataStorage = dataStorage;
		_getTopMessages = getTopMessages;
	}

	@Override
    public OutputValues execute(InputValues input) {
        List<Message> foundMessage = _getTopMessages.execute(
        		new GetTopLastestMessages.InputValues(input._nRetrievedMessages, 0, input._conversationId)
        		).getfoundMessages();

        return new OutputValues(FinalResult.SUCCESSFUL, "", foundMessage);
    }

	public static class InputValues {
		private int _nRetrievedMessages;
		private String _conversationId;

		public InputValues(int nRetrievedMessages, String conversationId) {
			_nRetrievedMessages = nRetrievedMessages;
			_conversationId = conversationId;
		}
	}

	public static class OutputValues {
		private final FinalResult RESULT;
		private final String MESSAGE;
		private List<Message> _foundMessages;

		public OutputValues(FinalResult result, String message, List<Message> messages) {
			MESSAGE = message;
			RESULT = result;
			_foundMessages = messages;
		}

		public FinalResult getResult() {
			return RESULT;
		}

		public List<Message> getfoundMessages() {
			return _foundMessages;
		}

		public String getMessage() {
			return MESSAGE;
		}
	}
}
