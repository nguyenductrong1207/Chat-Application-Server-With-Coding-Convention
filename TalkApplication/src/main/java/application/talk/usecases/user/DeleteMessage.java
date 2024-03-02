package application.talk.usecases.user;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        Message message = _dataStorage.getMessages().getById(input._messageId);

        if (message != null && message.getSender().equals(input._sender)) {

            File file = message.getAttachment();
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

            message.removeMessageById(input._messageId);

            return new OutputValues(DeleteMessageResult.SUCCESSED, "");
        } else {
            return new OutputValues(DeleteMessageResult.FAILED, "Unable to delete the message.");
        }
    }

    public static class InputValues {
        private String _messageId;
        private User _sender;

        public InputValues(String messageId, User sender) {
            _messageId = messageId;
            _sender = sender;
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
