package COM2006.project.tables;

public class Address {
    //Instance variables
    private int addressID;
    private int houseNumber;
    private String streetName;
    private String postCode;
    private String city;
    private String country;

    //Constructor
    public Address(int addressID, int houseNumber,String streetName, String postCode, String city, String country) {
        this.addressID = addressID;
        this.houseNumber = houseNumber;
        this.streetName = streetName;
        this.postCode = postCode;
        this.city = city;
        this.country = country;
    }

    //Getters
    public int getAddressID() {
        return addressID;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    //Setters

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}

