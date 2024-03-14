package application.talk.domains;

import application.talk.enums.GroupType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public boolean checkAdminById(String id) {
        Optional<User> foundAdmin = _admins.stream().filter(e -> e.getId().equals(id)).findFirst();

        return foundAdmin.isPresent();
    }
}
