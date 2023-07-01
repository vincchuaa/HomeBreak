package COM2006.project.tables;

import COM2006.project.ConvertBoolToInt;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BathingFacilityModel extends Model {
    BathingFacility bathingFacility;

    public BathingFacilityModel(boolean hasHairDryer,boolean hasShampoo,boolean hasToiletPaper){
        ConvertBoolToInt converter = new ConvertBoolToInt();
        int hasHairDryerInt = converter.convertBoolToInt(hasHairDryer);
        int hasShampooInt = converter.convertBoolToInt(hasShampoo);
        int hasToiletPaperInt = converter.convertBoolToInt(hasToiletPaper);
        setBathingFacility(new BathingFacility(0,hasHairDryerInt,hasShampooInt,hasToiletPaperInt));
    }
    public BathingFacilityModel(int bathingFacilityID) {
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                openResSet("SELECT * FROM BathingFacility WHERE bathingFacilityID='" + bathingFacilityID + "';");

                while (getResSet().next()) {
                    int hasHairDryer = getResSet().getInt("hasHairDryer");
                    int hasShampoo = getResSet().getInt("hasShampoo");
                    int hasToiletPaper = getResSet().getInt("hasToiletPaper");
                    setBathingFacility(new BathingFacility(bathingFacilityID, hasHairDryer,
                            hasShampoo,hasToiletPaper));
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



    public BathingFacility getBathingFacility() {
        return bathingFacility;
    }

    public void setBathingFacility(BathingFacility bathingFacility) {
        this.bathingFacility = bathingFacility;
    }

    /*
     * @return - A list of bathrooms with fks of this bathing facility.
     */
    public ArrayList<Bathroom> getAttachedBathrooms() {
        ArrayList<Bathroom> bathroom = new ArrayList<>();
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                //untested
                openResSet("SELECT " +
                        "bathroomID, " +
                        "bathingFacilityID_fk, " +
                        "hasToilet, " +
                        "hasShower, " +
                        "hasBath, " +
                        "sharedWithHost " +
                        "FROM BathingFacility " +
                        "INNER JOIN Bathroom " +
                        "ON Bathroom.bathingFacilityID_fk = BathingFacility.bathingFacilityID " +
                        "WHERE BathingFacility.bathingFacilityID='" + bathingFacility.getBathingFacilityID() + "';");

                while (getResSet().next()) {
                    int bathroomID = getResSet().getInt("bathroomID");
                    int bathingFacilityID_fk = getResSet().getInt("bathingFacilityID_fk");
                    int hasToilet = getResSet().getInt("hasToilet");
                    int hasShower = getResSet().getInt("hasShower");
                    int hasBath = getResSet().getInt("hasBath");
                    int sharedWithHost = getResSet().getInt("sharedWithHost");
                    bathroom.add(new Bathroom(bathroomID, bathingFacilityID_fk, hasToilet, hasShower, hasBath,
                            sharedWithHost));
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
        return bathroom;
    }

    public void insertBathingFacilityRow() {
        insert("BathingFacility",
                "(DEFAULT,'"+getBathingFacility().getHasHairDryer()+"','"
                        +getBathingFacility().getHasShampoo()+"','"+ getBathingFacility().getHasToiletPaper() +"')");
    }
    public void setBathingFacilityID(){
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                openResSet("SELECT bathingFacilityID " +
                        "FROM BathingFacility ORDER BY bathingFacilityID DESC LIMIT 1;");

                while(getResSet().next()) {
                    int bathingFacilityID = getResSet().getInt(1);
                    getBathingFacility().setBathingFacilityID(bathingFacilityID);
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

