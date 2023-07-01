package COM2006.project.tables;

public class Bathroom {
    private int bathroomID;
    private int bathingFacilityID_fk;
    private int hasToilet;
    private int hasShower;
    private int hasBath;
    private int sharedWithHost;

    public Bathroom(int bathroomID, int bathingFacilityID_fk, int hasToilet, int hasShower, int hasBath, int sharedWithHost){
        this.bathroomID = bathroomID;
        this.hasToilet = hasToilet;
        this.hasShower = hasShower;
        this.hasBath = hasBath;
        this.sharedWithHost = sharedWithHost;
        this.bathingFacilityID_fk = bathingFacilityID_fk;
    }

    //Setters
    public void setBathroomID(int bedroomID) {
        this.bathroomID = bedroomID;
    }

    public void setBathingFacilityID_fk(int bathingFacilityID_fk) {
        this.bathingFacilityID_fk = bathingFacilityID_fk;
    }

    public void setHasToilet(int hasToilet) {
        this.hasToilet = hasToilet;
    }

    public void setHasShower(int hasShower) {
        this.hasShower = hasShower;
    }

    public void setHasBath(int hasBath){
        this.hasBath=hasBath;
    }

    public void setSharedWithHost(int sharedWithHost) {
        this.sharedWithHost = sharedWithHost;
    }

    //Getters
    public int getBedroomID() {
        return bathroomID;
    }

    public int getBathingFacilityID_fk() {
        return bathingFacilityID_fk;
    }

    public int getHasToilet() {
        return hasToilet;
    }

    public int getHasShower() {
        return hasShower;
    }

    public int getHasBath() {
        return hasBath;
    }

    public int getSharedWithHost() {
        return sharedWithHost;
    }
}
