package application.talk.usecases.group;

import application.talk.domains.PrivateGroup;
import application.talk.domains.User;
import application.talk.enums.FinalResult;
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

        return new OutputValues(FinalResult.SUCCESSFUL, "");
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
