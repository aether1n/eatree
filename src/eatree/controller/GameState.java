package eatree.controller;

/**
 * All possible game states. This class is used to implement a state machine in the controller.
 */
public enum GameState {
    /**
     * The starting state of the game. Transitions to the GAME state.
     */
    GAME_MENU,
    /**
     * The state in which the user can play Eatree. Transitions to the GAME_OVER state.
     */
    GAME,
    /**
     * The end state of the game.
     */
    GAME_OVER
}
