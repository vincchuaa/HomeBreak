package COM2006.project.tables;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;
import java.util.Date;

public class GuestsModel extends Model{
    Guest guest;

    public GuestsModel(){}

    public GuestsModel(String email) {
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                //untested
                openResSet("SELECT " +
                        "guestID, " +
                        "email, " +
                        "password, " +
                        "salt, " +
                        "username, " +
                        "forename, " +
                        "surname, " +
                        "phoneNumber, " +
                        "addressID_fk, " +
                        "title " +
                        "FROM Account " +
                        "INNER JOIN Guests " +
                        "ON Account.email = Guests.guest_email_fk " +
                        "WHERE Account.email='" + email + "';");

                while(getResSet().next()) {
                    int guestID = getResSet().getInt("guestID");
                    String password = getResSet().getString("password");
                    String salt = getResSet().getString("salt");
                    String username = getResSet().getString("username");
                    String forename = getResSet().getString("forename");
                    String surname = getResSet().getString("surname");
                    String phoneNumber = getResSet().getString("phoneNumber");
                    int addressID_fk = getResSet().getInt("addressID_fk");
                    String title = getResSet().getString("title");
                    setGuest(new Guest (guestID, email, password, salt, username,
                            forename, surname, phoneNumber, addressID_fk, title));
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
    /*
     * @return - A list of bookings made by the model's guest, and is confirmed by the host.
     */
    public ArrayList<ArrayList<Object>> getConfirmedBookings(){
        ArrayList<ArrayList<Object>> bookings = new ArrayList<>();

        //Current Date
        long millis=System.currentTimeMillis();
        java.sql.Date currentDate=new java.sql.Date(millis);

        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                //untested
                openResSet("SELECT propertyName, startDate, endDate, forename, "
                        +"phoneNumber, houseNumber, streetName, postCode, city, country FROM Guests "
                        +"LEFT JOIN Bookings ON Guests.guestID = Bookings.guestID_fk "
                        +"LEFT JOIN Property ON Bookings.bookedPropertyID_fk = Property.propertyID "
                        +"LEFT JOIN Hosts ON Property.hostID_fk = Hosts.hostID "
                        +"LEFT JOIN Account ON Hosts.host_email_fk = Account.email "
                        +"LEFT JOIN Address ON Account.addressID_fk = Address.addressID "
                        +"WHERE confirmation = 1 AND "
                        +"Guests.guestID='" + guest.getGuestID() + "';");

                while (getResSet().next()) {
                    ArrayList<Object> data = new ArrayList<>();
                    java.sql.Date endDate = getResSet().getDate("endDate");
                    if(endDate.compareTo(currentDate) > 0) {
                        data.add(getResSet().getString("propertyName"));
                        data.add(getResSet().getDate("startDate"));
                        data.add(endDate);
                        data.add(getResSet().getString("forename"));
                        data.add(getResSet().getString("phoneNumber"));
                        int houseNumber = getResSet().getInt("houseNumber");
                        String streetName = getResSet().getString("streetName");
                        String postCode = getResSet().getString("postCode");
                        String city = getResSet().getString("city");
                        String country = getResSet().getString("country");
                        String address = houseNumber + " " + streetName + ", " + postCode + " " + city +
                                " " + country;
                        data.add(address);
                        bookings.add(data);
                    }
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
        return bookings;
    }
    /*
     * @return - A list of bookings that were made by the model's guest.
     */
    public ArrayList<ArrayList<Object>> getAllBookings(){
        ArrayList<ArrayList<Object>> bookings = new ArrayList<>();
        //Current Date
        long millis=System.currentTimeMillis();
        java.sql.Date currentDate=new java.sql.Date(millis);

        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                //untested
                openResSet("SELECT propertyName, startDate, endDate FROM Guests " +
                        "LEFT JOIN Bookings ON Guests.guestID = Bookings.guestID_fk " +
                        "LEFT JOIN Property ON Bookings.bookedPropertyID_fk = Property.propertyID " +
                        "WHERE guestID ='" + guest.getGuestID() + "';");

                while (getResSet().next()) {
                    ArrayList<Object> data = new ArrayList<>();
                    java.sql.Date endDate = getResSet().getDate("endDate");
                    if(endDate != null && endDate.compareTo(currentDate) > 0) {
                        data.add(getResSet().getString("propertyName"));
                        data.add(getResSet().getDate("startDate"));
                        data.add(endDate);
                        bookings.add(data);
                    }
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
        return bookings;
    }
    /*
     * @return - A list of provisional bookings that were made by the model's guest.
     */
    public ArrayList<ArrayList<Object>> getProvisionalBookings(){
        ArrayList<ArrayList<Object>> bookings = new ArrayList<>();
        //Current Date
        long millis=System.currentTimeMillis();
        java.sql.Date currentDate=new java.sql.Date(millis);

        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                //untested
                openResSet("SELECT propertyName, startDate, endDate FROM Guests " +
                        "LEFT JOIN Bookings ON Guests.guestID = Bookings.guestID_fk " +
                        "LEFT JOIN Property ON Bookings.bookedPropertyID_fk = Property.propertyID " +
                        "WHERE confirmation = 0 AND "+
                        "guestID ='" + guest.getGuestID() + "';");

                while (getResSet().next()) {
                    ArrayList<Object> data = new ArrayList<>();
                    java.sql.Date endDate = getResSet().getDate("endDate");
                    if(endDate != null && endDate.compareTo(currentDate) > 0) {
                        data.add(getResSet().getString("propertyName"));
                        data.add(getResSet().getDate("startDate"));
                        data.add(endDate);
                        bookings.add(data);
                    }
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
        return bookings;
    }
    /*
     * @return - A list of bookings that were made by the model's guest that ended before the current date.
     */
    public ArrayList<ArrayList<Object>> getPastBookings(){
        ArrayList<ArrayList<Object>> data = new ArrayList<>();
        long millis = System.currentTimeMillis();
        java.sql.Date currentDate = new java.sql.Date(millis);
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                openResSet("SELECT propertyID, propertyName, startDate, endDate FROM Property "+
                        "INNER JOIN Bookings ON Property.propertyID = Bookings.bookedPropertyID_fk "+
                        "WHERE endDate < '" + currentDate + "' AND guestID_fk='" + getGuest().getGuestID() + "';");


                while(getResSet().next()) {
                    ArrayList<Object> booking = new ArrayList<>();
                    booking.add(getResSet().getInt("propertyID"));
                    booking.add(getResSet().getString("propertyName"));
                    booking.add(getResSet().getDate("startDate"));
                    booking.add(getResSet().getDate("endDate"));
                    data.add(booking);
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
        return data;
    }
    public void insertGuest(String email){
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                PreparedStatement prpStmt = getCon().prepareStatement("INSERT INTO Guests VALUES " +
                        "(DEFAULT, ?)");
                prpStmt.setString(1,email);
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
        //this.insert("Guests","(DEFAULT, '"+email+"')");
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }
}
