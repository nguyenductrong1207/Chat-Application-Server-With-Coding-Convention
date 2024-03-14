package application.talk.usecases.message;

import application.talk.domains.Conversation;
import application.talk.domains.Group;
import application.talk.domains.PrivateGroup;
import application.talk.enums.FinalResult;
import application.talk.enums.GroupType;
import application.talk.usecases.UseCase;
import application.talk.usecases.adapters.DataStorage;

public class AdminMessageDeletion extends UseCase<AdminMessageDeletion.InputValues, AdminMessageDeletion.OutputValues> {
    private DataStorage _dataStorage;
    private MessageDeletion _deleteMessage;

    public AdminMessageDeletion(DataStorage dataStorage, MessageDeletion deleteMessage) {
        super();
        _dataStorage = dataStorage;
        _deleteMessage = deleteMessage;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Conversation conversation = _dataStorage.getConversations().getById(input._conversationId);
        Group group = (Group) conversation.getReceiver();

        if (group.getGroupType().equals(GroupType.PRIVATEGROUP) && ((PrivateGroup) group).checkAdminById(input._userId)) {
            _deleteMessage.removeMessageById(conversation, input._messageId);

            return new OutputValues(FinalResult.SUCCESSFUL, "");
        }

        return new OutputValues(FinalResult.FAILED, "");
    }

    public static class InputValues {
        private String _userId;
        private String _conversationId;
        private String _messageId;

        public InputValues(String userId, String conversationId, String messageId) {
            super();
            _userId = userId;
            _conversationId = conversationId;
            _messageId = messageId;
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
