package COM2006.project.tables;

public class Facilities {
    private int facilityID;
    private int sleepingFacilityID;
    private int bathingFacilityID;
    private int kitchenFacilityID;
    private int utilityFacilityID;
    private int outdoorsFacilityID;
    private int livingFacilityID;

    public Facilities(int facilityID, int sleepingFacilityID, int bathingFacilityID, int kitchenFacilityID,
                      int utilityFacilityID, int outdoorsFacilityID, int livingFacilityID){
        this.facilityID = facilityID;
        this.sleepingFacilityID = sleepingFacilityID;
        this.bathingFacilityID = bathingFacilityID;
        this.kitchenFacilityID = kitchenFacilityID;
        this.utilityFacilityID = utilityFacilityID;
        this.outdoorsFacilityID = outdoorsFacilityID;
        this.livingFacilityID = livingFacilityID;
    }

    //Setters
    public void setFacilityID(int facilityID) {
        this.facilityID = facilityID;
    }

    public void setSleepingFacilityID(int sleepingFacilityID) {
        this.sleepingFacilityID = sleepingFacilityID;
    }

    public void setBathingFacilityID(int bathingFacilityID) {
        this.bathingFacilityID = bathingFacilityID;
    }

    public void setKitchenFacilityID(int kitchenFacilityID) {
        this.kitchenFacilityID = kitchenFacilityID;
    }

    public void setUtilityFacilityID(int utilityFacilityID) {
        this.utilityFacilityID = utilityFacilityID;
    }

    public void setOutdoorsFacilityID(int outdoorsFacilityID) {
        this.outdoorsFacilityID = outdoorsFacilityID;
    }

    public void setLivingFacilityID(int livingFacilityID) {
        this.livingFacilityID = livingFacilityID;
    }

    //Getters
    public int getFacilityID() {
        return facilityID;
    }

    public int getBathingFacilityID() {
        return bathingFacilityID;
    }

    public int getSleepingFacilityID() {
        return sleepingFacilityID;
    }

    public int getKitchenFacilityID() {
        return kitchenFacilityID;
    }

    public int getLivingFacilityID() {
        return livingFacilityID;
    }

    public int getOutdoorsFacilityID() {
        return outdoorsFacilityID;
    }

    public int getUtilityFacilityID() {
        return utilityFacilityID;
    }

    public BathingFacility getBathingFacilityObj() {
        return (new BathingFacilityModel(bathingFacilityID).getBathingFacility());
    }

    public KitchenFacility getKitchenFacilityObj() {
        return (new KitchenFacilityModel(kitchenFacilityID).getKitchenFacility());
    }

    public LivingFacility getLivingFacilityObj() {
        return (new LivingFacilityModel(livingFacilityID).getLivingFacility());
    }

    public OutdoorsFacility getOutdoorsFacilityObj() {
        return (new OutdoorsFacilityModel(outdoorsFacilityID).getOutdoorsFacility());
    }

    public SleepingFacility getSleepingFacilityObj() {
        return (new SleepingFacilityModel(sleepingFacilityID).getSleepingFacility());
    }

    public UtilityFacility getUtilityFacilityObj() {
        return (new UtilityFacilityModel(utilityFacilityID).getUtilityFacility());
    }
}
