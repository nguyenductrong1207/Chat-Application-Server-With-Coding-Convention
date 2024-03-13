package application.talk.domains;

public abstract class ChatEntity extends BaseEntity {
    private String _name;

    public ChatEntity(String name) {
        super();
        _name = name;
    }

    public ChatEntity() {
        super();
    }

    public String getName() {
        return _name;
    }

    public void setName(String _name) {
        this._name = _name;
    }
}
