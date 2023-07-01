package COM2006.project.tables;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class BookingsModel extends Model{

    Bookings booking;

    public BookingsModel() {

    }

    public BookingsModel(int bookedID) {
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                openResSet("SELECT bookedPropertyID_fk, guestID_fk, startDate, endDate, confirmation " +
                        "FROM Bookings WHERE bookedID='" + bookedID + "';");

                while(getResSet().next()) {
                    int bookedPropertyID_fk = getResSet().getInt(1);
                    int guestID_fk = getResSet().getInt(2);
                    java.sql.Date startDate = getResSet().getDate(3); //might need to change cal type
                    java.sql.Date endDate = getResSet().getDate(4);
                    int confirmation = getResSet().getInt(5);
                    setBookings(new Bookings (bookedID, bookedPropertyID_fk, guestID_fk, startDate,
                            endDate, confirmation));
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

    public Bookings getBookings() {
        return booking;
    }

    public void setBookings(Bookings booking) {
        this.booking = booking;
    }

    public void insertBookingsRow() {
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                PreparedStatement prpStmt = getCon().prepareStatement("INSERT INTO Bookings VALUES " +
                        "(DEFAULT, ?, ?, ?, ?, ?)");
                prpStmt.setInt(1,getBookings().getBookedPropertyID_fk());
                prpStmt.setInt(2,getBookings().getGuestID_fk());
                prpStmt.setDate(3,getBookings().getStartDate());
                prpStmt.setDate(4,getBookings().getEndDate());
                prpStmt.setInt(5,getBookings().getConfirmation());
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
        insert("Bookings",
                "(DEFAULT,'"+getBookings().getBookedPropertyID_fk()+ "','"+getBookings().getGuestID_fk()+
                        "','"+getBookings().getStartDate()+"','"+getBookings().getEndDate()+"','"
                        +getBookings().getConfirmation()+"')");

         */
    }

    public void removeBookings(){
        this.remove("Bookings","bookedID ='"
                + getBookings().getBookedID() + "';");
    }

    /*
     * Remove a booking from a property in a specific time range.
     */
    public void removePastBooking(int bookedPropertyID, java.sql.Date sDate, java.sql.Date eDate) {
        this.remove("Bookings","bookedPropertyID_fk ='" + bookedPropertyID + "' AND startDate = '"
                + sDate + "' AND endDate= '" + eDate + "';");
    }

    /*
     * Confirm the booking in the model.
     */

    public void confirmBookings(){
        update("Bookings","confirmation = 1", "bookedID = '" + getBookings().getBookedID()
        + "';");
    }

    /*
     * Finds a list of bookings that overlap with the model's booking.
     * @return - A list of the IDs of the bookings that overlap.
     */
    public ArrayList<Integer> getOverlappedBookings(){
        ArrayList<Integer> overlappedBookings = new ArrayList<>();
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                openResSet("SELECT bookedID FROM Bookings " +
                        "WHERE NOT (startDate > '" + getBookings().getEndDate() +
                        "' OR endDate < '" + getBookings().getStartDate() + "') " +
                        "AND NOT (bookedID = '" + getBookings().getBookedID() + "') " +
                        "AND bookedPropertyID_fk = '" + getBookings().getBookedPropertyID_fk() + "';");

                while(getResSet().next()) {
                    overlappedBookings.add(getResSet().getInt("bookedID"));
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
        return overlappedBookings;
    }

}

