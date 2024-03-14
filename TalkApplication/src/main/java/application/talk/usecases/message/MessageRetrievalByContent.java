package application.talk.usecases.message;

import application.talk.domains.Conversation;
import application.talk.domains.Message;
import application.talk.enums.FinalResult;
import application.talk.usecases.UseCase;
import application.talk.usecases.adapters.DataStorage;

import java.util.ArrayList;
import java.util.List;

public class MessageRetrievalByContent extends UseCase<MessageRetrievalByContent.InputValues, MessageRetrievalByContent.OutputValues> {
    private DataStorage _dataStorage;

    public MessageRetrievalByContent(DataStorage dataStorage) {
        super();
        _dataStorage = dataStorage;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Conversation conversation = _dataStorage.getConversations().getById(input._conversationId);
        List<Message> messages = conversation.getMessages();
        List<Message> foundMessages = new ArrayList<>();

        for (Message message : messages) {
            if (message.getContent().contains(input._keyword)) {
                foundMessages.add(message);
            }
        }

        return new OutputValues(FinalResult.SUCCESSFUL, "", foundMessages);
    }

    public static class InputValues {
        private String _keyword;
        private String _conversationId;

        public InputValues(String keyword, String conversationId) {
            _keyword = keyword;
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

        public List<Message> getFoundMessages() {
            return _foundMessages;
        }

        public String getMessage() {
            return MESSAGE;
        }
    }
}
