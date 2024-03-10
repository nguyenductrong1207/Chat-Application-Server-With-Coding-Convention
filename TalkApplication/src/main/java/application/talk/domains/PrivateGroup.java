package application.talk.domains;

import java.util.ArrayList;
import java.util.List;

public class PrivateGroup extends Group {
    private List<User> _users;
    private List<User> _admins;
    private String _name;

    public PrivateGroup(String name, User admin) {
        super(name);
        _name = name;
        _admins = new ArrayList<User>();
        _admins.add(admin);
    }

    public void addAdmin(User user) {
        if (_admins == null) {
            _admins = new ArrayList<>();
        }
        _admins.add(user);
    }

    public List<User> getAdmins() {
        return _admins;
    }

}
