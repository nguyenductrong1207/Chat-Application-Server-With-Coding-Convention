package application.talk.usecases.user;

import java.util.List;

import application.talk.domains.Message;
import application.talk.domains.PrivateGroup;
import application.talk.domains.User;
import application.talk.enums.FinalResult;
import application.talk.usecases.UseCase;
import application.talk.usecases.adapters.DataStorage;

    public class RemovingMessages extends UseCase<RemovingMessages.InputValues, RemovingMessages.OutputValues> {
        private DataStorage _dataStorage;

        public RemovingMessages(DataStorage dataStorage) {
            super();
            _dataStorage = dataStorage;
        }

        @Override
        public OutputValues execute(InputValues input) {
            User user1 = new User("abc","abc");
            user1.setId("001");
            Message message = _dataStorage.getMessages().getById(input._messageId);
            PrivateGroup group = (PrivateGroup) _dataStorage.getGroups().getById(input._groupId);
            group.addAdmin(user1);

//		 if (group == null) {
//		        return new OutputValues(CreatingResult.FAILED, "");
//		    }
            System.out.println("Group ID: " + input._groupId);
            System.out.println("Group: " + group);

            List<User> privateGroup = group.getAdmins();

//            for (User user : privateGroup) {
//                if (user.getId().equals(input._userId)) {
//                    message.removeMessageById(input._messageId);
//                    return new OutputValues(CreatingResult.SUCCESSFUL, "");
//                }
//            }
//            if (message != null && message.getSender().getId().equals(input._userId)) {
//                message.removeMessageById(input._messageId);
//                return new OutputValues(CreatingResult.SUCCESSFUL, "");
//            }

            return new OutputValues(FinalResult.FAILED, "");
        }

        public static class InputValues {
            private String _userId;
            private String _messageId;
            private String _groupId;

            public InputValues(String userId, String messageId, String groupId) {
                super();
                _userId = userId;
                _messageId = messageId;
                _groupId = groupId;
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

