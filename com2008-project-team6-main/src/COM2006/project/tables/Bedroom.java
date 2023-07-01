package COM2006.project.tables;

public class Bedroom {
    private int bedroomID;
    private int sleepingFacilityID_fk;

    private String bedOneType;
    private String bedTwoType;

    public Bedroom(int bedroomID, int sleepingFacilityID_fk,
                   String bedOneType, String bedTwoType){
        this.bedroomID = bedroomID;
        this.bedOneType = bedOneType;
        this.bedTwoType = bedTwoType;
        this.sleepingFacilityID_fk = sleepingFacilityID_fk;
    }

    //Setters
    public void setBedroomID(int bedroomID) {
        this.bedroomID = bedroomID;
    }

    public void setSleepingFacilityID_fk(int sleepingFacilityID_fk) {
        this.sleepingFacilityID_fk = sleepingFacilityID_fk;
    }

    public void setBedOneType(String bedOneType) {
        this.bedOneType = bedOneType;
    }

    public void setBedTwoType(String bedTwoType) {
        this.bedTwoType = bedTwoType;
    }

    //Getters
    public int getBedroomID() {
        return bedroomID;
    }

    public int getSleepingFacilityID_fk() {
        return sleepingFacilityID_fk;
    }

    public String getBedOneType() {
        return bedOneType;
    }

    public String getBedTwoType() {
        return bedTwoType;
    }
}
