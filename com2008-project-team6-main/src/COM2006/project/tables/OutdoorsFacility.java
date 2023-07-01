package COM2006.project.tables;

public class OutdoorsFacility {
    //Instance Variables
    private int outdoorsFacilityID;
    private int hasFreeOnSiteParking;
    private int hasPaidCarPark;
    private int hasPatio;
    private int hasBarbequeFacilities;

    //Constructor
    public OutdoorsFacility(int outdoorsFacilityID, int hasFreeOnSiteParking,int hasPaidCarPark, int hasPatio
            , int hasBarbequeFacilities) {
        this.outdoorsFacilityID = outdoorsFacilityID;
        this.hasFreeOnSiteParking = hasFreeOnSiteParking;
        this.hasPaidCarPark = hasPaidCarPark;
        this.hasPatio = hasPatio;
        this.hasBarbequeFacilities = hasBarbequeFacilities;
    }

    //Getters
    public int getOutdoorsFacilityID() {return outdoorsFacilityID;}

    public int getHasFreeOnSiteParking() {return hasFreeOnSiteParking;}

    public int getHasPaidCarPark() {return hasPaidCarPark;}

    public int getHasPatio() {return hasPatio;}

    public int getHasBarbequeFacilities() {return hasBarbequeFacilities;}

    //Setters

    public void setOutdoorsFacilityID(int outdoorsFacilityID) {this.outdoorsFacilityID = outdoorsFacilityID;}

    public void setHasFreeOnSiteParking(int hasFreeOnSiteParking) {this.hasFreeOnSiteParking = hasFreeOnSiteParking;}

    public void setHasPaidCarPark(int hasPaidCarPark) {this.hasPaidCarPark = hasPaidCarPark;}

    public void setHasPatio(int hasPatio) {this.hasPatio = hasPatio;}

    public void setHasBarbequeFacilities(int hasBarbequeFacilities) {this.hasBarbequeFacilities = hasBarbequeFacilities;}

}
