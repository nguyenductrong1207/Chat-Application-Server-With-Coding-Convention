package application.talk.usecases.group;

import application.talk.domains.Group;
import application.talk.domains.PublicGroup;
import application.talk.domains.User;
import application.talk.enums.FinalResult;
import application.talk.usecases.UseCase;
import application.talk.usecases.adapters.DataStorage;

import java.util.List;

public class PublicGroupJoining extends UseCase<PublicGroupJoining.InputValues, PublicGroupJoining.OutputValues> {
    private DataStorage _dataStorage;

    public PublicGroupJoining(DataStorage dataStorage) {
        super();
        _dataStorage = dataStorage;
    }

    @Override
    public OutputValues execute(InputValues input) {
        List<Group> groups = _dataStorage.getGroups().getAll();

        for (Group group : groups) {
            if (group instanceof PublicGroup && ((PublicGroup) group).getJoinCode().equals(input._joinCode)) {
                User joinedUser = _dataStorage.getUsers().getById(input._receiverId);
                group.addUser(joinedUser);
            }

            return new OutputValues(FinalResult.SUCCESSFUL, "");
        }
        return new OutputValues(FinalResult.FAILED, "");
    }

    public static class InputValues {
        private String _receiverId;
        private String _joinCode;

        public InputValues(String userId, String joinedCode) {
            super();
            _receiverId = userId;
            _joinCode = joinedCode;
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
