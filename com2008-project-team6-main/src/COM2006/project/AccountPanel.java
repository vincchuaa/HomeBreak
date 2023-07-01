package COM2006.project;

import COM2006.project.tables.AddressModel;
import COM2006.project.tables.GuestsModel;
import COM2006.project.tables.HostsModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AccountPanel extends JPanel{
    private MainFrame mainFrame;
    private GuestsModel guestAccount;
    private HostsModel hostAccount;
    private JPanel rowHolderPanel = new JPanel(new GridLayout(0,1,1,1));

    public AccountPanel(MainFrame frame,GuestsModel account) {
        mainFrame = frame;
        guestAccount=account;

        //Creating JScrollPane to add components too
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(rowHolderPanel, BorderLayout.PAGE_START);
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //Getting information about the guest
        String guestEmail = guestAccount.getGuest().getEmail();
        String guestTitle = guestAccount.getGuest().getTitle();
        String guestForename = guestAccount.getGuest().getForename();
        String guestSurname = guestAccount.getGuest().getSurname();
        String guestPhoneNumber = guestAccount.getGuest().getPhoneNumber();
        int guestAddressID = guestAccount.getGuest().getAddressID_fk();
        AddressModel guestAddress = new AddressModel(guestAddressID);
        String guestAddressStreetName = guestAddress.getAddress().getStreetName();
        int guestAddressHouseNumber = guestAddress.getAddress().getHouseNumber();
        String guestAddressPostCode = guestAddress.getAddress().getPostCode();
        String guestAddressCity = guestAddress.getAddress().getCity();
        String guestAddressCountry = guestAddress.getAddress().getCountry();

        //Adding guests email to panel
        JPanel guestEmailPanel = new JPanel();
        JLabel guestEmailLabel = new JLabel("Email: ");
        JLabel guestEmailValueLabel = new JLabel(guestEmail);
        guestEmailPanel.add(guestEmailLabel);
        guestEmailPanel.add(guestEmailValueLabel);
        rowHolderPanel.add(guestEmailPanel);

        //Adding guests name to panel
        JPanel guestNamePanel = new JPanel();
        JLabel guestNameLabel = new JLabel("Name");
        JLabel guestNameValueLabel = new JLabel(guestTitle +" "+guestForename+" "+guestSurname);
        guestNamePanel.add(guestNameLabel);
        guestNamePanel.add(guestNameValueLabel);
        rowHolderPanel.add(guestNamePanel);

        //Adding guests phone number to panel
        JPanel guestPhoneNumberPanel = new JPanel();
        JLabel guestPhoneNumberLabel = new JLabel("Phone number: ");
        JLabel guestPhoneNumberValueLabel = new JLabel(guestPhoneNumber);
        guestPhoneNumberPanel.add(guestPhoneNumberLabel);
        guestPhoneNumberPanel.add(guestPhoneNumberValueLabel);
        rowHolderPanel.add(guestPhoneNumberPanel);

        //Adding guests house number to panel
        JPanel guestAddressHouseNumberPanel = new JPanel();
        JLabel guestAddressHouseNumberLabel = new JLabel("House Number: ");
        JLabel guestAddressHouseNumberValueLabel = new JLabel(String.valueOf(guestAddressHouseNumber));
        guestAddressHouseNumberPanel.add(guestAddressHouseNumberLabel);
        guestAddressHouseNumberPanel.add(guestAddressHouseNumberValueLabel);
        rowHolderPanel.add(guestAddressHouseNumberPanel);

        //Adding guests street name to panel
        JPanel guestAddressStreetNamePanel = new JPanel();
        JLabel guestAddressStreetNameLabel = new JLabel("Street Name: ");
        JLabel guestAddressStreetNameValueLabel = new JLabel(guestAddressStreetName);
        guestAddressStreetNamePanel.add(guestAddressStreetNameLabel);
        guestAddressStreetNamePanel.add(guestAddressStreetNameValueLabel);
        rowHolderPanel.add(guestAddressStreetNamePanel);

        //Adding guests post code to panel
        JPanel guestAddressPostCodePanel = new JPanel();
        JLabel guestAddressPostCodeLabel = new JLabel("Post Code: ");
        JLabel guestAddressPostCodeValueLabel = new JLabel(guestAddressPostCode);
        guestAddressPostCodePanel.add(guestAddressPostCodeLabel);
        guestAddressPostCodePanel.add(guestAddressPostCodeValueLabel);
        rowHolderPanel.add(guestAddressPostCodePanel);

        //adding guests city to panel
        JPanel guestAddressCityPanel = new JPanel();
        JLabel guestAddressCityLabel = new JLabel("City: ");
        JLabel guestAddressCityValueLabel = new JLabel(guestAddressCity);
        guestAddressCityPanel.add(guestAddressCityLabel);
        guestAddressCityPanel.add(guestAddressCityValueLabel);
        rowHolderPanel.add(guestAddressCityPanel);

        //adding guests country to panel
        JPanel guestAddressCountryPanel = new JPanel();
        JLabel guestAddressCountryLabel = new JLabel("Country: ");
        JLabel guestAddressCountryValueLabel = new JLabel(guestAddressCountry);
        guestAddressCountryPanel.add(guestAddressCountryLabel);
        guestAddressCountryPanel.add(guestAddressCountryValueLabel);
        rowHolderPanel.add(guestAddressCountryPanel);

        //Adding scrollPane to the MainFrame
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

    }
    public AccountPanel(MainFrame frame,HostsModel account) {
        mainFrame = frame;
        hostAccount=account;
        //Creating JScrollPane to add components too
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(rowHolderPanel, BorderLayout.PAGE_START);
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //Getting information about the host
        String hostEmail = hostAccount.getHost().getEmail();
        String hostTitle = hostAccount.getHost().getTitle();
        String hostForename = hostAccount.getHost().getForename();
        String hostSurname = hostAccount.getHost().getSurname();
        String hostPhoneNumber = hostAccount.getHost().getPhoneNumber();
        int hostAddressID = hostAccount.getHost().getAddressID_fk();
        AddressModel hostAddress = new AddressModel(hostAddressID);
        String hostAddressStreetName = hostAddress.getAddress().getStreetName();
        int hostAddressHouseNumber = hostAddress.getAddress().getHouseNumber();
        String hostAddressPostCode = hostAddress.getAddress().getPostCode();
        String hostAddressCity = hostAddress.getAddress().getCity();
        String hostAddressCountry = hostAddress.getAddress().getCountry();
        int hostSuperHost = hostAccount.getHost().getIsSuperHost();
        String isHostSuperHost = "No";
        if(hostSuperHost == 1){
            isHostSuperHost = "Yes";
        }

        //Adding hosts email to panel
        JPanel hostEmailPanel = new JPanel();
        JLabel hostEmailLabel = new JLabel("Email: ");
        JLabel hostEmailValueLabel = new JLabel(hostEmail);
        hostEmailPanel.add(hostEmailLabel);
        hostEmailPanel.add(hostEmailValueLabel);
        rowHolderPanel.add(hostEmailPanel);

        //Adding hosts name to panel
        JPanel hostNamePanel = new JPanel();
        JLabel hostNameLabel = new JLabel("Name");
        JLabel hostNameValueLabel = new JLabel(hostTitle+" "+hostForename+" "+hostSurname);
        hostNamePanel.add(hostNameLabel);
        hostNamePanel.add(hostNameValueLabel);
        rowHolderPanel.add(hostNamePanel);

        //Adding hosts phone number to panel
        JPanel hostPhoneNumberPanel = new JPanel();
        JLabel hostPhoneNumberLabel = new JLabel("Phone number: ");
        JLabel hostPhoneNumberValueLabel = new JLabel(hostPhoneNumber);
        hostPhoneNumberPanel.add(hostPhoneNumberLabel);
        hostPhoneNumberPanel.add(hostPhoneNumberValueLabel);
        rowHolderPanel.add(hostPhoneNumberPanel);

        //Adding hosts house number to panel
        JPanel hostAddressHouseNumberPanel = new JPanel();
        JLabel hostAddressHouseNumberLabel = new JLabel("House Number: ");
        JLabel hostAddressHouseNumberValueLabel = new JLabel(String.valueOf(hostAddressHouseNumber));
        hostAddressHouseNumberPanel.add(hostAddressHouseNumberLabel);
        hostAddressHouseNumberPanel.add(hostAddressHouseNumberValueLabel);
        rowHolderPanel.add(hostAddressHouseNumberPanel);

        //Adding hosts street name to panel
        JPanel hostAddressStreetNamePanel = new JPanel();
        JLabel hostAddressStreetNameLabel = new JLabel("Street Name: ");
        JLabel hostAddressStreetNameValueLabel = new JLabel(hostAddressStreetName);
        hostAddressStreetNamePanel.add(hostAddressStreetNameLabel);
        hostAddressStreetNamePanel.add(hostAddressStreetNameValueLabel);
        rowHolderPanel.add(hostAddressStreetNamePanel);

        //Adding hosts post code to panel
        JPanel hostAddressPostCodePanel = new JPanel();
        JLabel hostAddressPostCodeLabel = new JLabel("Post Code: ");
        JLabel hostAddressPostCodeValueLabel = new JLabel(hostAddressPostCode);
        hostAddressPostCodePanel.add(hostAddressPostCodeLabel);
        hostAddressPostCodePanel.add(hostAddressPostCodeValueLabel);
        rowHolderPanel.add(hostAddressPostCodePanel);

        //Adding hosts city to panel
        JPanel hostAddressCityPanel = new JPanel();
        JLabel hostAddressCityLabel = new JLabel("City: ");
        JLabel hostAddressCityValueLabel = new JLabel(hostAddressCity);
        hostAddressCityPanel.add(hostAddressCityLabel);
        hostAddressCityPanel.add(hostAddressCityValueLabel);
        rowHolderPanel.add(hostAddressCityPanel);

        //Adding hosts country to panel
        JPanel hostAddressCountryPanel = new JPanel();
        JLabel hostAddressCountryLabel = new JLabel("Country: ");
        JLabel hostAddressCountryValueLabel = new JLabel(hostAddressCountry);
        hostAddressCountryPanel.add(hostAddressCountryLabel);
        hostAddressCountryPanel.add(hostAddressCountryValueLabel);
        rowHolderPanel.add(hostAddressCountryPanel);

        //Adding if host is superhost to panel
        JPanel hostSuperHostPanel = new JPanel();
        JLabel hostSuperHostLabel = new JLabel("Super Host: ");
        JLabel hostSuperHostValueLabel = new JLabel(isHostSuperHost);
        hostSuperHostPanel.add(hostSuperHostLabel);
        hostSuperHostPanel.add(hostSuperHostValueLabel);
        rowHolderPanel.add(hostSuperHostPanel);

        //Adding button for host to view their properties to panel
        JPanel viewHostsPropertiesPanel = new JPanel();
        JButton viewHostsProperties = new JButton("View My Properties");
        viewHostsPropertiesPanel.add(viewHostsProperties);
        rowHolderPanel.add(viewHostsPropertiesPanel);

        //Adding scrollPane to the MainFrame
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        //Changing contentPane to be a navigation bar and list of properties owned by host
        viewHostsProperties.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Container contentPane = mainFrame.getContentPane();
                revalidate();
                repaint();
                NavigationBarPanel navigationBarPanel = new NavigationBarPanel(mainFrame,hostAccount);
                HostRegisteredPropertiesPanel hostRegisteredPropertiesPanel = new HostRegisteredPropertiesPanel(mainFrame,hostAccount);
                contentPane.removeAll();
                contentPane.add(navigationBarPanel, BorderLayout.NORTH);
                contentPane.add(hostRegisteredPropertiesPanel);
                revalidate();
                repaint();
            }
        });
    }
}
