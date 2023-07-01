package COM2006.project.tables;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;


public class HostsModel extends Model {
    Hosts host;

    public HostsModel(String email) {
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                //untested
                openResSet("SELECT " +
                        "hostID, " +
                        "isSuperHost, " +
                        "password, " +
                        "salt, " +
                        "username, " +
                        "forename, " +
                        "surname, " +
                        "phoneNumber, " +
                        "addressID_fk, " +
                        "title " +
                        "FROM Account " +
                        "INNER JOIN Hosts " +
                        "ON Account.email = Hosts.host_email_fk " +
                        "WHERE Account.email='" + email + "';");

                while (getResSet().next()) {
                    int hostID = getResSet().getInt("hostID");
                    int isSuperHost = getResSet().getInt("isSuperHost");
                    String password = getResSet().getString("password");
                    String salt = getResSet().getString("salt");
                    String username = getResSet().getString("username");
                    String forename = getResSet().getString("forename");
                    String surname = getResSet().getString("surname");
                    String phoneNumber = getResSet().getString("phoneNumber");
                    int addressID_fk = getResSet().getInt("addressID_fk");
                    String title = getResSet().getString("title");
                    setHost(new Hosts(hostID, isSuperHost, email, password, salt, username,
                            forename, surname, phoneNumber, addressID_fk, title));

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

        public HostsModel(int id) {
            declareCon();
            declareStmt();
            declareResSet();
            try {
                try {
                    openCon();
                    openStmt();
                    //untested
                    openResSet("SELECT " +
                            "email, " +
                            "isSuperHost, " +
                            "password, " +
                            "salt, " +
                            "username, " +
                            "forename, " +
                            "surname, " +
                            "phoneNumber, " +
                            "addressID_fk, " +
                            "title " +
                            "FROM Account " +
                            "LEFT JOIN Hosts " +
                            "ON Account.email = Hosts.host_email_fk " +
                            "WHERE Hosts.hostID='" + id + "';");

                    while (getResSet().next()) {
                        String email = getResSet().getString("email");
                        int isSuperHost = getResSet().getInt("isSuperHost");
                        String password = getResSet().getString("password");
                        String salt = getResSet().getString("salt");
                        String username = getResSet().getString("username");
                        String forename = getResSet().getString("forename");
                        String surname = getResSet().getString("surname");
                        String phoneNumber = getResSet().getString("phoneNumber");
                        int addressID_fk = getResSet().getInt("addressID_fk");
                        String title = getResSet().getString("title");
                        setHost(new Hosts(id, isSuperHost, email, password, salt, username,
                                forename, surname, phoneNumber, addressID_fk, title));

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
    //need to add this int otherwise can't overload
    public HostsModel(Hosts host) {
        setHost(host);
    }

    public Hosts getHost() {
        return host;
    }

    public void setHost(Hosts host) {
        this.host = host;
    }

    public void insertHost(String email) {
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                PreparedStatement prpStmt = getCon().prepareStatement("INSERT INTO Hosts VALUES " +
                        "(DEFAULT, ?, ?)");
                prpStmt.setString(1,email);
                prpStmt.setInt(2,getHost().getIsSuperHost());
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
        //this.insert("Hosts", "DEFAULT, '" + email + "','0'");
    }


    /*
     * @return - A list of provisional bookings from the host's properties.
     */
    public ArrayList<ArrayList<Object>> getProvisionalBookings() {
        ArrayList<ArrayList<Object>> bookings = new ArrayList<>();
        //Current Date
        long millis = System.currentTimeMillis();
        java.sql.Date currentDate = new java.sql.Date(millis);

        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                //untested
                openResSet("SELECT bookedID, propertyName, startDate, endDate FROM Bookings " +
                        "LEFT JOIN Property ON Bookings.bookedPropertyID_fk = Property.propertyID " +
                        "WHERE confirmation = 0 AND hostID_fk ='" + host.getHostID() + "';");

                while (getResSet().next()) {
                    ArrayList<Object> data = new ArrayList<>();
                    int bookedID = getResSet().getInt("bookedID");
                    java.sql.Date endDate = getResSet().getDate("endDate");
                    if (endDate.compareTo(currentDate) > 0) {
                        data.add(bookedID);
                        data.add(getResSet().getString("propertyName"));
                        data.add(getResSet().getDate("startDate"));
                        data.add(endDate);
                        bookings.add(data);
                    }
                    else {
                        BookingsModel booking = new BookingsModel(bookedID);
                    }
                }
            } finally {
                closeCon();
                closeStmt();
                closeResSet();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return bookings;
    }

    /*
     * @return - A list of bookings that from the host's properties that were confirmed.
     */
    public ArrayList<ArrayList<Object>> getConfirmedBookings() {
        ArrayList<ArrayList<Object>> bookings = new ArrayList<>();

        //Current Date
        long millis = System.currentTimeMillis();
        java.sql.Date currentDate = new java.sql.Date(millis);

        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                //untested
                openResSet("SELECT propertyName, startDate, endDate, forename, phoneNumber FROM Property " +
                        "LEFT JOIN Bookings ON Property.propertyID = Bookings.bookedPropertyID_fk " +
                        "LEFT JOIN Guests ON Bookings.guestID_fk = Guests.guestID " +
                        "LEFT JOIN Account ON Guests.guest_email_fk = Account.email " +
                        "WHERE confirmation = 1 AND hostID_fk ='" + host.getHostID() + "';");

                while (getResSet().next()) {
                    ArrayList<Object> data = new ArrayList<>();
                    java.sql.Date endDate = getResSet().getDate("endDate");
                    if (endDate.compareTo(currentDate) > 0) {
                        data.add(getResSet().getString("propertyName"));
                        data.add(getResSet().getDate("startDate"));
                        data.add(endDate);
                        data.add(getResSet().getString("forename"));
                        data.add(getResSet().getString("phoneNumber"));
                        bookings.add(data);
                    }
                }
            } finally {
                closeCon();
                closeStmt();
                closeResSet();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return bookings;
    }

    /*
     * @return - A list of properties owned by the host.
     */
    public ArrayList<Property> getOwnedProperties() {
        ArrayList<Property> properties = new ArrayList<Property>();
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                //untested
                openResSet("SELECT " +
                        "propertyID, " +
                        "facilityID_fk, " +
                        "propertyName, " +
                        "hostID_fk, " +
                        "description, " +
                        "hasBreakfast, " +
                        "addressID_fk " +
                        "FROM Hosts " +
                        "INNER JOIN Property " +
                        "ON Property.hostID_fk = Hosts.hostID " +
                        "WHERE Hosts.hostID='" + getHost().getHostID() + "';");

                while (getResSet().next()) {
                    int propertyID = getResSet().getInt("propertyID");
                    int facilityID_fk = getResSet().getInt("facilityID_fk");
                    String propertyName = getResSet().getString("propertyName");
                    int hostID_fk = getResSet().getInt("hostID_fk");
                    String description = getResSet().getString("description");
                    int hasBreakfast = getResSet().getInt("hasBreakfast");
                    int addressID_fk = getResSet().getInt("addressID_fk");
                    properties.add(new Property(propertyID, facilityID_fk, propertyName, hostID_fk, description,
                            hasBreakfast, addressID_fk));
                }
            } finally {
                closeCon();
                closeStmt();
                closeResSet();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return properties;
    }
    /* Calculates the host's average rating by iterating through their properties' average ratings.
     * @return - The host's average rating.
     */
    public double calculateAvgHostRating() {
        ArrayList<Property> propList = getOwnedProperties();
        double rating = 0.00;
        if(propList.size() == 0) {
            return rating;
        }
        for (int i = 0; i < propList.size(); i++) {
            PropertyModel ptyMdl = new PropertyModel(propList.get(i).getPropertyID());
            rating += ptyMdl.calcAvgRating();
        }
        return (rating/ propList.size());
    }

    public void setSuperHost(int binary) {
        if (binary == 1 && getHost().getIsSuperHost() == 0) {
            update("Hosts", "isSuperHost = 1", "hostID = '" + getHost().getHostID() + "';");
        }

        else if (binary == 0 && getHost().getIsSuperHost() == 1) {
            update("Hosts", "isSuperHost = 0", "hostID = '" + getHost().getHostID() + "';");
        }
    }
}


