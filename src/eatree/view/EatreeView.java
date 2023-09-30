package eatree.view;

import eatree.controller.IEatreeController;
import eatree.controller.IEatreeView;
import gifAnimation.*;
import java.util.ArrayList;

import eatree.model.Player;
import eatree.model.Wood;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;
import processing.sound.*;

/**
 * View class for Eatree.
 * Contains different methods to draw the GUI.
 * Several data types are passed from the model through the controller this view for drawing.
 */
public class EatreeView extends PApplet implements IEatreeView {
    Gif character;
    IEatreeController controller;
    PFont font1;
    PFont font2;
    PImage bg;
    PImage gameover;
    PImage branchimageleft;
    PImage branchimageright;
    PImage menu;
    PImage woodimages;
    Sound s;
    SoundFile chew;

    /**
     * Creates a new Processing view with 500x500px.
     */
    public EatreeView(){
        setSize(500, 500);
    }

    /**
     * Sets the controller. Should only be set at application start.
     *
     * @param controller controller to use
     */
    public void setController(IEatreeController controller){
        this.controller = controller;
    }

    /**
     * Loads files of image, gif and sound to be used.
     */
    @Override
    public void setup(){
        bg = loadImage("bg.jpg");
        background(bg);
        s = new Sound(this);
        s.volume(0.05F);
        woodimages = loadImage("wood.png");
        branchimageleft = loadImage("branch.png");
        branchimageright = loadImage("branchright.png");
        character = new Gif(this, "bearidle.gif");
        character.play();
        font1 = createFont("PixeloidSans.ttf", 15);
        font2 = createFont("Fipps-Regular.otf", 30);
        chew = new SoundFile(this, "chew.mp3");
        menu = loadImage("menu.png");
        gameover = loadImage("game_over.png");
    }

    /**
     * Draws the current game state (menu/gameplay/game over) if object controller is not null
     */
    @Override
    public void draw(){
        if (controller != null) {
            controller.nextFrame();
        }
    }

    /**
     * Receives the user input if object controller is not null
     */
    @Override
    public void keyPressed(){
        if (controller != null){
            controller.userInput(keyCode);
            if ((keyCode == LEFT) || (keyCode == RIGHT) || (keyCode == 'A') || (keyCode == 'D')){
                chew.play();
            }
        }
    }

    /**
     * Draws only the gameplay state of the game.
     * @param woods woods that can have branches
     * @param p player
     * @param numbercalls counter for method move(String direction) {@link eatree.model.EatreeModel}
     * @param score score achieved by the player
     */
    @Override
    public void drawGame(ArrayList<Wood> woods, Player p, int numbercalls, int score){
        background(bg);
        for (Wood wood : woods) {
            image(woodimages, wood.getX(), wood.getY(), wood.getSize(), wood.getSize());
            if (wood.isHasBranch()) {
                if (wood.isLeft()) {
                    image(branchimageleft, wood.getX() - 79, wood.getY(), wood.getSize() + 30, wood.getSize());
                } else {
                    image(branchimageright, wood.getX() + 49, wood.getY(), wood.getSize() + 30, wood.getSize());
                }
            }
        }
        image(character, p.getX(), p.getY(), p.getSize(), p.getSize());
        image(woodimages, 230, 5, 50, 50);
        if (numbercalls == 0){
            textFont(font1);
            fill(0);
            text("Press A or <- \n to go left", 20, 350);
            text("Press D or -> \n to go right", 390, 350);
        }
        textFont(font1);
        text(score, 250, 50);
    }

    /**
     * Draws the timer for the gameplay.
     * @param s time elapsed in seconds
     * @param seconds total time allotted until game over in seconds
     */
    @Override
    public void drawSeconds(float s, float seconds){
        stroke(44, 0, 0);
        fill(253, 188, 4);
        rect(178, 5, 150, 30);
        noStroke();
        fill(94, 20, 8);
        rect(183, 10, 140, 20);
        fill(232, 45, 20);
        float wid = map(seconds - s, 0, 14, 0, 140);
        rect(183, 10, wid, 20);
        fill(255);
    }

    /**
     * Draws the main menu.
     */
    @Override
    public void drawMenu(){
        image(menu, 55, 60, 400, 400);
        image(character, 230, 300, 50, 50);
    }

    /**
     * Draws the game over screen.
     * @param score score achieved by the player at the end of the game session
     * @param bestscore all time best score achieved by the player
     */
    @Override
    public void drawLose(int score, int bestscore){
        image(gameover, 55, 60, 400, 400);
        textFont(font2);
        textAlign(CENTER);
        fill(0);
        text(bestscore, 255, 310);
        text(score, 255, 400);
    }
}
