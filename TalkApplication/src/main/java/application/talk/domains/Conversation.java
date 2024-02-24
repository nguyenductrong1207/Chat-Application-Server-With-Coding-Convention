package application.talk.domains;

import java.util.List;

public class Conversation extends BaseEntity {
	private List<Message> _messages;
	private User _sender;
	private ChatEntity _receiver;

	public Conversation(User sender, ChatEntity receiver) {
		_sender = sender;
		_receiver = receiver;
	}

	public List<Message> getMessages() {
		return _messages;
	}

	public void setMessages(List<Message> _messages) {
		this._messages = _messages;
	}

	public User getSender() {
		return _sender;
	}

	public void setSender(User sender) {
		_sender = sender;
	}

	public ChatEntity getReceiver() {
		return _receiver;
	}

	public void setReceiver(ChatEntity receiver) {
		_receiver = receiver;
	}
}
