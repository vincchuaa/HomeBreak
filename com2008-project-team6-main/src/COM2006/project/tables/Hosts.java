package COM2006.project.tables;

public class Hosts extends Account {
    private int hostID;
    private int isSuperHost;

    public Hosts(int hostID, int isSuperHost, String email, String password, String salt, String username,
                 String forename, String surname, String phoneNumber, int addressID_fk, String title) {
        super(email, password, salt, username, forename, surname, phoneNumber, addressID_fk, title);
        this.hostID = hostID;
        this.isSuperHost = isSuperHost;
    }

    //Getters
    public int getHostID() {
        return hostID;
    }

    public int getIsSuperHost() {
        return isSuperHost;
    }

    public String getEmail() {
        return super.getEmail();
    }

    public String getPassword() {
        return super.getPassword();
    }

    public String getSalt() {
        return super.getSalt();
    }

    public String getUsername() {
        return super.getUsername();
    }

    public String getForename() {
        return super.getForename();
    }

    public String getSurname() {
        return super.getSurname();
    }

    public int getAddressID_fk() {
        return super.getAddressID_fk();
    }

    public String getPhoneNumber() {
        return super.getPhoneNumber();
    }

    public String getTitle() {
        return super.getTitle();
    }

    //Setters
    public void setHostID(int hostID) {
        this.hostID = hostID;
    }

    public void setIsSuperHost(int superHost) {
        this.isSuperHost = superHost;
    }

    public void setEmail(String email) {
        super.setEmail(email);
    }

    public void setPassword(String password) {
        super.setPassword(password);
    }

    public void setSalt(String salt) {
        super.setSalt(salt);
    }

    public void setUsername(String username) {
        super.setUsername(username);
    }

    public void setForename(String forename) {
        super.setForename(forename);
    }

    public void setSurname(String surname) {
        super.setSurname(surname);
    }

    public void setAddressID_fk(int addressID_fk) {
        super.setAddressID_fk(addressID_fk);
    }

    public void setPhoneNumber(String phoneNumber) {
        super.setPhoneNumber(phoneNumber);
    }

    public void setTitle(String title) {
        super.setTitle(title);
    }
}
