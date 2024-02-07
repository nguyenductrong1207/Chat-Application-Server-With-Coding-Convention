package application.talk.usecases.user;

import java.util.List;

import application.talk.domains.File;
import application.talk.domains.Group;
import application.talk.usecases.UseCase;
import application.talk.usecases.adapters.DataStorage;

public class SeeingFile extends UseCase<SeeingFile.InputValues, SeeingFile.OutputValues> {
	private DataStorage _dataStorage;

	public SeeingFile(DataStorage dataStorage) {
		super();
		_dataStorage = dataStorage;
	}

	@Override
	public OutputValues execute(InputValues input) {
		String groupId = input.getGroupId();
		Group group = _dataStorage.getGroups().getById(groupId);

		if (group == null) {
			return new OutputValues(CreatingResult.FAILED, "Group does not exist.", null);
		}

		List<File> files = group.getFiles();

		return new OutputValues(CreatingResult.SUCCESSFUL, "", files);
	}

	public static class InputValues {
		private final String groupId;

		public InputValues(String groupId) {
			this.groupId = groupId;
		}

		public String getGroupId() {
			return groupId;
		}
	}

	public static class OutputValues {
		private final CreatingResult RESULT;
		private final String MESSAGE;
		private final List<File> FILES;

		public OutputValues(CreatingResult result, String message, List<File> files) {
			MESSAGE = message;
			RESULT = result;
			FILES = files;
		}

		public CreatingResult getResult() {
			return RESULT;
		}

		public String getMessage() {
			return MESSAGE;
		}

		public List<File> getFILES() {
			return FILES;
		}
	}

	public static enum CreatingResult {
		SUCCESSFUL, FAILED
	}
}
