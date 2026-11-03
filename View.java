import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;


/**
 * View creates the window where the inputs/animation will
 * be presented to the user.
 * 
 * @author  Zackary Nelson, Bradley Patton, Peyton Slusser, Ulises Royal
 * @version 11-2-2026
 * @since   11-1-2026
 */
public class View{
    
    private JFrame frame = new JFrame ("Bouncing Ball");
    private JPanel pane = new JPanel(new BorderLayout());

    private JButton start = new JButton ("Start");

    private JButton pauseResumeButton = new JButton ("Pause/Resume");

    private JButton reset = new JButton ("Reset");

    private JPanel ballAnimationPanel = new JPanel(){
        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);

            if(ball != null){
                ball.draw(g);
            }
        }
    };


    private JPanel inputPanel = new JPanel(new FlowLayout());
    private JLabel timeInputLabel = new JLabel("Enter Time in Seconds for Ball to Bounce:");
    private JTextField timeInputField = new JTextField();

    private JLabel progressLabel = new JLabel("Bouncing Ball Progress:");
    private JPanel progressPanel = new JPanel(new FlowLayout());
    private JProgressBar bounceProgressBar = new JProgressBar();
    private JLabel progressTimeElapsedLabel = new JLabel("Time Left: 0.00");
    private double progressCountDown;

    private  int userInput;

    //bouncing ball stuff
    private Ball ball;
    private ImageIcon ballImage;

    private Timer ballTimer;

    private int BALL_CENTER_OG_VALUES = 100;
    private int BALL_OG_RADIUS = 100;
    private int OG_DX_AND_DY = 3;

    private boolean hasStarted = false;
    //timer trackers
    private long totalTime;
    private long timeElapsed;
    private long totalTimeleft;

    /**
     * Constructor for the bouncing ball animation main window.
     * 
     * @since 11-1-2026
     */
    public View(){

    System.out.println("Main Bouncing Ball View Window is Being Called!");
    
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);

    start.addActionListener(new startButtonHandler());
    pauseResumeButton.addActionListener(new pauseResumeButtonHandler());
    reset.addActionListener(new resetButtonHandler());


    //input panels
    timeInputField.setPreferredSize(new Dimension(150, 25));
    inputPanel.add(timeInputLabel);
    inputPanel.add(timeInputField);
    inputPanel.add(start);
    inputPanel.add(pauseResumeButton);
    inputPanel.add(reset);
    
    //animation panel
    ballAnimationPanel.setPreferredSize(new Dimension(600, 400));

    ball = new Ball(new Point(100,100), 100, 3,3);

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


    ballTimer = new Timer(10, new TimerListener());
    }

    /**
     * Private helper method determines if the ball needs to reverse course on the x axis.
     * 
     * @return boolean representing if the ball needs to bounce/reverse
     */
    private boolean boundaryX(){
        if(ball.getCenter().x - ball.getRadius() <= 0 || ball.getCenter().x + ball.getRadius() >= ballAnimationPanel.getWidth()){
            return true;
        }

        return false;
    }

    /**
     * Private helper method determines in the ball needs to reverse course on the y axis.
     * 
     * @return boolean representing if the ball needs to bounce/reverse
     */
    private boolean boundaryY(){
        if(ball.getCenter().y - ball.getRadius() <= 0 || ball.getCenter().y + ball.getRadius() >= ballAnimationPanel.getHeight()){
            return true;
        }
        return false;
    }

    /**
     * Private inner class startButtonHandler provides action listender for the start button
     */
    private class startButtonHandler implements ActionListener{
        /**
         * Method starts the timer
         * 
         * @param e the action event handled by this method
         */
        public void actionPerformed(ActionEvent e){
            //Checks to see if text is in block must be positive and no letters
            if(!hasStarted){
                if(timeInputField.getText().isEmpty()){
                    return;
                }
                double secs = Double.parseDouble(timeInputField.getText());
                totalTime = (long) (secs * 1000);
                timeElapsed = 0;
                totalTimeleft = System.nanoTime();
                ballTimer.start();
                hasStarted = true;
            }
            System.out.println("Start Button Clicked!");
        }
    }

    /**
     * Private inner class stopButtonHandler provides action listener for the stop button.
     */
    private class pauseResumeButtonHandler implements ActionListener{
        /**
         * method starts the timer
         * 
         * @param e the action event handled by this method
         */
        public void actionPerformed(ActionEvent e){
        
            if(!hasStarted){
                return;
            }

            if(ballTimer.isRunning() || ball.getCenter().equals(new Point(BALL_CENTER_OG_VALUES, BALL_CENTER_OG_VALUES))){
                ballTimer.stop();
            } else {
                ballTimer.start();
            }
            System.out.println("Pause Button CLicked!");
        }
    }


    /**
     * Private inner Class resetButtonListener provides action listener for the resetButton.
     */
    private class resetButtonHandler implements ActionListener{
        /**
         * Method starts the timer
         * 
         * @param e the action event handled by this method
         */
        public void actionPerformed(ActionEvent e){
        
            hasStarted = false;
            timeElapsed = 0;
            bounceProgressBar.setValue(0);
            progressTimeElapsedLabel.setText("Time Left: 0.00");

                //removed is running condition was preventing the reset from happening
                //when paused

                //stop the animation
                ballTimer.stop();

                //revert balls position to origin
                ball.setCenter(new Point(BALL_CENTER_OG_VALUES, BALL_CENTER_OG_VALUES));
                ball.setRadius(BALL_OG_RADIUS);
                ball.setDx(OG_DX_AND_DY);
                ball.setDy(OG_DX_AND_DY);

                //redraw ball in og position
                ballAnimationPanel.repaint();
        
            
            System.out.println("Reset Button Clicked!");
        }
        
    }

    /**
     * private inner class TimerListener provides the action listener for the swing Timer.
     */
    private class TimerListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            //updates the center point location of the ball
            ball.update();

            //countdown timer and ball shrink rate
            timeElapsed += 10;

            double fraction = (double) timeElapsed / totalTime;
            if (fraction > 1) {
                fraction = 1;
            }

            ball.setRadius((int) (BALL_OG_RADIUS * (1 - fraction)));
            bounceProgressBar.setValue((int) (fraction * 100));

            double timeLeft = (totalTime - timeElapsed) / 1000.0;
            if (timeLeft < 0) {
                timeLeft = 0;
            }
            progressTimeElapsedLabel.setText(String.format("Time Left: %.2f", timeLeft));

            //two seperate if so it can reverese both in the case it hits a corner
            if (boundaryX()){
                ball.setDx(ball.getDx() * -1);
            }

            if (boundaryY()){
                ball.setDy(ball.getDy() * -1);
            }

            ballAnimationPanel.repaint();
        }
    }
}
