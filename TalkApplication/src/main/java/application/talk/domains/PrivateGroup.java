package application.talk.domains;

import application.talk.enums.GroupType;

import java.util.ArrayList;
import java.util.List;

public class PrivateGroup extends Group {
    private List<User> _admins;

    public PrivateGroup(String name, User admin) {
        super(name, GroupType.PRIVATEGROUP);
        _admins = new ArrayList<>();
        _admins.add(admin);
    }

    public void addAdmin(User user) {
        _admins.add(user);
    }

    public List<User> getAdmins() {
        return _admins;
    }
}
