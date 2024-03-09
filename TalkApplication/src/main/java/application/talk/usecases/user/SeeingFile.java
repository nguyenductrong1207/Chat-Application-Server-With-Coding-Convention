package application.talk.usecases.user;

import java.util.List;

import application.talk.domains.Conversation;
import application.talk.domains.File;
import application.talk.domains.Group;
import application.talk.domains.User;
import application.talk.usecases.UseCase;
import application.talk.usecases.adapters.DataStorage;

public class SeeingFile extends UseCase<SeeingFile.InputValues, SeeingFile.OutputValues> {
    private DataStorage _dataStorage;

    public SeeingFile(DataStorage dataStorage) {
        super();
        _dataStorage = dataStorage;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Conversation conversation = _dataStorage.getConversations().getById(input._conversationId);
        Group group = (Group) conversation.getReceiver();
        User foundUser = group.findUserByID(input._userId);
        
        if (foundUser == null || group == null) {
            return new OutputValues(CreatingResult.FAILED, "", null);
        }

        return new OutputValues(CreatingResult.SUCCESSFUL, "", conversation.getAttachments());
    }

    public static class InputValues {
        private String _userId;
        private String _conversationId;

        public InputValues(String userId, String conversationID) {
            _userId = userId;
            _conversationId = conversationID;
        }
    }

    public static class OutputValues {
        private final CreatingResult RESULT;
        private final String MESSAGE;
        private  List<File> _files;

        public OutputValues(CreatingResult result, String message, List<File> files) {
            MESSAGE = message;
            RESULT = result;
            _files = files;
        }

        public CreatingResult getResult() {
            return RESULT;
        }

        public String getMessage() {
            return MESSAGE;
        }

        public List<File> getFiles() {
            return _files;
        }
    }

    public enum CreatingResult {
        SUCCESSFUL, FAILED
    }
}
