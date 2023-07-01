package COM2006.project.tables;

import java.sql.SQLException;

public class FacilitiesModel extends Model {
    Facilities facility;

    public FacilitiesModel(int sleepingFacilityID,int bathingFacilityID,int kitchenFacilityID,
                           int utilityFacilityID,int outdoorsFacilityID,int livingFacilityID ){
        setFacilities(new Facilities(0, sleepingFacilityID, bathingFacilityID, kitchenFacilityID,
                utilityFacilityID,outdoorsFacilityID,livingFacilityID));
    }


    public FacilitiesModel(int facilityID) {
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                openResSet("SELECT * FROM Facilities WHERE facilityID='" + facilityID + "';");

                while (getResSet().next()) {
                    int sleepingFacilityID = getResSet().getInt("sleepingFacilityID_fk");
                    int bathingFacilityID = getResSet().getInt("bathingFacilityID_fk");
                    int kitchenFacilityID = getResSet().getInt("kitchenFacilityID_fk");
                    int utilityFacilityID = getResSet().getInt("utilityFacilityID_fk");
                    int outdoorsFacilityID = getResSet().getInt("outdoorsFacilityID_fk");
                    int livingFacilityID = getResSet().getInt("livingFacilityID_fk");
                    setFacilities(new Facilities(facilityID, sleepingFacilityID, bathingFacilityID, kitchenFacilityID,
                            utilityFacilityID,outdoorsFacilityID,livingFacilityID));
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

    public Facilities getFacilities() {
        return facility;
    }

    public void setFacilities(Facilities facility) {
        this.facility = facility;
    }

    public void insertFacilitiesRow() {
        insert("Facilities",
                "(DEFAULT,'"+getFacilities().getSleepingFacilityID()+"','"
                        +getFacilities().getBathingFacilityID()+"','"+ getFacilities().getKitchenFacilityID()+
                        "','"+getFacilities().getUtilityFacilityID()+"','"+ getFacilities().getOutdoorsFacilityID()
                        +"','" +getFacilities().getLivingFacilityID()+"')");
    }

    public void setFacilityID(){
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                openResSet("SELECT facilityID " +
                        "FROM Facilities ORDER BY facilityID DESC LIMIT 1;");

                while(getResSet().next()) {
                    int facilityID = getResSet().getInt(1);
                    getFacilities().setFacilityID(facilityID);
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
