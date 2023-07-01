package COM2006.project.tables;

import java.sql.SQLException;

public class BedroomModel extends Model {
    Bedroom bedroom;

    public BedroomModel (int sleepingFacilityID_fk,String bedOneType,String bedTwoType){
        setBedroom(new Bedroom(0,sleepingFacilityID_fk,bedOneType,bedTwoType));
    }
    public BedroomModel(int bedroomID) {
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                openResSet("SELECT * FROM Bedroom WHERE bedroomID='" + bedroomID + "';");

                while (getResSet().next()) {
                    int sleepingFacilityID_fk = getResSet().getInt("sleepingFacilityID_fk");
                    String bedOneType = getResSet().getString("bedOneType");
                    String bedTwoType = getResSet().getString("bedOneType");
                    setBedroom(new Bedroom(bedroomID, sleepingFacilityID_fk,
                            bedOneType, bedTwoType));
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

    public Bedroom getBedroom() {
        return bedroom;
    }

    public void setBedroom(Bedroom bedroom) {
        this.bedroom = bedroom;
    }

    public void insertBedroomRow() {
        insert("Bedroom",
                "(DEFAULT,'"+ getBedroom().getBedOneType()+"','"+getBedroom().getBedTwoType()+"','"
                        +getBedroom().getSleepingFacilityID_fk()+"')");
    }
}
