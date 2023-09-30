package eatree.model;

import java.io.*;
import java.util.ArrayList;

/**
 * The interface of a model. Contains all necessary methods to be implemented.
 */
interface IEatreeModel {
    IEatreeModel move(String direction);

    boolean gameisOver();

    String toString();

    long getMillisFromThread();

    void writeSaveData(int score);

    void loadScores();
}

/**
 * The game implementation of Eatree. This class is the model of the game.
 * It can be either used in combination with a view+controller or directly through the JShell.
 */
public class EatreeModel implements IEatreeModel {
    private ArrayList<Wood> woods = new ArrayList<>();
    private final Player p;
    private int score;
    private int bestscore = 0;
    private final TimerThread thread;
    private float seconds = 14;
    private int numbercalls;
    private boolean playerDied = false;

    /**
     * Creates a new game instance.
     */
    public EatreeModel() {
        thread = new TimerThread();
        thread.start();
        for (int i = 0; i < 10; i++) {
            Wood newWood = new Wood(i);
            woods.add(newWood);
        }
        for (int i = 0; i < woods.size(); i++) {
            if ((woods.get(i).isHasBranch()) && (i != 9)) {
                woods.get(i + 1).setHasBranch(false);
            }
        }
        woods.get(9).setHasBranch(false);
        woods.get(8).setHasBranch(false);
        p = new Player();
    }

    /**
     * Checks if game is over under several circumstances.
     * 1. If player collides / crashes with branch, game over.
     * 2. If the time runs out, also game over.
     * @return true if game is over, false if still running.
     */
    public boolean gameisOver() {
        if (woods.get(9).isHasBranch()) {
            if (woods.get(9).isLeft()) {
                if (p.getLeft()) {
                    return true;
                }
            } else if (!woods.get(9).isLeft()) {
                if (!p.getLeft()) {
                    return true;
                }
            }
        }
        if ((float) thread.getMillis() / 1000 >= seconds) {
            return true;
        }
        return false;
    }

    private void chopTree() {
        woods.remove(9);
        Wood newWood = new Wood(0);
        if (woods.get(0).isHasBranch()) {
            newWood.setHasBranch(false);
        }
        woods.add(0, newWood);
        score++;
        System.out.println("Score: " + score);
    }

    /**
     * Essential method for the game. Moves player to left or right.
     * @param direction String "left" or "right". If any other string is entered,
     *                  the system will ask the user to give only left or right.
     * @return game instance (useful for jshell)
     */
    public EatreeModel move(String direction) {
        assert !gameisOver();
        numbercalls += 1;
        EatreeModel game = this;
        if ((direction.equals("left")) || (direction.equals("right"))) {
            for (Wood wood : woods) {
                int woodY = wood.getY();
                woodY += wood.getSize();
                wood.setY(woodY);
            }
            if ((woods.get(9).isHasBranch()) && ((woods.get(9).isLeft() && (direction.equals("left"))))) {
                playerDied = true;
            } else if ((woods.get(9).isHasBranch()) && (!(woods.get(9).isLeft()) && (direction.equals("right")))) {
                playerDied = true;
            } else {
                chopTree();
            }

            if ((seconds + 0.15) - ((float) thread.getMillis() / 1000) <= 14) {
                seconds += 0.15;
            }
        }
        if (direction.equals("left")) {
            p.setLeft(true);
            p.setX(185);
        } else if (direction.equals("right")) {
            p.setLeft(false);
            p.setX(275);
        } else {
            System.out.println("Please enter left or right to the function.");
        }
        return game;
    }

    /**
     * A textual representation about the current game state.
     * @return information on the game state (score and symbols that represents the game state).
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Wood wood : woods) {
            if (wood.isHasBranch()) {
                if (wood.isLeft()) {
                    s.append("\n" + "-- X ");
                } else {
                    s.append("\n" + "   X --");
                }
            } else {
                s.append("\n" + "   X ");
            }
        }

        if (p.getLeft()) {
            s.append("\n" + "O    ");
        } else {
            s.append("\n" + "      O");
        }

        if (gameisOver() || playerDied) {
            thread.stop();
            System.out.println("Game over. \n Your score: " + score);
            System.exit(0);
        }
        return s.toString();
    }

    /**
     * Getter for the time elapsed created using the thread.
     * @return time elapsed in milliseconds
     */
    public long getMillisFromThread() {
        return thread.getMillis();
    }

    /**
     * Writes and saves data to a file named scores.txt saved in the project's directory.
     * @param score score achieved by the player at the end of a game session
     */
    public void writeSaveData(int score) {
        File file = new File("scores.txt");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write(Integer.toString(score));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads best score from scores.txt file.
     * Unfortunately this part is still not perfect.
     * If run from another computer, it is necessary to change the directory first before running the game to avoid exceptions.
     * The expected directory is where scores.txt is stored in your computer.
     */
    public void loadScores() {
        File file = new File("C:\\Users\\Jane ET\\Desktop\\Folders\\Informatik\\PIS\\Eatree\\scores.txt");
        BufferedReader br = null;

        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = br.readLine();
            bestscore = Integer.parseInt(line);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }

    /**
     * Getter for the ArrayList woods.
     * @return woods
     */
    public ArrayList<Wood> getWoods() {
        return woods;
    }

    /**
     * Getter for the score.
     * @return score achieved in a session
     */
    public int getScore() {
        return score;
    }

    /**
     * Getter for the best score.
     * @return all time best score
     */
    public int getBestscore() {
        return bestscore;
    }

    /**
     * Getter for seconds.
     * @return seconds total time allotted until game over in seconds
     */
    public float getSeconds() {
        return seconds;
    }

    /**
     * Getter for variable numbercalls.
     * @return numbercalls; variable to count how many times a method has been called
     */
    public int getNumbercalls() {
        return numbercalls;
    }

    /**
     * Getter for player object.
     * @return player object p
     */
    public Player getPlayer(){
        return p;
    }

    /**
     * A thread class for the timer.
     */
    class TimerThread extends Thread {
        private long millis;

        /**
         * Specifies what should the thread do when it starts.
         * In this case, if the move method is called once or more than once,
         * the timer starts by incrementing millis variable for every 1 ms.
         */
        public void run() {
            while (true) {
                try {
                    if (numbercalls >= 1) {
                        millis++;
                    }
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private long getMillis() {
            return millis;
        }
    }
}
