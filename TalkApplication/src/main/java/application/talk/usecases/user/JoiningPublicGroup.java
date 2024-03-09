package application.talk.usecases.user;

import application.talk.domains.PrivateGroup;
import application.talk.domains.PublicGroup;
import application.talk.domains.User;
import application.talk.usecases.UseCase;
import application.talk.usecases.adapters.DataStorage;

public class JoiningPublicGroup extends UseCase<JoiningPublicGroup.InputValues, JoiningPublicGroup.OutputValues> {
    private DataStorage _dataStorage;

    public JoiningPublicGroup(DataStorage dataStorage) {
        super();
        _dataStorage = dataStorage;
    }

    @Override
    public OutputValues execute(InputValues input) {
        PublicGroup publicGroup = new PublicGroup(null, input._joinCode);

        if (publicGroup != null && publicGroup.getJoinCode().equals(input._joinCode)) {
            publicGroup.addUser(input._user);
        }

        _dataStorage.getGroups().add(publicGroup);

        return new OutputValues(JoiningPublicGroupResult.SUCCESSFUL, "");
    }

    public static class InputValues {
        private User _inviter;
        private User _receiver;
        private String _joinCode;
        private User _user;


        public InputValues(User inviter, User receiver) {
            super();
            _inviter = inviter;
            _receiver = receiver;
        }

        public InputValues(User user, String joinCode) {
            super();
            _user = user;
            _joinCode = joinCode;
        }
    }

    public static class OutputValues {
        private final JoiningPublicGroupResult RESULT;
        private final String MESSAGE;

        public OutputValues(JoiningPublicGroupResult result, String message) {
            MESSAGE = message;
            RESULT = result;
        }

        public JoiningPublicGroupResult getResult() {
            return RESULT;
        }

        public String getMessage() {
            return MESSAGE;
        }
    }

    public enum JoiningPublicGroupResult {
        SUCCESSFUL, FAILED
    }
}