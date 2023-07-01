package COM2006.project;

import COM2006.project.tables.GuestsModel;
import COM2006.project.tables.HostsModel;
import COM2006.project.tables.Validation;
import COM2006.project.tables.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import COM2006.project.PasswordManager;

public class RegisterPanel extends JPanel{

    private MainFrame mainFrame;
    private HostsModel hostAccount;
    private GuestsModel guestAccount;
    private AddressModel address;
    private JPanel rowHolderPanel = new JPanel(new GridLayout(0,1,1,1));

    public RegisterPanel(MainFrame frame){
        mainFrame = frame;

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(rowHolderPanel, BorderLayout.PAGE_START);
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //Defining labels, text fields, combo boxes, and other elements needed to be used in the panel
        JLabel accountTypeLabel = new JLabel("Account type");
        JLabel emailLabel = new JLabel("Email");
        JLabel passwordLabel = new JLabel("Password");
        JLabel usernameLabel = new JLabel("Username");
        JLabel forenameLabel = new JLabel("Forename");
        JLabel surnameLabel = new JLabel("Surname");
        JLabel titleLabel = new JLabel("Title");
        JLabel phoneNumberLabel = new JLabel("Phone Number");
        JLabel houseNumberLabel = new JLabel("House Number");
        JLabel streetNameLabel = new JLabel("Street Name");
        JLabel postCodeLabel = new JLabel("Post Code");
        JLabel cityLabel = new JLabel("City");
        JLabel countryLabel = new JLabel("Country");

        JTextField emailTF = new JTextField(30);
        JTextField usernameTF = new JTextField(30);
        JTextField forenameTF = new JTextField(20);
        JTextField surnameTF = new JTextField(20);

        JComboBox<String> titleCB = new JComboBox<String>();
        titleCB.addItem("");
        titleCB.addItem("Mr");
        titleCB.addItem("Mrs");
        titleCB.addItem("Miss");
        titleCB.addItem("Dr");
        JTextField phoneNumberTF = new JTextField(10);
        JTextField houseNumberTF = new JTextField(6);
        JTextField streetNameTF = new JTextField(30);
        JTextField postCodeTF = new JTextField(10);
        JTextField cityTF = new JTextField(30);
        JTextField countryTF = new JTextField(30);

        JComboBox<String> accountTypeCB = new JComboBox<String>();
        accountTypeCB.addItem("Guest");
        accountTypeCB.addItem("Host");

        JPasswordField passwordField = new JPasswordField(30);

        JCheckBox showPassword = new JCheckBox("Show password");

        JButton registerButton = new JButton("Register");
        JButton resetButton = new JButton("Reset");


        //Adding declared elements to their own panels and adding those panels to rowHolder (to work with scroll bars)
        JPanel accountTypePanel = new JPanel();
        accountTypePanel.add(accountTypeLabel);
        accountTypePanel.add(accountTypeCB);
        rowHolderPanel.add(accountTypePanel);

        JPanel emailPanel = new JPanel();
        emailPanel.add(emailLabel);
        emailPanel.add(emailTF);
        rowHolderPanel.add(emailPanel);

        JPanel passwordPanel = new JPanel();
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        passwordPanel.add(showPassword);
        rowHolderPanel.add(passwordPanel);

        JPanel usernamePanel = new JPanel();
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameTF);
        rowHolderPanel.add(usernamePanel);

        JPanel forenamePanel = new JPanel();
        forenamePanel.add(forenameLabel);
        forenamePanel.add(forenameTF);
        rowHolderPanel.add(forenamePanel);

        JPanel surnamePanel = new JPanel();
        surnamePanel.add(surnameLabel);
        surnamePanel.add(surnameTF);
        rowHolderPanel.add(surnamePanel);

        JPanel titlePanel = new JPanel();
        titlePanel.add(titleLabel);
        titlePanel.add(titleCB);
        rowHolderPanel.add(titlePanel);

        JPanel phoneNumberPanel = new JPanel();
        phoneNumberPanel.add(phoneNumberLabel);
        phoneNumberPanel.add(phoneNumberTF);
        rowHolderPanel.add(phoneNumberPanel);

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

        JPanel registerAndResetPanel = new JPanel();
        registerAndResetPanel.add(registerButton);
        registerAndResetPanel.add(resetButton);
        rowHolderPanel.add(registerAndResetPanel);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        passwordField.setEchoChar('*');

        //Action listener that shows the entered text in the password field when clicked on
        showPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(passwordField.getEchoChar()=='\u0000') {
                    passwordField.setEchoChar('*');
                }
                else{
                    passwordField.setEchoChar('\u0000');
                }
            }
        });
        //Action listener that clears all of the text fields (does not affect combo boxes)
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                emailTF.setText("");
                passwordField.setText("");
                usernameTF.setText("");
                forenameTF.setText("");
                surnameTF.setText("");
                houseNumberTF.setText("");
                streetNameTF.setText("");
                postCodeTF.setText("");
                cityTF.setText("");
                countryTF.setText("");
                phoneNumberTF.setText("");
            }
        });

        //When the register button is clicked
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Boolean registered = false;
                //assigning input from text fields
                int selectedIndex = accountTypeCB.getSelectedIndex();
                String userType = accountTypeCB.getItemAt(selectedIndex);
                int selectedIndex2 = accountTypeCB.getSelectedIndex();
                String title = titleCB.getItemAt(selectedIndex2);
                String email = emailTF.getText();
                String password = String.valueOf(passwordField.getPassword());
                String username = usernameTF.getText();
                String forename = forenameTF.getText();
                String surname = surnameTF.getText();
                String houseNumber = houseNumberTF.getText();
                String streetName = streetNameTF.getText();
                String postCode = postCodeTF.getText();
                String city = cityTF.getText();
                String country = countryTF.getText();
                String phoneNumber = phoneNumberTF.getText();

                //Validation for the text fields
                //TODO - actually validate the text fields instead of checking if they're empty
                String lengthErrorMsg = Validation.checkLengthRegister(email,password,username,forename,surname,
                        houseNumber,streetName, postCode,city,country,phoneNumber);
                if (Validation.checkEmptyRegister(email,password,username,forename,surname,houseNumber,streetName,
                        postCode,city,country,phoneNumber)){
                    JOptionPane.showMessageDialog(frame, "Please make sure all fields are filled in!");}
                else if(!Validation.checkEmail(email)){
                    JOptionPane.showMessageDialog(frame, "Invalid Email!");}
                else if(!Validation.checkName(forename)){
                    JOptionPane.showMessageDialog(frame, "Invalid forename!");}
                else if(!Validation.checkName(surname)){
                    JOptionPane.showMessageDialog(frame, "Invalid surname!");}
                else if(!Validation.checkHasLetterOrDigit(username)){
                    JOptionPane.showMessageDialog(frame, "Invalid username!");}
                else if(!Validation.checkIsNumbers(houseNumber)){
                    JOptionPane.showMessageDialog(frame, "Invalid House number!");}
                else if(!Validation.checkName(streetName)){
                    JOptionPane.showMessageDialog(frame, "Invalid Street Name!");}
                else if(!Validation.checkHasLetterOrDigit(postCode)){
                    JOptionPane.showMessageDialog(frame, "Invalid Post Code!");}
                else if(!Validation.checkName(city)){
                    JOptionPane.showMessageDialog(frame, "Invalid City Name!");}
                else if(!Validation.checkName(country)){
                    JOptionPane.showMessageDialog(frame, "Invalid Country Name!");}
                else if(!Validation.checkIsNumbers(phoneNumber)){
                    JOptionPane.showMessageDialog(frame, "Invalid Phone Number!");}
                else if(!(lengthErrorMsg == "")){
                    JOptionPane.showMessageDialog(frame,lengthErrorMsg);}
                else{
                    //Checks if the email has already been used or not
                    //account, host, guest, address
                    if (userType.equals("Host")){
                        if (new HostsModel(email).getHost()==null){
                            //adds new address to DB
                            address = new AddressModel(Integer.valueOf(houseNumber),streetName,postCode,city,country);
                            //sets the address ID for object address, used as FK for Account Obj
                            address.setAddressID();
                            PasswordManager pw = new PasswordManager();
                            //generate a salt and hash user's password with it
                            String salt = PasswordManager.generateSalt(8);
                            String hashedPW = PasswordManager.hashPassword(password, salt);

                            //insert account if the email doesn't exist as a PK in Account
                            if (new AccountModel(email).getAccount()==null){
                                Account newAcc = new Account(email, hashedPW, salt, username,
                                        forename, surname, phoneNumber, address.getAddress().getAddressID(),
                                        title);
                                new AccountModel(newAcc).insertAccountRow();
                            }
                            //insert to Hosts table
                            Hosts host = new Hosts(0, 0, email, hashedPW, salt, username,
                                    forename, surname, phoneNumber, address.getAddress().getAddressID(), title);
                            hostAccount = new HostsModel(host);
                            hostAccount.insertHost(email);
                            registered = true;


                        }
                        else{JOptionPane.showMessageDialog(frame, "Email has already been taken!");}
                    }
                    else {
                        //insert account if the email doesn't exist as a PK in Account
                        if (new GuestsModel(email).getGuest() == null) {
                            address = new AddressModel(Integer.valueOf(houseNumber), streetName, postCode, city, country);
                            address.setAddressID();
                            PasswordManager pw = new PasswordManager();
                            //generate a salt and hash user's password with it
                            String salt = PasswordManager.generateSalt(8);
                            String hashedPW = PasswordManager.hashPassword(password, salt);
                            //-1 as a placeholder, could use default to generate id

                            //insert account if the email doesn't exist as a PK in Account
                            if (new AccountModel(email).getAccount() == null) {
                                Account newAcc = new Account(email, hashedPW, salt, username,
                                        forename, surname, phoneNumber, address.getAddress().getAddressID(), title);
                                new AccountModel(newAcc).insertAccountRow();
                            }
                            //insert to Guest table
                            guestAccount = new GuestsModel();
                            guestAccount.insertGuest(email);
                            registered = true;
                        } else {
                            JOptionPane.showMessageDialog(frame, "Email has already been taken!");
                        }

                    }
                    if (registered){
                        //after registration, redirect to main page
                        Container contentPane = mainFrame.getContentPane();
                        revalidate();
                        repaint();
                        NavigationBarPanel navigationBarPanel = new NavigationBarPanel(mainFrame);
                        HomePanel homePanel = new HomePanel(mainFrame);
                        contentPane.removeAll();
                        contentPane.add(navigationBarPanel,BorderLayout.NORTH);
                        contentPane.add(homePanel);
                        revalidate();
                        repaint();
                    }
                }
            }
        });
    }
}
