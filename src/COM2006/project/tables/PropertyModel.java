package COM2006.project.tables;

import COM2006.project.ConvertBoolToInt;

import javax.lang.model.type.ArrayType;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class PropertyModel extends Model{
    Property property;

    public PropertyModel(){}

    public  PropertyModel(int facilityID, String propertyName,int hostID, String description,boolean hasBreakfast,
                          int addressID){
        ConvertBoolToInt converter = new ConvertBoolToInt();
        int hasBreakfastInt = converter.convertBoolToInt(hasBreakfast);
        setProperty(new Property (0, facilityID, propertyName, hostID, description,
                hasBreakfastInt, addressID));

    }
    public PropertyModel(int propertyID) {
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                openResSet("SELECT facilityID_fk, propertyName, hostID_fk, description, " +
                        "hasBreakfast, addressID_fk FROM Property WHERE propertyID='" + propertyID + "';");

                while(getResSet().next()) {
                    int facilityID_fk = getResSet().getInt("facilityID_fk");
                    String propertyName = getResSet().getString("propertyName");
                    int hostID_fk = getResSet().getInt("hostID_fk");
                    String description = getResSet().getString("description");
                    int hasBreakfast = getResSet().getInt("hasBreakfast");
                    int addressID_fk = getResSet().getInt("addressID_fk");
                    setProperty(new Property (propertyID, facilityID_fk, propertyName, hostID_fk, description,
                            hasBreakfast, addressID_fk));

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
     * @return - A list of bookings made to the property.
     */
    public List<Bookings> getPropertyBookings(){
        ArrayList<Bookings> bookings = new ArrayList<>();
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                //untested
                openResSet("SELECT " +
                        "bookedID, " +
                        "bookedPropertyID_fk, " +
                        "guestID_fk, " +
                        "startDate, " +
                        "endDate, " +
                        "confirmation " +
                        "FROM Bookings " +
                        "INNER JOIN Property " +
                        "ON Property.propertyID = Bookings.bookedPropertyID_fk " +
                        "WHERE Property.propertyID='" + property.getPropertyID() + "';");

                while (getResSet().next()) {
                    int bookedID = getResSet().getInt("bookedID");
                    int bookedPropertyID_fk = getResSet().getInt("bookedPropertyID_fk");
                    int guestID_fk = getResSet().getInt("guestID_fk");
                    java.sql.Date startDate = getResSet().getDate("startDate");
                    java.sql.Date endDate = getResSet().getDate("endDate");
                    int confirmation = getResSet().getInt("confirmation");
                    bookings.add(new Bookings(bookedID, bookedPropertyID_fk, guestID_fk, startDate, endDate,
                            confirmation));
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
     * @return - A list of reviews of the property.
     */
    public List<Reviews> getPropertyReviews(){
        ArrayList<Reviews> reviews = new ArrayList<>();
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                //untested
                openResSet("SELECT " +
                        "reviewID, " +
                        "reviews_propertyID_fk, " +
                        "reviewDescription, " +
                        "cleanliness, " +
                        "communication, " +
                        "checkin, " +
                        "accuracy, " +
                        "location, " +
                        "value " +
                        "FROM Reviews " +
                        "INNER JOIN Property " +
                        "ON Property.propertyID = Reviews.reviews_propertyID_fk " +
                        "WHERE Property.propertyID='" + property.getPropertyID() + "';");

                while (getResSet().next()) {
                    int reviewID = getResSet().getInt("reviewID");
                    int reviews_propertyID_fk = getResSet().getInt("reviews_propertyID_fk");
                    String reviewDescription = getResSet().getString("reviewDescription");
                    int cleanliness = getResSet().getInt("cleanliness");
                    int communication = getResSet().getInt("communication");
                    int checkin = getResSet().getInt("checkin");
                    int accuracy = getResSet().getInt("accuracy");
                    int location = getResSet().getInt("location");
                    int value = getResSet().getInt("value");
                    reviews.add(new Reviews(reviewID, reviews_propertyID_fk, reviewDescription, cleanliness,
                            communication, checkin, accuracy, location, value));
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
        return reviews;
    }

    /*
     * @return - Adds the number of days specified in numDays to date.
     */
    private java.sql.Date addDays (java.sql.Date date, int numDays) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, numDays);
        return new java.sql.Date(calendar.getTimeInMillis());
    }

    /* Calculates the average rating of the property by iterating over its list of ratings.
     * @return - The average rating of the property.
     */
    public Double calcAvgRating() {
        List<Reviews> allReviews= getPropertyReviews();
        Double overallRating = 0.00;
        for (int i = 0; i < allReviews.size(); i++) {
            ReviewModel rvwMdl = new ReviewModel(allReviews.get(i).getReviewID());
            overallRating += rvwMdl.calcOverallRating();
        }
        if(allReviews.size()>0) {
            return overallRating / allReviews.size();
        }
        else{
            return overallRating;
        }

    }

    /*
     * @return - The list of the property's charge bands.
     */
    public ArrayList<ChargeBand> getChargeBands(){
        ArrayList<ChargeBand> chargeBands = new ArrayList<>();
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                openResSet("SELECT * FROM ChargeBands INNER JOIN Property ON Property.propertyID = " +
                        "ChargeBands.charge_propertyID_fk WHERE charge_propertyID_fk = '"
                        + getProperty().getPropertyID()+"';");


                while (getResSet().next()) {
                    int chargeBandsID = getResSet().getInt("chargeBandsID");
                    int charge_propertyID_fk = getResSet().getInt("charge_propertyID_fk");
                    java.sql.Date startDate = getResSet().getDate("startDate");
                    java.sql.Date endDate = getResSet().getDate("endDate");
                    int pricePerNight = getResSet().getInt("pricePerNight");
                    int serviceCharge = getResSet().getInt("serviceCharge");
                    int cleaningCharge = getResSet().getInt("cleaningCharge");
                    chargeBands.add(new ChargeBand (chargeBandsID, charge_propertyID_fk, startDate, endDate,
                            pricePerNight, serviceCharge, cleaningCharge));
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
        return chargeBands;
    }

    /*
     * @return - The list of the property's charge bands in a specified date range.
     */
    public ArrayList<ChargeBand> getChargeBandsInRange(java.sql.Date sDate, java.sql.Date eDate){
        ArrayList<ChargeBand> chargeBands = new ArrayList<>();
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                    openResSet("SELECT * FROM ChargeBands INNER JOIN Property ON Property.propertyID = " +
                            "ChargeBands.charge_propertyID_fk WHERE charge_propertyID_fk = '"
                            + getProperty().getPropertyID()+"' AND NOT " +
                            "(startDate > '" + eDate + "' OR endDate < '" + sDate + "')");


                while (getResSet().next()) {
                    int chargeBandsID = getResSet().getInt("chargeBandsID");
                    int charge_propertyID_fk = getResSet().getInt("charge_propertyID_fk");
                    java.sql.Date startDate = getResSet().getDate("startDate");
                    java.sql.Date endDate = getResSet().getDate("endDate");
                    int pricePerNight = getResSet().getInt("pricePerNight");
                    int serviceCharge = getResSet().getInt("serviceCharge");
                    int cleaningCharge = getResSet().getInt("cleaningCharge");
                    chargeBands.add(new ChargeBand (chargeBandsID, charge_propertyID_fk, startDate, endDate,
                            pricePerNight, serviceCharge, cleaningCharge));
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
        return chargeBands;
    }

    /*
     * @return - The difference between two dates, in days.
     */
    public int calculateLengthOfStay(java.sql.Date sDate, java.sql.Date eDate) {
        long lengthOfStay = Math.abs(eDate.getTime() - sDate.getTime());
        long lengthOfStayInDays = TimeUnit.DAYS.convert(lengthOfStay, TimeUnit.MILLISECONDS);
        return Math.toIntExact(lengthOfStayInDays);
    }

    /* Iterates through each day of the stay and finds the appropriate charge band to apply.
     * @return - The cost of a stay
     */
    public double calculateCostOfStay(java.sql.Date sDate, java.sql.Date eDate) {
        ArrayList<ChargeBand> chargeBands = getChargeBandsInRange(sDate, eDate);
        int lengthOfStay = calculateLengthOfStay(sDate, eDate);
        double costOfStay = 0;
        java.sql.Date nthDay = sDate;
        for (int i = 0; i <= lengthOfStay; i++) {
            nthDay = addDays(sDate, i);
            for (int j = 0; j < chargeBands.size(); j++) {
                ChargeBand cb = chargeBands.get(j);
                if ((nthDay.compareTo(cb.getStartDate()) >= 0) && (nthDay.compareTo(cb.getEndDate()) < 0)) {
                    double totalCost = cb.getPricePerNight() + cb.getServiceCharge() + cb.getCleaningCharge();
                    costOfStay += totalCost;
                    break;
                }
            }
        }
    return costOfStay;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public void insertPropertyRow() {
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                PreparedStatement prpStmt = getCon().prepareStatement("INSERT INTO Property VALUES " +
                        "(DEFAULT, ?, ?, ?, ?, ?, ?)");
                prpStmt.setInt(1,getProperty().getFacilityID_fk());
                prpStmt.setString(2,getProperty().getPropertyName());
                prpStmt.setInt(3,getProperty().getHostID_fk());
                prpStmt.setString(4,getProperty().getDescription());
                prpStmt.setInt(5,getProperty().getHasBreakfast());
                prpStmt.setInt(6,getProperty().getAddressID_fk());
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

    /*
     * @return - The general location of the property (City, Country)
     */
    public String getGeneralLocation(){
        StringBuilder stringBuilder = new StringBuilder();
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                /*
                openResSet("SELECT streetName, city, country FROM Property " +
                        "LEFT JOIN Hosts ON Property.hostID_fk = Hosts.hostID " +
                        "LEFT JOIN Account ON Hosts.host_email_fk = Account.email " +
                        "LEFT JOIN Address ON Account.addressID_fk = Address.addressID " +
                        "WHERE propertyID = '" + getProperty().getPropertyID() + "';");
*/
                openResSet("SELECT city, country FROM Property " +
                        "INNER JOIN Address ON Property.addressID_fk = Address.addressID " +
                        "WHERE propertyID = '" + getProperty().getPropertyID() + "';");

                while(getResSet().next()){
                    String city = getResSet().getString("city");
                    String country = getResSet().getString("country");
                    String[] generalLocation = {city, ", ", country};
                    for(int i=0;i<generalLocation.length;i++){
                        stringBuilder.append(generalLocation[i]);
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
        return stringBuilder.toString();
    }

    /* Derives the number of bedrooms, bathrooms, bedrooms, and guests through queries and loops.
     * @return - A list that contains the number of beds, the number of bathrooms, the number of bedrooms, and the
     * number of guests, in that order.
     */
    public ArrayList<Integer> getNumBedsBathroomsBedroomsGuests(){
        ArrayList<String> beds = new ArrayList<>();
        ArrayList<Integer> bathrooms = new ArrayList<>();
        ArrayList<Integer> bedrooms = new ArrayList<>();
        ArrayList<Integer> bedsBathroomsBedroomsGuests = new ArrayList<>();
        int numSleepers = 0;
        declareCon();
        declareStmt();
        declareResSet();

        //use 2 different queries to prevent duplicated counts
        try {
            try {
                openCon();
                openStmt();
                openResSet("SELECT bedOneType, bedTwoType, bedroomID FROM Property " +
                        "LEFT JOIN Facilities ON Property.facilityID_fk = Facilities.facilityID " +
                        "LEFT JOIN SleepingFacility ON Facilities.sleepingFacilityID_fk = SleepingFacility.sleepingFacilityID " +
                        "LEFT JOIN Bedroom ON SleepingFacility.sleepingFacilityID = Bedroom.sleepingFacilityID_fk " +
                        "WHERE propertyID = '" + getProperty().getPropertyID() + "';");
                while(getResSet().next()){
                    beds.add(getResSet().getString("bedOneType"));
                    beds.add(getResSet().getString("bedTwoType"));
                    bedrooms.add(getResSet().getInt("bedroomID"));

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

        try {
            try {
                openCon();
                openStmt();
                openResSet("SELECT bathroomID FROM Property " +
                        "LEFT JOIN Facilities ON Property.facilityID_fk = Facilities.facilityID " +
                        "LEFT JOIN BathingFacility ON Facilities.bathingFacilityID_fk = BathingFacility.bathingFacilityID " +
                        "LEFT JOIN Bathroom ON BathingFacility.bathingFacilityID = Bathroom.bathingFacilityID_fk " +
                        "WHERE propertyID = '" + getProperty().getPropertyID() + "';");
                //make sure to remove duplicates
                while(getResSet().next()){
                    bathrooms.add(getResSet().getInt("bathroomID"));

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

        for (int i = 0; i < beds.size(); i++) {
            String currBed = beds.get(i);
            if (currBed != null && currBed.equals("single")) {
                numSleepers += 1;
            }
            else if (currBed != null && (currBed.equals("double") || currBed.equals("bunk") ||
                    currBed.equals("kingsize"))) {

                numSleepers += 2;
            }
        }

        bedsBathroomsBedroomsGuests.add(beds.size());
        bedsBathroomsBedroomsGuests.add(bathrooms.size());
        bedsBathroomsBedroomsGuests.add(bedrooms.size());
        bedsBathroomsBedroomsGuests.add(numSleepers);

        return bedsBathroomsBedroomsGuests;
    }

    public int isSuperHost(){
        int superHost = 0;
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                openResSet("SELECT isSuperHost FROM Property " +
                        "LEFT JOIN Hosts ON Property.hostID_fk = Hosts.hostID " +
                        "WHERE propertyID = '" + getProperty().getPropertyID() + "';");

                while(getResSet().next()){
                    superHost = getResSet().getInt("isSuperHost");
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
        return superHost;
    }

    public ArrayList<Integer> getSleepingFacilityInfo(){
        ArrayList<Integer> data = new ArrayList<>();
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                openResSet("SELECT hasBedLinen, hasTowels, sleepingFacilityID FROM Property " +
                        "LEFT JOIN Facilities ON Property.facilityID_fk = Facilities.facilityID " +
                        "LEFT JOIN SleepingFacility ON Facilities.sleepingFacilityID_fk = SleepingFacility.sleepingFacilityID " +
                        "WHERE propertyID = '" + getProperty().getPropertyID() + "';");

                while(getResSet().next()){
                    data.add(getResSet().getInt("hasBedLinen"));
                    data.add(getResSet().getInt("hasTowels"));
                    data.add(getResSet().getInt("sleepingFacilityID"));
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

    public ArrayList<Integer> getBathingFacilityInfo(){
        ArrayList<Integer> data = new ArrayList<>();
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                openResSet("SELECT hasHairDryer, hasShampoo, hasToiletPaper," +
                        " bathingFacilityID FROM Property " +
                        "LEFT JOIN Facilities ON Property.facilityID_fk = Facilities.facilityID " +
                        "LEFT JOIN BathingFacility ON Facilities.bathingFacilityID_fk = BathingFacility.bathingFacilityID " +
                        "WHERE propertyID = '" + getProperty().getPropertyID() + "';");

                while(getResSet().next()){
                    data.add(getResSet().getInt("hasHairDryer"));
                    data.add(getResSet().getInt("hasShampoo"));
                    data.add(getResSet().getInt("hasToiletPaper"));
                    data.add(getResSet().getInt("bathingFacilityID"));
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

    public ArrayList<Integer> getKitchenFacilityInfo(){
        ArrayList<Integer> data = new ArrayList<>();
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                openResSet("SELECT hasOven, hasFridge, hasMicrowave, hasStove, hasDishWasher, hasTableware," +
                        " hasCookware, hasBasicProvisions FROM Property " +
                        "LEFT JOIN Facilities ON Property.facilityID_fk = Facilities.facilityID " +
                        "LEFT JOIN KitchenFacility ON Facilities.kitchenFacilityID_fk = KitchenFacility.kitchenFacilityID " +
                        "WHERE propertyID = '" + getProperty().getPropertyID() + "';");

                while(getResSet().next()){
                    data.add(getResSet().getInt("hasOven"));
                    data.add(getResSet().getInt("hasFridge"));
                    data.add(getResSet().getInt("hasMicrowave"));
                    data.add(getResSet().getInt("hasStove"));
                    data.add(getResSet().getInt("hasDishWasher"));
                    data.add(getResSet().getInt("hasTableware"));
                    data.add(getResSet().getInt("hasCookware"));
                    data.add(getResSet().getInt("hasBasicProvisions"));
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

    public ArrayList<Integer> getLivingFacilityInfo(){
        ArrayList<Integer> data = new ArrayList<>();
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                openResSet("SELECT hasWifi, hasTelevision, hasSatellite, hasStreamingService, " +
                        "hasDvdPlayer, hasBoardGames FROM Property " +
                        "LEFT JOIN Facilities ON Property.facilityID_fk = Facilities.facilityID " +
                        "LEFT JOIN LivingFacility ON Facilities.livingFacilityID_fk = LivingFacility.livingFacilityID " +
                        "WHERE propertyID = '" + getProperty().getPropertyID() + "';");

                while(getResSet().next()){
                    data.add(getResSet().getInt("hasWifi"));
                    data.add(getResSet().getInt("hasTelevision"));
                    data.add(getResSet().getInt("hasSatellite"));
                    data.add(getResSet().getInt("hasStreamingService"));
                    data.add(getResSet().getInt("hasDvdPlayer"));
                    data.add(getResSet().getInt("hasBoardGames"));
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

    public ArrayList<Integer> getUtilityFacilityInfo(){
        ArrayList<Integer> data = new ArrayList<>();
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                openResSet("SELECT hasHeating, hasWashingMachine, hasDryingMachine, hasFireExtinguisher, " +
                        "hasSmokeAlarm, hasFirstAidKit FROM Property " +
                        "LEFT JOIN Facilities ON Property.facilityID_fk = Facilities.facilityID " +
                        "LEFT JOIN UtilityFacility ON Facilities.utilityFacilityID_fk = UtilityFacility.utilityFacilityID " +
                        "WHERE propertyID = '" + getProperty().getPropertyID() + "';");

                while(getResSet().next()){
                    data.add(getResSet().getInt("hasHeating"));
                    data.add(getResSet().getInt("hasWashingMachine"));
                    data.add(getResSet().getInt("hasDryingMachine"));
                    data.add(getResSet().getInt("hasFireExtinguisher"));
                    data.add(getResSet().getInt("hasSmokeAlarm"));
                    data.add(getResSet().getInt("hasFirstAidKit"));
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

    public ArrayList<Integer> getOutdoorsFacilityInfo(){
        ArrayList<Integer> data = new ArrayList<>();
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                openResSet("SELECT hasFreeOnSiteParking, hasPaidCarPark, hasPatio, hasBarbequeFacilities FROM Property " +
                        "LEFT JOIN Facilities ON Property.facilityID_fk = Facilities.facilityID " +
                        "LEFT JOIN OutdoorsFacility ON Facilities.outdoorsFacilityID_fk = OutdoorsFacility.outdoorsFacilityID " +
                        "WHERE propertyID = '" + getProperty().getPropertyID() + "';");

                while(getResSet().next()){
                    data.add(getResSet().getInt("hasFreeOnSiteParking"));
                    data.add(getResSet().getInt("hasPaidCarPark"));
                    data.add(getResSet().getInt("hasPatio"));
                    data.add(getResSet().getInt("hasBarbequeFacilities"));
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

    /*
     * @return - The list of properties from a given city.
     */
    public ArrayList<Property> getMatchingProperty(String city){
        ArrayList<Property> data = new ArrayList<>();
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                openResSet("SELECT propertyID, facilityID_fk, propertyName, hostID_fk, description, hasBreakfast, " +
                        "addressID_fk FROM Property " +
                        "INNER JOIN Address ON Property.addressID_fk = Address.addressID "+
                        "WHERE city = '" +city +"';");

                while(getResSet().next()){
                    int propertyID = getResSet().getInt("propertyID");
                    int facilityID_fk = getResSet().getInt("facilityID_fk");
                    String propertyName = getResSet().getString("propertyName");
                    int hostID_fk = getResSet().getInt("hostID_fk");
                    String description = getResSet().getString("description");
                    int hasBreakfast = getResSet().getInt("hasBreakfast");
                    int addressID_fk = getResSet().getInt("addressID_fk");
                    data.add(new Property(propertyID,facilityID_fk,propertyName,hostID_fk,description,hasBreakfast,
                            addressID_fk));
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


    /*
     * @return - The list of properties with bookings that overlap with the specified date range.
     */
    public ArrayList<Property> getConfirmedPropertyInRange(java.sql.Date sDate, java.sql.Date eDate){
        ArrayList<Property> data = new ArrayList<>();
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                openResSet("SELECT propertyID, facilityID_fk, propertyName, hostID_fk, description," +
                        "hasBreakfast, addressID_fk FROM Property "+
                        "LEFT JOIN Bookings ON Property.propertyID = Bookings.bookedPropertyID_fk "+
                        "LEFT JOIN Address ON Property.addressID_fk = Address.addressID " +
                        "WHERE NOT (startDate > '" + eDate + "' OR endDate < '" + sDate + "') AND confirmation = 1;");

                while(getResSet().next()){
                    int propertyID = getResSet().getInt("propertyID");
                    int facilityID_fk = getResSet().getInt("facilityID_fk");
                    String propertyName = getResSet().getString("propertyName");
                    int hostID_fk = getResSet().getInt("hostID_fk");
                    String description = getResSet().getString("description");
                    int hasBreakfast = getResSet().getInt("hasBreakfast");
                    int addressID_fk = getResSet().getInt("addressID_fk");
                    data.add(new Property(propertyID,facilityID_fk,propertyName,hostID_fk,description,hasBreakfast,
                            addressID_fk));
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


    /* Usinng getConfirmedPropertyInRange and getMatchingProperty, check if the matching property's names are included
    in the confirmed property's list of names. Mark the index of the matching property's list true/false in another
    array, depending on the results.
     * @return - An array of Booleans, corresponding to the index of the matching property's list.
     */
    public Boolean[] getPropertyAvailability(String city, java.sql.Date sDate, java.sql.Date eDate){
        ArrayList<Property> properties = new ArrayList<>(getMatchingProperty(city));
        ArrayList<String> propertiesName = new ArrayList<>();
        ArrayList<Property> confirmedProperties = new ArrayList<>(getConfirmedPropertyInRange(sDate, eDate));
        ArrayList<String> confirmedPropertiesName = new ArrayList<>();
        Boolean[] bools = new Boolean[properties.size()];


        for(int i=0;i<properties.size();i++){
            propertiesName.add(properties.get(i).getPropertyName());
        }
        for(int i=0;i<confirmedProperties.size();i++){
            confirmedPropertiesName.add(confirmedProperties.get(i).getPropertyName());
        }

        //initialise array with trues
        for (int i = 0; i < properties.size(); i++) {
            bools[i] = true;
        }
        for(int i=0;i<propertiesName.size();i++) {
            if (confirmedPropertiesName.contains(propertiesName.get(i))) {
                bools[i] = false;


            } else bools[i] = true;
        }

        return bools;
    }


    public void setPropertyID(){
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                openResSet("SELECT propertyID " +
                        "FROM Property ORDER BY propertyID DESC LIMIT 1;");

                while(getResSet().next()) {
                    int propertyID = getResSet().getInt(1);
                    getProperty().setPropertyID(propertyID);
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
}
