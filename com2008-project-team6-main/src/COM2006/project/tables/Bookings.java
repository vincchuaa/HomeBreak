package COM2006.project.tables;

public class Bookings {
    //Instance variables
    private int bookedID;
    private int bookedPropertyID_fk;
    private int guestID_fk;
    private java.sql.Date startDate;
    private java.sql.Date endDate;
    private int confirmation; // might need to convert type to Bool since DB, the type is binary

    //Constructor
    public Bookings(int bookedID, int bookedPropertyID_fk,int guestID_fk, java.sql.Date startDate, java.sql.Date endDate
            , int confirmation) {
        this.bookedID = bookedID;
        this.bookedPropertyID_fk = bookedPropertyID_fk;
        this.guestID_fk = guestID_fk;
        this.startDate = startDate;
        this.endDate = endDate;
        this.confirmation = confirmation;
    }

    //Getters
    public int getBookedID() {
        return bookedID;
    }

    public int getBookedPropertyID_fk() {
        return bookedPropertyID_fk;
    }

    public int getGuestID_fk() {
        return guestID_fk;
    }

    public java.sql.Date getStartDate() {
        return startDate;
    }

    public java.sql.Date getEndDate() {
        return endDate;
    }

    public int getConfirmation() {
        return confirmation;
    }

    //Setters
    public void setBookedID(int bookedID) {
        this.bookedID = bookedID;
    }

    public void setBookedPropertyID_fk(int bookedPropertyID_fk) {
        this.bookedPropertyID_fk = bookedPropertyID_fk;
    }

    public void setGuestID_fk(int guestID_fk) {
        this.guestID_fk = guestID_fk;
    }

    public void setStartDate(java.sql.Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(java.sql.Date endDate) {
        this.endDate = endDate;
    }

    public void setConfirmation(int confirmation) {
        this.confirmation = confirmation;
    }
}
