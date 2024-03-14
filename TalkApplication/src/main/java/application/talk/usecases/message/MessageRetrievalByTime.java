package application.talk.usecases.message;

import application.talk.domains.Conversation;
import application.talk.domains.Message;
import application.talk.enums.FinalResult;
import application.talk.usecases.UseCase;
import application.talk.usecases.adapters.DataStorage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MessageRetrievalByTime extends UseCase<MessageRetrievalByTime.InputValues, MessageRetrievalByTime.OutputValues> {
    private DataStorage _dataStorage;

    public MessageRetrievalByTime(DataStorage dataStorage) {
        super();
        _dataStorage = dataStorage;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Conversation conversation = _dataStorage.getConversations().getById(input._conversationId);

        if (conversation == null) {
            return new OutputValues(FinalResult.FAILED, "", null);
        }

        List<Message> messages = conversation.getMessages();
        List<Message> foundMessages = new ArrayList<>();

        for (Message message : messages) {
            if (input._time.isBefore(message.getTimestamp())) {
                break;
            }

            foundMessages.add(message);
        }
        return new OutputValues(FinalResult.SUCCESSFUL, "", foundMessages);
    }

    public static class InputValues {
        private LocalDateTime _time;
        private String _conversationId;

        public InputValues(LocalDateTime time, String conversationId) {
            _time = time;
            _conversationId = conversationId;
        }
    }

    public static class OutputValues {
        private final FinalResult RESULT;
        private final String MESSAGE;
        private List<Message> _foundMessages;

        public OutputValues(FinalResult result, String message, List<Message> foundMessages) {
            MESSAGE = message;
            RESULT = result;
            _foundMessages = foundMessages;
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