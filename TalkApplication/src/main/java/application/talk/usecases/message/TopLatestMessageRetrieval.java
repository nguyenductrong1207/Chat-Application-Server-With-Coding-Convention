package application.talk.usecases.message;

import application.talk.domains.Conversation;
import application.talk.domains.Message;
import application.talk.enums.FinalResult;
import application.talk.usecases.UseCase;
import application.talk.usecases.adapters.DataStorage;

import java.util.ArrayList;
import java.util.List;

public class TopLatestMessageRetrieval extends UseCase<TopLatestMessageRetrieval.InputValues, TopLatestMessageRetrieval.OutputValues> {
    private DataStorage _dataStorage;

    public TopLatestMessageRetrieval(DataStorage dataStorage) {
        super();
        _dataStorage = dataStorage;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Conversation conversation = _dataStorage.getConversations().getById(input._conversationId);
        List<Message> messages = conversation.getMessages();
        List<Message> foundMessages = new ArrayList<>();
        int count = 0;

        for (int i = messages.size() - input._nSkippedMessages; count > input._nRetrievedMessages; i--) {
            foundMessages.add(messages.get(i));
            count++;
        }

        return new OutputValues(FinalResult.SUCCESSFUL, "", foundMessages);
    }

    public static class InputValues {
        private int _nRetrievedMessages;
        private int _nSkippedMessages;
        private String _conversationId;

        public InputValues(int nRetrievedMessages, int nSkippedMessages, String conversationId) {
            _nRetrievedMessages = nRetrievedMessages;
            _nSkippedMessages = nSkippedMessages;
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
