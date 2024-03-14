package application.talk.usecases.group;

import application.talk.domains.Group;
import application.talk.domains.PrivateGroup;
import application.talk.domains.User;
import application.talk.enums.FinalResult;
import application.talk.usecases.UseCase;
import application.talk.usecases.adapters.DataStorage;

public class PrivateGroupJoining extends UseCase<PrivateGroupJoining.InputValues, PrivateGroupJoining.OutputValues> {
    private DataStorage _dataStorage;

    public PrivateGroupJoining(DataStorage dataStorage) {
        super();
        _dataStorage = dataStorage;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Group group = _dataStorage.getGroups().getById(input._groupId);

        if (group != null && ((PrivateGroup) group).checkAdminById(input._inviterId)) {
            User receiver = _dataStorage.getUsers().getById(input._receiverId);
            group.addUser(receiver);

            return new OutputValues(FinalResult.SUCCESSFUL, "");
        }
        return new OutputValues(FinalResult.FAILED, "");
    }

    public static class InputValues {
        private String _groupId;
        private String _inviterId;
        private String _receiverId;

        public InputValues(String inviterId, String receiverId, String groupId) {
            super();
            _inviterId = inviterId;
            _receiverId = receiverId;
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
