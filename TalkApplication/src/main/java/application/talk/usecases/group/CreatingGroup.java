package application.talk.usecases.group;

import java.util.UUID;

import application.talk.domains.Group;
import application.talk.domains.PrivateGroup;
import application.talk.domains.PublicGroup;
import application.talk.domains.User;
import application.talk.enums.FinalResult;
import application.talk.enums.GroupType;
import application.talk.usecases.UseCase;
import application.talk.usecases.adapters.DataStorage;

public class CreatingGroup extends UseCase<CreatingGroup.InputValues, CreatingGroup.OutputValues> {
    private DataStorage _dataStorage;

    public CreatingGroup(DataStorage dataStorage) {
        super();
        _dataStorage = dataStorage;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Group group = null;

        if (input._groupType.equals("PUBLICGROUP")) {
            group = new PublicGroup(input._name, input._joinCode);

        } else if (input._groupType.equals("PRIVATEGROUP")) {
            group = new PrivateGroup(input._name, input._admin);
        }

        _dataStorage.getGroups().add(group);
        return new OutputValues(FinalResult.SUCCESSFUL, "");
    }

    public static class InputValues {
        private GroupType _groupType;
        private String _joinCode;
        private User _admin;
        private String _name;

        public InputValues(GroupType groupType, User admin, String name) {
            super();
            _groupType = groupType;
            _joinCode = UUID.randomUUID().toString().substring(0, 6);
            _admin = admin;
            _name = name;
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
