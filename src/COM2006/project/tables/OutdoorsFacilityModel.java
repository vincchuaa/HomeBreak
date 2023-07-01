package COM2006.project.tables;

import COM2006.project.ConvertBoolToInt;

import java.sql.SQLException;

public class OutdoorsFacilityModel extends Model {
    OutdoorsFacility outdoorsFacility;

    public OutdoorsFacilityModel(boolean hasFreeOnSiteParking,boolean hasPaidCarPark, boolean hasPatio,
                                 boolean hasBarbequeFacilities) {
        ConvertBoolToInt converter = new ConvertBoolToInt();
        int hasFreeOnSiteParkingInt = converter.convertBoolToInt(hasFreeOnSiteParking);
        int hasPaidCarParkInt = converter.convertBoolToInt(hasPaidCarPark);
        int hasPatioInt = converter.convertBoolToInt(hasPatio);
        int hasBarbequeFacilitiesInt = converter.convertBoolToInt(hasBarbequeFacilities);
        setOutdoorsFacility(new OutdoorsFacility (0, hasFreeOnSiteParkingInt, hasPaidCarParkInt
                , hasPatioInt, hasBarbequeFacilitiesInt));
    }

    public OutdoorsFacilityModel(int outdoorsFacilityID) {
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                openResSet("SELECT hasFreeOnSiteParking, hasPaidCarPark, hasPatio, hasBarbequeFacilities, " +
                        "FROM OutdoorsFacility WHERE outdoorsFacilityID='" + outdoorsFacilityID + "';");

                while(getResSet().next()) {
                    int hasFreeOnSiteParking = getResSet().getInt(1);
                    int hasPaidCarPark = getResSet().getInt(2);
                    int hasPatio = getResSet().getInt(3);
                    int hasBarbequeFacilities = getResSet().getInt(4);

                    setOutdoorsFacility(new OutdoorsFacility (outdoorsFacilityID, hasFreeOnSiteParking, hasPaidCarPark
                            , hasPatio, hasBarbequeFacilities));
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

    public OutdoorsFacility getOutdoorsFacility() {
        return outdoorsFacility;
    }

    public void setOutdoorsFacility(OutdoorsFacility outdoorsFacility) {
        this.outdoorsFacility = outdoorsFacility;
    }

    public void insertOutdoorsFacilityRow() {
        insert("OutdoorsFacility",
                "(DEFAULT,'"+getOutdoorsFacility().getHasFreeOnSiteParking()+"','"
                        +getOutdoorsFacility().getHasPaidCarPark()+"','"
                        +getOutdoorsFacility().getHasPatio()+"','"
                        +getOutdoorsFacility().getHasBarbequeFacilities()+"')");
    }
    public void setOutdoorsFacilityID() {
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                openResSet("SELECT outdoorsFacilityID " +
                        "FROM OutdoorsFacility ORDER BY outdoorsFacilityID DESC LIMIT 1;");

                while (getResSet().next()) {
                    int outdoorsFacilityID = getResSet().getInt(1);
                    getOutdoorsFacility().setOutdoorsFacilityID(outdoorsFacilityID);
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

