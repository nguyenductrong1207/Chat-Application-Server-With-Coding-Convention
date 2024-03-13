package application.talk.usecases.user;

import application.talk.domains.Conversation;
import application.talk.domains.Group;
import application.talk.enums.FinalResult;
import application.talk.usecases.UseCase;
import application.talk.usecases.adapters.DataStorage;

import java.util.ArrayList;
import java.util.List;

public class SeeAllConversationOfUser extends UseCase<SeeAllConversationOfUser.InputValues, SeeAllConversationOfUser.OutputValues> {
    private DataStorage _dataStorage;

    public SeeAllConversationOfUser(DataStorage dataStorage) {
        super();
        _dataStorage = dataStorage;
    }

    @Override
    public OutputValues execute(InputValues input) {
        List<String> userConversations = new ArrayList<>();
        List<Conversation> conversations = _dataStorage.getConversations().getAll();
        List<Group> groups = _dataStorage.getGroups().getAll();
        String userId = input._userId;

        for (Conversation conversation : conversations) {
            if (conversation.getSender().getId().equals(userId)) {
                userConversations.add(conversation.getReceiver().getId() + ": " + conversation.getReceiver().getName());
            }
        }

        for (Group group : groups) {
            if (group.findUserByID(userId) != null) {
                userConversations.add(group.getName());
            }
        }

        return new OutputValues(FinalResult.SUCCESSFUL, "", userConversations);
    }

    public static class InputValues {
        private String _userId;

        public InputValues(String _userId) {
            this._userId = _userId;
        }
    }

    public static class OutputValues {
        private final FinalResult RESULT;
        private final String MESSAGE;
        private  List<String> _userConversations;

        public OutputValues(FinalResult result, String message, List<String> userConversations) {
            MESSAGE = message;
            RESULT = result;
            _userConversations = userConversations;
        }

        public FinalResult getResult() {
            return RESULT;
        }

        public String getMessage() {
            return MESSAGE;
        }

        public List<String> getUserConversations() {
            return _userConversations;
        }
    }
}
