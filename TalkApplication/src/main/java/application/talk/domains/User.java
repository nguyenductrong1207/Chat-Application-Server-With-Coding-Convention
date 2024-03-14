package application.talk.domains;

import application.talk.enums.Gender;

import java.time.LocalDate;

public class User extends ChatEntity {
    private String _firstName;
    private String _lastName;
    private String _username;
    private String _hashedPassword;
    private LocalDate _dateOfBirth;
    private Gender _gender;
    private String _address;
    private String _email;

    public User(String username, String hashedPassword) {
        super();
        _firstName = username;
        _hashedPassword = hashedPassword;
    }

    public String getFirstName() {
        return _firstName;
    }

    public String getLastName() {
        return _lastName;
    }

    public String getFullName() {
        return _lastName + " " + _firstName;
    }

    public LocalDate getDateOfBirth() {
        return _dateOfBirth;
    }

    public Gender getGender() {
        return _gender;
    }

    public String getAddress() {
        return _address;
    }

    public String getEmail() {
        return _email;
    }

    public void setFirstName(String firstName) {
        _firstName = firstName;
    }

    public void setLastName(String lastName) {
        _lastName = lastName;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        _dateOfBirth = dateOfBirth;
    }

    public void setGender(Gender gender) {
        _gender = gender;
    }

    public void setAddress(String address) {
        _address = address;
    }

    public void setEmail(String email) {
        _email = email;
    }

    public static class UserBuilder {
        private final String USERNAME;
        private final String PASSWORD;
        private String _email;
        private String _firstName;
        private String _lastName;
        private LocalDate _dateOfBirth;
        private String _address;
        private Gender _gender;

        public UserBuilder(String username, String password) {
            USERNAME = username;
            PASSWORD = password;
        }

        public UserBuilder email(String email) {
            _email = email;
            return this;
        }

        public UserBuilder firstName(String firstName) {
            _firstName = firstName;
            return this;
        }

        public UserBuilder lastName(String lastName) {
            _lastName = lastName;
            return this;
        }

        public UserBuilder dateOfBirth(LocalDate dateOfBirth) {
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
            User user = new User(USERNAME, PASSWORD);

            user.setFirstName(_firstName);
            user.setLastName(_lastName);
            user.setGender(_gender);
            user.setDateOfBirth(_dateOfBirth);
            user.setEmail(_email);
            user.setAddress(_address);

            return user;
        }
    }
}



