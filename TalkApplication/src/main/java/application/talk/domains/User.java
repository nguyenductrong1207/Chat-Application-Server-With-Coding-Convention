package application.talk.domains;

import java.util.Date;

public class User extends ChatEntity{
	private String _firstName;
	private String _lastName;
	private String _username;
	private String _hashedPassword;
	private Date _dateOfBirth;
	private Gender _gender;

	public enum Gender {
		MALE, FEMALE, OTHER
	}

	public User(String username, String hashedPassword) {
		super();
		_firstName = username;
		_hashedPassword = hashedPassword;
	}

	public String getFirstName() {
		return _firstName;
	}

	public void setFirstName(String firstName) {
		_firstName = firstName;
	}

	public String getLastName() {
		return _lastName;
	}

	public void setLastName(String lastName) {
		_lastName = lastName;
	}

	public String getFullName() {
		return _lastName + " " + _firstName;
	}

	public Date getDateOfBirth() {
		return _dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		_dateOfBirth = dateOfBirth;
	}

	public Gender getGender() {
		return _gender;
	}

	public void setGender(Gender gender) {
		_gender = gender;
	}

	public static class UserBuilder {
        private final String _username;
        private final String _password;
        private String _email;
        private String _fullName;
        private Date _dateOfBirth;
        private String _address;
        private Gender _gender;

        public UserBuilder(String username, String password) {
            this._username = username;
            this._password = password;
        }

        public UserBuilder email(String email) {
            _email = email;
            return this;
        }

        public UserBuilder fullName(String fullName) {
            _fullName = fullName;
            return this;
        }

        public UserBuilder dateOfBirth(Date dateOfBirth) {
            _dateOfBirth = dateOfBirth;
            return this;
        }

        public UserBuilder address(String address) {
            _address = address;
            return this;
        }

        public UserBuilder gender(Gender gender) {
            _gender = gender;
            return this;
        }

        public User build() {
            User user = new User(_username, _password);
            
            return user;
        }
    }
}



