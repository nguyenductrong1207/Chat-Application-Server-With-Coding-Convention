package application.talk.usecases.group;

import application.talk.domains.PrivateGroup;
import application.talk.enums.FinalResult;
import application.talk.usecases.UseCase;
import application.talk.usecases.adapters.DataStorage;

public class MemberRemoval extends UseCase<MemberRemoval.InputValues, MemberRemoval.OutputValues> {
    private DataStorage _dataStorage;

    public MemberRemoval(DataStorage dataStorage) {
        super();
        _dataStorage = dataStorage;
    }

    @Override
    public OutputValues execute(InputValues input) {
        PrivateGroup group = (PrivateGroup) _dataStorage.getGroups().getById(input._groupId);

        if (group.checkAdminById(input._userId)) {
            group.removeUserById(input._memberId);
            return new OutputValues(FinalResult.SUCCESSFUL, "");
        }

        return new OutputValues(FinalResult.FAILED, "");
    }

    public static class InputValues {
        private String _userId;
        private String _groupId;
        private String _memberId;

        public InputValues(String userId, String groupId, String memberId) {
            super();
            _userId = userId;
            _groupId = groupId;
            _memberId = memberId;
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
