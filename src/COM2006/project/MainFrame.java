package COM2006.project;

import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame{
    //Needed for serialisation
    private static final long serialVersionUID = 1L;
    //JPanel buttonPanel = new JPanel();
    //Constructor with frame title
    public MainFrame (String title) throws HeadlessException{
        //Construction code goes here
        super(title);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        //Setting size and location of the application
        setSize(screenSize.width/2,screenSize.height/2);
        setLocation(screenSize.width/4,screenSize.height/4);

        //Creating initial HomePanel view
        Container contentPane = this.getContentPane();
        contentPane.setLayout(new BorderLayout());
        NavigationBarPanel navigationBarPanel = new NavigationBarPanel(this);
        HomePanel homePanel = new HomePanel(this);
        contentPane.add(navigationBarPanel,BorderLayout.NORTH);
        contentPane.add(homePanel);

        //Ensures java terminates on close
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setVisible(true);

    }
    //Launching the application
    public static void main(String[] args) {
        new MainFrame("My Application");

    }
}
