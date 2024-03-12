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
        		new GetTopLastestMessages.InputValues(input._nRetrievedMessages, 0)
        		).getfoundMessages();

        OutputValues output = new OutputValues(FinalResult.SUCCESSFUL, "");
        output.setFoundMessages(foundMessage);
        
        return output;
    }

	public static class InputValues {
		private int _nRetrievedMessages;

		public InputValues(int nRetrievedMessages) {
			_nRetrievedMessages = nRetrievedMessages;
		}
	}

	public static class OutputValues {
		private final FinalResult RESULT;
		private final String MESSAGE;
		private List<Message> _foundMessages;

		public OutputValues(FinalResult result, String message) {
			MESSAGE = message;
			RESULT = result;
		}

		public FinalResult getResult() {
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
}