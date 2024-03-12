package application.talk.usecases.user;

import application.talk.domains.*;
import application.talk.enums.FinalResult;
import application.talk.usecases.UseCase;
import application.talk.usecases.adapters.DataStorage;

import java.util.ArrayList;
import java.util.List;

public class GettingMessageViewers extends UseCase<GettingMessageViewers.InputValues, GettingMessageViewers.OutputValues> {
    private DataStorage _dataStorage;
    private RecordLastMessage _recordLastMessage;

    public GettingMessageViewers(DataStorage dataStorage, RecordLastMessage recordLastMessage) {
        super();
        _dataStorage = dataStorage;
        _recordLastMessage = recordLastMessage;
    }

    @Override
    public OutputValues execute(InputValues input) {
        List<Conversation> conversations = _dataStorage.getConversations().getAll();
        Conversation foundConversation = null;
        Message checkedMessage = null;
        List<User> viewers = new ArrayList<>();

        for (Conversation conversation : conversations) {
            if (conversation.getMessageByID(input._messageID) != null && conversation.getSender().getId().equals(input._userID)) {
                foundConversation = conversation;
                checkedMessage = conversation.getMessageByID(input._messageID);

                break;
            }
        }

        if (foundConversation != null && checkedMessage != null) {
            ChatEntity chatEntity = (Group) foundConversation.getReceiver();

            for (User user : ((Group) chatEntity).getUsers()) {
                RecordLastMessage.InputValues inputValue = new RecordLastMessage.InputValues(user.getId(), input._messageID);
                List<Message> checkedMessages = _recordLastMessage.execute(inputValue).getFoundMessage();

                if(checkedMessages.contains(checkedMessage)){
                    viewers.add(user);
                }
            }

        }
        return new OutputValues(FinalResult.SUCCESSFUL, "", viewers);
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
        private final FinalResult RESULT;
        private final String MESSAGE;
        private List<User> _viewers;

        public OutputValues(FinalResult result, String message, List<User> users) {
            MESSAGE = message;
            RESULT = result;
            _viewers = users;
        }

        public FinalResult getResult() {
            return RESULT;
        }

        public String getMessage() {
            return MESSAGE;
        }

        public List<User> getViewers() {
            return _viewers;
        }
    }

}
