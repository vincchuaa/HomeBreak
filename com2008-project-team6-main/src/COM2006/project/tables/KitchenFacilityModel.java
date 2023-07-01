package COM2006.project.tables;

import java.sql.SQLException;

import COM2006.project.ConvertBoolToInt;
import COM2006.project.tables.Model;

public class KitchenFacilityModel extends Model{
    KitchenFacility kitchenFacility;

    public KitchenFacilityModel(boolean hasOven,boolean hasFridge,boolean hasMicrowave,
                                boolean hasStove,boolean hasDishWasher,boolean hasTableWare,
                                boolean hasCookWare,boolean hasBasicProvisions){
        ConvertBoolToInt converter = new ConvertBoolToInt();
        int hasOvenInt = converter.convertBoolToInt(hasOven);
        int hasFridgeInt = converter.convertBoolToInt(hasFridge);
        int hasMicrowaveInt = converter.convertBoolToInt(hasMicrowave);
        int hasStoveInt = converter.convertBoolToInt(hasStove);
        int hasDishWasherInt = converter.convertBoolToInt(hasDishWasher);
        int hasTablewareInt = converter.convertBoolToInt(hasTableWare);
        int hasCookwareInt = converter.convertBoolToInt(hasCookWare);
        int hasBasicProvisionsInt = converter.convertBoolToInt(hasBasicProvisions);

        setKitchenFacility(new KitchenFacility(0,hasOvenInt,hasFridgeInt,hasMicrowaveInt,hasStoveInt,
                hasDishWasherInt,hasTablewareInt,hasCookwareInt,hasBasicProvisionsInt));

    }

    public KitchenFacilityModel(int kitchenFacilityID) {
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                openResSet("SELECT hasOven, hasFridge, hasMicrowave, hasStove, hasDishWasher, " +
                        "hasTableware,hasCookware,hasBasicProvisions "+
                        "FROM KitchenFacility WHERE kitchenFacilityID='" + kitchenFacilityID + "';");

                while(getResSet().next()) {
                    int hasOven = getResSet().getInt(1);
                    int hasFridge = getResSet().getInt(2);
                    int hasMicrowave = getResSet().getInt(3);
                    int hasStove = getResSet().getInt(4);
                    int hasDishWasher = getResSet().getInt(5);
                    int hasTableware = getResSet().getInt(6);
                    int hasCookware = getResSet().getInt(7);
                    int hasBasicProvisions = getResSet().getInt(8);
                    setKitchenFacility(new KitchenFacility (kitchenFacilityID, hasOven, hasFridge, hasMicrowave,
                            hasStove, hasDishWasher, hasTableware,hasCookware,hasBasicProvisions));
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

    public KitchenFacility getKitchenFacility() {
        return kitchenFacility;
    }

    public void setKitchenFacility(KitchenFacility kitchenFacility) {
        this.kitchenFacility = kitchenFacility;
    }

    public void insertKitchenFacilityRow() {
        insert("KitchenFacility",
                "(DEFAULT,'"+getKitchenFacility().getHasOven()+"','"
                        +getKitchenFacility().getHasFridge()+"','"
                        +getKitchenFacility().getHasMicrowave()+"','"
                        +getKitchenFacility().getHasStove()+"','"
                        +getKitchenFacility().getHasDishWasher()+"','"
                        +getKitchenFacility().getHasTableware()+"','"
                        +getKitchenFacility().getHasCookware()+"','"
                        +getKitchenFacility().getHasBasicProvisions()+"')");
    }

    public void setKitchenFacilityID() {
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                openResSet("SELECT kitchenFacilityID " +
                        "FROM KitchenFacility ORDER BY kitchenFacilityID DESC LIMIT 1;");

                while (getResSet().next()) {
                    int kitchenFacilityID = getResSet().getInt(1);
                    getKitchenFacility().setKitchenFacilityID(kitchenFacilityID);
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

