package COM2006.project.tables;
import java.sql.*;

/**
 * Abstract class used to streamline creating models by providing methods to execute queries etc.
 */
public abstract class Model {
    private Connection con;
    private Statement stmt;
    private ResultSet resultSet;

    //Connection
    public void declareCon() {
        con = null;
    }

    public void openCon() throws SQLException {
        String DB = "jdbc:mysql://stusql.dcs.shef.ac.uk/team006?user=team006&password=1904d842";
        con = DriverManager.getConnection(DB);
    }

    public void closeCon() throws SQLException {
        if (con != null) {
            con.close();
        }
    }

    public Connection getCon() {
        return con;
    }

    //Statement
    public void declareStmt() {
        stmt = null;
    }

    public void openStmt() throws SQLException {
        stmt = con.createStatement();
    }

    public void closeStmt() throws SQLException {
        if (stmt != null) {
            stmt.close();
        }
    }

    public Statement getStmt() {
        return stmt;
    }


    //ResultSet
    public void declareResSet() {
        resultSet = null;
    }

    public void openResSet(String query) throws SQLException {
        resultSet = getStmt().executeQuery(query);
    }

    public void closeResSet() throws SQLException {
        if (stmt != null) {
            stmt.close();
        }
    }

    public ResultSet getResSet() {
        return resultSet;
    }

    public void insert(String table, String values) {

        try {
            declareCon();
            declareStmt();
            try {
                openCon();
                openStmt();
                int count = getStmt().executeUpdate("INSERT INTO " + table + " VALUES " + values + ";");
            } finally {
                closeCon();
                closeStmt();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void remove(String table, String conditional) {

        try {
            declareCon();
            declareStmt();
            try {
                openCon();
                openStmt();
                int count = getStmt().executeUpdate("DELETE FROM " + table + " WHERE " + conditional + ";");
            } finally {
                closeCon();
                closeStmt();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void update(String table, String updateColumns, String conditional) {

        try {
            declareCon();
            declareStmt();
            try {
                openCon();
                openStmt();
                int count = getStmt().executeUpdate("UPDATE " + table +
                        " SET " + updateColumns + " WHERE " + conditional + ";");
            } finally {
                closeCon();
                closeStmt();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }
}
