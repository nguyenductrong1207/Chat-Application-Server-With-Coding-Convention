package application.talk.usecases.user;

import java.util.List;
import application.talk.domains.User;
import application.talk.enums.FinalResult;
import application.talk.usecases.UseCase;
import application.talk.usecases.adapters.DataStorage;

public class FindingUser extends UseCase<FindingUser.InputValues, FindingUser.OutputValues> {
	private DataStorage _dataStorage;

	public FindingUser(DataStorage dataStorage) {
		_dataStorage = dataStorage;
	}

	@Override
	public OutputValues execute(InputValues input) {
		List<User> users = _dataStorage.getUsers().getAll();

		for (User user : users) {
			if (user.getFullName().contains(input._givenString)) {
				return new OutputValues(FinalResult.SUCCESSFUL, "", user);
			}
		}

		return new OutputValues(FinalResult.FAILED, "", null);
	}

	public static class InputValues {
		private String _givenString;

		public InputValues(String givenString) {
			super();
			_givenString = givenString;
		}

	}

	public static class OutputValues {
		private final FinalResult RESULT;
		private final String MESSAGE;
		private final User _foundUsers;

		public OutputValues(FinalResult result, String message, User users) {
			MESSAGE = message;
			RESULT = result;
			_foundUsers = users;
		}

		public FinalResult getResult() {
			return RESULT;
		}

		public String getMessage() {
			return MESSAGE;
		}

		public User getFoundUsers() {
			return _foundUsers;
		}

	}
}
