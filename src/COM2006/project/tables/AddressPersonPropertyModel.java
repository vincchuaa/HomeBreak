package COM2006.project.tables;

import java.sql.SQLException;

public class AddressPersonPropertyModel extends Model {
    AddressPersonProperty addressPersonProperty;
    public AddressPersonPropertyModel(int addressID) {
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                openResSet("SELECT * FROM AddressPersonProperty WHERE addressID='" + addressID + "';");

                while (getResSet().next()) {
                    String email = getResSet().getString(1);
                    int propertyID = getResSet().getInt(2);
                    int host = getResSet().getInt(3);
                    setAddressPersonProperty(new AddressPersonProperty(addressID, email, propertyID, host));
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

    public AddressPersonProperty getAddressPersonProperty() {
        return addressPersonProperty;
    }

    public void setAddressPersonProperty(AddressPersonProperty addressPersonProperty) {
        this.addressPersonProperty = addressPersonProperty;
    }
}

