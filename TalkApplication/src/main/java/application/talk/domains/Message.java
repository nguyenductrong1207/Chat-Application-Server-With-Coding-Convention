package application.talk.domains;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import application.talk.usecases.adapters.DataStorage;

public class Message extends BaseEntity {
    private User _sender;
    private LocalDateTime _timestamp;
    private ChatEntity _receiver;
    private String _content;
    private File _attachment;
    private List<String> _messageHistory;

    public Message(User sender, LocalDateTime timestamp, ChatEntity receiver, String content) {
        super();
        _sender = sender;
        _timestamp = timestamp;
        _receiver = receiver;
        _content = content;
        _messageHistory = new ArrayList<>();
        _messageHistory.add(content);

        this.formatDateTime();
    }

    public void removeMessageById(String id) {
        DataStorage dataStorage = null;
        Message message = dataStorage.getMessages().getById(id);

        for (String i : _messageHistory) {
            if (i.equals(message)) {
                _messageHistory.remove(i);

                return;
            }
        }
    }

	public void formatDateTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
        _timestamp.format(formatter);
	}

    public List<String> getMessageHistory() {
        return _messageHistory;
    }

    public String getContent() {
        return _content;
    }

    public void setContent(String newContent) {
        _messageHistory.add(_content);
        _content = newContent;
    }

    public User getSender() {
        return _sender;
    }

    public LocalDateTime getTimestamp() {
        return _timestamp;
    }

    public ChatEntity getReceiver() {
        return _receiver;
    }

    public File getAttachment() {
        return _attachment;
    }

    public void setAttachment(File attachment) {
        _attachment = attachment;
    }
}
