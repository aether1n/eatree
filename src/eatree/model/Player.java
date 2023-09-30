package eatree.model;

import java.util.Random;

/**
 * Class to store methods and variables related to player.
 * This class is also part of the model.
 */
public class Player {
    Random random = new Random();
    private int x = 275;
    private boolean left = random.nextBoolean();

    /**
     * Getter for x position
     * @return x value
     */
    public int getX(){
        return x;
    }

    /**
     * Setter for x position
     * @param x desired x value to be set
     */
    public void setX(int x){
        this.x = x;
    }

    /**
     * Getter for y position
     * @return y value
     */
    public int getY(){
        return 458;
    }

    /**
     * Getter to get size of wood (width, height)
     * @return size
     */
    public int getSize(){
        return 50;
    }

    /**
     * Getter to know if player is currently on the left or right
     * @return true if left, false if right
     */
    public boolean getLeft(){
        return left;
    }

    /**
     * Setter to set branch to spawn on left or right
     * @param left boolean for determining direction of branch.
     */
    public void setLeft(boolean left){
        this.left = left;
    }
}
