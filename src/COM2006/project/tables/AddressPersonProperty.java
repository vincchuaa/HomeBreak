package COM2006.project.tables;

public class AddressPersonProperty {
    private int addressID;
    private String email;
    private int propertyID;
    private int host;

    public AddressPersonProperty(int addressID, String email, int propertyID, int host){
        this.addressID = addressID;
        this.email = email;
        this.propertyID = propertyID;
        this.host = host;
    }

    //Setters

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setHost(int host) {
        this.host = host;
    }

    public void setPropertyID(int propertyID) {
        this.propertyID = propertyID;
    }

    //Getters

    public int getAddressID() {
        return addressID;
    }

    public String getEmail() {
        return email;
    }

    public int getHost() {
        return host;
    }

    public int getPropertyID() {
        return propertyID;
    }
}
