package application.talk.usecases.user;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import application.talk.domains.Conversation;
import application.talk.domains.File;
import application.talk.domains.Message;
import application.talk.domains.User;
import application.talk.usecases.UseCase;
import application.talk.usecases.adapters.DataStorage;

public class DeleteMessage extends UseCase<DeleteMessage.InputValues, DeleteMessage.OutputValues> {
    private DataStorage _dataStorage;

    public DeleteMessage(DataStorage dataStorage) {
        _dataStorage = dataStorage;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Conversation conversation = _dataStorage.getConversations().getById(input._conversationId);

        if (conversation.getSender().getId().equals(input._senderId)) {
            removeMessageById(conversation, input._messageId);

            return new OutputValues(DeleteMessageResult.SUCCESSED, "");
        } else {
            return new OutputValues(DeleteMessageResult.FAILED, "");
        }
    }

    public void removeMessageById(Conversation conversation, String messageID) {
        List<Message> messages = conversation.getMessages();

        for (Message i : messages) {
            if (i.getId().equals(messageID)) {
                messages.remove(i);

                File file = i.getAttachment();
                if (file != null) {
                    String filePath = file.getFilePath();

                    try {
                        Path fileDelete = Paths.get(filePath);

                        if (Files.exists(fileDelete)) {
                            Files.delete(fileDelete);
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                return;
            }
        }
    }

    public static class InputValues {

        private String _conversationId;
        private String _messageId;
        private String _senderId;

        public InputValues(String conversationId, String messageId, String senderId) {
            this._conversationId = conversationId;
            this._messageId = messageId;
            this._senderId = senderId;
        }
    }

    public static class OutputValues {
        private final DeleteMessageResult RESULT;
        private final String MESSAGE;

        public OutputValues(DeleteMessageResult result, String message) {
            MESSAGE = message;
            RESULT = result;
        }

        public DeleteMessageResult getResult() {
            return RESULT;
        }

        public String getMessage() {
            return MESSAGE;
        }
    }

    public enum DeleteMessageResult {
        SUCCESSED, FAILED
    }
}
