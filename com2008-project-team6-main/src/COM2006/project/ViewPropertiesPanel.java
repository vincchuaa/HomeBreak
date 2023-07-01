package COM2006.project;

import COM2006.project.tables.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ViewPropertiesPanel extends JPanel{
    private MainFrame mainFrame;
    private GuestsModel guestAccount;
    private JPanel rowHolderPanel = new JPanel(new GridLayout(0,1,1,1));
    private JPanel rowHolderPanel2 = new JPanel(new GridLayout(0,1,1,1));

    public ViewPropertiesPanel(MainFrame frame){
        mainFrame=frame;

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(rowHolderPanel, BorderLayout.PAGE_START);
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JPanel panel2 = new JPanel();
        panel2.add(rowHolderPanel2);
        JScrollPane scrollPane2 = new JScrollPane(panel2);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //Creating fields for user to enter filtering information
        JPanel filterPanel = new JPanel();
        JLabel cityLabel = new JLabel("Enter the city you want to stay in: ");
        JTextField cityTextField = new JTextField(30);
        JPanel datePanel = new JPanel();
        JTextField startTF = new JTextField(10);
        JTextField endTF = new JTextField(10);
        JButton cityFilterButton = new JButton("Submit");

        //Adding fields to panels
        filterPanel.add(cityLabel);
        filterPanel.add(cityTextField);
        filterPanel.add(datePanel);
        datePanel.add(new JLabel("Period of stay: (YYYY-MM-DD)"));
        datePanel.add(new JLabel("Start Date:"));
        datePanel.add(startTF);
        datePanel.add(new JLabel("End Date:"));
        datePanel.add(endTF);
        filterPanel.add(cityFilterButton);
        rowHolderPanel2.add(filterPanel);

        //Adding scrollPane and filterPanel to mainframe
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(scrollPane2, BorderLayout.PAGE_START);

        cityFilterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rowHolderPanel.removeAll();
                String cityInput = cityTextField.getText();
                String startDate = startTF.getText();
                String endDate = endTF.getText();
                PropertyModel ptyMdl = new PropertyModel();
                //Gets all properties matching city
                ArrayList<Property> filteredProperties = new ArrayList<Property>(ptyMdl.getMatchingProperty(cityInput));
                if (filteredProperties.size() == 0) {
                    JLabel noPropertiesLabel = new JLabel("There are no properties in that city");
                    JPanel noPropertiesPanel = new JPanel();
                    noPropertiesPanel.add(noPropertiesLabel);
                    rowHolderPanel.add(noPropertiesPanel);
                    revalidate();
                    repaint();
                }
                //validation for inputted dates
                else if (!Validation.checkDateFormat(startDate) || !Validation.checkDateFormat(endDate)) {
                    JOptionPane.showMessageDialog(frame, "Invalid date format");
                }

                else if (!Validation.checkValidDate(startDate)|| !Validation.checkValidDate(startDate))
                {
                    JOptionPane.showMessageDialog(frame, "Invalid date entered");
                }
                else if (!Validation.checkDateStart(java.sql.Date.valueOf(startDate))||
                        !Validation.checkDateStart(java.sql.Date.valueOf(endDate))) {
                    JOptionPane.showMessageDialog(frame, "One or more dates are set before today");
                }
                else if (!Validation.checkDateEnd(java.sql.Date.valueOf(startDate)) ||
                        !Validation.checkDateEnd(java.sql.Date.valueOf(endDate))) {
                    JOptionPane.showMessageDialog(frame, "No dates can be set on 2023 or beyond");
                }

                else {
                    Boolean[] isPtyAvailable = ptyMdl.getPropertyAvailability(cityInput, java.sql.Date.valueOf(startDate),
                            java.sql.Date.valueOf(endDate));
                    for (int i = 0; i < filteredProperties.size(); i++) {
                        Property property1 = filteredProperties.get(i);

                        PropertyModel property = new PropertyModel(property1.getPropertyID());

                        //Getting info about property
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

                        ArrayList<Reviews> propertyReviews = new ArrayList<>(property.getPropertyReviews());

                        //Calculating average review for property
                        Double score = 0.0;
                        Double totalScore = 0.0;
                        for (int n = 0; n < propertyReviews.size(); n++) {
                            score = (propertyReviews.get(n).getAccuracy() + propertyReviews.get(n).getCheckin() + propertyReviews.get(n).getCleanliness() + propertyReviews.get(n).getCommunication() + propertyReviews.get(n).getLocation() + propertyReviews.get(n).getValue()) / (6.0);
                            totalScore += score;
                        }
                        if (propertyReviews.size() > 0) {
                            totalScore = (totalScore) / (propertyReviews.size());
                        }

                        BathingFacilityModel bathingFacility = new BathingFacilityModel(bathingFacilitiesID);
                        SleepingFacilityModel sleepingFacility = new SleepingFacilityModel(sleepingFacilitiesID);

                        ArrayList<Bathroom> bathrooms = new ArrayList<Bathroom>(bathingFacility.getAttachedBathrooms());
                        ArrayList<Bedroom> bedrooms = new ArrayList<Bedroom>(sleepingFacility.getAttachedBedrooms());

                        int numOfBathrooms = bathrooms.size();
                        int numOfBedrooms = bedrooms.size();
                        int numberOfGuests = 0;
                        int numberOfBeds = 0;

                        //Calculating number of guests and beds for a property
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
                        JLabel isAvailableLabel = new JLabel();
                        if (isPtyAvailable[i] == true) {
                            isAvailableLabel.setText("AVAILABLE - ");
                        }
                        else {
                            isAvailableLabel.setText("UNAVAILABLE - ");
                        }


                        //Creating labels containing property and host info
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

                        JButton viewPropertyDetailsButton = new JButton("View Property");

                        //Adding labels to panel
                        JPanel propertyPanel = new JPanel();
                        propertyPanel.add(isAvailableLabel);
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

                        //Adding panel to rowHolder
                        rowHolderPanel.add(propertyPanel);

                        //Action listener that opens a properties individual page when button clicked
                        viewPropertyDetailsButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                Container contentPane = mainFrame.getContentPane();
                                revalidate();
                                repaint();
                                NavigationBarPanel navigationBarPanel = new NavigationBarPanel(mainFrame);
                                PropertyPanel propertyPanel = new PropertyPanel(mainFrame, property.getProperty().getPropertyID());
                                contentPane.removeAll();
                                contentPane.add(navigationBarPanel, BorderLayout.NORTH);
                                contentPane.add(propertyPanel);
                                revalidate();
                                repaint();
                            }
                        });
                    }
                    revalidate();
                    repaint();
                }
            }
        });
    }

    public ViewPropertiesPanel(MainFrame frame, GuestsModel guest) {
        mainFrame = frame;
        guestAccount = guest;

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(rowHolderPanel, BorderLayout.PAGE_START);
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JPanel panel2 = new JPanel();
        panel2.add(rowHolderPanel2);
        JScrollPane scrollPane2 = new JScrollPane(panel2);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //Creating fields for user to enter filtering information
        JPanel filterPanel = new JPanel();
        JLabel cityLabel = new JLabel("Enter the city you want to stay in: ");
        JTextField cityTextField = new JTextField(30);
        JPanel datePanel = new JPanel();
        JTextField startTF = new JTextField(10);
        JTextField endTF = new JTextField(10);
        JButton cityFilterButton = new JButton("Submit");

        //Adding fields to panels
        filterPanel.add(cityLabel);
        filterPanel.add(cityTextField);
        filterPanel.add(datePanel);
        datePanel.add(new JLabel("Period of stay: (YYYY-MM-DD)"));
        datePanel.add(new JLabel("Start Date:"));
        datePanel.add(startTF);
        datePanel.add(new JLabel("End Date:"));
        datePanel.add(endTF);
        filterPanel.add(cityFilterButton);
        rowHolderPanel2.add(filterPanel);

        //Adding scrollPane and filterPanel to mainframe
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(scrollPane2, BorderLayout.PAGE_START);

        cityFilterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rowHolderPanel.removeAll();

                String cityInput = cityTextField.getText();
                String startDate = startTF.getText();
                String endDate = endTF.getText();
                PropertyModel ptyMdl = new PropertyModel();
                //Gets all properties matching city
                ArrayList<Property> filteredProperties = new ArrayList<Property>(ptyMdl.getMatchingProperty(cityInput));
                if (filteredProperties.size() == 0) {
                    JLabel noPropertiesLabel = new JLabel("There are no properties in that city");
                    JPanel noPropertiesPanel = new JPanel();
                    noPropertiesPanel.add(noPropertiesLabel);
                    rowHolderPanel.add(noPropertiesPanel);
                    revalidate();
                    repaint();
                }
                //Validation for inputted dates
                else if (!Validation.checkDateFormat(startDate) || !Validation.checkDateFormat(endDate)) {
                        JOptionPane.showMessageDialog(frame, "Invalid date format");
                    }

                else if (!Validation.checkValidDate(startDate)|| !Validation.checkValidDate(startDate))
                {
                    JOptionPane.showMessageDialog(frame, "Invalid date entered");
                }
                else if (!Validation.checkDateStart(java.sql.Date.valueOf(startDate))||
                        !Validation.checkDateStart(java.sql.Date.valueOf(endDate))) {
                    JOptionPane.showMessageDialog(frame, "One or more dates are set before today");
                }
                else if (!Validation.checkDateEnd(java.sql.Date.valueOf(startDate)) ||
                        !Validation.checkDateEnd(java.sql.Date.valueOf(endDate))) {
                    JOptionPane.showMessageDialog(frame, "No dates can be set on 2023 or beyond");
                }

                else {
                    Boolean[] isPtyAvailable = ptyMdl.getPropertyAvailability(cityInput, java.sql.Date.valueOf(startDate),
                            java.sql.Date.valueOf(endDate));
                    for (int i = 0; i < filteredProperties.size(); i++) {
                        Property property1 = filteredProperties.get(i);

                        PropertyModel property = new PropertyModel(property1.getPropertyID());

                        //Getting property info
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

                        //Getting host info
                        int isSuperHost = new HostsModel(hostID).getHost().getIsSuperHost();
                        String superHost = "";
                        if (isSuperHost == 1) {
                            superHost = "Yes";
                        } else {
                            superHost = "No";
                        }
                        String username = new HostsModel(hostID).getHost().getUsername();

                        ArrayList<Reviews> propertyReviews = new ArrayList<>(property.getPropertyReviews());

                        //Calculating average review score for a property
                        Double score = 0.0;
                        Double totalScore = 0.0;
                        for (int n = 0; n < propertyReviews.size(); n++) {
                            score = (propertyReviews.get(n).getAccuracy() + propertyReviews.get(n).getCheckin() + propertyReviews.get(n).getCleanliness() + propertyReviews.get(n).getCommunication() + propertyReviews.get(n).getLocation() + propertyReviews.get(n).getValue()) / (6.0);
                            totalScore += score;
                        }
                        if (propertyReviews.size() > 0) {
                            totalScore = (totalScore) / (propertyReviews.size());
                        }

                        BathingFacilityModel bathingFacility = new BathingFacilityModel(bathingFacilitiesID);
                        SleepingFacilityModel sleepingFacility = new SleepingFacilityModel(sleepingFacilitiesID);

                        ArrayList<Bathroom> bathrooms = new ArrayList<Bathroom>(bathingFacility.getAttachedBathrooms());
                        ArrayList<Bedroom> bedrooms = new ArrayList<Bedroom>(sleepingFacility.getAttachedBedrooms());

                        int numOfBathrooms = bathrooms.size();
                        int numOfBedrooms = bedrooms.size();
                        int numberOfGuests = 0;
                        int numberOfBeds = 0;

                        //Calculating number of guests and beds for a property
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
                        JLabel isAvailableLabel = new JLabel();
                        if (isPtyAvailable[i] == true) {
                            isAvailableLabel.setText("AVAILABLE - ");
                        }
                        else {
                            isAvailableLabel.setText("UNAVAILABLE - ");
                        }


                        //Creating labels containing property and host info
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

                        JButton viewPropertyDetailsButton = new JButton("View Property");

                        //Adding labels to panel
                        JPanel propertyPanel = new JPanel();
                        propertyPanel.add(isAvailableLabel);
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

                        rowHolderPanel.add(propertyPanel);
                        //Action listener that opens a properties individual page when button clicked
                        viewPropertyDetailsButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                Container contentPane = mainFrame.getContentPane();
                                revalidate();
                                repaint();
                                NavigationBarPanel navigationBarPanel = new NavigationBarPanel(mainFrame, guestAccount);
                                PropertyPanel propertyPanel = new PropertyPanel(mainFrame, guestAccount, property.getProperty().getPropertyID());
                                contentPane.removeAll();
                                contentPane.add(navigationBarPanel, BorderLayout.NORTH);
                                contentPane.add(propertyPanel);
                                revalidate();
                                repaint();
                            }
                        });
                    }
                    revalidate();
                    repaint();
                }
            }
        });
    }
}
