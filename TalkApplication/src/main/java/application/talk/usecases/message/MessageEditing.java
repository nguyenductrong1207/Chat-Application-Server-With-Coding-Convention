package application.talk.usecases.message;

import application.talk.domains.Message;
import application.talk.domains.User;
import application.talk.enums.FinalResult;
import application.talk.usecases.UseCase;
import application.talk.usecases.adapters.DataStorage;

public class MessageEditing extends UseCase<MessageEditing.InputValues, MessageEditing.OutputValues> {
    private DataStorage _dataStorage;

    public MessageEditing(DataStorage dataStorage) {
        _dataStorage = dataStorage;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Message message = _dataStorage.getMessages().getById(input._messageId);

        if (message != null && message.getSender().equals(input._sender)) {
            message.setContent(input._newContent);

            return new OutputValues(FinalResult.SUCCESSFUL, "");
        } else {
            return new OutputValues(FinalResult.FAILED, "");
        }
    }

    public static class InputValues {
        private String _messageId;
        private User _sender;
        private String _newContent;

        public InputValues(String messageId, User sender, String newContent) {
            _messageId = messageId;
            _sender = sender;
            _newContent = newContent;
        }
    }

    public static class OutputValues {
        private final FinalResult RESULT;
        private final String MESSAGE;

        public OutputValues(FinalResult result, String message) {
            MESSAGE = message;
            RESULT = result;
        }

        public FinalResult getResult() {
            return RESULT;
        }

        public String getMessage() {
            return MESSAGE;
        }
    }
}
