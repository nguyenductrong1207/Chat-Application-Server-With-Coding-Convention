package application.talk.usecases.message;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import application.talk.domains.Conversation;
import application.talk.domains.File;
import application.talk.domains.Message;
import application.talk.enums.FinalResult;
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

            return new OutputValues(FinalResult.SUCCESSFUL, "");
        } else {
            return new OutputValues(FinalResult.FAILED, "");
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
