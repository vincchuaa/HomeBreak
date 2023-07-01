package COM2006.project.tables;

public class UtilityFacility {
    //Instance Variables
    private int utilityFacilityID;
    private int hasHeating;
    private int hasWashingMachine;
    private int hasDryingMachine;
    private int hasFireExtinguisher;
    private int hasSmokeAlarm;
    private int hasFirstAidKit;


    //Constructor
    public UtilityFacility(int utilityFacilityID, int hasHeating,int hasWashingMachine, int hasDryingMachine
            , int hasFireExtinguisher, int hasSmokeAlarm, int hasFirstAidKit) {
        this.utilityFacilityID = utilityFacilityID;
        this.hasHeating = hasHeating;
        this.hasWashingMachine = hasWashingMachine;
        this.hasDryingMachine = hasDryingMachine;
        this.hasFireExtinguisher = hasFireExtinguisher;
        this.hasSmokeAlarm = hasSmokeAlarm;
        this.hasFirstAidKit = hasFirstAidKit;

    }

    //Getters
    public int getUtilityFacilityID() {return utilityFacilityID;}

    public int getHasHeating() {return hasHeating;}

    public int getHasWashingMachine() {return hasWashingMachine;}

    public int getHasDryingMachine() {return hasDryingMachine;}

    public int getHasFireExtinguisher() {return hasFireExtinguisher;}

    public int getHasSmokeAlarm() {return hasSmokeAlarm;}

    public int getHasFirstAidKit() {return hasFirstAidKit;}


    //Setters

    public void setUtilityFacilityID(int utilityFacilityID) {this.utilityFacilityID = utilityFacilityID;}

    public void setHasHeating(int hasHeating) {this.hasHeating = hasHeating;}

    public void setHasWashingMachine(int hasWashingMachine) {this.hasWashingMachine = hasWashingMachine;}

    public void setHasDryingMachine(int hasDryingMachine) {this.hasDryingMachine = hasDryingMachine;}

    public void setHasFireExtinguisher(int hasFireExtinguisher) {this.hasFireExtinguisher = hasFireExtinguisher;}

    public void setHasSmokeAlarm(int hasSmokeAlarm) {this.hasSmokeAlarm = hasSmokeAlarm;}

    public void setHasFirstAidKit(int hasFirstAidKit) {this.hasFirstAidKit = hasFirstAidKit;}

}

