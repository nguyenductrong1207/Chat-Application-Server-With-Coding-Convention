package application.talk.domains;

import java.util.ArrayList;

public class Conversation extends BaseEntity {
	private ArrayList<Message> _messages;
	private ArrayList<User> _participants;
}
