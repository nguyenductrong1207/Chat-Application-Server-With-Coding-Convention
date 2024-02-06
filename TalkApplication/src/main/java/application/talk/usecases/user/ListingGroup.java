package application.talk.usecases.user;

import java.util.ArrayList;
import java.util.List;

import application.talk.domains.Group;
import application.talk.domains.PrivateGroup;
import application.talk.domains.PublicGroup;
import application.talk.usecases.UseCase;
import application.talk.usecases.adapters.DataStorage;

public class ListingGroup extends UseCase<ListingGroup.InputValues, ListingGroup.OutputValues> {
	private DataStorage _dataStorage;
	private List<String> listingGroups;

	public ListingGroup(DataStorage dataStorage) {
		_dataStorage = dataStorage;
	}

	@Override
	public OutputValues execute(InputValues input) {
		listingGroups = new ArrayList<String>();
		PrivateGroup privateGroup = new PrivateGroup(null, null);
		PublicGroup publicGroup = new PublicGroup(null, null);
		
		if (input._name == null) {
			return new OutputValues(RegisterResult.FAILED, "");
		}
		for (var i : privateGroup.getUsers()) {
			if (input._name.equals(i.getName())) {
				listingGroups.add(privateGroup.getName());
			}
		}

		for (var i : publicGroup.getUsers()) {
			if (input._name.equals(i.getName())) {
				listingGroups.add(publicGroup.getName());
			}
		}

		return new OutputValues(RegisterResult.SUCCESSED, "");
	}

	public static class InputValues {
		private String _name;

		public InputValues(String name) {
			_name = name;
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
