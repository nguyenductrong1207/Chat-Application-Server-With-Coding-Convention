package application.talk.domains;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Conversation extends BaseEntity {
    private List<Message> _messages;
    private List<File> _attachments;
    private User _sender;
    private ChatEntity _receiver;

    public Conversation(User sender, ChatEntity receiver) {
        _sender = sender;
        _receiver = receiver;
        _messages = new ArrayList<>();
    }

    public void addMessage(Message message) {
        File file = message.getAttachment();

        if (file != null) {
            _attachments.add(file);
        }

        _messages.add(message);
    }

    public List<Message> getARangeOfMessage(String destionationID){
        List<Message> foundMessages = new ArrayList<>();

        for(Message message : _messages){
            foundMessages.add(message);

            if(message.getId().equals(destionationID)){
                break;
            }

        }
        return foundMessages;
    }

    public Message getMessageByID(String id) {
        Optional<Message> message = _messages.stream().filter(e -> e.getId().equals(id)).findFirst();

        return message.orElse(null);
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

    public List<File> getAttachments() {
        return _attachments;
    }

    public void setAttachments(List<File> attachments) {
        _attachments = attachments;
    }


}
