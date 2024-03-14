package application.talk.usecases.user;

import application.talk.domains.PrivateGroup;
import application.talk.domains.Request;
import application.talk.domains.User;
import application.talk.enums.FinalResult;
import application.talk.enums.RequestStatus;
import application.talk.usecases.UseCase;
import application.talk.usecases.adapters.DataStorage;

public class AdminRequestExecution extends UseCase<AdminRequestExecution.InputValues, AdminRequestExecution.OutputValues> {
    private DataStorage _dataStorage;

    public AdminRequestExecution(DataStorage dataStorage) {
        super();
        _dataStorage = dataStorage;
    }

    @Override
    public OutputValues execute(InputValues input) {
        if (input._privateGroup.getAdmins().contains(input._user)) {
            input._request.setRequestStatus(input._requestStatus);

            if (input._requestStatus.equals(RequestStatus.APPROVE)) {
                _dataStorage.getGroups().getById(input._privateGroup.getId()).addUser(input._request.getRequester());
            }

        }
        return new OutputValues(FinalResult.SUCCESSFUL, "");
    }

    public static class InputValues {
        private PrivateGroup _privateGroup;
        private User _user;
        private Request _request;
        private RequestStatus _requestStatus;

        public InputValues(PrivateGroup privateGroup, User user, Request request, RequestStatus requestStatus) {
            this._privateGroup = privateGroup;
            this._user = user;
            this._request = request;
            this._requestStatus = requestStatus;
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