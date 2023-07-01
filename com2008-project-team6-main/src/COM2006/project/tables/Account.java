package COM2006.project.tables;

public class Account {
    //Instance variables
    private String email;
    private String password;
    private String salt;
    private String username;
    private String forename;
    private String surname;
    private String phoneNumber;
    private int addressID_fk;
    private String title;

    //Constructor
    public Account(String email, String password, String salt, String username, String forename, String surname,
                   String phoneNumber, int addressID_fk, String title) {
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.username = username;
        this.forename = forename;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.addressID_fk = addressID_fk;
        this.title = title;
    }

    //Getters
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public String getUsername() {
        return username;
    }

    public String getForename() {
        return forename;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getAddressID_fk() {
        return addressID_fk;
    }

    public String getTitle() {
        return title;
    }

    //Setters

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddressID_fk(int addressID_fk) {
        this.addressID_fk = addressID_fk;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
