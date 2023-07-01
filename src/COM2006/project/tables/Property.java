package COM2006.project.tables;

public class Property {
    private int propertyID;
    private int facilityID_fk;
    private String propertyName;
    private int hostID_fk;
    private String description;
    private int hasBreakfast;
    private int addressID_fk;

    public Property (int propertyID, int facilityID_fk, String propertyName,
                     int hostID_fk, String description, int hasBreakfast, int addressID_fk) {
        this.propertyID = propertyID;
        this.facilityID_fk = facilityID_fk;
        this.propertyName = propertyName;
        this.hostID_fk = hostID_fk;
        this.description = description;
        this.hasBreakfast = hasBreakfast;
        this.addressID_fk = addressID_fk;
    }

    //Getter

    public int getPropertyID() {
        return propertyID;
    }

    public int getFacilityID_fk() {
        return facilityID_fk;
    }

    public String getPropertyName() {
        return propertyName;
    }


    public int getHostID_fk() {
        return hostID_fk;
    }

    public String getDescription() {
        return description;
    }

    public int getHasBreakfast() {
        return hasBreakfast;
    }

    public int getAddressID_fk() {
        return addressID_fk;
    }

    //Setters

    public void setPropertyID(int propertyID) {
        this.propertyID = propertyID;
    }

    public void setFacilityID_fk(int facilityID_fk) {
        this.facilityID_fk = facilityID_fk;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public void setHostID_fk(int hostID_fk) {
        this.hostID_fk = hostID_fk;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setHasBreakfast(int hasBreakfast) {
        this.hasBreakfast = hasBreakfast;
    }

    public void setAddressID_fk(int addressID_fk) {
        this.addressID_fk = addressID_fk;
    }
}
