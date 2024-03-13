package application.talk.domains;

import java.util.UUID;

public abstract class BaseEntity {
    private String _id;

    public void setId(String id){
        _id = id;
    }

    public String getId() {
        return _id;
    }

    protected BaseEntity() {
        _id = UUID.randomUUID().toString();
    }
}
