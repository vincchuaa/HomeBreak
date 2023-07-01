package COM2006.project.tables;

public class BathingFacility {
    private int bathingFacilityID;
    private int hasHairDryer;
    private int hasShampoo;
    private int hasToiletPaper;
    private int numBathrooms;

    public BathingFacility(int bathingFacilityID, int hasHairDryer, int hasShampoo, int hasToiletPaper){
        this.bathingFacilityID = bathingFacilityID;
        this.hasHairDryer = hasHairDryer;
        this.hasShampoo = hasShampoo;
        this.hasToiletPaper = hasToiletPaper;
    }

    //Setters
    public void setBathingFacilityID(int bathingFacilityID) {
        this.bathingFacilityID = bathingFacilityID;
    }

    public void setHasHairDryer(int hasHairDryer) {
        this.hasHairDryer = hasHairDryer;
    }

    public void setHasShampoo(int hasShampoo) {
        this.hasShampoo = hasShampoo;
    }

    public void setHasToiletPaper(int hasToiletPaper) {
        this.hasToiletPaper = hasToiletPaper;
    }


    //Getters
    public int getBathingFacilityID() {
        return bathingFacilityID;
    }

    public int getHasHairDryer() {
        return hasHairDryer;
    }

    public int getHasShampoo() {
        return hasShampoo;
    }

    public int getHasToiletPaper() {
        return hasToiletPaper;
    }


    //public Bathroom getBathroomObj() {
        //return (new BathroomModel(bathroomID).getBathroom());
    //}
}
