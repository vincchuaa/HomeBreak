package COM2006.project.tables;

public class KitchenFacility {
    //Instance Variables
    private int kitchenFacilityID;
    private int hasOven;
    private int hasFridge;
    private int hasMicrowave;
    private int hasStove;
    private int hasDishWasher;
    private int hasTableware;
    private int hasCookware;
    private int hasBasicProvisions;

    //Constructor
    public KitchenFacility(int kitchenFacilityID, int hasOven,int hasFridge, int hasMicrowave, int hasStove
            , int hasDishWasher, int hasTableware, int hasCookware, int hasBasicProvisions) {
        this.kitchenFacilityID = kitchenFacilityID;
        this.hasOven = hasOven;
        this.hasFridge = hasFridge;
        this.hasMicrowave = hasMicrowave;
        this.hasStove = hasStove;
        this.hasDishWasher = hasDishWasher;
        this.hasTableware = hasTableware;
        this.hasCookware = hasCookware;
        this.hasBasicProvisions = hasBasicProvisions;
    }

    //Getters
    public int getKitchenFacilityID() {return kitchenFacilityID;}

    public int getHasOven() {return hasOven;}

    public int getHasFridge() {return hasFridge;}

    public int getHasMicrowave() {return hasMicrowave;}

    public int getHasStove() {return hasStove;}

    public int getHasDishWasher() {return hasDishWasher;}

    public int getHasTableware() {return hasTableware;}

    public int getHasCookware() {return hasCookware;}

    public int getHasBasicProvisions() {return hasBasicProvisions;}

    //Setters

    public void setKitchenFacilityID(int kitchenFacilityID) {this.kitchenFacilityID = kitchenFacilityID;}

    public void setHasOven(int hasOven) {this.hasOven = hasOven;}

    public void setHasFridge(int hasFridge) {this.hasFridge = hasFridge;}

    public void setHasMicrowave(int hasMicrowave) {this.hasMicrowave = hasMicrowave;}

    public void setHasStove(int hasStove) {this.hasStove = hasStove;}

    public void setHasDishWasher(int hasDishWasher) {this.hasDishWasher = hasDishWasher;}

    public void setHasTableware(int hasTableware) {this.hasTableware = hasTableware;}

    public void setHasCookware(int hasCookware) {this.hasCookware = hasCookware;}

    public void setHasBasicProvisions(int hasBasicProvisions) {this.hasBasicProvisions = hasBasicProvisions;}
}
