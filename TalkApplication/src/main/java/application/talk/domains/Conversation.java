package application.talk.domains;

import java.util.List;

public class Conversation extends BaseEntity {
	private List<Message> _messages;
	private List<User> _participants;
}
