package application.talk.usecases.message;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.talk.domains.Conversation;
import application.talk.domains.File;
import application.talk.domains.Group;
import application.talk.domains.File.Type;
import application.talk.domains.PrivateGroup;
import application.talk.domains.PublicGroup;
import application.talk.domains.User;
import application.talk.infrastructure.data.InMemoryDataStorage;
import application.talk.usecases.adapters.DataStorage;
import application.talk.usecases.user.ViewingFile.CreatingResult;

public class ViewingFileTest {

	private DataStorage _storage;
	private ViewingFile _seeingFile;

	@Before
	public void setUp() throws Exception {
		_storage = InMemoryDataStorage.getInstance();
		_seeingFile = new ViewingFile(_storage);

	}

	@After
	public void tearDown() throws Exception {
		DataStorage storage = InMemoryDataStorage.getInstance();
		storage.cleanAll();
	}

	@Test
	public void testSeeingFile() {
//		setup
		User user = new User("duyen", "001");
		User user2 = new User("kiet", "002");
		_storage.getUsers().add(user);
		_storage.getUsers().add(user2);

		PrivateGroup group = new PrivateGroup("group1", user);
		group.addUser(user2);
		_storage.getGroups().add(group);

		List<File> files = Arrays.asList(new File(10, Type.AUDIO), new File(5, Type.IMAGE));
		
		Conversation conversation = new Conversation(user2, group);
		
		conversation.setAttachments(files);
		
		_storage.getConversations().add(conversation);
		
		ViewingFile.InputValues input = new ViewingFile.InputValues(user2.getId(), conversation.getId());
		ViewingFile.OutputValues output = _seeingFile.execute(input);

		assertEquals(FinalResult.SUCCESSFUL, output.getResult());
	}

}
