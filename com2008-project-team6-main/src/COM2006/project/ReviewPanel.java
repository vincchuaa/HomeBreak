package COM2006.project;
import COM2006.project.tables.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ReviewPanel extends JPanel{
    private MainFrame mainFrame;
    private  GuestsModel guestAccount;
    private JPanel rowHolderPanel = new JPanel(new GridLayout(0,1,1,1));

    public ReviewPanel(MainFrame frame, int propertyID, java.sql.Date sDate, java.sql.Date eDate, GuestsModel guest) {
        mainFrame=frame;
        guestAccount=guest;

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(rowHolderPanel, BorderLayout.PAGE_START);
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //setLayout(new GridLayout(4,1));

        JLabel header = new JLabel("<HTML><U>Write A Review</U><HTML>",SwingConstants.CENTER);
        header.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        //Review description
        JPanel commentSection = new JPanel();
        JTextArea comments = new JTextArea(6,50);
        JLabel describeLabel = new JLabel("Describe your experience", SwingConstants.CENTER);
        JPanel describePanel = new JPanel();
        describePanel.add(describeLabel);
        commentSection.add(comments);

        //Ratings
        JPanel ratingSection = new JPanel();
        ArrayList<Integer> comboBoxProperties = new ArrayList<>();
        for(int i=0;i<=5;i++){
            comboBoxProperties.add(i);
        }

        JPanel cleanlinessPanel = new JPanel();
        JPanel communicationPanel = new JPanel();
        JPanel checkInPanel = new JPanel();
        JPanel accuracyPanel = new JPanel();
        JPanel locationPanel = new JPanel();
        JPanel valuePanel = new JPanel();

        JComboBox<Integer> cleanliness = new JComboBox<>();
        JComboBox<Integer> communication = new JComboBox<>();
        JComboBox<Integer> checkIn = new JComboBox<>();
        JComboBox<Integer> accuracy = new JComboBox<>();
        JComboBox<Integer> location = new JComboBox<>();
        JComboBox<Integer> value = new JComboBox<>();
        for(int i=0;i<=5;i++){
            cleanliness.addItem(comboBoxProperties.get(i));
            communication.addItem(comboBoxProperties.get(i));
            checkIn.addItem(comboBoxProperties.get(i));
            accuracy.addItem(comboBoxProperties.get(i));
            location.addItem(comboBoxProperties.get(i));
            value.addItem(comboBoxProperties.get(i));
        }

        cleanlinessPanel.add(new JLabel("Cleanliness:"));
        cleanlinessPanel.add(cleanliness);
        communicationPanel.add(new JLabel("Communication:"));
        communicationPanel.add(communication);
        checkInPanel.add(new JLabel("CheckIn:"));
        checkInPanel.add(checkIn);
        accuracyPanel.add(new JLabel("Accuracy:"));
        accuracyPanel.add(accuracy);
        locationPanel.add(new JLabel("Location:"));
        locationPanel.add(location);
        valuePanel.add(new JLabel("Value:"));
        valuePanel.add(value);

        ratingSection.add(cleanlinessPanel);
        ratingSection.add(communicationPanel);
        ratingSection.add(checkInPanel);
        ratingSection.add(accuracyPanel);
        ratingSection.add(locationPanel);
        ratingSection.add(valuePanel);



        JButton submitReview = new JButton("Submit");
        JPanel submit = new JPanel();
        submit.add(submitReview);
        rowHolderPanel.add(header);
        rowHolderPanel.add(describePanel);
        rowHolderPanel.add(commentSection);
        rowHolderPanel.add(ratingSection);
        rowHolderPanel.add(submit);

        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        submitReview.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                revalidate();
                repaint();
                boolean submitted = false;
                String comment = comments.getText();
                int cleanlinessRating = cleanliness.getSelectedIndex();
                int communicationRating = communication.getSelectedIndex();
                int checkInRating = checkIn.getSelectedIndex();
                int accuracyRating = accuracy.getSelectedIndex();
                int locationRating = location.getSelectedIndex();
                int valueRating = value.getSelectedIndex();
                ArrayList<Integer> ratings = new ArrayList<>();
                ratings.add(cleanlinessRating);
                ratings.add(communicationRating);
                ratings.add(checkInRating);
                ratings.add(accuracyRating);
                ratings.add(locationRating);
                ratings.add(valueRating);
                if(comment.equals("")||ratings.contains(0))
                    JOptionPane.showMessageDialog(mainFrame, "Please make sure all fields are filled in!");
                else{
                    Reviews review = new Reviews(0,propertyID,comment,cleanlinessRating,communicationRating,
                            checkInRating,accuracyRating,locationRating,valueRating);
                    ReviewModel reviewModel = new ReviewModel(propertyID);
                    reviewModel.setReview(review);
                    reviewModel.insertReviewRow();
                    submitted = true;
                }
                if(submitted){
                    PropertyModel ptyMdl = new PropertyModel(propertyID);
                    HostsModel hostsModel = new HostsModel(ptyMdl.getProperty().getHostID_fk());
                    if (hostsModel.calculateAvgHostRating() < 4.7) {
                        hostsModel.setSuperHost(0);

                    }
                    else {
                        hostsModel.setSuperHost(1);
                    }
                    Bookings booking = new Bookings(0,propertyID,0,sDate,eDate,0);
                    BookingsModel bookingsModel = new BookingsModel();
                    bookingsModel.setBookings(booking);
                    bookingsModel.removePastBooking(propertyID,sDate,eDate);
                    Container contentPane = mainFrame.getContentPane();
                    revalidate();
                    repaint();
                    NavigationBarPanel navigationBarPanel = new NavigationBarPanel(mainFrame, guestAccount);
                    HomePanel homePanel = new HomePanel(mainFrame, guestAccount);
                    contentPane.removeAll();
                    contentPane.add(navigationBarPanel,BorderLayout.NORTH);
                    contentPane.add(homePanel);
                    revalidate();
                    repaint();
                }
            }
        });
    }
}
