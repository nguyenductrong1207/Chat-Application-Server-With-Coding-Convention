package application.talk.usecases.user;

import application.talk.domains.PrivateGroup;
import application.talk.domains.User;
import application.talk.usecases.UseCase;
import application.talk.usecases.adapters.DataStorage;

public class JoiningPrivateGroup extends UseCase<JoiningPrivateGroup.InputValues, JoiningPrivateGroup.OutputValues> {
    private DataStorage _dataStorage;

    public JoiningPrivateGroup(DataStorage dataStorage) {
        super();
        _dataStorage = dataStorage;
    }

    @Override
    public OutputValues execute(InputValues input) {
        PrivateGroup privateGroup = new PrivateGroup(null, null);

        if (privateGroup != null && privateGroup.getAdmins().contains(input._inviter)) {
            privateGroup.addUser(input._receiver);
        }

        _dataStorage.getGroups().add(privateGroup);

        return new OutputValues(JoiningPrivateGroupResult.SUCCESSFUL, "");
    }

    public static class InputValues {
        private User _inviter;
        private User _receiver;

        public InputValues(User inviter, User receiver) {
            super();
            _inviter = inviter;
            _receiver = receiver;
        }
    }

    public static class OutputValues {
        private final JoiningPrivateGroupResult RESULT;
        private final String MESSAGE;

        public OutputValues(JoiningPrivateGroupResult result, String message) {
            MESSAGE = message;
            RESULT = result;
        }

        public JoiningPrivateGroupResult getResult() {
            return RESULT;
        }

        public String getMessage() {
            return MESSAGE;
        }
    }

    public enum JoiningPrivateGroupResult {
        SUCCESSFUL, FAILED
    }
}
