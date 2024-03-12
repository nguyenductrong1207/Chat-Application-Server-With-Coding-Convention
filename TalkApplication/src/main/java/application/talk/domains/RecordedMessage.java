package application.talk.domains;

public class RecordedMessage extends BaseEntity{
    private ChatEntity _sender;
    private Message _lastSeenMessage;

    public RecordedMessage(ChatEntity user, Message message){
        _sender = user;
        _lastSeenMessage = message;
    }

    public ChatEntity getSender() {
        return _sender;
    }

    public Message getRecordedMessages() {
        return _lastSeenMessage;
    }


}
