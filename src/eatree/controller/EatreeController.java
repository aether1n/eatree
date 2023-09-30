package eatree.controller;

import eatree.model.EatreeModel;

import static java.awt.event.KeyEvent.*;

/**
 * A controller implementation for a GUI view. This controller creates and uses a {@link eatree.model.EatreeModel}.
 * It implements a state machine to keep track of the state the view should draw next.
 */
public class EatreeController implements IEatreeController {
    private IEatreeView view;
    private final EatreeModel game;
    private GameState state;
    private boolean play = true;

    /**
     * Creates a new controller object with the given game instance.
     * @param game model object / game instance
     */
    public EatreeController(EatreeModel game){
        this.game = game;
        this.state = GameState.GAME_MENU;
    }

    /**
     * Set the used view. Should only be set at application start.
     *
     * @param view view to use
     */
    public void setView(IEatreeView view){
        this.view = view;
    }

    /**
     * This method should be called whenever the controller should decide what the view should display.
     * The controller will call one of the drawX()-Methods from the view.
     */
    public void nextFrame() {
        if (this.view != null) {
            switch (this.state) {
                case GAME_MENU -> this.view.drawMenu();
                case GAME -> {
                    this.view.drawGame(this.game.getWoods(), this.game.getPlayer(), this.game.getNumbercalls(), this.game.getScore());
                    this.view.drawSeconds((float)this.game.getMillisFromThread()/1000, this.game.getSeconds());
                    if (game.gameisOver()) {
                        this.game.loadScores();
                        if (this.game.getScore() > this.game.getBestscore()){
                            this.game.writeSaveData(this.game.getScore());
                        }
                        state = GameState.GAME_OVER;
                    }
                }
                case GAME_OVER -> this.view.drawLose(this.game.getScore(), this.game.getBestscore());
            }
        }
    }

    /**
     * Method that should be called whenever the user presses a corresponding key.
     * @param KeyEvent the pressed keycode
     */
    public void userInput(int KeyEvent) {
        switch(state){
            case GAME_MENU -> {
                if (KeyEvent == VK_ENTER) {
                    state = GameState.GAME;
                }
            }
            case GAME -> {
                if (play) {
                    switch (KeyEvent) {
                        case VK_LEFT, VK_A -> game.move("left");
                        case VK_RIGHT, VK_D -> game.move("right");
                    }
                }

                if (game.gameisOver()) {
                    play = false;
                }
            }
        }

    }
}
