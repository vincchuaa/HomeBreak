package COM2006.project;

import COM2006.project.tables.*;
import COM2006.project.tables.HostsModel;
import COM2006.project.tables.Validation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class HostAddPropertiesPanel extends JPanel{

    private MainFrame mainFrame;
    private HostsModel hostAccount;

    //stores all the panels that hold the labels, text fields and checkboxes
    private JPanel rowHolderPanel = new JPanel(new GridLayout(0,1,1,1));

    //Used for adding new bedroom and bathroom checkboxes to the panel
    private int numberOfBedrooms=0;
    private int numberOfBathrooms=0;
    private int bathroomIndex = 20;
    private int  bedroomIndex = 31;

    //Arrays that will hold the values that are stored in the checkboxes and comboboxes
    private ArrayList<JCheckBox> bathroomValues = new ArrayList<JCheckBox>();
    private ArrayList<JComboBox> bedroomValues = new ArrayList<JComboBox>();

    public HostAddPropertiesPanel(MainFrame frame, HostsModel account) {
        mainFrame = frame;
        hostAccount = account;

        //added scrolling for the values that do not fit on a single page
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(rowHolderPanel, BorderLayout.PAGE_START);
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);


        //Property Information Labels
        JLabel propertyMsg = new JLabel("Property Information");
        JLabel propertyNameLabel = new JLabel("Property Name");
        JLabel descriptionLabel = new JLabel("Description");

        //ChargeBand Labels
        JLabel chargeBandMsg = new JLabel("Charge Bands");
        JLabel octDec2021Label = new JLabel("Price per night from October to December 2021");

        JLabel janMar2022Label = new JLabel("Price per night from January to March 2022");
        JLabel aprJun2022Label = new JLabel("Price per night from April to June 2022");
        JLabel julSept2022Label = new JLabel("Price per night from July to September 2022");
        JLabel octDec2022Label = new JLabel("Price per night from October to December 2022");

        JLabel sc1Label = new JLabel("Service Charge from October to December 2021");
        JLabel sc2Label = new JLabel("Service Charge from January to March 2022");
        JLabel sc3Label = new JLabel("Service Charge from April to June 2022");
        JLabel sc4Label = new JLabel("Service Charge from July to September 2022");
        JLabel sc5Label = new JLabel("Service Charge from October to December 2022");

        JLabel cc1Label = new JLabel("Cleaning Charge from October to December 2021");
        JLabel cc2Label = new JLabel("Cleaning Charge from January to March 2022");
        JLabel cc3Label = new JLabel("Cleaning Charge from April to June 2022");
        JLabel cc4Label = new JLabel("Cleaning Charge from July to September 2022");
        JLabel cc5Label = new JLabel("Cleaning Charge from October to December 2022");

        //Address Labels
        JLabel addressMsg = new JLabel("Address");
        JLabel houseNumberLabel = new JLabel("House Number");
        JLabel streetNameLabel = new JLabel("Street Name");
        JLabel postCodeLabel = new JLabel("Post Code");
        JLabel cityLabel = new JLabel("City");
        JLabel countryLabel = new JLabel("Country");

        //Facility Labels
        JLabel BFLabel = new JLabel("Bathing Facility");
        JLabel bathroomMsg = new JLabel("Bathroom");
        JLabel KFLabel = new JLabel("Kitchen Facility");
        JLabel LFLabel = new JLabel("Living Facility");
        JLabel OFLabel = new JLabel("Outdoor Facility");
        JLabel SFLabel = new JLabel("Sleeping Facility");
        JLabel bedroomMsg = new JLabel("Bedroom");
        JLabel UFLabel = new JLabel("Utility Facility");

        //JComponenets for Property Information
        JTextField propertyNameTF = new JTextField(30);
        JTextField descriptionTF = new JTextField(45);
        JCheckBox hasBreakfastCB = new JCheckBox("Has Breakfast");

        //TextFields for Charge Bands
        JTextField octDec2021TF = new JTextField(8);
        JTextField janMar2022TF = new JTextField(8);
        JTextField aprJun2022TF = new JTextField(8);
        JTextField julSept2022TF = new JTextField(8);
        JTextField octDec2022TF = new JTextField(8);
        JTextField sc1TF = new JTextField(8);
        JTextField sc2TF = new JTextField(8);
        JTextField sc3TF = new JTextField(8);
        JTextField sc4TF = new JTextField(8);
        JTextField sc5TF = new JTextField(8);
        JTextField cc1TF = new JTextField(8);
        JTextField cc2TF = new JTextField(8);
        JTextField cc3TF = new JTextField(8);
        JTextField cc4TF = new JTextField(8);
        JTextField cc5TF = new JTextField(8);


        //TextFields for host to enter properties confidential address
        JTextField houseNumberTF = new JTextField(6);
        JTextField streetNameTF = new JTextField(30);
        JTextField postCodeTF = new JTextField(10);
        JTextField cityTF = new JTextField(30);
        JTextField countryTF = new JTextField(30);

        //Bathing Facility checkboxes and TextFields
        JCheckBox hasHairDryerCB = new JCheckBox("Hair Dryer");
        JCheckBox hasShampooCB = new JCheckBox("Shampoo");
        JCheckBox hasToiletPaperCB = new JCheckBox("Toilet Paper");

        //Bathroom Checkboxes
        JCheckBox hasToiletCB = new JCheckBox("Toilet");
        JCheckBox hasShowerCB = new JCheckBox("Shower");
        JCheckBox hasBathCB = new JCheckBox("Bath");
        JCheckBox sharedWithHostCB = new JCheckBox("Is it shared with Host");

        //Kitchen Facility checkboxes
        JCheckBox hasOvenCB = new JCheckBox("Oven");
        JCheckBox hasFridgeCB = new JCheckBox("Fridge");
        JCheckBox hasMicrowaveCB = new JCheckBox("Microwave");
        JCheckBox hasStoveCB = new JCheckBox("Stove");
        JCheckBox hasDishWasherCB = new JCheckBox("Dishwasher");
        JCheckBox hasTableWareCB = new JCheckBox("Tableware");
        JCheckBox hasCookWareCB = new JCheckBox("Cookware");
        JCheckBox hasBasicProvisionsCB = new JCheckBox("Basic Provisions");

        //Living Facility checkboxes
        JCheckBox hasWifiCB = new JCheckBox("WiFi");
        JCheckBox hasTelevisionCB = new JCheckBox("Television");
        JCheckBox hasSatelliteCB = new JCheckBox("Satellite");
        JCheckBox hasStreamingServiceCB = new JCheckBox("Streaming Service");
        JCheckBox hasDvdPlayerCB = new JCheckBox("DVD Player");
        JCheckBox hasBoardGamesCB = new JCheckBox("Board Games");

        //Outdoors Facility checkboxes
        JCheckBox hasFreeOnSiteParkingCB = new JCheckBox("Free Parking On site");
        JCheckBox hasPaidCarParkCB = new JCheckBox("Paid Car Park");
        JCheckBox hasPatioCB = new JCheckBox("Patio");
        JCheckBox hasBarbequeFacilitiesCB = new JCheckBox("Barbeque Facilities");

        //Sleeping Facility checkboxes
        JCheckBox hasBedLinenCB = new JCheckBox("Bed Linen");
        JCheckBox hasTowelsCB = new JCheckBox("Towels");


        //Bedroom JComboBoxes
        JLabel bedOneMsg = new JLabel("Bed One Type: ");
        JLabel bedTwoMsg = new JLabel("Bed Two Type: ");
        JComboBox<String> bedOneTypeCBox = new JComboBox<String>();
        JComboBox<String> bedTwoTypeCBox = new JComboBox<String>();
        bedOneTypeCBox.addItem("Single");
        bedTwoTypeCBox.addItem("Single");

        bedOneTypeCBox.addItem("Double");
        bedTwoTypeCBox.addItem("Double");

        bedOneTypeCBox.addItem("Kingsized");
        bedTwoTypeCBox.addItem("Kingsized");

        bedOneTypeCBox.addItem("Bunk");
        bedTwoTypeCBox.addItem("Bunk");

        bedOneTypeCBox.addItem("None");
        bedTwoTypeCBox.addItem("None");

        //Utility Facility checkboxes
        JCheckBox hasHeatingCB = new JCheckBox("Heating");
        JCheckBox hasWashingMachineCB = new JCheckBox("Washing Machine");
        JCheckBox hasDryingMachineCB = new JCheckBox("Drying Machine");
        JCheckBox hasFireExtinguisherCB = new JCheckBox("Fire Extinguisher");
        JCheckBox hasSmokeAlarmCB = new JCheckBox("Smoke Alarm");
        JCheckBox hasFirstAidKitCB = new JCheckBox("First Aid Kit");

        //JButtons
        JButton addPropertyButton = new JButton("Add Property");
        JButton addBedroomButton = new JButton("Add Bedroom");
        JButton addBathroomButton = new JButton("Add Bathroom");


        //property info JPanels
        JPanel propertyMsgPanel = new JPanel();
        propertyMsgPanel.add(propertyMsg);
        rowHolderPanel.add(propertyMsgPanel);

        JPanel propertyNamePanel = new JPanel();
        propertyNamePanel.add(propertyNameLabel);
        propertyNamePanel.add(propertyNameTF);
        rowHolderPanel.add(propertyNamePanel);

        JPanel propertyDescPanel = new JPanel();
        propertyDescPanel.add(descriptionLabel);
        propertyDescPanel.add(descriptionTF);
        rowHolderPanel.add(propertyDescPanel);

        JPanel propertyBreakfastPanel = new JPanel();
        propertyBreakfastPanel.add(hasBreakfastCB);
        rowHolderPanel.add(propertyBreakfastPanel);

        //charge-band JPanels
        JPanel chargeBandMsgPanel = new JPanel();
        chargeBandMsgPanel.add(chargeBandMsg);
        rowHolderPanel.add(chargeBandMsgPanel);

        JPanel chargeBand1Panel = new JPanel();
        chargeBand1Panel.add(octDec2021Label);
        chargeBand1Panel.add(octDec2021TF);
        chargeBand1Panel.add(sc1Label);
        chargeBand1Panel.add(sc1TF);
        chargeBand1Panel.add(cc1Label);
        chargeBand1Panel.add(cc1TF);
        rowHolderPanel.add(chargeBand1Panel);

        JPanel chargeBand2Panel = new JPanel();
        chargeBand2Panel.add(janMar2022Label);
        chargeBand2Panel.add(janMar2022TF);
        chargeBand2Panel.add(sc2Label);
        chargeBand2Panel.add(sc2TF);
        chargeBand2Panel.add(cc2Label);
        chargeBand2Panel.add(cc2TF);
        rowHolderPanel.add(chargeBand2Panel);

        JPanel chargeBand3Panel = new JPanel();
        chargeBand3Panel.add(aprJun2022Label);
        chargeBand3Panel.add(aprJun2022TF);
        chargeBand3Panel.add(sc3Label);
        chargeBand3Panel.add(sc3TF);
        chargeBand3Panel.add(cc3Label);
        chargeBand3Panel.add(cc3TF);
        rowHolderPanel.add(chargeBand3Panel);

        JPanel chargeBand4Panel = new JPanel();
        chargeBand4Panel.add(julSept2022Label);
        chargeBand4Panel.add(julSept2022TF);
        chargeBand4Panel.add(sc4Label);
        chargeBand4Panel.add(sc4TF);
        chargeBand4Panel.add(cc4Label);
        chargeBand4Panel.add(cc4TF);
        rowHolderPanel.add(chargeBand4Panel);

        JPanel chargeBand5Panel = new JPanel();
        chargeBand5Panel.add(octDec2022Label);
        chargeBand5Panel.add(octDec2022TF);
        chargeBand5Panel.add(sc5Label);
        chargeBand5Panel.add(sc5TF);
        chargeBand5Panel.add(cc5Label);
        chargeBand5Panel.add(cc5TF);
        rowHolderPanel.add(chargeBand5Panel);

        //address JPanels
        JPanel addressMsgPanel = new JPanel();
        addressMsgPanel.add(addressMsg);
        rowHolderPanel.add(addressMsgPanel);

        JPanel houseNumberPanel = new JPanel();
        houseNumberPanel.add(houseNumberLabel);
        houseNumberPanel.add(houseNumberTF);
        rowHolderPanel.add(houseNumberPanel);

        JPanel streetNamePanel = new JPanel();
        streetNamePanel.add(streetNameLabel);
        streetNamePanel.add(streetNameTF);
        rowHolderPanel.add(streetNamePanel);

        JPanel postCodePanel = new JPanel();
        postCodePanel.add(postCodeLabel);
        postCodePanel.add(postCodeTF);
        rowHolderPanel.add(postCodePanel);

        JPanel cityPanel = new JPanel();
        cityPanel.add(cityLabel);
        cityPanel.add(cityTF);
        rowHolderPanel.add(cityPanel);

        JPanel countryPanel = new JPanel();
        countryPanel.add(countryLabel);
        countryPanel.add(countryTF);
        rowHolderPanel.add(countryPanel);

        //bathing facility JPanels
        JPanel BFLabelPanel = new JPanel();
        BFLabelPanel.add(BFLabel);
        rowHolderPanel.add(BFLabelPanel);

        JPanel BFPanel = new JPanel();
        BFPanel.add(hasHairDryerCB);
        BFPanel.add(hasShampooCB);
        BFPanel.add(hasToiletPaperCB);
        rowHolderPanel.add(BFPanel);

        /*
         * Button added so that each time it is clicked
         * new checkboxes appear so that user can add another
         * bathroom to the database
         */
        JPanel bathroomButtonPanel = new JPanel();
        bathroomButtonPanel.add(addBathroomButton);
        rowHolderPanel.add(bathroomButtonPanel);

        JPanel BathroomMsgPanel = new JPanel();
        BathroomMsgPanel.add(bathroomMsg);
        rowHolderPanel.add(BathroomMsgPanel);

        JPanel bathroomPanel = new JPanel();
        bathroomPanel.add(hasToiletCB);
        bathroomPanel.add(hasShowerCB);
        bathroomPanel.add(hasBathCB);
        bathroomPanel.add(sharedWithHostCB);
        rowHolderPanel.add(bathroomPanel);

        //kitchen facility JPanel
        JPanel KFLabelPanel = new JPanel();
        KFLabelPanel.add(KFLabel);
        rowHolderPanel.add(KFLabelPanel);

        JPanel KFPanel = new JPanel();
        KFPanel.add(hasOvenCB);
        KFPanel.add(hasFridgeCB);
        KFPanel.add(hasMicrowaveCB);
        KFPanel.add(hasStoveCB);
        KFPanel.add(hasDishWasherCB);
        KFPanel.add(hasTableWareCB);
        KFPanel.add(hasCookWareCB);
        KFPanel.add(hasBasicProvisionsCB);
        rowHolderPanel.add(KFPanel);

        //living facility JPanel
        JPanel LFLabelPanel = new JPanel();
        LFLabelPanel.add(LFLabel);
        rowHolderPanel.add(LFLabelPanel);

        JPanel LFPanel = new JPanel();
        LFPanel.add(hasWifiCB);
        LFPanel.add(hasTelevisionCB);
        LFPanel.add(hasSatelliteCB);
        LFPanel.add(hasStreamingServiceCB);
        LFPanel.add(hasDvdPlayerCB);
        LFPanel.add(hasBoardGamesCB);
        rowHolderPanel.add(LFPanel);

        //outdoors facility JPanel
        JPanel OFLabelPanel = new JPanel();
        OFLabelPanel.add(OFLabel);
        rowHolderPanel.add(OFLabelPanel);

        JPanel OFPanel = new JPanel();
        OFPanel.add(hasFreeOnSiteParkingCB);
        OFPanel.add(hasPaidCarParkCB);
        OFPanel.add(hasPatioCB);
        OFPanel.add(hasBarbequeFacilitiesCB);
        rowHolderPanel.add(OFPanel);

        //sleeping facility JPanel
        JPanel SFLabelPanel = new JPanel();
        SFLabelPanel.add(SFLabel);
        rowHolderPanel.add(SFLabelPanel);

        JPanel SFPanel = new JPanel();
        SFPanel.add(hasBedLinenCB);
        SFPanel.add(hasTowelsCB);
        rowHolderPanel.add(SFPanel);

        //Bedroom button added to add another bedroom
        JPanel bedroomButtonPanel = new JPanel();
        bedroomButtonPanel.add(addBedroomButton);
        rowHolderPanel.add(bedroomButtonPanel);

        JPanel BedroomMsgPanel = new JPanel();
        BedroomMsgPanel.add(bedroomMsg);
        rowHolderPanel.add(BedroomMsgPanel);

        JPanel bedroomPanel = new JPanel();
        bedroomPanel.add(bedOneMsg);
        bedroomPanel.add(bedOneTypeCBox);
        bedroomPanel.add(bedTwoMsg);
        bedroomPanel.add(bedTwoTypeCBox);
        rowHolderPanel.add(bedroomPanel);

        //utility facility JPanel
        JPanel UFLabelPanel = new JPanel();
        UFLabelPanel.add(UFLabel);
        rowHolderPanel.add(UFLabelPanel);

        JPanel UFPanel = new JPanel();
        UFPanel.add(hasHeatingCB);
        UFPanel.add(hasWashingMachineCB);
        UFPanel.add(hasDryingMachineCB);
        UFPanel.add(hasFireExtinguisherCB);
        UFPanel.add(hasSmokeAlarmCB);
        UFPanel.add(hasFirstAidKitCB);
        rowHolderPanel.add(UFPanel);

        /*  Button that validates the text inputs
         *  and inserts the data to the DB tables
         */
        JPanel addPropertyButtonPanel = new JPanel();
        addPropertyButtonPanel.add(addPropertyButton);
        rowHolderPanel.add(addPropertyButtonPanel);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        /*
         * The add bathroom button, each time it is pressed, will
         * add four new JCheckBoxes to the Panel and these JCheckBoxes
         * are stored in bathroomValues array, so that the user input
         * can be later retrieved from array
         */
        addBathroomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberOfBathrooms+=1;
                int temp = 4;

                JPanel newBathroomPanel = new JPanel();
                bathroomValues.add(new JCheckBox("Toilet"));
                bathroomValues.add(new JCheckBox("Shower"));
                bathroomValues.add(new JCheckBox("Bath"));
                bathroomValues.add(new JCheckBox("Is it shared with Host"));
                newBathroomPanel.add(bathroomValues.get((temp*numberOfBathrooms)-4));
                newBathroomPanel.add(bathroomValues.get((temp*numberOfBathrooms)-3));
                newBathroomPanel.add(bathroomValues.get((temp*numberOfBathrooms)-2));
                newBathroomPanel.add(bathroomValues.get((temp*numberOfBathrooms)-1));
                /*
                 * The new Check boxes are added to a specific index in the rowHolderPanel displayed under
                 * the rest of the bathroom checkboxes
                 */
                rowHolderPanel.add(newBathroomPanel,(bathroomIndex+numberOfBathrooms));
                revalidate();
                repaint();

            }
        });

        /*
         * Similar to the above action listener, add bedroom button
         * creates two JComboBoxes and the JComboBoxes are stored
         * in bedroomValues Array.
         */
        addBedroomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                numberOfBedrooms+=1;
                int scale = 2;

                JPanel newBedroomPanel = new JPanel();
                JLabel newBedMsg1 = new JLabel("Bed One Type: ");
                JLabel newBedMsg2 = new JLabel("Bed Two Type: ");

                JComboBox<String> newBed1 = new JComboBox<String>();
                JComboBox<String> newBed2 = new JComboBox<String>();
                newBed1.addItem("Single");
                newBed2.addItem("Single");

                newBed1.addItem("Double");
                newBed2.addItem("Double");

                newBed1.addItem("Kingsized");
                newBed2.addItem("Kingsized");

                newBed1.addItem("Bunk");
                newBed2.addItem("Bunk");

                newBed1.addItem("None");
                newBed2.addItem("None");

                bedroomValues.add(newBed1);
                bedroomValues.add(newBed2);
                newBedroomPanel.add(newBedMsg1);
                newBedroomPanel.add(bedroomValues.get((numberOfBedrooms*scale)-2));
                newBedroomPanel.add(newBedMsg2);
                newBedroomPanel.add(bedroomValues.get((numberOfBedrooms*scale)-1));
                rowHolderPanel.add(newBedroomPanel,(bedroomIndex+numberOfBathrooms+numberOfBedrooms));
                revalidate();
                repaint();


            }
        });

        addPropertyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*
                 * All the inputs from the user are stored in variables
                 * retrieved from all the textfields,checkboxes and comboboxes
                 */
                String propertyName = propertyNameTF.getText();
                String description = descriptionTF.getText();
                boolean hasBreakfast = hasBreakfastCB.isSelected();
                String octDec2021PPN = octDec2021TF.getText();
                String janMar2022PPN = janMar2022TF.getText();
                String arpJun2022PPN = aprJun2022TF.getText();
                String julSept2022PPN = julSept2022TF.getText();
                String octDec2022PPN = octDec2022TF.getText();
                String serviceCharge1 = sc1TF.getText();
                String serviceCharge2 = sc2TF.getText();
                String serviceCharge3 = sc3TF.getText();
                String serviceCharge4 = sc4TF.getText();
                String serviceCharge5 = sc5TF.getText();
                String cleaningCharge1 = cc1TF.getText();
                String cleaningCharge2 = cc2TF.getText();
                String cleaningCharge3 = cc3TF.getText();
                String cleaningCharge4 = cc4TF.getText();
                String cleaningCharge5 = cc5TF.getText();
                String houseNumber = houseNumberTF.getText();
                String streetName = streetNameTF.getText();
                String postCode = postCodeTF.getText();
                String city = cityTF.getText();
                String country = countryTF.getText();

                boolean hasHairDryer = hasHairDryerCB.isSelected();
                boolean hasShampoo = hasShampooCB.isSelected();
                boolean hasToiletPaper = hasToiletPaperCB.isSelected();

                boolean hasToilet = hasToiletCB.isSelected();
                boolean hasShower = hasShowerCB.isSelected();
                boolean hasBath = hasBathCB.isSelected();
                boolean sharedWithHost = sharedWithHostCB.isSelected();

                boolean hasOven = hasToiletCB.isSelected();
                boolean hasFridge = hasShowerCB.isSelected();
                boolean hasMicrowave = hasBathCB.isSelected();
                boolean hasStove = sharedWithHostCB.isSelected();
                boolean hasDishWasher = hasToiletCB.isSelected();
                boolean hasTableWare = hasShowerCB.isSelected();
                boolean hasCookWare = hasBathCB.isSelected();
                boolean hasBasicProvisions = sharedWithHostCB.isSelected();

                boolean hasWifi = hasWifiCB.isSelected();
                boolean hasTelevision = hasTelevisionCB.isSelected();
                boolean hasSatellite = hasSatelliteCB.isSelected();
                boolean hasStreamingService = hasStreamingServiceCB.isSelected();
                boolean hasDvdPlayer = hasDvdPlayerCB.isSelected();
                boolean hasBoardGames = hasBoardGamesCB.isSelected();

                boolean hasFreeOnSiteParking = hasFreeOnSiteParkingCB.isSelected();
                boolean hasPaidCarPark = hasPaidCarParkCB.isSelected();
                boolean hasPatio = hasPatioCB.isSelected();
                boolean hasBarbequeFacilities = hasBarbequeFacilitiesCB.isSelected();

                boolean hasBedLinen = hasBedLinenCB.isSelected();
                boolean hasTowels = hasTowelsCB.isSelected();

                int  bedOneIndex = bedOneTypeCBox.getSelectedIndex();
                String bedOneType = bedOneTypeCBox.getItemAt(bedOneIndex);
                int  bedTwoIndex = bedTwoTypeCBox.getSelectedIndex();
                String bedTwoType = bedTwoTypeCBox.getItemAt(bedTwoIndex);

                boolean hasHeating = hasHeatingCB.isSelected();
                boolean hasWashingMachine = hasWashingMachineCB.isSelected();
                boolean hasDryingMachine = hasDryingMachineCB.isSelected();
                boolean hasFireExtinguisher = hasFireExtinguisherCB.isSelected();
                boolean hasSmokeAlarm = hasSmokeAlarmCB.isSelected();
                boolean hasFirstAidKit = hasFirstAidKitCB.isSelected();


                //text inputs are then checked
                /*
                 * a string is returned when the length check method is called, if the string is "" then length
                 * matches the length specified in the column types in the database,
                 * otherwise the string will be a message that states which variable does not match the required length
                 */
                String lengthErrorMsg = Validation.checkLengthAddProperty(propertyName,description,houseNumber,
                        streetName,postCode,city,country);

                //Each textField input is checked to see if it was filled out and not left empty
                if (Validation.checkEmptyAddProperty(propertyName, description, octDec2021PPN, janMar2022PPN,
                        arpJun2022PPN,  julSept2022PPN, octDec2022PPN, serviceCharge1, serviceCharge2, serviceCharge3,
                        serviceCharge4, serviceCharge5, cleaningCharge1, cleaningCharge2, cleaningCharge3,
                        cleaningCharge4, cleaningCharge5, houseNumber, streetName, postCode, city, country)){
                    JOptionPane.showMessageDialog(frame, "Please make sure all fields are filled in!");}

                //checking whether names are just letters and have no unique chars or numbers
                else if(!Validation.checkName(propertyName)){
                    JOptionPane.showMessageDialog(frame, "Invalid Property Name!");}

                //check if it is a suitable type double input
                else if(!Validation.checkIsDouble(octDec2021PPN)){
                    JOptionPane.showMessageDialog(frame, "Invalid Price between Oct-Dec 2021");}
                else if(!Validation.checkIsDouble(janMar2022PPN)) {
                    JOptionPane.showMessageDialog(frame, "Invalid Price between Jan-Mar 2022 ");}
                else if(!Validation.checkIsDouble(arpJun2022PPN)) {
                    JOptionPane.showMessageDialog(frame, "Invalid Price between Apr-Jun 2022 ");}
                else if(!Validation.checkIsDouble(julSept2022PPN)){
                    JOptionPane.showMessageDialog(frame, "Invalid Price between Jul-Sept 2022 ");}
                else if(!Validation.checkIsDouble(octDec2022PPN)) {
                    JOptionPane.showMessageDialog(frame, "Invalid Price between Oct-Dec 2022 ");}
                else if(!Validation.checkIsDouble(serviceCharge1)) {
                    JOptionPane.showMessageDialog(frame, "Invalid Service Charge for Oct-Dec 2021");}
                else if(!Validation.checkIsDouble(serviceCharge2)){
                    JOptionPane.showMessageDialog(frame, "Invalid Service Charge for Jan-Mar 2022");}
                else if(!Validation.checkIsDouble(serviceCharge3)) {
                    JOptionPane.showMessageDialog(frame, "Invalid Service Charge for Apr-Jun 2022 ");}
                else if(!Validation.checkIsDouble(serviceCharge4)) {
                    JOptionPane.showMessageDialog(frame, "Invalid Service Charge for Jul-Sept 2022 ");}
                else if(!Validation.checkIsDouble(serviceCharge5)){
                    JOptionPane.showMessageDialog(frame, "Invalid Service Charge for Oct-Dec 2022 ");}
                else if(!Validation.checkIsDouble(cleaningCharge1)) {
                    JOptionPane.showMessageDialog(frame, "Invalid Cleaning Charge for Oct-Dec 2021");}
                else if(!Validation.checkIsDouble(cleaningCharge2)) {
                    JOptionPane.showMessageDialog(frame, "Invalid Cleaning Charge for Jan-Mar 2022");}
                else if(!Validation.checkIsDouble(cleaningCharge3)){
                    JOptionPane.showMessageDialog(frame, "Invalid Cleaning Charge for Apr-Jun 2022");}
                else if(!Validation.checkIsDouble(cleaningCharge4)) {
                    JOptionPane.showMessageDialog(frame, "Invalid Cleaning Charge for Jul-Sept 2022");}
                else if(!Validation.checkIsDouble(cleaningCharge5)) {
                    JOptionPane.showMessageDialog(frame, "Invalid Cleaning Charge for Oct-Dec 2022");}

                //check if input is just integer digits
                else if(!Validation.checkIsNumbers(houseNumber)){
                    JOptionPane.showMessageDialog(frame, "Invalid House number!");}
                else if(!Validation.checkName(streetName)){
                    JOptionPane.showMessageDialog(frame, "Invalid Street Name!");}

                //checks for integers or letters input
                else if(!Validation.checkHasLetterOrDigit(postCode)){
                    JOptionPane.showMessageDialog(frame, "Invalid Post Code!");}
                else if(!Validation.checkName(city)){
                    JOptionPane.showMessageDialog(frame, "Invalid City Name!");}
                else if(!Validation.checkName(country)){
                    JOptionPane.showMessageDialog(frame, "Invalid Country Name!");}

                //if there is an error msg from the string above, it will display it
                else if(!(lengthErrorMsg == "")){
                    JOptionPane.showMessageDialog(frame,lengthErrorMsg);}
                else{
                    //if all checks work out, proceed to insert the data to DB tables

                    //Inserting inputs for bathing facility
                    BathingFacilityModel bathingFacility = new BathingFacilityModel(hasHairDryer,hasShampoo,
                            hasToiletPaper);

                    /*
                     * setting the bathingFacilityID is done after as the id can only be retrieved after it is inserted
                     * the id is set and to be used for the foreign key in Bathroom table
                     */
                    bathingFacility.insertBathingFacilityRow();

                    bathingFacility.setBathingFacilityID();
                    BathroomModel bathroom = new BathroomModel(bathingFacility.getBathingFacility().getBathingFacilityID()
                            ,hasToilet,hasShower,hasBath,sharedWithHost);
                    bathroom.insertPropertyRow();

                    /*
                     * for loop is used to retrieve all the bathroom checkbox values,
                     * which is dependent on how many new bathrooms were added in runtime
                     */
                    for (int i=0;i<numberOfBathrooms*4;i+=4){
                        boolean newToilet = bathroomValues.get(i).isSelected();
                        boolean newShower = bathroomValues.get(i+1).isSelected();
                        boolean newHasBath = bathroomValues.get(i+2).isSelected();
                        boolean newSharedWithHost = bathroomValues.get(i+3).isSelected();
                        BathroomModel newBathroom = new BathroomModel(
                                bathingFacility.getBathingFacility().getBathingFacilityID(),newToilet,newShower,
                                newHasBath,newSharedWithHost);
                        newBathroom.insertPropertyRow();
                    }

                    //Like what is done for bathing facility, it is repeated for sleeping facility
                    SleepingFacilityModel sleepingFacility = new SleepingFacilityModel(hasBedLinen,hasTowels);
                    sleepingFacility.insertSleepingFacilityRow();
                    sleepingFacility.setSleepingFacilityID();

                    BedroomModel bedroom = new BedroomModel(sleepingFacility.getSleepingFacility().getSleepingFacilityID(),
                            bedOneType,bedTwoType);
                    bedroom.insertBedroomRow();

                    for (int i=0;i<numberOfBedrooms*2;i+=2){
                        int newBedOneIndex = bedroomValues.get(i).getSelectedIndex();
                        String newBedOneType = bedOneTypeCBox.getItemAt(newBedOneIndex);
                        int newBedTwoIndex = bedroomValues.get(i).getSelectedIndex();
                        String newBedTwoType = bedTwoTypeCBox.getItemAt(newBedTwoIndex);
                        BedroomModel newBedroom = new BedroomModel(
                                sleepingFacility.getSleepingFacility().getSleepingFacilityID(),newBedOneType,
                                newBedTwoType);
                        newBedroom.insertBedroomRow();
                    }

                    //inserting Kitchen facility inputs
                    KitchenFacilityModel kitchenFacility = new KitchenFacilityModel( hasOven, hasFridge, hasMicrowave,
                            hasStove, hasDishWasher, hasTableWare, hasCookWare, hasBasicProvisions);
                    kitchenFacility.insertKitchenFacilityRow();
                    kitchenFacility.setKitchenFacilityID();

                    //inserting Living facility inputs
                    LivingFacilityModel livingFacility = new LivingFacilityModel( hasWifi, hasTelevision, hasSatellite,
                            hasStreamingService, hasDvdPlayer, hasBoardGames);
                    livingFacility.insertLivingFacilityRow();
                    livingFacility.setLivingFacilityID();

                    //inserting Outdoors facility inputs
                    OutdoorsFacilityModel outdoorsFacility = new OutdoorsFacilityModel( hasFreeOnSiteParking,
                            hasPaidCarPark,  hasPatio, hasBarbequeFacilities);
                    outdoorsFacility.insertOutdoorsFacilityRow();
                    outdoorsFacility.setOutdoorsFacilityID();

                    //inserting Utility facility inputs
                    UtilityFacilityModel utilityFacility = new UtilityFacilityModel( hasHeating, hasWashingMachine,
                            hasDryingMachine, hasFireExtinguisher, hasSmokeAlarm, hasFirstAidKit);
                    utilityFacility.insertUtilityFacilityRow();
                    utilityFacility.setUtilityFacilityID();

                    /*
                     * for all the facilities inserted into DB,
                     * the IDs are set and retrieved to be inserted into facility table as foreign keys
                     */
                    FacilitiesModel facilities = new FacilitiesModel(
                            sleepingFacility.getSleepingFacility().getSleepingFacilityID(),
                            bathingFacility.getBathingFacility().getBathingFacilityID(),
                            kitchenFacility.getKitchenFacility().getKitchenFacilityID(),
                            utilityFacility.getUtilityFacility().getUtilityFacilityID(),
                            outdoorsFacility.getOutdoorsFacility().getOutdoorsFacilityID(),
                            livingFacility.getLivingFacility().getLivingFacilityID());
                    facilities.insertFacilitiesRow();
                    facilities.setFacilityID();


                    //Inserting address inputs
                    AddressModel address = new AddressModel(Integer.valueOf(houseNumber),streetName,postCode,city,
                            country);
                    address.setAddressID();

                    //inserting property inputs with the addressID and facilityID foreign keys
                    PropertyModel property = new PropertyModel(facilities.getFacilities().getFacilityID(),
                            propertyName,hostAccount.getHost().getHostID(),description,hasBreakfast,
                            address.getAddress().getAddressID());
                    property.insertPropertyRow();
                    property.setPropertyID();

                    //Charge-Bands are inserted
                    ChargeBandModel chargeBand1 = new ChargeBandModel(property.getProperty().getPropertyID(),
                            "2021-10-01","2021-12-31",octDec2021PPN,serviceCharge1,cleaningCharge1);
                    ChargeBandModel chargeBand2 = new ChargeBandModel(property.getProperty().getPropertyID(),
                            "2022-01-01","2022-03-31",janMar2022PPN,serviceCharge2,cleaningCharge2);
                    ChargeBandModel chargeBand3 = new ChargeBandModel(property.getProperty().getPropertyID(),
                            "2022-04-01","2022-06-30",arpJun2022PPN,serviceCharge3,cleaningCharge3);
                    ChargeBandModel chargeBand4 = new ChargeBandModel(property.getProperty().getPropertyID(),
                            "2022-07-01","2022-09-30",julSept2022PPN,serviceCharge4,cleaningCharge4);
                    ChargeBandModel chargeBand5 = new ChargeBandModel(property.getProperty().getPropertyID(),
                            "2022-10-01","2022-12-31",octDec2022PPN,serviceCharge5,cleaningCharge5);

                    chargeBand1.insertChargeBandRow();
                    chargeBand2.insertChargeBandRow();
                    chargeBand3.insertChargeBandRow();
                    chargeBand4.insertChargeBandRow();
                    chargeBand5.insertChargeBandRow();

                    //after inserting to DB, user is redirected back to the hostRegisteredPropertiesPanel
                    Container contentPane = mainFrame.getContentPane();
                    revalidate();
                    repaint();
                    NavigationBarPanel navigationBarPanel = new NavigationBarPanel(mainFrame);
                    HostRegisteredPropertiesPanel hostRegisteredPropertiesPanel = new
                            HostRegisteredPropertiesPanel(mainFrame,hostAccount);
                    contentPane.removeAll();
                    contentPane.add(navigationBarPanel,BorderLayout.NORTH);
                    contentPane.add(hostRegisteredPropertiesPanel);
                    revalidate();
                    repaint();
                }
            }
        });
    }
}

