package COM2006.project.tables;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class ChargeBandModel extends Model{
    ChargeBand chargeBand;

    public ChargeBandModel(int propertyID, String startDate,String endDate,String ppn, String sc,
                           String cc){
        Date sDate = Date.valueOf(startDate);
        Date eDate = Date.valueOf(endDate);
        double ppnDouble = Double.parseDouble(ppn);
        double scDouble = Double.parseDouble(sc);
        double ccDouble = Double.parseDouble(cc);

        setChargeBand(new ChargeBand (0, propertyID, sDate, eDate, ppnDouble,
                scDouble, ccDouble));
    }

    public ChargeBandModel(int chargeBandsID) {
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                openResSet("SELECT charge_propertyID_fk, startDate, endDate, pricePerNight," +
                        "serviceCharge, cleaningCharge WHERE '" + chargeBandsID + "';");

                while(getResSet().next()) {
                    int charge_propertyID_fk = getResSet().getInt("charge_propertyID_fk");
                    java.sql.Date startDate = getResSet().getDate("startDate");
                    java.sql.Date endDate = getResSet().getDate("endDate");
                    double pricePerNight = getResSet().getInt("pricePerNight");
                    double serviceCharge = getResSet().getInt("serviceCharge");
                    double cleaningCharge = getResSet().getInt("cleaningCharge");
                    setChargeBand(new ChargeBand (chargeBandsID, charge_propertyID_fk, startDate, endDate, pricePerNight,
                            serviceCharge, cleaningCharge));

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

    public ChargeBand getChargeBand() {
        return chargeBand;
    }

    public void setChargeBand(ChargeBand chargeBand) {
        this.chargeBand = chargeBand;
    }

    public void insertChargeBandRow() {
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                PreparedStatement prpStmt = getCon().prepareStatement("INSERT INTO ChargeBands VALUES " +
                        "(DEFAULT, ?, ?, ?, ?, ?, ?)");
                prpStmt.setInt(1,getChargeBand().getCharge_PropertyID_fk());
                prpStmt.setDate(2,getChargeBand().getStartDate());
                prpStmt.setDate(3,getChargeBand().getEndDate());
                prpStmt.setDouble(4,getChargeBand().getPricePerNight());
                prpStmt.setDouble(5,getChargeBand().getServiceCharge());
                prpStmt.setDouble(6,getChargeBand().getCleaningCharge());
                prpStmt.executeUpdate();
                prpStmt.close();
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
        /*
        insert("ChargeBands",
                "(DEFAULT,'"+getChargeBand().getCharge_PropertyID_fk()+
                        "','"+getChargeBand().getStartDate()+"','"+getChargeBand().getEndDate()+"','"+
                        getChargeBand().getPricePerNight()+"','"+getChargeBand().getServiceCharge()+"','"+
                        getChargeBand().getCleaningCharge()+"')");

         */
    }

}
