package application.talk.domains;

public abstract class ChatEntity extends BaseEntity {
    private User _user;
    private Group _group;

    public ChatEntity(User user) {
        super();
        _user = user;
    }

    public ChatEntity(Group group) {
        super();
        _group = group;
    }

    public ChatEntity() {
        super();
    }

    public User getUser() {
        return _user;
    }

    public Group getGroup() {
        return _group;
    }
}
