package eatree.controller;

import eatree.model.Player;
import eatree.model.Wood;
import java.util.ArrayList;

/**
 * This interface contains all necessary classes of a view to be usable by the controller.
 * The view class can contain additional methods, which will not accessible by the controller.
 */
public interface IEatreeView {
    /**
     * Draws only the gameplay state of the game.
     * @param woods woods that can have branches
     * @param p player
     * @param numbercalls counter for method move(String direction) {@link eatree.model.EatreeModel}
     * @param score score achieved by the player
     */
    void drawGame(ArrayList<Wood> woods, Player p, int numbercalls, int score);

    /**
     * Draws the timer for the gameplay.
     * @param s time elapsed in seconds
     * @param seconds total time allotted until game over in seconds
     */
    void drawSeconds(float s, float seconds);

    /**
     * Draws the main menu.
     */
    void drawMenu();

    /**
     * Draws the game over screen.
     * @param score score achieved by the player at the end of the game session
     * @param bestscore all time best score achieved by the player
     */
    void drawLose(int score, int bestscore);
}
