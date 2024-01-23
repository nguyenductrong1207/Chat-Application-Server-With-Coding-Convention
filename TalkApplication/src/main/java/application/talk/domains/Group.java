package application.talk.domains;

import java.util.List;

public interface Group {
	List<User> getUsers();
	
	void setName(String name);
	
	String getName();
}
