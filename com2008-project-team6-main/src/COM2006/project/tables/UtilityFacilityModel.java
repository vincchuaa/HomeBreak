package COM2006.project.tables;

import COM2006.project.ConvertBoolToInt;

import java.sql.SQLException;

public class UtilityFacilityModel extends Model{
    UtilityFacility utilityFacility;

    public UtilityFacilityModel(boolean hasHeating,boolean hasWashingMachine,boolean hasDryingMachine,
                                boolean hasFireExtinguisher,boolean hasSmokeAlarm,boolean hasFirstAidKit){
        ConvertBoolToInt converter = new ConvertBoolToInt();
        int hasHeatingInt = converter.convertBoolToInt(hasHeating);
        int hasWashingMachineInt = converter.convertBoolToInt(hasWashingMachine);
        int hasDryingMachineInt = converter.convertBoolToInt(hasDryingMachine);
        int hasFireExtinguisherInt = converter.convertBoolToInt(hasFireExtinguisher);
        int hasSmokeAlarmInt = converter.convertBoolToInt(hasSmokeAlarm);
        int hasFirstAidKitInt = converter.convertBoolToInt(hasFirstAidKit);
        setUtilityFacility(new UtilityFacility (0, hasHeatingInt, hasWashingMachineInt,
                hasDryingMachineInt, hasFireExtinguisherInt, hasSmokeAlarmInt,hasFirstAidKitInt));
    }

    public UtilityFacilityModel(int utilityFacilityID) {
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                openResSet("SELECT hasHeating, hasWashingMachine, hasDryingMachine, hasFireExtinguisher," +
                        " hasSmokeAlarm, hasFirstAidKit " +
                        "FROM UtilityFacility WHERE utilityFacilityID='" + utilityFacilityID + "';");

                while(getResSet().next()) {
                    int hasHeating = getResSet().getInt(1);
                    int hasWashingMachine = getResSet().getInt(2);
                    int hasDryingMachine = getResSet().getInt(3); //might need to change cal type
                    int hasFireExtinguisher = getResSet().getInt(4);
                    int hasSmokeAlarm = getResSet().getInt(5);
                    int hasFirstAidKit = getResSet().getInt(6);
                    setUtilityFacility(new UtilityFacility (utilityFacilityID, hasHeating, hasWashingMachine,
                            hasDryingMachine, hasFireExtinguisher, hasSmokeAlarm,hasFirstAidKit));
                }
            }
            finally {
                closeCon();
                closeStmt();
                closeResSet();
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public UtilityFacility getUtilityFacility() {
        return utilityFacility;
    }

    public void setUtilityFacility(UtilityFacility utilityFacility) {
        this.utilityFacility = utilityFacility;
    }

    public void insertUtilityFacilityRow() {
        insert("UtilityFacility",
                "(DEFAULT,'"+getUtilityFacility().getHasHeating()+"','"
                        +getUtilityFacility().getHasWashingMachine()+"','"
                        +getUtilityFacility().getHasDryingMachine()+"','"
                        +getUtilityFacility().getHasFireExtinguisher()+"','"
                        +getUtilityFacility().getHasSmokeAlarm()+"','"
                        +getUtilityFacility().getHasFirstAidKit()+"')");
    }

    public void setUtilityFacilityID() {
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                openResSet("SELECT utilityFacilityID " +
                        "FROM UtilityFacility ORDER BY utilityFacilityID DESC LIMIT 1;");

                while (getResSet().next()) {
                    int utilityFacilityID = getResSet().getInt(1);
                    getUtilityFacility().setUtilityFacilityID(utilityFacilityID);
                }
            } finally {
                closeCon();
                closeStmt();
                closeResSet();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
