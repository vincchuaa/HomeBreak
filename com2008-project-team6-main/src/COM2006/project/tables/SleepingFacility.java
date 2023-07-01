package COM2006.project.tables;

public class SleepingFacility {
    private int sleepingFacilityID;
    private int hasBedLinen;
    private int hasTowels;
    private int numBedrooms;

    public SleepingFacility(int sleepingFacilityID, int hasBedLinen, int hasTowels){
        this.sleepingFacilityID = sleepingFacilityID;
        this.hasBedLinen = hasBedLinen;
        this.hasTowels = hasTowels;
    }

    //Setters
    public void setSleepingFacilityID(int sleepingFacilityID) {
        this.sleepingFacilityID = sleepingFacilityID;
    }


    public void setHasBedLinen(int hasBedLinen) {
        this.hasBedLinen = hasBedLinen;
    }

    public void setHasTowels(int hasTowels) {
        this.hasTowels = hasTowels;
    }


    //Getters
    public int getSleepingFacilityID() {
        return sleepingFacilityID;
    }


    public int getHasBedLinen() {
        return hasBedLinen;
    }

    public int getHasTowels() {
        return hasTowels;
    }

}

