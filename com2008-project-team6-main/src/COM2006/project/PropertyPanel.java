package COM2006.project;

import COM2006.project.tables.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import javax.swing.*;

public class PropertyPanel extends JPanel{

    private JPanel rowHolderPanel = new JPanel(new GridLayout(0, 1, 1, 1));
    private MainFrame mainFrame;
    private GuestsModel guestAccount;
    private HostsModel hostAccount;
    private PropertyModel property;
    private int propertyID;

    //Query database for property id
    //add property name, property description, general location?, number of guests, num of bedrooms, num of beds, num of bathrooms, rating, superhost.
    //Add facility with ameneties
    //Add bedrooms and bathrooms if applicable
    //Repeat above 2 lines 5 more times
    //Have area for selecting stat and end dates of stay
    //Show what price band the property is in
    //Button to book - checks if property has a confirmed booking that overlaps
    //if does say booking overlaps with someone elses
    //otherwise create provisional booking
    //Reviews
    //Show score from 0-5 for the 6 criteria
    //Show comments left by past guests
    //Have an area that allows a guest to make a review if they have stayed at the property before and haven't already made a review
    //Button to submit review

    //unregistered users
    public PropertyPanel(MainFrame frame, int id) {
        mainFrame = frame;
        propertyID = id;
        property = new PropertyModel(id);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(rowHolderPanel, BorderLayout.PAGE_START);
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        ArrayList<Integer> numBedsBathroomsBedroomsGuests = new ArrayList<>(property.getNumBedsBathroomsBedroomsGuests());

        //declare panels for attributes of property
        JLabel propertyText = new JLabel("<HTML><U>Property Information</U><HTML>",SwingConstants.CENTER);
        propertyText.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JPanel namePanel = new JPanel();
        namePanel.add(new JLabel("Property Name:"));
        namePanel.add(new JLabel(property.getProperty().getPropertyName()));

        JPanel descPanel = new JPanel();
        descPanel.add(new JLabel("Property Description:"));
        descPanel.add(new JLabel(property.getProperty().getDescription()));

        JPanel addressPanel = new JPanel();
        addressPanel.add(new JLabel("General Location:"));
        addressPanel.add(new JLabel(property.getGeneralLocation()));

        JPanel numGuestsPanel = new JPanel();
        numGuestsPanel.add(new JLabel("Number of Guests:"));
        numGuestsPanel.add(new JLabel(String.valueOf(numBedsBathroomsBedroomsGuests.get(3))));

        JPanel numBedsPanel = new JPanel();
        numBedsPanel.add(new JLabel("Number of Beds:"));
        numBedsPanel.add(new JLabel(String.valueOf(numBedsBathroomsBedroomsGuests.get(0))));

        JPanel numBedroomsPanel = new JPanel();
        numBedroomsPanel.add(new JLabel("Number of Bedrooms:"));
        numBedroomsPanel.add(new JLabel(String.valueOf(numBedsBathroomsBedroomsGuests.get(2))));

        JPanel numBathPanel = new JPanel();
        numBathPanel.add(new JLabel("Number of Bathrooms:"));
        numBathPanel.add(new JLabel(String.valueOf(numBedsBathroomsBedroomsGuests.get(1))));

        JPanel ratingPanel = new JPanel();
        ratingPanel.add(new JLabel("Ratings:"));
        ratingPanel.add(new JLabel(String.format("%.2f",property.calcAvgRating())));

        String superHost ="";
        if(property.isSuperHost() == 0) superHost = "No";
        else superHost = "Yes";

        JPanel superHostPanel = new JPanel();
        superHostPanel.add(new JLabel("SuperHost:"));
        superHostPanel.add(new JLabel(superHost));

        //add to row holder to make scroll bar work
        rowHolderPanel.add(namePanel);
        rowHolderPanel.add(descPanel);
        rowHolderPanel.add(addressPanel);
        rowHolderPanel.add(numGuestsPanel);
        rowHolderPanel.add(numBedsPanel);
        rowHolderPanel.add(numBathPanel);
        rowHolderPanel.add(ratingPanel);
        rowHolderPanel.add(superHostPanel);

        //fetch information about individual facilities from DB, then put them in individual panels
        ArrayList<String> sleepingFacilityData = new ArrayList<>(convert(property.getSleepingFacilityInfo()));
        ArrayList<String> bathroomFacilityData = new ArrayList<>(convert(property.getBathingFacilityInfo()));
        ArrayList<String> kitchenFacilityData = new ArrayList<>(convert(property.getKitchenFacilityInfo()));
        ArrayList<String> livingFacilityData = new ArrayList<>(convert(property.getLivingFacilityInfo()));
        ArrayList<String> outdoorsFacilityData = new ArrayList<>(convert(property.getOutdoorsFacilityInfo()));
        ArrayList<String> utilityFacilityData = new ArrayList<>(convert(property.getUtilityFacilityInfo()));
        ArrayList<Bathroom> bathrooms = new BathingFacilityModel
                (property.getBathingFacilityInfo().get(3)).getAttachedBathrooms();
        ArrayList<Bedroom> bedrooms = new SleepingFacilityModel
                (property.getSleepingFacilityInfo().get(2)).getAttachedBedrooms();

        JLabel sleepingFacilityText = new JLabel("<HTML><U>Sleeping Facility Amenities</U><HTML>",SwingConstants.CENTER);
        sleepingFacilityText.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        JLabel bathroomFacilityText = new JLabel("<HTML><U>Bathroom Facility Amenities</U><HTML>",SwingConstants.CENTER);
        bathroomFacilityText.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        JLabel kitchenFacilityText = new JLabel("<HTML><U>Kitchen Facility Amenities</U><HTML>",SwingConstants.CENTER);
        kitchenFacilityText.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        JLabel livingFacilityText = new JLabel("<HTML><U>Living Facility Amenities</U><HTML>",SwingConstants.CENTER);
        livingFacilityText.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        JLabel outdoorsFacilityText = new JLabel("<HTML><U>Outdoors Facility Amenities</U><HTML>",SwingConstants.CENTER);
        outdoorsFacilityText.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        JLabel utilityFacilityText = new JLabel("<HTML><U>Utility Facility Amenities</U><HTML>",SwingConstants.CENTER);
        utilityFacilityText.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JPanel sleepingFacilityInfo = new JPanel();
        sleepingFacilityInfo.add(new JLabel("BedLinen: "));
        sleepingFacilityInfo.add(new JLabel(sleepingFacilityData.get(0)));
        sleepingFacilityInfo.add(new JLabel("•"));
        sleepingFacilityInfo.add(new JLabel("Towels: "));
        sleepingFacilityInfo.add(new JLabel(sleepingFacilityData.get(1)));
        JPanel sleepingFacilityPanel = new JPanel();
        sleepingFacilityPanel.add(sleepingFacilityText);
        sleepingFacilityPanel.add(sleepingFacilityInfo);

        JPanel bathroomFacilityInfo = new JPanel();
        bathroomFacilityInfo.add(new JLabel("HairDryer: "));
        bathroomFacilityInfo.add(new JLabel(bathroomFacilityData.get(0)));
        bathroomFacilityInfo.add(new JLabel("•"));
        bathroomFacilityInfo.add(new JLabel("Shampoo: "));
        bathroomFacilityInfo.add(new JLabel(bathroomFacilityData.get(1)));
        bathroomFacilityInfo.add(new JLabel("•"));
        bathroomFacilityInfo.add(new JLabel("ToiletPaper: "));
        bathroomFacilityInfo.add(new JLabel(bathroomFacilityData.get(2)));
        JPanel bathroomFacilityPanel = new JPanel();
        bathroomFacilityPanel.add(bathroomFacilityText);
        bathroomFacilityPanel.add(bathroomFacilityInfo);

        JPanel kitchenFacilityInfo = new JPanel();
        kitchenFacilityInfo.add(new JLabel("Oven: "));
        kitchenFacilityInfo.add(new JLabel(kitchenFacilityData.get(0)));
        kitchenFacilityInfo.add(new JLabel("•"));
        kitchenFacilityInfo.add(new JLabel("Fridge: "));
        kitchenFacilityInfo.add(new JLabel(kitchenFacilityData.get(1)));
        kitchenFacilityInfo.add(new JLabel("•"));
        kitchenFacilityInfo.add(new JLabel("Microwave: "));
        kitchenFacilityInfo.add(new JLabel(kitchenFacilityData.get(2)));
        kitchenFacilityInfo.add(new JLabel("•"));
        kitchenFacilityInfo.add(new JLabel("Stove: "));
        kitchenFacilityInfo.add(new JLabel(kitchenFacilityData.get(3)));
        kitchenFacilityInfo.add(new JLabel("•"));
        kitchenFacilityInfo.add(new JLabel("Dish Washer: "));
        kitchenFacilityInfo.add(new JLabel(kitchenFacilityData.get(4)));
        kitchenFacilityInfo.add(new JLabel("•"));
        kitchenFacilityInfo.add(new JLabel("Tableware: "));
        kitchenFacilityInfo.add(new JLabel(kitchenFacilityData.get(5)));
        kitchenFacilityInfo.add(new JLabel("•"));
        kitchenFacilityInfo.add(new JLabel("Cookware: "));
        kitchenFacilityInfo.add(new JLabel(kitchenFacilityData.get(6)));
        kitchenFacilityInfo.add(new JLabel("•"));
        kitchenFacilityInfo.add(new JLabel("Basic Provisions: "));
        kitchenFacilityInfo.add(new JLabel(kitchenFacilityData.get(7)));
        JPanel kitchenFacilityPanel = new JPanel();
        kitchenFacilityPanel.add(kitchenFacilityText);
        kitchenFacilityPanel.add(kitchenFacilityInfo);

        JPanel livingFacilityInfo = new JPanel();
        livingFacilityInfo.add(new JLabel("Wifi: "));
        livingFacilityInfo.add(new JLabel(livingFacilityData.get(0)));
        livingFacilityInfo.add(new JLabel("•"));
        livingFacilityInfo.add(new JLabel("Television: "));
        livingFacilityInfo.add(new JLabel(livingFacilityData.get(1)));
        livingFacilityInfo.add(new JLabel("•"));
        livingFacilityInfo.add(new JLabel("Satellite: "));
        livingFacilityInfo.add(new JLabel(livingFacilityData.get(2)));
        livingFacilityInfo.add(new JLabel("•"));
        livingFacilityInfo.add(new JLabel("Streaming Service: "));
        livingFacilityInfo.add(new JLabel(livingFacilityData.get(3)));
        livingFacilityInfo.add(new JLabel("•"));
        livingFacilityInfo.add(new JLabel("DVD Player: "));
        livingFacilityInfo.add(new JLabel(livingFacilityData.get(4)));
        livingFacilityInfo.add(new JLabel("•"));
        livingFacilityInfo.add(new JLabel("Board Games: "));
        livingFacilityInfo.add(new JLabel(livingFacilityData.get(5)));
        JPanel livingFacilityPanel = new JPanel();
        livingFacilityPanel.add(livingFacilityText);
        livingFacilityPanel.add(livingFacilityInfo);

        JPanel outdoorsFacilityInfo = new JPanel();
        outdoorsFacilityInfo.add(new JLabel("Free OnSite Parking: "));
        outdoorsFacilityInfo.add(new JLabel(outdoorsFacilityData.get(0)));
        outdoorsFacilityInfo.add(new JLabel("•"));
        outdoorsFacilityInfo.add(new JLabel("Paid Car Park: "));
        outdoorsFacilityInfo.add(new JLabel(outdoorsFacilityData.get(1)));
        outdoorsFacilityInfo.add(new JLabel("•"));
        outdoorsFacilityInfo.add(new JLabel("Patio: "));
        outdoorsFacilityInfo.add(new JLabel(outdoorsFacilityData.get(2)));
        outdoorsFacilityInfo.add(new JLabel("•"));
        outdoorsFacilityInfo.add(new JLabel("Barbeque Facilities: "));
        outdoorsFacilityInfo.add(new JLabel(outdoorsFacilityData.get(3)));
        JPanel outdoorsFacilityPanel = new JPanel();
        outdoorsFacilityPanel.add(outdoorsFacilityText);
        outdoorsFacilityPanel.add(outdoorsFacilityInfo);

        JPanel utilityFacilityInfo = new JPanel();
        utilityFacilityInfo.add(new JLabel("Heating: "));
        utilityFacilityInfo.add(new JLabel(utilityFacilityData.get(0)));
        utilityFacilityInfo.add(new JLabel("•"));
        utilityFacilityInfo.add(new JLabel("Washing Machine: "));
        utilityFacilityInfo.add(new JLabel(utilityFacilityData.get(1)));
        utilityFacilityInfo.add(new JLabel("•"));
        utilityFacilityInfo.add(new JLabel("Drying Machine: "));
        utilityFacilityInfo.add(new JLabel(utilityFacilityData.get(2)));
        utilityFacilityInfo.add(new JLabel("•"));
        utilityFacilityInfo.add(new JLabel("Fire Extinguisher: "));
        utilityFacilityInfo.add(new JLabel(utilityFacilityData.get(3)));
        utilityFacilityInfo.add(new JLabel("•"));
        utilityFacilityInfo.add(new JLabel("Smoke Alarm: "));
        utilityFacilityInfo.add(new JLabel(utilityFacilityData.get(4)));
        utilityFacilityInfo.add(new JLabel("•"));
        utilityFacilityInfo.add(new JLabel("First Aid Kit: "));
        utilityFacilityInfo.add(new JLabel(utilityFacilityData.get(5)));
        JPanel utilityFacilityPanel = new JPanel();
        utilityFacilityPanel.add(utilityFacilityText);
        utilityFacilityPanel.add(utilityFacilityInfo);

        JPanel facilitiesHead = new JPanel();
        JLabel facilitiesText = new JLabel("<HTML><U>Facilities</U><HTML>",SwingConstants.CENTER);
        facilitiesText.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        facilitiesHead.add(facilitiesText);
        JPanel facilitiesPanel = new JPanel();
        //facilitiesPanel.setLayout(new GridLayout(8,1));
        rowHolderPanel.add(facilitiesHead);
        rowHolderPanel.add(sleepingFacilityPanel);
        rowHolderPanel.add(bathroomFacilityPanel);
        rowHolderPanel.add(kitchenFacilityPanel);
        rowHolderPanel.add(livingFacilityPanel);
        rowHolderPanel.add(outdoorsFacilityPanel);
        rowHolderPanel.add(utilityFacilityPanel);

        //displaying bedrooms require a different approach because it is possible to have multiple bedrooms
        JPanel bedroomsHead = new JPanel();
        JLabel bedroomsText = new JLabel("<HTML><U>Bedrooms</U><HTML>",SwingConstants.CENTER);
        bedroomsText.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bedroomsHead.add(bedroomsText);
        rowHolderPanel.add(bedroomsHead);
        ArrayList<JPanel> bedroomArrayPanel = new ArrayList<>();
        for (int i = 0; i < bedrooms.size(); i++) {
            bedroomArrayPanel.add(new JPanel());
            bedroomArrayPanel.get(i).add(new JLabel("Bedroom " + (i+1) + ": "));
            bedroomArrayPanel.get(i).add(new JLabel("Bed 1: "+bedrooms.get(i).getBedOneType()));
            bedroomArrayPanel.get(i).add(new JLabel("• Bed 2: "+bedrooms.get(i).getBedTwoType()));
            //at the end of the loop, add the bedroomarraypanels to rowholderpanel
            //not possible to do outside of loop because it gets garbage collected
            if (i == (bedrooms.size() - 1)) {
                //bedroomInfo.setLayout(new GridLayout(8,i + 1));
                for (int j = 0; j < bedroomArrayPanel.size(); j++) {
                    rowHolderPanel.add(bedroomArrayPanel.get(j));
                }
            }
        }


        //same approach goes for bathrooms
        JPanel bathroomHead = new JPanel();
        JLabel bathroomText = new JLabel("<HTML><U>Bathrooms</U><HTML>",SwingConstants.CENTER);
        bathroomText.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bathroomHead.add(bathroomText);
        rowHolderPanel.add(bathroomHead);
        ArrayList<JPanel> bathroomArrayPanel = new ArrayList<>();
        //at the end of the loop, add the bathroomarraypanels to rowholderpanel
        //not possible to do outside of loop because it gets garbage collected
        for (int i = 0; i < bathrooms.size(); i++) {
            bathroomArrayPanel.add(new JPanel());
            ArrayList<Integer> bathroomList = new ArrayList<Integer>(
                    Arrays.asList(bathrooms.get(i).getHasToilet(), bathrooms.get(i).getHasShower() ,
                            bathrooms.get(i).getHasBath(), bathrooms.get(i).getSharedWithHost())
            );
            ArrayList<String> converted = convert(bathroomList);

            bathroomArrayPanel.get(i).add(new JLabel("Bathroom " + (i+1) + ": "));
            bathroomArrayPanel.get(i).add(new JLabel("Toilet: "));
            bathroomArrayPanel.get(i).add(new JLabel(converted.get(0)));
            bathroomArrayPanel.get(i).add(new JLabel("•"));
            bathroomArrayPanel.get(i).add(new JLabel("Shower: "));
            bathroomArrayPanel.get(i).add(new JLabel(converted.get(1)));
            bathroomArrayPanel.get(i).add(new JLabel("•"));
            bathroomArrayPanel.get(i).add(new JLabel("Bath: "));
            bathroomArrayPanel.get(i).add(new JLabel(converted.get(2)));
            bathroomArrayPanel.get(i).add(new JLabel("•"));
            bathroomArrayPanel.get(i).add(new JLabel("Shared With Host: "));
            bathroomArrayPanel.get(i).add(new JLabel(converted.get(3)));

            if (i == (bathrooms.size() - 1)) {
                //bathroomInfo.setLayout(new GridLayout(8,i + 1));
                for (int j = 0; j < bathroomArrayPanel.size(); j++) {
                    rowHolderPanel.add(bathroomArrayPanel.get(j));
                }
            }
        }

        JPanel reviewHead = new JPanel();
        JLabel reviewText = new JLabel("<HTML><U>Reviews</U><HTML>",SwingConstants.CENTER);
        reviewText.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        reviewHead.add(reviewText);
        rowHolderPanel.add(reviewHead);

        //and same approach for reviews too because there are multiple reviews
        ArrayList<Reviews> allReviews = new ArrayList<>(property.getPropertyReviews());
        //JPanel reviewPanel = new JPanel();
        for(int i=0;i<allReviews.size();i++){
            Reviews reviews = allReviews.get(i);
            ReviewModel reviewModel = new ReviewModel(reviews.getReviewID());

            JPanel avgRating = new JPanel();
            avgRating.add(new JLabel("Overall Rating:"));
            avgRating.add(new JLabel(String.format("%.2f",reviewModel.calcOverallRating())));

            JPanel desc = new JPanel();
            desc.add(new JLabel("Comments:"));
            desc.add(new JLabel(reviews.getReviewDescription()));

            JPanel allRatings = new JPanel();
            allRatings.add(new JLabel("Cleanliness:"));
            allRatings.add(new JLabel(String.valueOf(reviews.getCleanliness())));
            allRatings.add(new JLabel("•"));
            allRatings.add(new JLabel("Communication:"));
            allRatings.add(new JLabel(String.valueOf(reviews.getCommunication())));
            allRatings.add(new JLabel("•"));
            allRatings.add(new JLabel("Check In:"));
            allRatings.add(new JLabel(String.valueOf(reviews.getCheckin())));
            allRatings.add(new JLabel("•"));
            allRatings.add(new JLabel("Accuracy:"));
            allRatings.add(new JLabel(String.valueOf(reviews.getAccuracy())));
            allRatings.add(new JLabel("•"));
            allRatings.add(new JLabel("Location:"));
            allRatings.add(new JLabel(String.valueOf(reviews.getLocation())));
            allRatings.add(new JLabel("•"));
            allRatings.add(new JLabel("Value:"));
            allRatings.add(new JLabel(String.valueOf(reviews.getValue())));
            rowHolderPanel.add(avgRating);
            rowHolderPanel.add(desc);
            rowHolderPanel.add(allRatings);
        }

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }

    //guests constructor
    public PropertyPanel(MainFrame frame, GuestsModel account, int id) {
        mainFrame = frame;
        guestAccount = account;
        propertyID = id;
        property = new PropertyModel(id);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(rowHolderPanel, BorderLayout.PAGE_START);
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        ArrayList<Integer> numBedsBathroomsBedroomsGuests = new ArrayList<>(property.getNumBedsBathroomsBedroomsGuests());

        JLabel propertyText = new JLabel("<HTML><U>Property Information</U><HTML>",SwingConstants.CENTER);
        propertyText.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        //declare panels for property's attributes
        JPanel namePanel = new JPanel();
        namePanel.add(new JLabel("Property Name:"));
        namePanel.add(new JLabel(property.getProperty().getPropertyName()));

        JPanel descPanel = new JPanel();
        descPanel.add(new JLabel("Property Description:"));
        descPanel.add(new JLabel(property.getProperty().getDescription()));

        JPanel addressPanel = new JPanel();
        addressPanel.add(new JLabel("General Location:"));
        addressPanel.add(new JLabel(property.getGeneralLocation()));

        JPanel numGuestsPanel = new JPanel();
        numGuestsPanel.add(new JLabel("Number of Guests:"));
        numGuestsPanel.add(new JLabel(String.valueOf(numBedsBathroomsBedroomsGuests.get(3))));

        JPanel numBedsPanel = new JPanel();
        numBedsPanel.add(new JLabel("Number of Beds:"));
        numBedsPanel.add(new JLabel(String.valueOf(numBedsBathroomsBedroomsGuests.get(0))));

        JPanel numBedroomsPanel = new JPanel();
        numBedroomsPanel.add(new JLabel("Number of Bedrooms:"));
        numBedroomsPanel.add(new JLabel(String.valueOf(numBedsBathroomsBedroomsGuests.get(2))));

        JPanel numBathPanel = new JPanel();
        numBathPanel.add(new JLabel("Number of Bathrooms:"));
        numBathPanel.add(new JLabel(String.valueOf(numBedsBathroomsBedroomsGuests.get(1))));

        JPanel ratingPanel = new JPanel();
        ratingPanel.add(new JLabel("Ratings:"));
        ratingPanel.add(new JLabel(String.format("%.2f",property.calcAvgRating())));

        String superHost ="";
        if(property.isSuperHost() == 0) superHost = "No";
        else superHost = "Yes";

        JPanel superHostPanel = new JPanel();
        superHostPanel.add(new JLabel("SuperHost:"));
        superHostPanel.add(new JLabel(superHost));


        rowHolderPanel.add(namePanel);
        rowHolderPanel.add(descPanel);
        rowHolderPanel.add(addressPanel);
        rowHolderPanel.add(numGuestsPanel);
        rowHolderPanel.add(numBedsPanel);
        rowHolderPanel.add(numBathPanel);
        rowHolderPanel.add(ratingPanel);
        rowHolderPanel.add(superHostPanel);


        //fetch individual facility data from DB, then display them in their individual panels
        ArrayList<String> sleepingFacilityData = new ArrayList<>(convert(property.getSleepingFacilityInfo()));
        ArrayList<String> bathroomFacilityData = new ArrayList<>(convert(property.getBathingFacilityInfo()));
        ArrayList<String> kitchenFacilityData = new ArrayList<>(convert(property.getKitchenFacilityInfo()));
        ArrayList<String> livingFacilityData = new ArrayList<>(convert(property.getLivingFacilityInfo()));
        ArrayList<String> outdoorsFacilityData = new ArrayList<>(convert(property.getOutdoorsFacilityInfo()));
        ArrayList<String> utilityFacilityData = new ArrayList<>(convert(property.getUtilityFacilityInfo()));
        ArrayList<Bathroom> bathrooms = new BathingFacilityModel
                (property.getBathingFacilityInfo().get(3)).getAttachedBathrooms();
        ArrayList<Bedroom> bedrooms = new SleepingFacilityModel
                (property.getSleepingFacilityInfo().get(2)).getAttachedBedrooms();

        JLabel sleepingFacilityText = new JLabel("<HTML><U>Sleeping Facility Amenities</U><HTML>",SwingConstants.CENTER);
        sleepingFacilityText.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        JLabel bathroomFacilityText = new JLabel("<HTML><U>Bathroom Facility Amenities</U><HTML>",SwingConstants.CENTER);
        bathroomFacilityText.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        JLabel kitchenFacilityText = new JLabel("<HTML><U>Kitchen Facility Amenities</U><HTML>",SwingConstants.CENTER);
        kitchenFacilityText.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        JLabel livingFacilityText = new JLabel("<HTML><U>Living Facility Amenities</U><HTML>",SwingConstants.CENTER);
        livingFacilityText.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        JLabel outdoorsFacilityText = new JLabel("<HTML><U>Outdoors Facility Amenities</U><HTML>",SwingConstants.CENTER);
        outdoorsFacilityText.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        JLabel utilityFacilityText = new JLabel("<HTML><U>Utility Facility Amenities</U><HTML>",SwingConstants.CENTER);
        utilityFacilityText.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JPanel facilitiesHead = new JPanel();
        JLabel facilitiesText = new JLabel("<HTML><U>Facilities</U><HTML>",SwingConstants.CENTER);
        facilitiesText.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        facilitiesHead.add(facilitiesText);
        JPanel facilitiesPanel = new JPanel();
        facilitiesPanel.setLayout(new GridLayout(8,1));
        rowHolderPanel.add(facilitiesHead);

        JPanel sleepingFacilityInfo = new JPanel();
        sleepingFacilityInfo.add(new JLabel("BedLinen: "));
        sleepingFacilityInfo.add(new JLabel(sleepingFacilityData.get(0)));
        sleepingFacilityInfo.add(new JLabel("•"));
        sleepingFacilityInfo.add(new JLabel("Towels: "));
        sleepingFacilityInfo.add(new JLabel(sleepingFacilityData.get(1)));
        sleepingFacilityInfo.add(new JLabel("•"));

        rowHolderPanel.add(sleepingFacilityText);
        rowHolderPanel.add(sleepingFacilityInfo);

        JPanel bathroomFacilityInfo = new JPanel();
        bathroomFacilityInfo.add(new JLabel("HairDryer: "));
        bathroomFacilityInfo.add(new JLabel(bathroomFacilityData.get(0)));
        bathroomFacilityInfo.add(new JLabel("•"));
        bathroomFacilityInfo.add(new JLabel("Shampoo: "));
        bathroomFacilityInfo.add(new JLabel(bathroomFacilityData.get(1)));
        bathroomFacilityInfo.add(new JLabel("•"));
        bathroomFacilityInfo.add(new JLabel("ToiletPaper: "));
        bathroomFacilityInfo.add(new JLabel(bathroomFacilityData.get(2)));

        rowHolderPanel.add(bathroomFacilityText);
        rowHolderPanel.add(bathroomFacilityInfo);

        JPanel kitchenFacilityInfo = new JPanel();
        kitchenFacilityInfo.add(new JLabel("Oven: "));
        kitchenFacilityInfo.add(new JLabel(kitchenFacilityData.get(0)));
        kitchenFacilityInfo.add(new JLabel("•"));
        kitchenFacilityInfo.add(new JLabel("Fridge: "));
        kitchenFacilityInfo.add(new JLabel(kitchenFacilityData.get(1)));
        kitchenFacilityInfo.add(new JLabel("•"));
        kitchenFacilityInfo.add(new JLabel("Microwave: "));
        kitchenFacilityInfo.add(new JLabel(kitchenFacilityData.get(2)));
        kitchenFacilityInfo.add(new JLabel("•"));
        kitchenFacilityInfo.add(new JLabel("Stove: "));
        kitchenFacilityInfo.add(new JLabel(kitchenFacilityData.get(3)));
        kitchenFacilityInfo.add(new JLabel("•"));
        kitchenFacilityInfo.add(new JLabel("Dish Washer: "));
        kitchenFacilityInfo.add(new JLabel(kitchenFacilityData.get(4)));
        kitchenFacilityInfo.add(new JLabel("•"));
        kitchenFacilityInfo.add(new JLabel("Tableware: "));
        kitchenFacilityInfo.add(new JLabel(kitchenFacilityData.get(5)));
        kitchenFacilityInfo.add(new JLabel("•"));
        kitchenFacilityInfo.add(new JLabel("Cookware: "));
        kitchenFacilityInfo.add(new JLabel(kitchenFacilityData.get(6)));
        kitchenFacilityInfo.add(new JLabel("•"));
        kitchenFacilityInfo.add(new JLabel("Basic Provisions: "));
        kitchenFacilityInfo.add(new JLabel(kitchenFacilityData.get(7)));

        rowHolderPanel.add(kitchenFacilityText);
        rowHolderPanel.add(kitchenFacilityInfo);

        JPanel livingFacilityInfo = new JPanel();
        livingFacilityInfo.add(new JLabel("Wifi: "));
        livingFacilityInfo.add(new JLabel(livingFacilityData.get(0)));
        livingFacilityInfo.add(new JLabel("•"));
        livingFacilityInfo.add(new JLabel("Television: "));
        livingFacilityInfo.add(new JLabel(livingFacilityData.get(1)));
        livingFacilityInfo.add(new JLabel("•"));
        livingFacilityInfo.add(new JLabel("Satellite: "));
        livingFacilityInfo.add(new JLabel(livingFacilityData.get(2)));
        livingFacilityInfo.add(new JLabel("•"));
        livingFacilityInfo.add(new JLabel("Streaming Service: "));
        livingFacilityInfo.add(new JLabel(livingFacilityData.get(3)));
        livingFacilityInfo.add(new JLabel("•"));
        livingFacilityInfo.add(new JLabel("DVD Player: "));
        livingFacilityInfo.add(new JLabel(livingFacilityData.get(4)));
        livingFacilityInfo.add(new JLabel("•"));
        livingFacilityInfo.add(new JLabel("Board Games: "));
        livingFacilityInfo.add(new JLabel(livingFacilityData.get(5)));

        rowHolderPanel.add(livingFacilityText);
        rowHolderPanel.add(livingFacilityInfo);

        JPanel outdoorsFacilityInfo = new JPanel();
        outdoorsFacilityInfo.add(new JLabel("Free OnSite Parking: "));
        outdoorsFacilityInfo.add(new JLabel(outdoorsFacilityData.get(0)));
        outdoorsFacilityInfo.add(new JLabel("•"));
        outdoorsFacilityInfo.add(new JLabel("Paid Car Park: "));
        outdoorsFacilityInfo.add(new JLabel(outdoorsFacilityData.get(1)));
        outdoorsFacilityInfo.add(new JLabel("•"));
        outdoorsFacilityInfo.add(new JLabel("Patio: "));
        outdoorsFacilityInfo.add(new JLabel(outdoorsFacilityData.get(2)));
        outdoorsFacilityInfo.add(new JLabel("•"));
        outdoorsFacilityInfo.add(new JLabel("Barbeque Facilities: "));
        outdoorsFacilityInfo.add(new JLabel(outdoorsFacilityData.get(3)));

        rowHolderPanel.add(outdoorsFacilityText);
        rowHolderPanel.add(outdoorsFacilityInfo);

        JPanel utilityFacilityInfo = new JPanel();
        utilityFacilityInfo.add(new JLabel("Heating: "));
        utilityFacilityInfo.add(new JLabel(utilityFacilityData.get(0)));
        utilityFacilityInfo.add(new JLabel("•"));
        utilityFacilityInfo.add(new JLabel("Washing Machine: "));
        utilityFacilityInfo.add(new JLabel(utilityFacilityData.get(1)));
        utilityFacilityInfo.add(new JLabel("•"));
        utilityFacilityInfo.add(new JLabel("Drying Machine: "));
        utilityFacilityInfo.add(new JLabel(utilityFacilityData.get(2)));
        utilityFacilityInfo.add(new JLabel("•"));
        utilityFacilityInfo.add(new JLabel("Fire Extinguisher: "));
        utilityFacilityInfo.add(new JLabel(utilityFacilityData.get(3)));
        utilityFacilityInfo.add(new JLabel("•"));
        utilityFacilityInfo.add(new JLabel("Smoke Alarm: "));
        utilityFacilityInfo.add(new JLabel(utilityFacilityData.get(4)));
        utilityFacilityInfo.add(new JLabel("•"));
        utilityFacilityInfo.add(new JLabel("First Aid Kit: "));
        utilityFacilityInfo.add(new JLabel(utilityFacilityData.get(5)));

        rowHolderPanel.add(utilityFacilityText);
        rowHolderPanel.add(utilityFacilityInfo);



        //booking section
        JButton submitBookingBtn = new JButton("Make booking");
        JPanel bookingsPanelBtn = new JPanel();
        JPanel bookingsPanelTF = new JPanel();
        JPanel bookingsPanel = new JPanel();
        JTextField startTF = new JTextField(10);
        JTextField endTF = new JTextField(10);
        bookingsPanel.add(new JLabel("Make your booking (enter dates in the format YYYY-MM-DD) • "));
        bookingsPanel.add(new JLabel(""));
        bookingsPanel.add(bookingsPanelTF);
        bookingsPanel.add(bookingsPanelBtn);
        bookingsPanelTF.add(new JLabel("Start Date:"));
        bookingsPanelTF.add(startTF);
        bookingsPanelTF.add(new JLabel("End Date:"));
        bookingsPanelTF.add(endTF);
        bookingsPanelTF.add(submitBookingBtn);
        rowHolderPanel.add(bookingsPanel);


        //bedrooms are displayed in a different way because there can be multiple bedrooms
        JPanel bedroomsHead = new JPanel();
        JLabel bedroomsText = new JLabel("<HTML><U>Bedrooms</U><HTML>",SwingConstants.CENTER);
        bedroomsText.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bedroomsHead.add(bedroomsText);
        rowHolderPanel.add(bedroomsHead);
        ArrayList<JPanel> bedroomArrayPanel = new ArrayList<>();
        for (int i = 0; i < bedrooms.size(); i++) {
            bedroomArrayPanel.add(new JPanel());
            bedroomArrayPanel.get(i).add(new JLabel("Bedroom " + (i+1) + ": "));
            bedroomArrayPanel.get(i).add(new JLabel("Bed 1: "+bedrooms.get(i).getBedOneType()));
            bedroomArrayPanel.get(i).add(new JLabel("• Bed 2: "+bedrooms.get(i).getBedTwoType()));

            if (i == (bedrooms.size() - 1)) {
                for (int j = 0; j < bedroomArrayPanel.size(); j++) {
                    rowHolderPanel.add(bedroomArrayPanel.get(j));
                }
            }
        }

        //similar to bedrooms
        JPanel bathroomHead = new JPanel();
        JLabel bathroomText = new JLabel("<HTML><U>Bathrooms</U><HTML>",SwingConstants.CENTER);
        bathroomText.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bathroomHead.add(bathroomText);
        rowHolderPanel.add(bathroomHead);
        ArrayList<JPanel> bathroomArrayPanel = new ArrayList<>();
        for (int i = 0; i < bathrooms.size(); i++) {
            bathroomArrayPanel.add(new JPanel());
            ArrayList<Integer> bathroomList = new ArrayList<Integer>(
                    Arrays.asList(bathrooms.get(i).getHasToilet(), bathrooms.get(i).getHasShower() ,
                            bathrooms.get(i).getHasBath(), bathrooms.get(i).getSharedWithHost())
            );
            ArrayList<String> converted = convert(bathroomList);

            bathroomArrayPanel.get(i).add(new JLabel("Bathroom " + (i+1) + ": "));
            bathroomArrayPanel.get(i).add(new JLabel("Toilet: "));
            bathroomArrayPanel.get(i).add(new JLabel(converted.get(0)));
            bathroomArrayPanel.get(i).add(new JLabel("•"));
            bathroomArrayPanel.get(i).add(new JLabel("Shower: "));
            bathroomArrayPanel.get(i).add(new JLabel(converted.get(1)));
            bathroomArrayPanel.get(i).add(new JLabel("•"));
            bathroomArrayPanel.get(i).add(new JLabel("Bath: "));
            bathroomArrayPanel.get(i).add(new JLabel(converted.get(2)));
            bathroomArrayPanel.get(i).add(new JLabel("•"));
            bathroomArrayPanel.get(i).add(new JLabel("Shared With Host: "));
            bathroomArrayPanel.get(i).add(new JLabel(converted.get(3)));

            if (i == (bathrooms.size() - 1)) {
                for (int j = 0; j < bathroomArrayPanel.size(); j++) {
                    rowHolderPanel.add(bathroomArrayPanel.get(j));
                }
            }
        }
        //Submits the information above when clicked on
        submitBookingBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String startDate = startTF.getText();
                String endDate = endTF.getText();

                //doesn't follow YYYY-MM-DD format
                if (!Validation.checkDateFormat(startDate) || !Validation.checkDateFormat(endDate)) {
                    JOptionPane.showMessageDialog(frame, "Invalid date format");
                }

                //invalid date e.g. 2022-02-31
                else if (!Validation.checkValidDate(startDate)|| !Validation.checkValidDate(startDate))
                {
                    JOptionPane.showMessageDialog(frame, "Invalid date entered");
                }
                //check if either dates are set before today
                else if (!Validation.checkDateStart(java.sql.Date.valueOf(startDate))||
                        !Validation.checkDateStart(java.sql.Date.valueOf(endDate))) {
                    JOptionPane.showMessageDialog(frame, "One or more dates are set before today");
                }

                //check if either dates are set after 2022
                else if (!Validation.checkDateEnd(java.sql.Date.valueOf(startDate)) ||
                        !Validation.checkDateEnd(java.sql.Date.valueOf(endDate))) {
                    JOptionPane.showMessageDialog(frame, "No dates can be set on 2023 or beyond");
                }

                //if everything clears
                else {
                    ArrayList<Property> confirmedPtys = property.getConfirmedPropertyInRange(
                            java.sql.Date.valueOf(startDate), java.sql.Date.valueOf(endDate));
                    int binaryConfirmed = 0;
                    //check if the list of confirmed properties include the current property
                    for (int i = 0; i < confirmedPtys.size(); i++) {
                        if (confirmedPtys.get(i).getPropertyID() == propertyID) {
                            binaryConfirmed += 1;
                            break;
                        }
                    }
                    //refuses if yes
                    if (binaryConfirmed == 1) {
                        JOptionPane.showMessageDialog(frame, "Your booking overlaps with a confirmed one. " +
                                "Try another period of stay.");
                    }
                    //otherwise calculate price and add booking to db
                    else {
                        double price = property.calculateCostOfStay
                                (java.sql.Date.valueOf(startDate), java.sql.Date.valueOf(endDate));
                        Bookings bkng = new Bookings(0, propertyID, guestAccount.getGuest().getGuestID(),
                                java.sql.Date.valueOf(startDate), java.sql.Date.valueOf(endDate), 0);
                        BookingsModel bkngMdl = new BookingsModel();
                        bkngMdl.setBookings(bkng);
                        bkngMdl.insertBookingsRow();
                        JOptionPane.showMessageDialog(frame, "Provisional booking made! " +
                                "Awaiting decision from host. \n The price of your stay is " +price);
                    }
                }


            }
        } );

        //reviews
        JPanel reviewPanel2 = new JPanel();
        reviewPanel2.setLayout(new GridLayout(5,1));
        JPanel reviewHead = new JPanel();
        JLabel reviewText = new JLabel("<HTML><U>Reviews</U><HTML>",SwingConstants.CENTER);
        reviewText.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        reviewHead.add(reviewText);
        rowHolderPanel.add(reviewHead);


        ArrayList<Reviews> allReviews = new ArrayList<>(property.getPropertyReviews());
        //iterate through all reviews and display their attributes
        for(int i=0;i<allReviews.size();i++){
            Reviews reviews = allReviews.get(i);
            ReviewModel reviewModel = new ReviewModel(reviews.getReviewID());

            JPanel avgRating = new JPanel();
            avgRating.add(new JLabel("Overall Rating:"));
            avgRating.add(new JLabel(String.format("%.2f",reviewModel.calcOverallRating())));

            JPanel desc = new JPanel();
            desc.add(new JLabel("Comments:"));
            desc.add(new JLabel(reviews.getReviewDescription()));

            JPanel allRatings = new JPanel();
            allRatings.add(new JLabel("Cleanliness:"));
            allRatings.add(new JLabel(String.valueOf(reviews.getCleanliness())));
            allRatings.add(new JLabel("•"));
            allRatings.add(new JLabel("Communication:"));
            allRatings.add(new JLabel(String.valueOf(reviews.getCommunication())));
            allRatings.add(new JLabel("•"));
            allRatings.add(new JLabel("Check In:"));
            allRatings.add(new JLabel(String.valueOf(reviews.getCheckin())));
            allRatings.add(new JLabel("•"));
            allRatings.add(new JLabel("Accuracy:"));
            allRatings.add(new JLabel(String.valueOf(reviews.getAccuracy())));
            allRatings.add(new JLabel("•"));
            allRatings.add(new JLabel("Location:"));
            allRatings.add(new JLabel(String.valueOf(reviews.getLocation())));
            allRatings.add(new JLabel("•"));
            allRatings.add(new JLabel("Value:"));
            allRatings.add(new JLabel(String.valueOf(reviews.getValue())));
            rowHolderPanel.add(avgRating);
            rowHolderPanel.add(desc);
            rowHolderPanel.add(allRatings);

        }

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }
    //hosts constructor, near identical to unregistered constructor
    public PropertyPanel(MainFrame frame, HostsModel account, int id) {
        mainFrame = frame;
        propertyID = id;
        hostAccount=account;
        property = new PropertyModel(id);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(rowHolderPanel, BorderLayout.PAGE_START);
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        ArrayList<Integer> numBedsBathroomsBedroomsGuests = new ArrayList<>(property.getNumBedsBathroomsBedroomsGuests());

        JLabel propertyText = new JLabel("<HTML><U>Property Information</U><HTML>",SwingConstants.CENTER);
        propertyText.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JPanel namePanel = new JPanel();
        namePanel.add(new JLabel("Property Name:"));
        namePanel.add(new JLabel(property.getProperty().getPropertyName()));

        JPanel descPanel = new JPanel();
        descPanel.add(new JLabel("Property Description:"));
        descPanel.add(new JLabel(property.getProperty().getDescription()));

        JPanel addressPanel = new JPanel();
        addressPanel.add(new JLabel("General Location:"));
        addressPanel.add(new JLabel(property.getGeneralLocation()));

        JPanel numGuestsPanel = new JPanel();
        numGuestsPanel.add(new JLabel("Number of Guests:"));
        numGuestsPanel.add(new JLabel(String.valueOf(numBedsBathroomsBedroomsGuests.get(3))));

        JPanel numBedsPanel = new JPanel();
        numBedsPanel.add(new JLabel("Number of Beds:"));
        numBedsPanel.add(new JLabel(String.valueOf(numBedsBathroomsBedroomsGuests.get(0))));

        JPanel numBedroomsPanel = new JPanel();
        numBedroomsPanel.add(new JLabel("Number of Bedrooms:"));
        numBedroomsPanel.add(new JLabel(String.valueOf(numBedsBathroomsBedroomsGuests.get(2))));

        JPanel numBathPanel = new JPanel();
        numBathPanel.add(new JLabel("Number of Bathrooms:"));
        numBathPanel.add(new JLabel(String.valueOf(numBedsBathroomsBedroomsGuests.get(1))));

        JPanel ratingPanel = new JPanel();
        ratingPanel.add(new JLabel("Ratings:"));
        ratingPanel.add(new JLabel(String.format("%.2f",property.calcAvgRating())));

        String superHost ="";
        if(property.isSuperHost() == 0) superHost = "No";
        else superHost = "Yes";

        JPanel superHostPanel = new JPanel();
        superHostPanel.add(new JLabel("SuperHost:"));
        superHostPanel.add(new JLabel(superHost));


        rowHolderPanel.add(namePanel);
        rowHolderPanel.add(descPanel);
        rowHolderPanel.add(addressPanel);
        rowHolderPanel.add(numGuestsPanel);
        rowHolderPanel.add(numBedsPanel);
        rowHolderPanel.add(numBathPanel);
        rowHolderPanel.add(ratingPanel);
        rowHolderPanel.add(superHostPanel);


        ArrayList<String> sleepingFacilityData = new ArrayList<>(convert(property.getSleepingFacilityInfo()));
        ArrayList<String> bathroomFacilityData = new ArrayList<>(convert(property.getBathingFacilityInfo()));
        ArrayList<String> kitchenFacilityData = new ArrayList<>(convert(property.getKitchenFacilityInfo()));
        ArrayList<String> livingFacilityData = new ArrayList<>(convert(property.getLivingFacilityInfo()));
        ArrayList<String> outdoorsFacilityData = new ArrayList<>(convert(property.getOutdoorsFacilityInfo()));
        ArrayList<String> utilityFacilityData = new ArrayList<>(convert(property.getUtilityFacilityInfo()));
        ArrayList<Bathroom> bathrooms = new BathingFacilityModel
                (property.getBathingFacilityInfo().get(3)).getAttachedBathrooms();
        ArrayList<Bedroom> bedrooms = new SleepingFacilityModel
                (property.getSleepingFacilityInfo().get(2)).getAttachedBedrooms();

        JLabel sleepingFacilityText = new JLabel("<HTML><U>Sleeping Facility Amenities</U><HTML>",SwingConstants.CENTER);
        sleepingFacilityText.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        JLabel bathroomFacilityText = new JLabel("<HTML><U>Bathroom Facility Amenities</U><HTML>",SwingConstants.CENTER);
        bathroomFacilityText.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        JLabel kitchenFacilityText = new JLabel("<HTML><U>Kitchen Facility Amenities</U><HTML>",SwingConstants.CENTER);
        kitchenFacilityText.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        JLabel livingFacilityText = new JLabel("<HTML><U>Living Facility Amenities</U><HTML>",SwingConstants.CENTER);
        livingFacilityText.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        JLabel outdoorsFacilityText = new JLabel("<HTML><U>Outdoors Facility Amenities</U><HTML>",SwingConstants.CENTER);
        outdoorsFacilityText.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        JLabel utilityFacilityText = new JLabel("<HTML><U>Utility Facility Amenities</U><HTML>",SwingConstants.CENTER);
        utilityFacilityText.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JPanel facilitiesHead = new JPanel();
        JLabel facilitiesText = new JLabel("<HTML><U>Facilities</U><HTML>",SwingConstants.CENTER);
        facilitiesText.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        facilitiesHead.add(facilitiesText);
        rowHolderPanel.add(facilitiesHead);

        JPanel sleepingFacilityInfo = new JPanel();
        sleepingFacilityInfo.add(new JLabel("BedLinen: "));
        sleepingFacilityInfo.add(new JLabel(sleepingFacilityData.get(0)));
        sleepingFacilityInfo.add(new JLabel("•"));
        sleepingFacilityInfo.add(new JLabel("Towels: "));
        sleepingFacilityInfo.add(new JLabel(sleepingFacilityData.get(1)));

        rowHolderPanel.add(sleepingFacilityText);
        rowHolderPanel.add(sleepingFacilityInfo);

        JPanel bathroomFacilityInfo = new JPanel();
        bathroomFacilityInfo.add(new JLabel("HairDryer: "));
        bathroomFacilityInfo.add(new JLabel(bathroomFacilityData.get(0)));
        bathroomFacilityInfo.add(new JLabel("•"));
        bathroomFacilityInfo.add(new JLabel("Shampoo: "));
        bathroomFacilityInfo.add(new JLabel(bathroomFacilityData.get(1)));
        bathroomFacilityInfo.add(new JLabel("•"));
        bathroomFacilityInfo.add(new JLabel("ToiletPaper: "));
        bathroomFacilityInfo.add(new JLabel(bathroomFacilityData.get(2)));

        rowHolderPanel.add(bathroomFacilityText);
        rowHolderPanel.add(bathroomFacilityInfo);

        JPanel kitchenFacilityInfo = new JPanel();
        kitchenFacilityInfo.add(new JLabel("Oven: "));
        kitchenFacilityInfo.add(new JLabel(kitchenFacilityData.get(0)));
        kitchenFacilityInfo.add(new JLabel("•"));
        kitchenFacilityInfo.add(new JLabel("Fridge: "));
        kitchenFacilityInfo.add(new JLabel(kitchenFacilityData.get(1)));
        kitchenFacilityInfo.add(new JLabel("•"));
        kitchenFacilityInfo.add(new JLabel("Microwave: "));
        kitchenFacilityInfo.add(new JLabel(kitchenFacilityData.get(2)));
        kitchenFacilityInfo.add(new JLabel("•"));
        kitchenFacilityInfo.add(new JLabel("Stove: "));
        kitchenFacilityInfo.add(new JLabel(kitchenFacilityData.get(3)));
        kitchenFacilityInfo.add(new JLabel("•"));
        kitchenFacilityInfo.add(new JLabel("Dish Washer: "));
        kitchenFacilityInfo.add(new JLabel(kitchenFacilityData.get(4)));
        kitchenFacilityInfo.add(new JLabel("•"));
        kitchenFacilityInfo.add(new JLabel("Tableware: "));
        kitchenFacilityInfo.add(new JLabel(kitchenFacilityData.get(5)));
        kitchenFacilityInfo.add(new JLabel("•"));
        kitchenFacilityInfo.add(new JLabel("Cookware: "));
        kitchenFacilityInfo.add(new JLabel(kitchenFacilityData.get(6)));
        kitchenFacilityInfo.add(new JLabel("•"));
        kitchenFacilityInfo.add(new JLabel("Basic Provisions: "));
        kitchenFacilityInfo.add(new JLabel(kitchenFacilityData.get(7)));

        rowHolderPanel.add(kitchenFacilityText);
        rowHolderPanel.add(kitchenFacilityInfo);

        JPanel livingFacilityInfo = new JPanel();
        livingFacilityInfo.add(new JLabel("Wifi: "));
        livingFacilityInfo.add(new JLabel(livingFacilityData.get(0)));
        livingFacilityInfo.add(new JLabel("•"));
        livingFacilityInfo.add(new JLabel("Television: "));
        livingFacilityInfo.add(new JLabel(livingFacilityData.get(1)));
        livingFacilityInfo.add(new JLabel("•"));
        livingFacilityInfo.add(new JLabel("Satellite: "));
        livingFacilityInfo.add(new JLabel(livingFacilityData.get(2)));
        livingFacilityInfo.add(new JLabel("•"));
        livingFacilityInfo.add(new JLabel("Streaming Service: "));
        livingFacilityInfo.add(new JLabel(livingFacilityData.get(3)));
        livingFacilityInfo.add(new JLabel("•"));
        livingFacilityInfo.add(new JLabel("DVD Player: "));
        livingFacilityInfo.add(new JLabel(livingFacilityData.get(4)));
        livingFacilityInfo.add(new JLabel("•"));
        livingFacilityInfo.add(new JLabel("Board Games: "));
        livingFacilityInfo.add(new JLabel(livingFacilityData.get(5)));

        rowHolderPanel.add(livingFacilityText);
        rowHolderPanel.add(livingFacilityInfo);

        JPanel outdoorsFacilityInfo = new JPanel();
        outdoorsFacilityInfo.add(new JLabel("Free OnSite Parking: "));
        outdoorsFacilityInfo.add(new JLabel(outdoorsFacilityData.get(0)));
        outdoorsFacilityInfo.add(new JLabel("•"));
        outdoorsFacilityInfo.add(new JLabel("Paid Car Park: "));
        outdoorsFacilityInfo.add(new JLabel(outdoorsFacilityData.get(1)));
        outdoorsFacilityInfo.add(new JLabel("•"));
        outdoorsFacilityInfo.add(new JLabel("Patio: "));
        outdoorsFacilityInfo.add(new JLabel(outdoorsFacilityData.get(2)));
        outdoorsFacilityInfo.add(new JLabel("•"));
        outdoorsFacilityInfo.add(new JLabel("Barbeque Facilities: "));
        outdoorsFacilityInfo.add(new JLabel(outdoorsFacilityData.get(3)));

        rowHolderPanel.add(outdoorsFacilityText);
        rowHolderPanel.add(outdoorsFacilityInfo);

        JPanel utilityFacilityInfo = new JPanel();
        utilityFacilityInfo.add(new JLabel("Heating: "));
        utilityFacilityInfo.add(new JLabel(utilityFacilityData.get(0)));
        utilityFacilityInfo.add(new JLabel("•"));
        utilityFacilityInfo.add(new JLabel("Washing Machine: "));
        utilityFacilityInfo.add(new JLabel(utilityFacilityData.get(1)));
        utilityFacilityInfo.add(new JLabel("•"));
        utilityFacilityInfo.add(new JLabel("Drying Machine: "));
        utilityFacilityInfo.add(new JLabel(utilityFacilityData.get(2)));
        utilityFacilityInfo.add(new JLabel("•"));
        utilityFacilityInfo.add(new JLabel("Fire Extinguisher: "));
        utilityFacilityInfo.add(new JLabel(utilityFacilityData.get(3)));
        utilityFacilityInfo.add(new JLabel("•"));
        utilityFacilityInfo.add(new JLabel("Smoke Alarm: "));
        utilityFacilityInfo.add(new JLabel(utilityFacilityData.get(4)));
        utilityFacilityInfo.add(new JLabel("•"));
        utilityFacilityInfo.add(new JLabel("First Aid Kit: "));
        utilityFacilityInfo.add(new JLabel(utilityFacilityData.get(5)));

        rowHolderPanel.add(utilityFacilityText);
        rowHolderPanel.add(utilityFacilityInfo);





        JPanel bedroomsHead = new JPanel();
        JLabel bedroomsText = new JLabel("<HTML><U>Bedrooms</U><HTML>",SwingConstants.CENTER);
        bedroomsText.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bedroomsHead.add(bedroomsText);
        rowHolderPanel.add(bedroomsHead);
        ArrayList<JPanel> bedroomArrayPanel = new ArrayList<>();
        for (int i = 0; i < bedrooms.size(); i++) {
            bedroomArrayPanel.add(new JPanel());
            bedroomArrayPanel.get(i).add(new JLabel("Bedroom " + (i+1) + ": "));
            bedroomArrayPanel.get(i).add(new JLabel("Bed 1: "+bedrooms.get(i).getBedOneType()));
            bedroomArrayPanel.get(i).add(new JLabel("• Bed 2: "+bedrooms.get(i).getBedTwoType()));

            if (i == (bedrooms.size() - 1)) {
                for (int j = 0; j < bedroomArrayPanel.size(); j++) {
                    rowHolderPanel.add(bedroomArrayPanel.get(j));
                }
            }
        }


        JPanel bathroomHead = new JPanel();
        JLabel bathroomText = new JLabel("<HTML><U>Bathrooms</U><HTML>",SwingConstants.CENTER);
        bathroomText.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bathroomHead.add(bathroomText);
        rowHolderPanel.add(bathroomHead);
        ArrayList<JPanel> bathroomArrayPanel = new ArrayList<>();
        for (int i = 0; i < bathrooms.size(); i++) {
            bathroomArrayPanel.add(new JPanel());
            ArrayList<Integer> bathroomList = new ArrayList<Integer>(
                    Arrays.asList(bathrooms.get(i).getHasToilet(), bathrooms.get(i).getHasShower() ,
                            bathrooms.get(i).getHasBath(), bathrooms.get(i).getSharedWithHost())
            );
            ArrayList<String> converted = convert(bathroomList);

            bathroomArrayPanel.get(i).add(new JLabel("Bathroom " + (i+1) + ": "));
            bathroomArrayPanel.get(i).add(new JLabel("Toilet: "));
            bathroomArrayPanel.get(i).add(new JLabel(converted.get(0)));
            bathroomArrayPanel.get(i).add(new JLabel("•"));
            bathroomArrayPanel.get(i).add(new JLabel("Shower: "));
            bathroomArrayPanel.get(i).add(new JLabel(converted.get(1)));
            bathroomArrayPanel.get(i).add(new JLabel("•"));
            bathroomArrayPanel.get(i).add(new JLabel("Bath: "));
            bathroomArrayPanel.get(i).add(new JLabel(converted.get(2)));
            bathroomArrayPanel.get(i).add(new JLabel("•"));
            bathroomArrayPanel.get(i).add(new JLabel("Shared With Host: "));
            bathroomArrayPanel.get(i).add(new JLabel(converted.get(3)));

            if (i == (bathrooms.size() - 1)) {
                for (int j = 0; j < bathroomArrayPanel.size(); j++) {
                    rowHolderPanel.add(bathroomArrayPanel.get(j));
                }
            }
        }


        JPanel reviewHead = new JPanel();
        JLabel reviewText = new JLabel("<HTML><U>Reviews</U><HTML>",SwingConstants.CENTER);
        reviewText.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        reviewHead.add(reviewText);
        rowHolderPanel.add(reviewHead);

        ArrayList<Reviews> allReviews = new ArrayList<>(property.getPropertyReviews());
        for(int i=0;i<allReviews.size();i++){
            Reviews reviews = allReviews.get(i);
            ReviewModel reviewModel = new ReviewModel(reviews.getReviewID());

            JPanel avgRating = new JPanel();
            avgRating.add(new JLabel("Overall Rating:"));
            avgRating.add(new JLabel(String.valueOf(reviewModel.calcOverallRating())));

            JPanel desc = new JPanel();
            desc.add(new JLabel("Comments:"));
            desc.add(new JLabel(reviews.getReviewDescription()));

            JPanel allRatings = new JPanel();
            allRatings.add(new JLabel("Cleanliness:"));
            allRatings.add(new JLabel(String.valueOf(reviews.getCleanliness())));
            allRatings.add(new JLabel("•"));
            allRatings.add(new JLabel("Communication:"));
            allRatings.add(new JLabel(String.valueOf(reviews.getCommunication())));
            allRatings.add(new JLabel("•"));
            allRatings.add(new JLabel("Check In:"));
            allRatings.add(new JLabel(String.valueOf(reviews.getCheckin())));
            allRatings.add(new JLabel("•"));
            allRatings.add(new JLabel("Accuracy:"));
            allRatings.add(new JLabel(String.valueOf(reviews.getAccuracy())));
            allRatings.add(new JLabel("•"));
            allRatings.add(new JLabel("Location:"));
            allRatings.add(new JLabel(String.valueOf(reviews.getLocation())));
            allRatings.add(new JLabel("•"));
            allRatings.add(new JLabel("Value:"));
            allRatings.add(new JLabel(String.valueOf(reviews.getValue())));
            System.out.println(reviews.getValue());
            rowHolderPanel.add(avgRating);
            rowHolderPanel.add(desc);
            rowHolderPanel.add(allRatings);
        }

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }

    public static ArrayList<String> convert(ArrayList<Integer> list){
        ArrayList<String> bool = new ArrayList<>();

        for(int i=0;i<list.size();i++) {
            if(list.get(i)==1) bool.add("Provided");
            else bool.add("Not Provided");
        }
        return bool;
    }
}
