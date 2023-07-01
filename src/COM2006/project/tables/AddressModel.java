package COM2006.project.tables;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddressModel extends Model{

    Address address;

    public AddressModel(int houseNumber, String streetName, String postCode,String city, String country){
        //int houseNumber, String streetName, String postCode,String city, String country
        setAddress(new Address (1, houseNumber, streetName, postCode,
                city, country));
        insertAddressRow();
    }

    public AddressModel(int addressID) {
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                openResSet("SELECT houseNumber, streetName, postCode, city, country " +
                        "FROM Address WHERE addressID='" + addressID + "';");

                while(getResSet().next()) {
                    int houseNumber = getResSet().getInt(1);
                    String streetName = getResSet().getString(2);
                    String postCode = getResSet().getString(3);
                    String city = getResSet().getString(4);
                    String country = getResSet().getString(5);
                    setAddress(new Address (addressID, houseNumber, streetName, postCode,
                            city, country));
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
    //will get the addressID of the last record created
    //then set it to the address obj
    public void setAddressID(){
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                openResSet("SELECT addressID " +
                        "FROM Address ORDER BY AddressID DESC LIMIT 1;");

                while(getResSet().next()) {
                    int addressID = getResSet().getInt(1);
                    getAddress().setAddressID(addressID);
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void insertAddressRow() {
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                PreparedStatement prpStmt = getCon().prepareStatement("INSERT INTO Address VALUES " +
                        "(DEFAULT, ?, ?, ?, ?, ?)");
                prpStmt.setInt(1,address.getHouseNumber());
                prpStmt.setString(2,address.getStreetName());
                prpStmt.setString(3,address.getPostCode());
                prpStmt.setString(4,address.getCity());
                prpStmt.setString(5,address.getCountry());
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

    }
}
