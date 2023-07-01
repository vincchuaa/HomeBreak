package COM2006.project.tables;

import COM2006.project.ConvertBoolToInt;

import java.sql.SQLException;

public class LivingFacilityModel extends Model{
    LivingFacility livingFacility;

    public LivingFacilityModel(boolean hasWifi,boolean hasTelevision,boolean hasSatellite,boolean hasStreamingService,boolean hasDvdPlayer,
                               boolean hasBoardGames) {
        ConvertBoolToInt converter = new ConvertBoolToInt();
        int hasWifiInt = converter.convertBoolToInt(hasWifi);
        int hasTelevisionInt = converter.convertBoolToInt(hasTelevision);
        int hasSatelliteInt = converter.convertBoolToInt(hasSatellite);
        int hasStreamingServiceInt = converter.convertBoolToInt(hasStreamingService);
        int hasDvdPlayerInt = converter.convertBoolToInt(hasDvdPlayer);
        int hasBoardGamesInt = converter.convertBoolToInt(hasBoardGames);
        setLivingFacility(new LivingFacility (0, hasWifiInt, hasTelevisionInt, hasSatelliteInt,
                hasStreamingServiceInt, hasDvdPlayerInt, hasBoardGamesInt));
    }

    public LivingFacilityModel(int livingFacilityID) {
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                openResSet("SELECT hasWifi, hasTelevision, hasSatellite, hasStreamingService, hasDvdPlayer, " +
                        "hasBoardGames "+
                        "FROM LivingFacility WHERE LivingFacilityID='" + livingFacilityID + "';");

                while(getResSet().next()) {
                    int hasWiFi = getResSet().getInt(1);
                    int hasTelevision = getResSet().getInt(2);
                    int hasSatellite = getResSet().getInt(3);
                    int hasStreamingService = getResSet().getInt(4);
                    int hasDvdPlayer = getResSet().getInt(5);
                    int hasBoardGames = getResSet().getInt(6);
                    setLivingFacility(new LivingFacility (livingFacilityID, hasWiFi, hasTelevision, hasSatellite,
                            hasStreamingService, hasDvdPlayer, hasBoardGames));
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

    public LivingFacility getLivingFacility() {
        return this.livingFacility;
    }

    public void setLivingFacility(LivingFacility livingFacility) {
        this.livingFacility = livingFacility;
    }

    public void insertLivingFacilityRow() {
        insert("LivingFacility",
                "(DEFAULT,'"+getLivingFacility().getHasWiFi()+"','"
                        +getLivingFacility().getHasTelevision()+"','"
                        +getLivingFacility().getHasSatellite()+"','"
                        +getLivingFacility().getHasStreamingService()+"','"
                        +getLivingFacility().getHasDvdPlayer()+"','"
                        +getLivingFacility().getHasBoardGames()+"')");
    }

    public void setLivingFacilityID() {
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                openResSet("SELECT livingFacilityID " +
                        "FROM LivingFacility ORDER BY livingFacilityID DESC LIMIT 1;");

                while (getResSet().next()) {
                    int livingFacilityID = getResSet().getInt(1);
                    getLivingFacility().setLivingFacilityID(livingFacilityID);
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


}
