package application.talk.usecases.user;

import application.talk.usecases.UseCase;
import application.talk.usecases.adapters.DataStorage;
import application.talk.usecases.adapters.Hasher;
import application.talk.usecases.user.UserRegistration.RegisterResult;

public class LoginUseCase extends UseCase<LoginUseCase.InputValues, LoginUseCase.OutputValues> {
	private DataStorage _dataStorage;
	private Hasher _hasher;

	public LoginUseCase(DataStorage dataStorage, Hasher hasher) {
		_dataStorage = dataStorage;
		_hasher = hasher;
	}

	@Override
	public OutputValues execute(InputValues input) {
		return new OutputValues(LoginResult.Successed, "");
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
		private final LoginResult RESULT;
		private final String MESSAGE;

		public OutputValues(LoginResult result, String message) {
			MESSAGE = message;
			RESULT = result;
		}
		
		public LoginResult getResult() {
			return RESULT;
		}

		public String getMessage() {
			return MESSAGE;
		}
	}

	public static enum LoginResult {
		Successed, Failed;
	}
}