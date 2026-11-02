import javax.swing.*;
import java.beans.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.Flow;


/**
 * View creates the window where the inputs/animation will
 * be presented to the user.
 * 
 * @author  Zackary Nelson, Bradley Patton, Peyton Slusser, Ulises Royal
 * @version 11-2-2026
 * @since   11-1-2026
 */
public class View implements ActionListener, PropertyChangeListener {
    
    private JFrame frame = new JFrame ("Bouncing Ball");
    private JPanel pane = new JPanel(new BorderLayout());

    private JButton start = new JButton ("Start");

    private JButton pauseResumeButton = new JButton ("Pause/Resume");
    private JButton reset = new JButton ("Reset");

    private JPanel ballAnimationPanel = new JPanel();


    private JPanel inputPanel = new JPanel(new FlowLayout());
    private JLabel timeInputLabel = new JLabel("Enter Time in Seconds for Ball to Bounce:");
    private JTextField timeInputField = new JTextField();

    private JLabel progressLabel = new JLabel("Bouncing Ball Progress:");
    private JPanel progressPanel = new JPanel(new FlowLayout());
    private JProgressBar bounceProgressBar = new JProgressBar();
    private JLabel progressTimeElapsedLabel = new JLabel("Time Elapsed: ");
    private double progressCountDown;

    private  int userInput;


    /**
     * Constructor for the bouncing ball animation main window.
     * 
     * @since 11-1-2026
     */
    public View(){

    System.out.println("Main Bouncing Ball View Window is Being Called!");
    
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);

    //input panels
    timeInputField.setPreferredSize(new Dimension(150, 25));
    inputPanel.add(timeInputLabel);
    inputPanel.add(timeInputField);
    inputPanel.add(start);
    inputPanel.add(pauseResumeButton);
    inputPanel.add(reset);
    
    //animation panel
    ballAnimationPanel.setPreferredSize(new Dimension(600, 400));

    //progress panels
    progressPanel.add(progressLabel);
    progressPanel.add(bounceProgressBar);
    progressPanel.add(progressTimeElapsedLabel);

    //where the panels are added to the frame
    pane.add(inputPanel, BorderLayout.NORTH);
    pane.add(ballAnimationPanel, BorderLayout.CENTER);
    pane.add(progressPanel, BorderLayout.SOUTH);

    //final formalizing of the window frame with all previously designed information
    frame.add(pane);
    frame.pack();
    frame.setVisible(true);
    frame.setResizable(false);


    }
}
