package application.talk.usecases.message;

import java.time.LocalDateTime;

import application.talk.domains.ChatEntity;
import application.talk.domains.Conversation;
import application.talk.domains.File;
import application.talk.domains.File.Type;
import application.talk.domains.Message;
import application.talk.domains.User;
import application.talk.enums.FinalResult;
import application.talk.usecases.UseCase;
import application.talk.usecases.adapters.DataStorage;

public class MessageSending extends UseCase<MessageSending.InputValues, MessageSending.OutputValues> {
    private DataStorage _dataStorage;

    public MessageSending(DataStorage dataStorage) {
        _dataStorage = dataStorage;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Message message = new Message(input._sender, LocalDateTime.now(), input._receiver, input._content);
        byte[] attachmentByte = input._attachment;

        if (attachmentByte != null) {
            if (attachmentByte.length > 0) {
                File attachment = new File(input._attachment.length, input._type);

                attachment.writeFileFromByte(input._attachment);
                message.setAttachment(attachment);
            }
        }

        Conversation conversation = _dataStorage.getConversations().getById(input._conversationId);

        if (conversation != null) {
            conversation.addMessage(message);
        } else {
            return new OutputValues(FinalResult.FAILED, "");
        }

        return new OutputValues(FinalResult.SUCCESSFUL, "");
    }

    public static class InputValues {
        private ChatEntity _receiver;
        private User _sender;
        private String _content;
        private byte[] _attachment;
        private Type _type;
        private String _conversationId;

        public InputValues(User sender, ChatEntity receiver, String content, byte[] attachment, Type type, String conversationId) {
            _receiver = receiver;
            _sender = sender;
            _content = content;
            _attachment = attachment;
            _type = type;
            _conversationId = conversationId;
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
