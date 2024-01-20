package application.talk.usecases.user;

import application.talk.domains.User;
import application.talk.usecases.UseCase;
import application.talk.usecases.adapters.DataStorage;
import application.talk.usecases.adapters.Hasher;

public class UserRegistration extends UseCase<UserRegistration.InputValues, UserRegistration.OutputValues> {
	private DataStorage _dataStorage;
	private Hasher _hasher;

	public UserRegistration(DataStorage dataStorage, Hasher hasher) {
		_dataStorage = dataStorage;
		_hasher = hasher;
	}

	@Override
	public OutputValues execute(InputValues input) {
		User user = new User.UserBuilder(input._username, _hasher.hash(input._password)).build();
		_dataStorage.getUsers().add(user);
		return new OutputValues(RegisterResult.SUCCESSED, "");
	}

	public static class InputValues {
		private String _username;
		private String _password;

		public InputValues(String username, String password) {
			_username = username;
			_password = password;
		}
	}

	public static class OutputValues {
		private final RegisterResult RESULT;
		private final String MESSAGE;

		public OutputValues(RegisterResult result, String message) {
			MESSAGE = message;
			RESULT = result;
		}

		public RegisterResult getResult() {
			return RESULT;
		}

		public String getMessage() {
			return MESSAGE;
		}
	}

	public enum RegisterResult {
		SUCCESSED, FAILED
	}
}