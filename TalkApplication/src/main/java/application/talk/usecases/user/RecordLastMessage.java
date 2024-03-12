package application.talk.usecases.user;

import application.talk.domains.*;
import application.talk.usecases.UseCase;
import application.talk.usecases.adapters.DataStorage;
import application.talk.usecases.adapters.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.zip.CheckedInputStream;

public class RecordLastMessage extends UseCase<RecordLastMessage.InputValues, RecordLastMessage.OutputValues> {
    private DataStorage _dataStorage;

    public RecordLastMessage(DataStorage dataStorage) {
        super();
        _dataStorage = dataStorage;
    }

    @Override
    public OutputValues execute(InputValues input) {
        List<Conversation> conversations = _dataStorage.getConversations().getAll();

        for (Conversation conversation : conversations) {
            Message foundMessage = conversation.getMessageByID(input._messageID);

            if (foundMessage != null ) {
                RecordedMessage recordedMessage = null;
                String senderID = foundMessage.getSender().getId();

                if(senderID.equals(input._userID)){
                    recordedMessage = new RecordedMessage(foundMessage.getSender(), foundMessage);
                }else{
                    recordedMessage = new RecordedMessage(foundMessage.getReceiver(), foundMessage);
                }

                _dataStorage.getRecordMessages().removeByID(recordedMessage.getId());
                _dataStorage.getRecordMessages().add(recordedMessage);

                List<Message> foundMessages = conversation.getARangeOfMessage(recordedMessage.getId());

                return new OutputValues(RecordResult.SUCCESSFUL, "", foundMessages);
            }
        }
        return new OutputValues(RecordResult.FAILED, "", null);
    }

    public static class InputValues {
        private String _userID;
        private String _messageID;

        public InputValues(String userID, String messageID) {
            _userID = userID;
            _messageID = messageID;
        }
    }

    public static class OutputValues {
        private final RecordResult RESULT;
        private final String MESSAGE;
        private List<Message> _foundMessage;

        public OutputValues(RecordResult result, String message, List<Message> messages) {
            MESSAGE = message;
            RESULT = result;
            _foundMessage = messages;
        }

        public RecordResult getResult() {
            return RESULT;
        }

        public String getMessage() {
            return MESSAGE;
        }

        public List<Message> getFoundMessage() {
            return _foundMessage;
        }
    }

    public enum RecordResult {
        SUCCESSFUL, FAILED
    }
}
