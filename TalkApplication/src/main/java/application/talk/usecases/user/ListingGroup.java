package application.talk.usecases.user;

import java.util.ArrayList;
import java.util.List;

import application.talk.domains.Group;
import application.talk.usecases.UseCase;
import application.talk.usecases.adapters.DataStorage;

public class ListingGroup extends UseCase<ListingGroup.InputValues, ListingGroup.OutputValues> {
	private DataStorage _dataStorage;
	private List<Group> _groups;

	public ListingGroup(DataStorage dataStorage) {
		_dataStorage = dataStorage;
		_groups = new ArrayList<>();
	}

	@Override
	public OutputValues execute(InputValues input) {
		
		
		return new OutputValues(RegisterResult.SUCCESSED, "");
	}

	public static class InputValues {
		private String _userID;

		public InputValues(String userID) {
			_userID = userID;
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
