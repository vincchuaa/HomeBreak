package COM2006.project.tables;



import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountModel extends Model{
    Account account;

    //used in register when making a new account
    public AccountModel(String email,String password, String salt, String username, String forename,
                        String surname,String phoneNumber, int addressID_fk, String title){
        setAccount(new Account (email, password, salt, username, forename, surname, phoneNumber, addressID_fk,
                title));

    }

    public AccountModel(String email) {
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                openResSet("SELECT password, salt, username, forename, surname, " +
                            "phoneNumber, addressID_fk, title FROM Account WHERE email='" + email + "';");

                while(getResSet().next()) {
                    String password = getResSet().getString("password");
                    String salt = getResSet().getString("salt");
                    String username = getResSet().getString("username");
                    String forename = getResSet().getString("forename");
                    String surname = getResSet().getString("surname");
                    String phoneNumber = getResSet().getString("phoneNumber");
                    int addressID_fk = getResSet().getInt("addressID_fk");
                    String title = getResSet().getString("title");
                    this.account = new Account (email, password, salt, username, forename, surname, phoneNumber,
                            addressID_fk, title);

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

    public AccountModel(Account account){
        setAccount(account);
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void insertAccountRow() {
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                PreparedStatement prpStmt = getCon().prepareStatement("INSERT INTO Account VALUES " +
                        "(?, ?, ?, ?, ?, ?, ?, ?, ?)");
                prpStmt.setString(1,account.getEmail());
                prpStmt.setString(2,account.getPassword());
                prpStmt.setString(3,account.getSalt());
                prpStmt.setString(4,account.getUsername());
                prpStmt.setString(5,account.getForename());
                prpStmt.setString(6,account.getSurname());
                prpStmt.setString(7,account.getPhoneNumber());
                prpStmt.setInt(8,account.getAddressID_fk());
                prpStmt.setString(9,account.getTitle());
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
    /* Checks if the account associated with this model is registered as both host and guest
     */
    public Boolean hasSecondRole() {
        Guest guestAcc = new GuestsModel(getAccount().getEmail()).getGuest();
        Hosts hostsAcc = new HostsModel(getAccount().getEmail()).getHost();
        //an account always has a role, so if either == null then the acc doesn't have both roles
        if (guestAcc == null || hostsAcc == null) {
            return false;
        }
        else {
            return true;
        }
    }
}
