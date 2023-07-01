package COM2006.project;

import COM2006.project.tables.GuestsModel;
import COM2006.project.tables.HostsModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class NavigationBarPanel extends JPanel{

    private MainFrame mainFrame;
    private HostsModel hostAccount;
    private GuestsModel guestAccount;


    public NavigationBarPanel(MainFrame frame) {
        mainFrame=frame;

        JPanel navigationBarPanel = new JPanel();
        navigationBarPanel.setLayout(new FlowLayout());
        JButton homeButton = new JButton("Home");
        add(homeButton);
        JButton viewPropertiesButton = new JButton("View Properties");
        add(viewPropertiesButton);
        JButton loginButton = new JButton("Login");
        add(loginButton);

        //Action listener to take user to home panel
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Container contentPane = mainFrame.getContentPane();
                revalidate();
                repaint();
                HomePanel homePanel = new HomePanel(mainFrame);
                NavigationBarPanel navigationBarPanel = new NavigationBarPanel(mainFrame);
                contentPane.removeAll();
                contentPane.add(navigationBarPanel,BorderLayout.NORTH);
                contentPane.add(homePanel);
                revalidate();
                repaint();
            }
        });

        //Action listener to take user to view properties panel
        viewPropertiesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Container contentPane = mainFrame.getContentPane();
                revalidate();
                repaint();
                ViewPropertiesPanel viewPropertiesPanel = new ViewPropertiesPanel(mainFrame);
                NavigationBarPanel navigationBarPanel = new NavigationBarPanel(mainFrame);
                contentPane.removeAll();
                contentPane.add(navigationBarPanel,BorderLayout.NORTH);
                contentPane.add(viewPropertiesPanel);
                revalidate();
                repaint();
            }
        });

        //Action listener to take user to login panel
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Container contentPane = mainFrame.getContentPane();
                revalidate();
                repaint();
                LoginPanel loginPanel = new LoginPanel(mainFrame);
                NavigationBarPanel navigationBarPanel = new NavigationBarPanel(mainFrame);
                contentPane.removeAll();
                contentPane.add(navigationBarPanel,BorderLayout.NORTH);
                contentPane.add(loginPanel);
                revalidate();
                repaint();
            }
        });
    }
    public NavigationBarPanel(MainFrame frame,GuestsModel account) {
        mainFrame=frame;
        guestAccount=account;

        JPanel navigationBarPanel = new JPanel();
        navigationBarPanel.setLayout(new FlowLayout());
        JButton homeButton = new JButton("Home");
        add(homeButton);
        JButton viewPropertiesButton = new JButton("View Properties");
        add(viewPropertiesButton);
        JButton accountButton = new JButton("Account");
        add(accountButton);
        JButton logoutButton = new JButton("Logout");
        add(logoutButton);

        //Action listener to take logged in guest to home panel
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Container contentPane = mainFrame.getContentPane();
                revalidate();
                repaint();
                HomePanel homePanel = new HomePanel(mainFrame,guestAccount);
                NavigationBarPanel navigationBarPanel = new NavigationBarPanel(mainFrame,guestAccount);
                contentPane.removeAll();
                contentPane.add(navigationBarPanel,BorderLayout.NORTH);
                contentPane.add(homePanel);
                revalidate();
                repaint();
            }
        });

        //Action listener to take logged in guest to view properties panel
        viewPropertiesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Container contentPane = mainFrame.getContentPane();
                revalidate();
                repaint();
                ViewPropertiesPanel viewPropertiesPanel = new ViewPropertiesPanel(mainFrame,guestAccount);
                NavigationBarPanel navigationBarPanel = new NavigationBarPanel(mainFrame,guestAccount);
                contentPane.removeAll();
                contentPane.add(navigationBarPanel,BorderLayout.NORTH);
                contentPane.add(viewPropertiesPanel);
                revalidate();
                repaint();
            }
        });

        //Action listener to take logged in guest to account panel
        accountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Container contentPane = mainFrame.getContentPane();
                revalidate();
                repaint();
                AccountPanel accountPanel = new AccountPanel(mainFrame,guestAccount);
                NavigationBarPanel navigationBarPanel = new NavigationBarPanel(mainFrame,guestAccount);
                contentPane.removeAll();
                contentPane.add(navigationBarPanel,BorderLayout.NORTH);
                contentPane.add(accountPanel);
                revalidate();
                repaint();
            }
        });

        //Action listener to logout logged in guest
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Container contentPane = mainFrame.getContentPane();
                revalidate();
                repaint();
                HomePanel homePanel = new HomePanel(mainFrame);
                NavigationBarPanel navigationBarPanel = new NavigationBarPanel(mainFrame);
                contentPane.removeAll();
                contentPane.add(navigationBarPanel,BorderLayout.NORTH);
                contentPane.add(homePanel);
                revalidate();
                repaint();
            }
        });
    }
    public NavigationBarPanel(MainFrame frame,HostsModel account) {
        mainFrame=frame;
        hostAccount=account;

        JPanel navigationBarPanel = new JPanel();
        navigationBarPanel.setLayout(new FlowLayout());
        JButton homeButton = new JButton("Home");
        add(homeButton);
        JButton accountButton = new JButton("Account");
        add(accountButton);
        JButton logoutButton = new JButton("Logout");
        add(logoutButton);

        //Action listener to take logged in host to home panel
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Container contentPane = mainFrame.getContentPane();
                revalidate();
                repaint();
                HomePanel homePanel = new HomePanel(mainFrame,hostAccount);
                NavigationBarPanel navigationBarPanel = new NavigationBarPanel(mainFrame,hostAccount);
                contentPane.removeAll();
                contentPane.add(navigationBarPanel,BorderLayout.NORTH);
                contentPane.add(homePanel);
                revalidate();
                repaint();
            }
        });

        //Action listener to take logged in host to account panel
        accountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Container contentPane = mainFrame.getContentPane();
                revalidate();
                repaint();
                AccountPanel accountPanel = new AccountPanel(mainFrame,hostAccount);
                NavigationBarPanel navigationBarPanel = new NavigationBarPanel(mainFrame,hostAccount);
                contentPane.removeAll();
                contentPane.add(navigationBarPanel,BorderLayout.NORTH);
                contentPane.add(accountPanel);
                revalidate();
                repaint();
            }
        });

        //Action listener to logout logged in host
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Container contentPane = mainFrame.getContentPane();
                revalidate();
                repaint();
                HomePanel homePanel = new HomePanel(mainFrame);
                NavigationBarPanel navigationBarPanel = new NavigationBarPanel(mainFrame);
                contentPane.removeAll();
                contentPane.add(navigationBarPanel,BorderLayout.NORTH);
                contentPane.add(homePanel);
                revalidate();
                repaint();
            }
        });

    }
}
