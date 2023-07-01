package COM2006.project.tables;

public class Reviews {
    private int reviewID;
    private int reviews_propertyID_fk;
    private String reviewDescription;
    private int cleanliness;
    private int communication;
    private int checkin;
    private int accuracy;
    private int location;
    private int value;

    public Reviews(int reviewID, int reviews_propertyID_fk, String reviewDescription, int cleanliness, int communication,
                   int checkin, int accuracy, int location, int value) {
        this.reviewID = reviewID;
        this.reviews_propertyID_fk = reviews_propertyID_fk;
        this.reviewDescription = reviewDescription;
        this.cleanliness = cleanliness;
        this.communication = communication;
        this.checkin = checkin;
        this.accuracy = accuracy;
        this.location = location;
        this.value = value;
    }

    //Getter

    public int getReviewID() {
        return reviewID;
    }

    public int getReviews_propertyID_fk() {
        return reviews_propertyID_fk;
    }

    public String getReviewDescription() {
        return reviewDescription;
    }

    public int getCleanliness() {
        return cleanliness;
    }

    public int getCommunication() {
        return communication;
    }

    public int getCheckin() {
        return checkin;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public int getLocation() {
        return location;
    }

    public int getValue() {
        return value;
    }


    //Setter

    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }

    public void setReviews_propertyID_fk(int reviews_propertyID_fk) {
        this.reviews_propertyID_fk = reviews_propertyID_fk;
    }

    public void setReviewDescription(String reviewDescription) {
        this.reviewDescription = reviewDescription;
    }

    public void setCleanliness(int cleanliness) {
        this.cleanliness = cleanliness;
    }

    public void setCommunication(int communication) {
        this.communication = communication;
    }

    public void setCheckin(int checkin) {
        this.checkin = checkin;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public void setValue(int value) {
        this.value = value;
    }


}

