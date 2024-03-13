package application.talk.usecases.user;

import application.talk.domains.PrivateGroup;
import application.talk.domains.Request;
import application.talk.domains.User;
import application.talk.enums.FinalResult;
import application.talk.usecases.UseCase;
import application.talk.usecases.adapters.DataStorage;

import java.time.LocalDateTime;

public class RequestJoiningPrivateGroup extends UseCase<RequestJoiningPrivateGroup.InputValues, RequestJoiningPrivateGroup.OutputValues> {
    private DataStorage _dataStorage;

    public RequestJoiningPrivateGroup(DataStorage dataStorage) {
        super();
        _dataStorage = dataStorage;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Request request = new Request(input._requester, input._privateGroup, LocalDateTime.now(), Request.RequestStatus.WAITING);

        _dataStorage.getRequests().add(request);

        return new OutputValues(FinalResult.SUCCESSFUL, "");
    }

    public static class InputValues {
        private User _requester;
        private PrivateGroup _privateGroup;

        public InputValues(User requester, PrivateGroup privateGroup) {
            this._requester = requester;
            this._privateGroup = privateGroup;
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
