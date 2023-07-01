package COM2006.project.tables;

import COM2006.project.ConvertBoolToInt;

import java.sql.SQLException;

public class BathroomModel extends Model{
    Bathroom bathroom;

    public BathroomModel(int bathroomFacilityID, boolean hasToilet,boolean hasShower,boolean hasBath,boolean sharedWithHost){
        ConvertBoolToInt converter = new ConvertBoolToInt();
        int hasToiletInt = converter.convertBoolToInt(hasToilet);
        int hasShowerInt = converter.convertBoolToInt(hasShower);
        int hasBathInt = converter.convertBoolToInt(hasBath);
        int sharedWithHostInt = converter.convertBoolToInt(sharedWithHost);
        setBathroom(new Bathroom(0,bathroomFacilityID,hasToiletInt,hasShowerInt,hasBathInt,sharedWithHostInt));
    }
    public BathroomModel(int bathroomID) {
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                openResSet("SELECT * FROM Bathroom WHERE bathroomID='" + bathroomID + "';");

                while (getResSet().next()) {
                    int hasToilet = getResSet().getInt("hasToilet");
                    int bathingFacilityID_fk = getResSet().getInt("bathingFacilityID_fk");
                    int hasShower = getResSet().getInt("hasShower");
                    int hasBath = getResSet().getInt("hasBath");
                    int sharedWithHost = getResSet().getInt("sharedWithHost");
                    setBathroom(new Bathroom(bathroomID, bathingFacilityID_fk,
                            hasToilet, hasShower, hasBath, sharedWithHost));
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

    public Bathroom getBathroom() {
        return bathroom;
    }

    public void setBathroom(Bathroom bathroom) {
        this.bathroom = bathroom;
    }

    public void insertPropertyRow() {
        insert("Bathroom",
                "(DEFAULT,'"+getBathroom().getHasToilet()+ "','"+getBathroom().getHasShower()+
                        "','"+getBathroom().getHasBath()+"','"+getBathroom().getSharedWithHost()+"','"
                        +getBathroom().getBathingFacilityID_fk()+"')");
    }
}
