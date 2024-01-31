package application.talk.domains;

import java.util.ArrayList;
import java.util.List;

public abstract class ChatEntity extends BaseEntity {
	private User _user;
	private Group _group;
	private List<Message> _messages;
	
	public ChatEntity(User user) {
		super();
		_user = user;
		_messages = new ArrayList<>();
	}
	
	public ChatEntity( Group group) {
		super();
		_group = group;
		_messages = new ArrayList<>();
	}
	
	
	public User getUser() {
		return _user;
	}

	public Group getGroup() {
		return _group;
	}

	public List<Message> getMessages(){
		return _messages;
	}
}
