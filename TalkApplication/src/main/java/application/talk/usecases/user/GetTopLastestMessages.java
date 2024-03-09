package application.talk.usecases.user;

import application.talk.domains.*;
import application.talk.usecases.UseCase;
import application.talk.usecases.adapters.DataStorage;

import java.util.ArrayList;
import java.util.List;

public class GetTopLastestMessages extends UseCase<GetTopLastestMessages.InputValues, GetTopLastestMessages.OutputValues> {
    private DataStorage _dataStorage;

    public GetTopLastestMessages(DataStorage dataStorage) {
        super();
        _dataStorage = dataStorage;
    }

    @Override
    public OutputValues execute(InputValues input) {
        List<Message> messages = _dataStorage.getMessages().getAll();
        List<Message> foundMessages = new ArrayList<>();
        int count = 0;

        for (int i = messages.size() - input._nSkippedMessages; count > input._nRetrievedMessages; i--) {
            foundMessages.add(messages.get(i));
            count++;
        }

        OutputValues output = new OutputValues(GetTopLastestMessagesResult.SUCCESSFUL, "");
        output.setFoundMessages(foundMessages);

        return output;
    }

    public static class InputValues {
        private int _nRetrievedMessages;
        private int _nSkippedMessages;

        public InputValues(int nRetrievedMessages, int nSkippedMessages) {
            _nRetrievedMessages = nRetrievedMessages;
            _nSkippedMessages = nSkippedMessages;
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
