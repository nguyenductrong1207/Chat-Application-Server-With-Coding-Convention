package application.talk.usecases.user;

import java.util.ArrayList;
import java.util.List;

import application.talk.domains.Group;
import application.talk.domains.PrivateGroup;
import application.talk.domains.PublicGroup;
import application.talk.domains.User;
import application.talk.enums.FinalResult;
import application.talk.usecases.UseCase;
import application.talk.usecases.adapters.DataStorage;

public class GetGroupsOfUser extends UseCase<GetGroupsOfUser.InputValues, GetGroupsOfUser.OutputValues> {
	private DataStorage _dataStorage;

	public GetGroupsOfUser(DataStorage dataStorage) {
		_dataStorage = dataStorage;
	}

	@Override
	public OutputValues execute(InputValues input) {
		List<Group> joinedGroup = new ArrayList<>();

		for(Group group : _dataStorage.getGroups().getAll()){
			if(group.findUserByID(input._userId) != null){
				joinedGroup.add(group);
			}
		}

		return new OutputValues(FinalResult.SUCCESSFUL, "", joinedGroup);
	}

	public static class InputValues {
		private String _userId;

		public InputValues(String id) {
			_userId = id;
		}
	}

	public static class OutputValues {
		private final FinalResult RESULT;
		private final String MESSAGE;
		private List<Group> _foundGroups;
		public OutputValues(FinalResult result, String message, List<Group> groups) {
			MESSAGE = message;
			RESULT = result;
			_foundGroups = groups;
		}

		public FinalResult getResult() {
			return RESULT;
		}

		public String getMessage() {
			return MESSAGE;
		}

		public List<Group> getFoundGroups() {
			return _foundGroups;
		}
	}
}
