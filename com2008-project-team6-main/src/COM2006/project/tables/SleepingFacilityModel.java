package COM2006.project.tables;

import COM2006.project.ConvertBoolToInt;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SleepingFacilityModel extends Model {
    SleepingFacility sleepingFacility;

    public SleepingFacilityModel(boolean hasBedLinen, boolean hasTowels){
        ConvertBoolToInt converter = new ConvertBoolToInt();
        int hasBedLinenInt = converter.convertBoolToInt(hasBedLinen);
        int hasTowelsInt = converter.convertBoolToInt(hasTowels);
        setSleepingFacility(new SleepingFacility(0,hasBedLinenInt,hasTowelsInt));
    }
    public SleepingFacilityModel(int sleepingFacilityID) {
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                openResSet("SELECT * FROM SleepingFacility WHERE sleepingFacilityID='"
                        + sleepingFacilityID + "';");

                while (getResSet().next()) {
                    int hasBedLinen = getResSet().getInt("hasBedLinen");
                    int hasTowels = getResSet().getInt("hasTowels");
                    setSleepingFacility(new SleepingFacility(sleepingFacilityID, hasBedLinen,
                            hasTowels));
                }
            }
            finally {
                closeCon();
                closeStmt();
                closeResSet();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public ArrayList<Bedroom> getAttachedBedrooms() {
        ArrayList<Bedroom> bedroom = new ArrayList<>();
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                //untested
                openResSet("SELECT " +
                        "bedroomID, " +
                        "sleepingFacilityID_fk, " +
                        "bedOneType, " +
                        "bedTwoType " +
                        "FROM SleepingFacility " +
                        "INNER JOIN Bedroom " +
                        "ON Bedroom.sleepingFacilityID_fk = SleepingFacility.sleepingFacilityID " +
                        "WHERE SleepingFacility.sleepingFacilityID='" + sleepingFacility.getSleepingFacilityID() + "';");

                while (getResSet().next()) {
                    int bedroomID = getResSet().getInt("bedroomID");
                    int sleepingFacilityID_fk = getResSet().getInt("sleepingFacilityID_fk");
                    String bedOneType = getResSet().getString("bedOneType");
                    String bedTwoType = getResSet().getString("bedTwoType");
                    bedroom.add(new Bedroom(bedroomID, sleepingFacilityID_fk,bedOneType,
                            bedTwoType));
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
        return bedroom;
    }

    public SleepingFacility getSleepingFacility() {
        return sleepingFacility;
    }

    public void setSleepingFacility(SleepingFacility sleepingFacility) {
        this.sleepingFacility = sleepingFacility;
    }

    public void insertSleepingFacilityRow() {
        insert("SleepingFacility",
                "(DEFAULT,'"+getSleepingFacility().getHasBedLinen()+"','"
                        +getSleepingFacility().getHasTowels()+"')");
    }
    public void setSleepingFacilityID(){
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                openResSet("SELECT sleepingFacilityID " +
                        "FROM SleepingFacility ORDER BY sleepingFacilityID DESC LIMIT 1;");

                while(getResSet().next()) {
                    int sleepingFacilityID = getResSet().getInt(1);
                    getSleepingFacility().setSleepingFacilityID(sleepingFacilityID);
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
}
