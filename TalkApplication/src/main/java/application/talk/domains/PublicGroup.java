package application.talk.domains;

import application.talk.enums.GroupType;

public class PublicGroup extends Group {
    private String _joinCode;

    public PublicGroup(String name, String joinCode) {
        super(name, GroupType.PUBLICGROUP);
        _joinCode = joinCode;
    }

    public String getJoinCode() {
        return _joinCode;
    }
}
