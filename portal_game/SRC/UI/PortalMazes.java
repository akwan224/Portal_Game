package UI;

import Model.Exception.OutOfTimeException;
import Model.LevelSingleton;
import Model.PortalGame;


import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import javax.swing.JFrame;

public class PortalMazes extends JFrame {
    int secondsPassed = 0;
    private long quizStartTime;
    Timer myTimer = new Timer();
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            secondsPassed++;
        }
    };

    Scanner scanner = new Scanner(System.in);
    private PortalGame game;
    private GamePanel gp;
    private static final int INTERVAL = 20;

    // Constructs main window
    // effects: sets up window in which Space Invaders game will be played
    public PortalMazes() throws  OutOfTimeException{
        super("Portal Game");
        myTimer.scheduleAtFixedRate(task, 1000, 1000);
//        startScreen();
        startGame();
        finishGame();
    }

    private void finishGame() {
        System.out.println("thanks for playing ");
        System.out.println("total playtime: " + secondsPassed + "seconds! wow, great job!");
    }


    public void startGame() throws OutOfTimeException{

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        game = new PortalGame();

        gp = new GamePanel(game);
        add(gp);
//            add(sp, BorderLayout.NORTH);
        addKeyListener(new KeyHandler());
        pack();
        centreOnScreen();
        setVisible(true);
        quizStartTime = System.nanoTime();
        addTimer();
        LevelSingleton tmp = LevelSingleton.getSingletonInstance();
        tmp.getSingletonInstance().printSingleton();
    }

    // Set up timer
    // modifies: none
    // effects:  initializes a timer that updates game each
    //           INTERVAL milliseconds
    private void addTimer() {
        javax.swing.Timer t = new javax.swing.Timer(INTERVAL, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                game.update();
                gp.repaint();
//                sp.update();
            }
        });
        t.start();
    }

    private class KeyHandler extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            game.keyPressed(e);
        }
    }

    // Centres frame on desktop
    // modifies: this
    // effects:  location of frame is set so frame is centred on desktop
    private void centreOnScreen() {
        Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((scrn.width - getWidth()) / 2, (scrn.height - getHeight()) / 2);
    }

    public static void main(String[] args) throws  OutOfTimeException{
        new PortalMazes();
    }
}

//    public void start(String operation) {
//        switch (operation) {
//            case "tutorial":
//                System.out.println("Hi");
//                System.out.println("");
//                break;
//            case "option":
//                System.out.println("Go here to change settings");
//                System.out.println("");
//                break;
//            case "start":
//                System.out.println("startGame");
//                startGame();
//        }
//    }


//    private void startScreen() {
//        String operation;
//        while (true) {
//            System.out.println("Please select an option (tutorial/start/option/quit)");
//            operation = scanner.nextLine();
//            System.out.println("you selected: " + operation);
//            if (operation.equals("tutorial") || operation.equals("option") || operation.equals("start")) {
//                start(operation);
//            } else if (operation.equals("quit")) {
//                break;
//            }
//        }
//    }