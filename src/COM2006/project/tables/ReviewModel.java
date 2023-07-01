package COM2006.project.tables;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReviewModel extends Model {
    Reviews reviews;

    public ReviewModel(int reviewID) {
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                openResSet("SELECT reviews_propertyID_fk, reviewDescription, cleanliness, communication, " +
                        "checkin, accuracy, location, value FROM Reviews " +
                        "WHERE reviewID='" + reviewID + "';");

                while (getResSet().next()) {
                    int reviews_propertyID_fk = getResSet().getInt("reviews_propertyID_fk");
                    String reviewDescription = getResSet().getString("reviewDescription");
                    int cleanliness = getResSet().getInt("cleanliness");
                    int communication = getResSet().getInt("communication");
                    int checkin = getResSet().getInt("checkin");
                    int accuracy = getResSet().getInt("accuracy");
                    int location = getResSet().getInt("location");
                    int value = getResSet().getInt("value");
                    setReview(new Reviews(reviewID, reviews_propertyID_fk, reviewDescription, cleanliness,
                            communication, checkin, accuracy, location, value));

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

    public Reviews getReview() {
        return reviews;
    }

    public void setReview(Reviews reviews) {
        this.reviews = reviews;
    }

    public void insertReviewRow() {
        declareCon();
        declareStmt();
        declareResSet();
        try {
            try {
                openCon();
                openStmt();
                PreparedStatement prpStmt = getCon().prepareStatement("INSERT INTO Reviews VALUES " +
                        "(DEFAULT, '" + getReview().getReviews_propertyID_fk() + "', ?,'"
                        + getReview().getCleanliness() + "','"
                        + getReview().getCommunication() + "','"
                        + getReview().getCheckin() + "','"
                        + getReview().getAccuracy() + "','"
                        + getReview().getLocation() + "','"
                        + getReview().getValue() + "');");
                prpStmt.setString(1, getReview().getReviewDescription());
                prpStmt.executeUpdate();
                prpStmt.close();
            } finally {
                closeCon();
                closeStmt();
                closeResSet();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Double calcOverallRating() {
        int overall = getReview().getCleanliness() + getReview().getCommunication() + getReview().getCheckin() +
                getReview().getAccuracy() + getReview().getLocation() + getReview().getValue();
        return (double)overall/6 ;
    }
        /*
        insert("Reviews",
                "(DEFAULT,'"+getReview().getReviews_propertyID_fk()+"','"
                        +getReview().getReviewDescription()+"','"
                        +getReview().getCleanliness()+"','"
                        +getReview().getCommunication()+"','"
                        +getReview().getCheckin()+"','"
                        +getReview().getAccuracy()+"','"
                        +getReview().getLocation()+"','"
                        +getReview().getValue()+"','"+")");

         */
}

