package eatree.model;

import java.util.Random;

/**
 * Class to store methods and variables related to wood.
 * This class is also part of the model.
 */
public class Wood {
    private int y;
    private final int size = 50;
    private boolean hasBranch;
    private boolean left;

    /**
     * Creates a new Wood object. Later these created objects will be added to an ArrayList.
     * @param i the index of the Wood object from the ArrayList
     */
    Wood(int i) {
        Random random = new Random();
        int randomNumber = random.nextInt(100 + 1 - 1) + 1;
        int spawnRate = 40;
        hasBranch = randomNumber > spawnRate;
        left = random.nextBoolean();
        this.y = (i * this.size + this.size / 2) - 20;
    }

    /**
     * Getter for x position
     * @return x value
     */
    public int getX(){
        return 230;
    }

    /**
     * Getter for y position
     * @return y value
     */
    public int getY(){
        return y;
    }

    /**
     * Setter for y position
     * @param y desired y value to be set
     */
    public void setY(int y){
        this.y = y;
    }

    /**
     * Getter to know if a wood has a branch or not
     * @return true if wood has branch, false if not
     */
    public boolean isHasBranch(){
        return hasBranch;
    }

    /**
     * Setter to set a wood to have branch or not
     * @param hasBranch boolean for determining existence of wood branch
     */
    public void setHasBranch(boolean hasBranch){
        this.hasBranch = hasBranch;
    }

    /**
     * Getter to know if branch spawns on the left or right
     * @return true if left, false if right
     */
    public boolean isLeft(){
        return left;
    }

    /**
     * Setter to set branch to spawn on left or right
     * @param left boolean for determining direction of branch.
     */
    public void setLeft(boolean left){
        this.left = left;
    }

    /**
     * Getter to get size of wood (width, height)
     * @return size
     */
    public int getSize(){
        return size;
    }
}
