package application.talk.usecases.user;

import application.talk.domains.User;
import application.talk.usecases.UseCase;
import application.talk.usecases.adapters.DataStorage;

public class FindingUser extends UseCase<FindingUser.InputValues, FindingUser.OutputValues> {
	private DataStorage _dataStorage;

	public FindingUser(DataStorage dataStorage) {
		_dataStorage = dataStorage;
	}

	@Override
	public OutputValues execute(InputValues input) {
		User user = _dataStorage.getUsers().getByName(input.getName());
		
		if(user == null) {
			return new OutputValues(FindingResult.FAILED, "");
		}
		
		return new OutputValues(FindingResult.SUCCESSFUL, "");
	}

	public static class InputValues {
		private String _username;

		public InputValues(String username) {
			super();
			_username = username;
		}
		
		public String getName() {
			return  _username;
		}
	}

	public static class OutputValues {
		private final FindingResult RESULT;
		private final String MESSAGE;

		public OutputValues(FindingResult result, String message) {
			MESSAGE = message;
			RESULT = result;
		}

		public FindingResult getResult() {
			return RESULT;
		}

		public String getMessage() {
			return MESSAGE;
		}
	}

	public static enum FindingResult {
		SUCCESSFUL, FAILED;
	}

}
