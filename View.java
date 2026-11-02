import javax.swing.*;
import java.beans.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


/**
 * View creates the window where the inputs/animation will
 * be presented to the user.
 * 
 * @author  Zackary Nelson, Bradley Patton, Peyton Slusser, Ulises Royal
 * @version 11-1-2026
 * @since   11-1-2026
 */
public class View implements ActionListener {
    
    private JFrame frame;
    private JPanel pane;

    private JButton start;
    private JButton pause;
    private JButton resume;
    private JButton restart;

    /**
     * Constructor for the bouncing ball animation main window.
     * 
     * @since 11-1-2026
     */
    public View(){

    System.out.println("Main Bouncing Ball View Window is Being Called!");
    
    frame = new JFrame ("Bouncing Ball");
    pane = new JPanel (new BorderLayout());

    start = new JButton ("Start");
    pause = new JButton ("Pause");
    resume = new JButton ("Resume");
    restart = new JButton ("Restart");


    }
}
