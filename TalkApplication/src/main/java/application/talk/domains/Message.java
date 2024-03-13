package application.talk.domains;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message extends BaseEntity {
    private User _sender;
    private LocalDateTime _timestamp;
    private ChatEntity _receiver;
    private String _content;
    private File _attachment;

    public Message(User sender, LocalDateTime timestamp, ChatEntity receiver, String content) {
        super();
        _sender = sender;
        _timestamp = timestamp;
        _receiver = receiver;
        _content = content;
    }

//	public void formatDateTime(){
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
//        _timestamp.format(formatter);
//	}

    public String getContent() {
        return _content;
    }

    public void setContent(String newContent) {
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
