package COM2006.project;

import COM2006.project.tables.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.*;


public class HostRegisteredPropertiesPanel extends JPanel{
    private MainFrame mainFrame;
    private HostsModel hostAccount;
    private JPanel rowHolderPanel = new JPanel(new GridLayout(0,1,1,1));

    public HostRegisteredPropertiesPanel(MainFrame frame, HostsModel account){
        mainFrame=frame;
        hostAccount=account;

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(rowHolderPanel, BorderLayout.PAGE_START);
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        rowHolderPanel.removeAll();

        //Getting a list of all properties a host owns
        ArrayList<Property> filteredProperties = new ArrayList<Property>(hostAccount.getOwnedProperties());

        //If host owns no properties add text saying that the host has no registered properties
        if (filteredProperties.size() == 0) {
            JLabel noPropertiesLabel = new JLabel("You have no registered properties");
            JPanel noPropertiesPanel = new JPanel();
            noPropertiesPanel.add(noPropertiesLabel);
            rowHolderPanel.add(noPropertiesPanel);
            revalidate();
            repaint();
        }
        else {
            //Looping through all properties the host owns
            for (int i = 0; i < filteredProperties.size(); i++) {
                Property property1 = filteredProperties.get(i);

                //Getting ith property in list
                PropertyModel property = new PropertyModel(property1.getPropertyID());

                //Getting information about the property
                String propertyName = property.getProperty().getPropertyName();
                String propertyDescription = property.getProperty().getDescription();
                String propertyGeneralLocation = property.getGeneralLocation();
                int hostID = property.getProperty().getHostID_fk();
                int propertyFacilityID = property.getProperty().getFacilityID_fk();
                FacilitiesModel propertyFacilities = new FacilitiesModel(propertyFacilityID);
                int bathingFacilitiesID = propertyFacilities.getFacilities().getBathingFacilityID();
                int sleepingFacilitiesID = propertyFacilities.getFacilities().getSleepingFacilityID();
                int propertyHasBreakfast = property.getProperty().getHasBreakfast();
                String propertyHasBreakfastString = "";
                if (propertyHasBreakfast == 1) {
                    propertyHasBreakfastString = "Yes";
                } else {
                    propertyHasBreakfastString = "No";
                }

                //Getting info about host
                int isSuperHost = new HostsModel(hostID).getHost().getIsSuperHost();
                String superHost = "";
                if (isSuperHost == 1) {
                    superHost = "Yes";
                } else {
                    superHost = "No";
                }
                String username = new HostsModel(hostID).getHost().getUsername();

                //Getting all reviews for the property
                ArrayList<Reviews> propertyReviews = new ArrayList<>(property.getPropertyReviews());

                //Calculating the average review score
                Double score = 0.0;
                Double totalScore = 0.0;
                for (int n = 0; n < propertyReviews.size(); n++) {
                    score = (propertyReviews.get(n).getAccuracy() + propertyReviews.get(n).getCheckin() + propertyReviews.get(n).getCleanliness() + propertyReviews.get(n).getCommunication() + propertyReviews.get(n).getLocation() + propertyReviews.get(n).getValue()) / (6.0);
                    totalScore += score;
                }
                if (propertyReviews.size() > 0) {
                    totalScore = (totalScore) / (propertyReviews.size());
                }

                //Getting sleeping and bathing facility for property
                BathingFacilityModel bathingFacility = new BathingFacilityModel(bathingFacilitiesID);
                SleepingFacilityModel sleepingFacility = new SleepingFacilityModel(sleepingFacilitiesID);

                //Getting all bedrooms and bathrooms a property has
                ArrayList<Bathroom> bathrooms = new ArrayList<Bathroom>(bathingFacility.getAttachedBathrooms());
                ArrayList<Bedroom> bedrooms = new ArrayList<Bedroom>(sleepingFacility.getAttachedBedrooms());

                int numOfBathrooms = bathrooms.size();
                int numOfBedrooms = bedrooms.size();
                int numberOfGuests = 0;
                int numberOfBeds = 0;
                //Calculating the number of guests and beds a property has
                for (int x = 0; x < numOfBedrooms; x++) {
                    String bed1Type = bedrooms.get(x).getBedOneType().toLowerCase(Locale.ROOT);
                    if (bed1Type.equals("single")) {
                        numberOfGuests += 1;
                        numberOfBeds += 1;
                    } else if (bed1Type.equals("double") || bed1Type.equals("bunk") ||
                            bed1Type.equals("kingsize")) {
                        numberOfGuests += 2;
                        numberOfBeds += 1;
                    }
                    String bed2Type = bedrooms.get(x).getBedTwoType().toLowerCase(Locale.ROOT);
                    if (bed2Type.equals("single")) {
                        numberOfGuests += 1;
                        numberOfBeds += 1;
                    } else if (bed2Type.equals("double") || bed2Type.equals("bunk") ||
                            bed2Type.equals("kingsize")) {
                        numberOfGuests += 2;
                        numberOfBeds += 1;
                    }
                }

                //Creating labels for information to be shown for property
                JLabel propertyNameLabel = new JLabel("Property name: ");
                JLabel propertyNameValueLabel = new JLabel(propertyName);
                JLabel propertyDescriptionLabel = new JLabel("Property description: ");
                JLabel propertyDescriptionValueLabel = new JLabel(propertyDescription);
                JLabel propertyGeneralLocationLabel = new JLabel("General Location: ");
                JLabel propertyGeneralLocationValueLabel = new JLabel(propertyGeneralLocation);
                JLabel propertyHasBreakfastLabel = new JLabel("Breakfast offered: ");
                JLabel propertyHasBreakfastValueLabel = new JLabel(propertyHasBreakfastString);
                JLabel propertyRatingLabel = new JLabel("Property rating: ");
                JLabel propertyRatingValueLabel = new JLabel(String.format("%.2f",totalScore));
                JLabel propertyNumberOfGuestsLabel = new JLabel("Number of guests: ");
                JLabel propertyNumberOfGuestsValueLabel = new JLabel(String.valueOf(numberOfGuests));
                JLabel propertyNumberOfBedroomsLabel = new JLabel("Number of beds");
                JLabel propertyNumberOfBedroomsValueLabel = new JLabel(String.valueOf(numberOfBeds));
                JLabel propertyNumberOfBathroomsLabel = new JLabel("Number of bathrooms");
                JLabel propertyNumberOfBathroomsValueLabel = new JLabel(String.valueOf(numOfBathrooms));
                JLabel hostSuperHostLabel = new JLabel("SuperHost: ");
                JLabel hostSuperHostValueLabel = new JLabel(superHost);
                JLabel hostUsernameLabel = new JLabel("Host name: ");
                JLabel hostUsernameValueLabel = new JLabel(username);

                //Creating button to view property in more detail
                JButton viewPropertyDetailsButton = new JButton("View Property");

                //Adding all property info to a JPanel
                JPanel propertyPanel = new JPanel();
                propertyPanel.add(hostUsernameLabel);
                propertyPanel.add(hostUsernameValueLabel);
                propertyPanel.add(new JLabel("•"));
                propertyPanel.add(hostSuperHostLabel);
                propertyPanel.add(hostSuperHostValueLabel);
                propertyPanel.add(new JLabel("•"));
                propertyPanel.add(propertyNameLabel);
                propertyPanel.add(propertyNameValueLabel);
                propertyPanel.add(new JLabel("•"));
                propertyPanel.add(propertyDescriptionLabel);
                propertyPanel.add(propertyDescriptionValueLabel);
                propertyPanel.add(new JLabel("•"));
                propertyPanel.add(propertyGeneralLocationLabel);
                propertyPanel.add(propertyGeneralLocationValueLabel);
                propertyPanel.add(new JLabel("•"));
                propertyPanel.add(propertyHasBreakfastLabel);
                propertyPanel.add(propertyHasBreakfastValueLabel);
                propertyPanel.add(new JLabel("•"));
                propertyPanel.add(propertyRatingLabel);
                propertyPanel.add(propertyRatingValueLabel);
                propertyPanel.add(new JLabel("•"));
                propertyPanel.add(propertyNumberOfGuestsLabel);
                propertyPanel.add(propertyNumberOfGuestsValueLabel);
                propertyPanel.add(new JLabel("•"));
                propertyPanel.add(propertyNumberOfBedroomsLabel);
                propertyPanel.add(propertyNumberOfBedroomsValueLabel);
                propertyPanel.add(new JLabel("•"));
                propertyPanel.add(propertyNumberOfBathroomsLabel);
                propertyPanel.add(propertyNumberOfBathroomsValueLabel);
                propertyPanel.add(new JLabel("•"));
                propertyPanel.add(viewPropertyDetailsButton);

                //Adding JPanel to view
                rowHolderPanel.add(propertyPanel);

                //Action listener to open detailed view of property when button pressed
                viewPropertyDetailsButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Container contentPane = mainFrame.getContentPane();
                        revalidate();
                        repaint();
                        NavigationBarPanel navigationBarPanel = new NavigationBarPanel(mainFrame,hostAccount);
                        PropertyPanel propertyPanel = new PropertyPanel(mainFrame, hostAccount, property.getProperty().getPropertyID());
                        contentPane.removeAll();
                        contentPane.add(navigationBarPanel, BorderLayout.NORTH);
                        contentPane.add(propertyPanel);
                        revalidate();
                        repaint();
                    }
                });
            }
        }
        //Creating button to allow host to register new property
        JButton addPropertyButton = new JButton("Add property");
        JPanel addPropertyPanel = new JPanel();
        addPropertyPanel.add(addPropertyButton);

        //Actionlistener that opens HostAddPropertiesPanel when pressed
        addPropertyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Container contentPane = mainFrame.getContentPane();
                revalidate();
                repaint();
                NavigationBarPanel navigationBarPanel = new NavigationBarPanel(mainFrame,hostAccount);
                HostAddPropertiesPanel hostAddPropertiesPanel = new HostAddPropertiesPanel(mainFrame, hostAccount);
                contentPane.removeAll();
                contentPane.add(navigationBarPanel, BorderLayout.NORTH);
                contentPane.add(hostAddPropertiesPanel);
                revalidate();
                repaint();
            }
        });

        //Adding scrollPane & addPropertyPanel to the MainFrame
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(addPropertyPanel,BorderLayout.PAGE_START);
    }
}
