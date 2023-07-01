package COM2006.project;

import COM2006.project.tables.*;
import COM2006.project.controllers.LoginController;
import COM2006.project.PasswordManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LoginPanel extends JPanel{
    private MainFrame mainFrame;
    private GuestsModel guestAccount;
    private HostsModel hostAccount;
    private JPanel rowHolderPanel = new JPanel(new GridLayout(0,1,1,1));

    public LoginPanel(MainFrame frame){
        mainFrame=frame;

        //Creating JScrollPane to add components too
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(rowHolderPanel, BorderLayout.PAGE_START);
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //Creating components to add to panel
        JLabel emailLabel = new JLabel("Email");
        JLabel passwordLabel = new JLabel("Password");
        JTextField emailTF = new JTextField(30);
        JPasswordField passwordField = new JPasswordField(30);
        JCheckBox showPassword = new JCheckBox("Show password");
        JButton loginButton = new JButton("Login");
        JButton resetButton = new JButton("Reset");
        JLabel registerLabel = new JLabel("Don't have an account?");
        JButton registerButton = new JButton("Register");
        JComboBox<String> accountTypeCB = new JComboBox<String>();
        accountTypeCB.addItem("Guest");
        accountTypeCB.addItem("Host");

        //Adding components to separate panels
        JPanel emailPanel = new JPanel();
        emailPanel.add(emailLabel);
        emailPanel.add(emailTF);

        JPanel passwordPanel = new JPanel();
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);
        passwordPanel.add(showPassword);

        JPanel accountTypePanel = new JPanel();
        accountTypePanel.add(accountTypeCB);

        JPanel loginAndResetPanel = new JPanel();
        loginAndResetPanel.add(loginButton);
        loginAndResetPanel.add(resetButton);

        JPanel registerPanel = new JPanel();
        registerPanel.add(registerLabel);
        registerPanel.add(registerButton);

        //Adding panels to rowHolderPanel
        rowHolderPanel.add(emailPanel);
        rowHolderPanel.add(passwordPanel);
        rowHolderPanel.add(accountTypePanel);
        rowHolderPanel.add(loginAndResetPanel);
        rowHolderPanel.add(registerPanel);

        //Adding scrollPane to the MainFrame
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        passwordField.setEchoChar('*');

        //Action Listeners
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
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = accountTypeCB.getSelectedIndex();
                String userType = accountTypeCB.getItemAt(selectedIndex);
                String email = emailTF.getText();
                String password = String.valueOf(passwordField.getPassword());
                //Make below safe to put into DB
                if(!Validation.checkEmail(email)){
                    //Popup saying wrong email
                    JOptionPane.showMessageDialog(frame, "Invalid email");
                }
                else if(password.equals("")){
                    JOptionPane.showMessageDialog(frame, "Enter a password");
                }
                else {
                    if(new AccountModel(email).getAccount()==null){
                        JOptionPane.showMessageDialog(frame, "No account exists with that email");
                    }
                    else{
                        LoginController lController = new LoginController();
                        PasswordManager pwManager = new PasswordManager();
                        if(userType.equals("Guest")){
                            if(new GuestsModel(email).getGuest()==null) {
                                JOptionPane.showMessageDialog(frame, "No guest account exists with that email");
                            }
                            else {
                                guestAccount = new GuestsModel(email);
                                //Hashing inputted password
                                String hashedPassword = pwManager.hashPassword(password,
                                        guestAccount.getGuest().getSalt());
                                //checking if password for guest account is correct
                                if (guestAccount.getGuest().getPassword().equals(hashedPassword)) {
                                    Container contentPane = mainFrame.getContentPane();
                                    revalidate();
                                    repaint();
                                    NavigationBarPanel navigationBarPanel = new NavigationBarPanel(mainFrame,guestAccount);
                                    HomePanel homePanel = new HomePanel(mainFrame,guestAccount);
                                    contentPane.removeAll();
                                    contentPane.add(navigationBarPanel, BorderLayout.NORTH);
                                    contentPane.add(homePanel);
                                    revalidate();
                                    repaint();
                                } else {
                                    JOptionPane.showMessageDialog(frame, "Incorrect Password");
                                }
                            }
                        }
                        else{
                            if(new HostsModel(email).getHost()==null) {
                                JOptionPane.showMessageDialog(frame, "No host account exists with that email");
                            }
                            else {
                                hostAccount = new HostsModel(email);
                                //Hashing inputted password
                                String hashedPassword = pwManager.hashPassword(password,
                                        hostAccount.getHost().getSalt());
                                //checking if password for host account is correct
                                if (hostAccount.getHost().getPassword().equals(hashedPassword)) {
                                    Container contentPane = mainFrame.getContentPane();
                                    revalidate();
                                    repaint();
                                    NavigationBarPanel navigationBarPanel = new NavigationBarPanel(mainFrame,hostAccount);
                                    HomePanel homePanel = new HomePanel(mainFrame, hostAccount);
                                    contentPane.removeAll();
                                    contentPane.add(navigationBarPanel, BorderLayout.NORTH);
                                    contentPane.add(homePanel);
                                    revalidate();
                                    repaint();
                                } else {
                                    JOptionPane.showMessageDialog(frame, "Incorrect Password");
                                }
                            }
                        }
                    }
                }
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                emailTF.setText("");
                passwordField.setText("");
            }
        });

        //Action listener to take user to register page
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Container contentPane = mainFrame.getContentPane();
                revalidate();
                repaint();
                NavigationBarPanel navigationBarPanel = new NavigationBarPanel(mainFrame);
                RegisterPanel registerPanel = new RegisterPanel(mainFrame);
                contentPane.removeAll();
                contentPane.add(navigationBarPanel,BorderLayout.NORTH);
                contentPane.add(registerPanel);
                revalidate();
                repaint();

            }
        });
    }
}
