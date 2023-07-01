package COM2006.project.tables;
import java.util.Date;
public class ChargeBand {
    private int chargeBandsID;
    private int charge_propertyID_fk;
    private java.sql.Date startDate;
    private java.sql.Date endDate;
    private double pricePerNight;
    private double serviceCharge;
    private double cleaningCharge;

    public ChargeBand (int chargeBandsID, int charge_propertyID_fk, java.sql.Date startDate, java.sql.Date endDate,
                       double pricePerNight, double serviceCharge, double cleaningCharge) {
        this.chargeBandsID = chargeBandsID;
        this.charge_propertyID_fk = charge_propertyID_fk;
        this.startDate = startDate;
        this.endDate = endDate;
        this.pricePerNight = pricePerNight;
        this.serviceCharge = serviceCharge;
        this.cleaningCharge = cleaningCharge;
    }

    //Getter
    public int getChargeBandsID() {
        return chargeBandsID;
    }

    public int getCharge_PropertyID_fk() {
        return charge_propertyID_fk;
    }

    public java.sql.Date getStartDate() {
        return startDate;
    }

    public java.sql.Date getEndDate() {
        return endDate;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public double getServiceCharge() {
        return serviceCharge;
    }

    public double getCleaningCharge() {
        return cleaningCharge;
    }

    //Setter

        public void setChargeBandsID(int chargeBandsID) {
        this.chargeBandsID = chargeBandsID;
    }

    public void setCharge_Property_ID_fk(int charge_propertyID_fk) {
        this.charge_propertyID_fk = charge_propertyID_fk;
    }

    public void setStartDate(java.sql.Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(java.sql.Date endDate) {
        this.endDate = endDate;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public void setServiceCharge(double serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public void setCleaningCharge(double cleaningCharge) {
        this.cleaningCharge = cleaningCharge;
    }

}
