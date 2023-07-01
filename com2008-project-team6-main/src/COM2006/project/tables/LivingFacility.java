package COM2006.project.tables;

public class LivingFacility {
    //Instance Variables
    private int livingFacilityID;
    private int hasWiFi;
    private int hasTelevision;
    private int hasSatellite;
    private int hasStreamingService;
    private int hasDvdPlayer;
    private int hasBoardGames;


    //Constructor
    public LivingFacility(int livingFacilityID, int hasWiFi,int hasTelevision, int hasSatellite, int hasStreamingService
            , int hasDvdPlayer, int hasBoardGames) {
        this.livingFacilityID = livingFacilityID;
        this.hasWiFi = hasWiFi;
        this.hasTelevision = hasTelevision;
        this.hasSatellite = hasSatellite;
        this.hasStreamingService = hasStreamingService;
        this.hasDvdPlayer = hasDvdPlayer;
        this.hasBoardGames = hasBoardGames;

    }

    //Getters
    public int getLivingFacilityID() {return livingFacilityID;}

    public int getHasWiFi() {return hasWiFi;}

    public int getHasTelevision() {return hasTelevision;}

    public int getHasSatellite() {return hasSatellite;}

    public int getHasStreamingService() {return hasStreamingService;}

    public int getHasDvdPlayer() {return hasDvdPlayer;}

    public int getHasBoardGames() {return hasBoardGames;}


    //Setters

    public void setLivingFacilityID(int livingFacilityID) {this.livingFacilityID = livingFacilityID;}

    public void setHasWiFi(int hasWiFi) {this.hasWiFi = hasWiFi;}

    public void setHasTelevision(int hasTelevision) {this.hasTelevision = hasTelevision;}

    public void setHasSatellite(int hasSatellite) {this.hasSatellite = hasSatellite;}

    public void setHasStreamingService(int hasStreamingService) {this.hasStreamingService = hasStreamingService;}

    public void setHasDvdPlayer(int hasDvdPlayer) {this.hasDvdPlayer = hasDvdPlayer;}

    public void setHasBoardGames(int hasBoardGames) {this.hasBoardGames = hasBoardGames;}

}
