package eatree;

import processing.core.PApplet;
import eatree.controller.EatreeController;
import eatree.model.EatreeModel;
import eatree.view.EatreeView;

/**
 * Main class of Eatree game. Run this class to launch the game.
 * Eatree is a game where player eats the tree left and right while avoiding branches.
 *
 * @author Jane Edgina
 * @since 1.0
 * @version 1.0
 */
public abstract class Main {
    /**
     * Main method creates a model, view and controller classes.
     * It connects them and start the processing application.
     */
    public static void main(String[] args) {
        var model = new EatreeModel();
        var controller = new EatreeController(model);
        var view = new EatreeView();

        // Set view and controller
        controller.setView(view);
        view.setController(controller);

        // Run the processing sketch / application
        PApplet.runSketch(new String[]{"EatreeView"}, view);
    }
}
