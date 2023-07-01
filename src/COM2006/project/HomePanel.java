package COM2006.project;

import COM2006.project.tables.*;

import java.sql.Array;
import java.sql.Date;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class HomePanel extends JPanel{
    private JPanel rowHolderPanel = new JPanel(new GridLayout(0, 1, 1, 1));
    private MainFrame mainFrame;

    public HomePanel(MainFrame frame) {
        mainFrame=frame;

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(rowHolderPanel, BorderLayout.PAGE_START);
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JLabel welcomeMsg = new JLabel("Welcome to HomeBreak. Please login or register ");
        JPanel welcomeMsgPanel = new JPanel();
        welcomeMsgPanel.add(welcomeMsg);
        rowHolderPanel.add(welcomeMsgPanel);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }

    //If user is logged in and is a guest
    //Show them their confirmed bookings (if they have any) with property name, Start date, End date, Host name, Total Cost
    //Host confidential contact details, Confidential property address.
    //Show their provisional bookings with property name, start date, end date.
    public HomePanel(MainFrame frame, GuestsModel guest) {
        mainFrame=frame;
        JPanel homePanel = new JPanel();
        Container contentPane = mainFrame.getContentPane();
        JPanel outerPanel = new JPanel(new BorderLayout());
        outerPanel.add(rowHolderPanel, BorderLayout.PAGE_START);
        JScrollPane scrollPane = new JScrollPane(outerPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JLabel welcomeMsg = new JLabel("Welcome, " + guest.getGuest().getUsername(), SwingConstants.CENTER);
        JPanel welcomePanel = new JPanel();
        welcomePanel.add(welcomeMsg);
        rowHolderPanel.add(welcomePanel);
        //Setting up data for tables
        //All Bookings table data
        if (guest.getAllBookings() != null) {
            String[] columnNameA = {"Property", "Start Date", "End Date"};
            ArrayList<ArrayList<Object>> dataA = guest.getProvisionalBookings();

            //Converting 2d arraylist to 2d array
            Object[][] data2DA = dataA.stream().map(u -> u.toArray(new Object[0])).toArray(Object[][]::new);

            //Confirmed Booking table data
            String[] columnNameC = {"Property", "Start Date", "End Date", "Host", "Contact Details", "Address"};
            ArrayList<ArrayList<Object>> dataC = guest.getConfirmedBookings();

            //Converting 2d arraylist to 2d array
            Object[][] data2DC = dataC.stream().map(u -> u.toArray(new Object[0])).toArray(Object[][]::new);

            //Confirmed Bookings table
            JLabel confirmedBooking = new JLabel("<HTML><U>Confirmed Bookings</U><HTML>", SwingConstants.CENTER);
            confirmedBooking.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            JPanel confirmedPanel = new JPanel();
            confirmedPanel.add(confirmedBooking);


            JTable confirmedBookingTable = new JTable(data2DC, columnNameC) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }

                public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                    Component component = super.prepareRenderer(renderer, row, column);
                    int rendererWidth = component.getPreferredSize().width;
                    TableColumn tableColumn = getColumnModel().getColumn(column);
                    tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
                    return component;
                }
            };
            TableColumn column4 = confirmedBookingTable.getColumnModel().getColumn(4);
            column4.setMinWidth(100);
            column4.setPreferredWidth(100);




            confirmedBookingTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            confirmedBookingTable.getTableHeader().setReorderingAllowed(false);
            JScrollPane confirmedBookingSP = new JScrollPane(confirmedBookingTable,
                    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            //confirmedBookingSP.getVerticalScrollBar().setValue(2);

            Dimension preferredSize = confirmedBookingTable.getPreferredSize();
            int width = (int) Math.ceil(preferredSize.getWidth());
            Dimension test = new Dimension(width,70);
            confirmedBookingTable.setPreferredScrollableViewportSize(test);
            confirmedBookingTable.setFillsViewportHeight(true);



            JPanel confirmedBookingPanel = new JPanel();
            confirmedBookingPanel.add(confirmedBookingSP);

            //All Bookings table
            JLabel allBookings = new JLabel("<HTML><U>Provisional Bookings</U><HTML>", SwingConstants.CENTER);
            allBookings.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            JPanel allBookingsHeader = new JPanel();
            allBookingsHeader.add(allBookings);
            JTable allBookingsTable = new JTable(data2DA, columnNameA) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                };
            };
            allBookingsTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            allBookingsTable.getTableHeader().setReorderingAllowed(false);
            JScrollPane allBookingsSP = new JScrollPane(allBookingsTable,
                    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            //allBookingsTable.setPreferredScrollableViewportSize(allBookingsTable.getPreferredSize());
            Dimension preferredSize2 = allBookingsTable.getPreferredSize();
            int width2 = (int) Math.ceil(preferredSize2.getWidth());
            Dimension test2 = new Dimension(width2,70);
            allBookingsTable.setPreferredScrollableViewportSize(test2);
            allBookingsTable.setFillsViewportHeight(true);

            JPanel allBookingsPanel = new JPanel();
            allBookingsPanel.add(allBookingsSP);

            //Past Bookings
            JPanel pastBookingHeader = new JPanel();
            JLabel pastBookingText = new JLabel("<HTML><U>Past Bookings</U><HTML>",SwingConstants.CENTER);
            pastBookingText.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            pastBookingHeader.add(pastBookingText);
            ArrayList<ArrayList<Object>> pastBookings = guest.getPastBookings();
            JPanel pastBookingPanel = new JPanel();


            rowHolderPanel.add(confirmedPanel);
            rowHolderPanel.add(confirmedBookingPanel);
            rowHolderPanel.add(allBookingsHeader);
            rowHolderPanel.add(allBookingsPanel);
            rowHolderPanel.add(pastBookingHeader);
            if(pastBookings.size()>0) {
                for (int i = 0; i < pastBookings.size(); i++) {
                    ArrayList<Object> data = pastBookings.get(i);
                    JPanel pastBookingsInfo = new JPanel();
                    pastBookingsInfo.add(new JLabel("Property Name:"));
                    pastBookingsInfo.add(new JLabel(data.get(1).toString()));
                    pastBookingsInfo.add(new JLabel("Start Date:"));
                    java.sql.Date sDate = (Date) data.get(2);
                    pastBookingsInfo.add(new JLabel(sDate.toString()));
                    pastBookingsInfo.add(new JLabel("End Date:"));
                    java.sql.Date eDate = (Date) data.get(3);
                    pastBookingsInfo.add(new JLabel(eDate.toString()));
                    JButton reviewButton = new JButton("Review");
                    int propertyID = (int) data.get(0);
                    pastBookingsInfo.add(reviewButton);
                    pastBookingPanel.add(pastBookingsInfo);

                    rowHolderPanel.add(pastBookingsInfo);

                    reviewButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            revalidate();
                            repaint();
                            contentPane.removeAll();
                            ReviewPanel reviewPanel = new ReviewPanel(mainFrame, propertyID, sDate, eDate, guest);
                            NavigationBarPanel navigationBarPanel = new NavigationBarPanel(mainFrame, guest);
                            contentPane.add(navigationBarPanel, BorderLayout.NORTH);
                            contentPane.add(reviewPanel);
                            revalidate();
                            repaint();
                        }
                    });
                }
            }

        }
        //Add alt role button
        AccountModel accModel = new AccountModel(guest.getGuest().getEmail());
        if (accModel.hasSecondRole() == false) {
            JButton switchRolesBtn = new JButton("Register");
            JLabel altSignupMsg = new JLabel("Register as a host:", SwingConstants.RIGHT);
            JPanel rolePanel = new JPanel();
            switchRolesBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent a){
                    Hosts host = new Hosts(0, 0, guest.getGuest().getEmail(),
                            guest.getGuest().getPassword(), guest.getGuest().getSalt(), guest.getGuest().getUsername(),
                            guest.getGuest().getForename(), guest.getGuest().getSurname(),
                            guest.getGuest().getPhoneNumber(), guest.getGuest().getAddressID_fk(),
                            guest.getGuest().getTitle());
                    HostsModel hostsMdl = new HostsModel(host);
                    hostsMdl.insertHost(guest.getGuest().getEmail());
                    HomePanel homePanelRole = new HomePanel(frame, guest);
                    NavigationBarPanel navigationBarPanel = new NavigationBarPanel(frame,guest);
                    revalidate();
                    repaint();
                    contentPane.removeAll();
                    contentPane.add(navigationBarPanel,BorderLayout.NORTH);
                    contentPane.add(homePanelRole);
                    revalidate();
                    repaint();
                }
            });
            rolePanel.add(altSignupMsg);
            rolePanel.add(switchRolesBtn);
            rowHolderPanel.add(rolePanel);
        }
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }

    //If user is logged in as host
    //Show provisional bookings for their properties with Property name, start date, end date and buttons to confirm or deny
    //Confirm button should also delete any other provisional booking for a property that overlap
    //Deny button deletes that booking request
    //Show confirmed bookings for their properties with property name, start date, end date, guest name, guest email.
    public HomePanel(MainFrame frame, HostsModel host) {
        mainFrame=frame;

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(rowHolderPanel, BorderLayout.PAGE_START);
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JPanel homePanel = new JPanel();
        Container contentPane = mainFrame.getContentPane();
        homePanel.add(new JLabel("Welcome, " + host.getHost().getUsername(), SwingConstants.CENTER));
        rowHolderPanel.add(homePanel);

        //Current Date
        long millis=System.currentTimeMillis();
        java.sql.Date currentDate=new java.sql.Date(millis);

        //Confirmed Bookings table
        String[] columnName = {"Property","Start Date","End Date","Guest","Phone Number"};
        ArrayList<ArrayList<Object>> data = host.getConfirmedBookings();

        //Converting 2d arraylist to 2d array
        Object[][] data2D = data.stream().map(u -> u.toArray(new Object[0])).toArray(Object[][]::new);

        //Confirmed Booking table
        JTable allBookingsTable = new JTable(data2D,columnName){
            public boolean isCellEditable(int row, int column) {
                return false;
            };
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component component = super.prepareRenderer(renderer, row, column);
                int rendererWidth = component.getPreferredSize().width;
                TableColumn tableColumn = getColumnModel().getColumn(column);
                tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
                return component;
            }
        };

        TableColumn column4 = allBookingsTable.getColumnModel().getColumn(4);
        column4.setMinWidth(100);
        column4.setPreferredWidth(100);

        allBookingsTable.getTableHeader().setReorderingAllowed(false);
        JScrollPane allBookingsPanel = new JScrollPane(allBookingsTable,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        allBookingsTable.setPreferredScrollableViewportSize(allBookingsTable.getPreferredSize());
        allBookingsTable.setFillsViewportHeight(true);
        JPanel confirmedBookingPanel = new JPanel();
        confirmedBookingPanel.add(allBookingsPanel);

        rowHolderPanel.add(new JLabel("Confirmed Bookings", SwingConstants.CENTER));
        rowHolderPanel.add(confirmedBookingPanel);
        rowHolderPanel.add(new JLabel("Provisional Bookings", SwingConstants.CENTER));

        //Confirmation for provisional bookings
        ArrayList<ArrayList<Object>> provisionalBookings = host.getProvisionalBookings();
        int counter = 1;
        for(int i = 0; i<provisionalBookings.size(); i++) {
            java.sql.Date endDate = ((Date) provisionalBookings.get(i).get(2));

            if (endDate.compareTo(currentDate) > 0) {
                JLabel countLabel = new JLabel(counter + ")  ");
                JLabel bookingIDLabel = new JLabel(provisionalBookings.get(i).get(0).toString());
                JLabel propertyNameLabel = new JLabel(provisionalBookings.get(i).get(1).toString());
                JLabel startDateLabel = new JLabel(provisionalBookings.get(i).get(2).toString());
                JLabel endDateLabel = new JLabel(provisionalBookings.get(i).get(3).toString());
                JButton acceptButton = new JButton("Accept");
                JButton denyButton = new JButton("Deny");

                JPanel textPanel = new JPanel();
                textPanel.add(countLabel);
                textPanel.add(new JLabel("BookingID: "));
                textPanel.add(bookingIDLabel);
                textPanel.add(new JLabel(","));
                textPanel.add(propertyNameLabel);
                textPanel.add(new JLabel(", Starting at"));
                textPanel.add(startDateLabel);
                textPanel.add(new JLabel(", Ending at"));
                textPanel.add(endDateLabel);

                textPanel.add(acceptButton);
                textPanel.add(denyButton);
                rowHolderPanel.add(textPanel);
                counter++;


                acceptButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent a){
                        revalidate();
                        repaint();
                        int bookedID = Integer.parseInt(bookingIDLabel.getText());
                        BookingsModel booking = new BookingsModel(bookedID);
                        ArrayList<Integer> overLappedBookings = new ArrayList<>(booking.getOverlappedBookings());
                        booking.confirmBookings();

                        if(!overLappedBookings.isEmpty()){
                            for(int i=0;i<overLappedBookings.size();i++){
                                BookingsModel overlappedBooking = new BookingsModel(overLappedBookings.get(i));
                                overlappedBooking.removeBookings();
                            }
                        }
                        //Updates panel
                        NavigationBarPanel navigationBarPanel = new NavigationBarPanel(mainFrame,host);
                        HomePanel homePanel = new HomePanel(mainFrame,host);
                        contentPane.removeAll();
                        contentPane.add(navigationBarPanel, BorderLayout.NORTH);
                        contentPane.add(homePanel);
                        revalidate();
                        repaint();

                    }
                });
                denyButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent a){
                        revalidate();
                        repaint();

                        int bookedID = Integer.parseInt(bookingIDLabel.getText());
                        BookingsModel booking = new BookingsModel(bookedID);
                        booking.removeBookings();

                        //Updates panel
                        NavigationBarPanel navigationBarPanel = new NavigationBarPanel(mainFrame,host);
                        HomePanel homePanel = new HomePanel(mainFrame,host);
                        contentPane.removeAll();
                        contentPane.add(navigationBarPanel, BorderLayout.NORTH);
                        contentPane.add(homePanel);
                        revalidate();
                        repaint();
                    }
                });
           }
        }
        //Add alt role button
        AccountModel accModel = new AccountModel(host.getHost().getEmail());

        if (accModel.hasSecondRole() == false) {
            JButton switchRolesBtn = new JButton("Register");
            JLabel altSignupMsg = new JLabel("Register as a guest:", SwingConstants.RIGHT);
            JPanel rolePanel = new JPanel();
            switchRolesBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent a){
                    GuestsModel guestsMdl = new GuestsModel();
                    guestsMdl.insertGuest(host.getHost().getEmail());
                    HomePanel homePanelRole = new HomePanel(frame, host);
                    NavigationBarPanel navigationBarPanel = new NavigationBarPanel(frame,host);
                    revalidate();
                    repaint();
                    contentPane.removeAll();
                    contentPane.add(navigationBarPanel,BorderLayout.NORTH);
                    contentPane.add(homePanelRole);
                    revalidate();
                    repaint();
                }
            });

            rolePanel.add(altSignupMsg);
            rolePanel.add(switchRolesBtn);
            rowHolderPanel.add(rolePanel);
        }
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }

}
