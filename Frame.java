import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class Frame extends JFrame {
    private InputBuffer inputBuffer;
    private Panel panelMain;
    private StateManager stateManager;
    private GameLoop gameLoop;

    /*
     * Create a frame with default parameters.
     */
    public Frame() {
        // Set the frame's parameters.
        super("Snake Game");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(450, 100);

        this.inputBuffer = new InputBuffer();
        this.stateManager = new StateManager(inputBuffer);
        this.panelMain = new Panel(inputBuffer, stateManager);
        this.gameLoop = new GameLoop(panelMain, stateManager);
    }

    /*
     * Initialise the UI layout.
     */
    public void init() {
        // Set the layout.
        setLayout(new BorderLayout());

        // Set the layout of panelTop
        JPanel panelTop = new JPanel();
        panelTop.setPreferredSize(new Dimension(605, 70));
        panelTop.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelTop.setBackground(new Color(147, 109, 62));
        panelTop.setLayout(new BorderLayout());

        // Set the layout of panelScore
        JPanel panelScore = new JPanel();
        panelScore.setPreferredSize(new Dimension(80, 30));
        panelScore.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panelScore.setBackground(Color.gray);
        panelScore.setLayout(new BorderLayout());

        // Set score
        JLabel score = new JLabel("score:");
        panelScore.add(score, BorderLayout.CENTER);
        Integer a = 0;
        JLabel numberScore = new JLabel(a.toString());
        panelScore.add(numberScore, BorderLayout.EAST);

        panelTop.add(panelScore, BorderLayout.WEST);

        // Set pause button
        PauseButton pause = new PauseButton("");
        panelTop.add(pause, BorderLayout.EAST);
        pause.addActionListener((ActionEvent e) -> {

            // TODO: IMPLEMENT PAUSE PANEL
            System.out.println("Something");
        });

        // Set the layout of panelBot
        JPanel panelBot = new JPanel();
        panelBot.setPreferredSize(new Dimension(605, 100));
        panelBot.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelBot.setBackground(new Color(147, 109, 62));
        panelBot.setLayout(new BorderLayout());

        // Set the layout of panelNextApple
        JPanel panelNextaApple = new JPanel();
        panelNextaApple.setPreferredSize(new Dimension(80, 30));
        panelNextaApple.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panelNextaApple.setBackground(Color.gray);
        panelNextaApple.setLayout(new BorderLayout());

        // Set nextApple
        JLabel nextApple = new JLabel("Next Apple:");
        panelNextaApple.add(nextApple, BorderLayout.WEST);
        panelBot.add(panelNextaApple, BorderLayout.WEST);

        // Adding all of the panels to the frame
        add(panelTop, BorderLayout.NORTH);
        add(panelBot, BorderLayout.SOUTH);
        add(panelMain, BorderLayout.CENTER);

        // Display the frame.
        pack();
        setVisible(true);

        gameLoop.start();
    }
}
