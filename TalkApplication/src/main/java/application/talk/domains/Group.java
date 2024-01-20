package application.talk.domains;

import java.util.ArrayList;

public interface Group {
	ArrayList<User> getUsers();
	void setName(String name);
	String getName();
}
