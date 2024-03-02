package application.talk.usecases.user;

import application.talk.domains.Message;
import application.talk.domains.User;
import application.talk.usecases.UseCase;
import application.talk.usecases.adapters.DataStorage;

import java.util.ArrayList;
import java.util.List;

public class GetConversations extends UseCase<GetConversations.InputValues, GetConversations.OutputValues> {
    private DataStorage _dataStorage;

    public GetConversations(DataStorage dataStorage) {
        super();
        _dataStorage = dataStorage;
    }

    @Override
    public OutputValues execute(InputValues input) {

        List<Message> conversations = new ArrayList<>();


        OutputValues output = new OutputValues(GetConversationsResult.SUCCESSFUL, "");
        output.setConversations(conversations);

        return output;
    }

    public static class InputValues {
        private User _user;

        public InputValues(User user) {
            _user = user;
        }
    }

    public static class OutputValues {
        private final GetConversationsResult RESULT;
        private final String MESSAGE;
        private List<Message> _conversations;

        public OutputValues(GetConversationsResult result, String message) {
            MESSAGE = message;
            RESULT = result;
        }

        public GetConversationsResult getResult() {
            return RESULT;
        }

        public String getMessage() {
            return MESSAGE;
        }

        public List<Message> getConversations() {
            return _conversations;
        }

        public void setConversations(List<Message> conversations) {
            _conversations = conversations;
        }
    }

    public static enum GetConversationsResult {
        SUCCESSFUL, FAILED
    }
}
