package eatree.test_model;

import eatree.model.EatreeModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the model class of Eatree game.
 */
class EatreeModelTest {
    /**
     * Tests if some actions will end the game or not.
     * In this case, player is set to right while the next wood is set to have a branch.
     * The branch is set to left.
     * Then player will make a move to left. It should end the game because player collides with the branch.
     */
    @Test
    void ShouldEndTheGame(){
        var game = new EatreeModel();
        game.getPlayer().setLeft(false);
        game.getWoods().get(9).setHasBranch(true);
        game.getWoods().get(9).setLeft(true);
        game.move("left");
        assertTrue(game.gameisOver());
    }

    /**
     * Tests if the game starts with zero score.
     */
    @Test
    void ShouldStartWithZeroScore(){
        var game = new EatreeModel();
        assertEquals(0, game.getScore());
    }

    /**
     * Tests if the game runs the timer before player makes a move.
     */
    @Test
    void ShouldNotRunTheTimerBeforeMoving(){
        var game = new EatreeModel();
        assertEquals(0, game.getMillisFromThread());
    }

    /**
     * Tests if the game starts with 10 woods or not.
     */
    @Test
    void ShouldStartWith10Woods(){
        var game = new EatreeModel();
        assertEquals(10, game.getWoods().size());
    }

    /**
     * Tests if the move function makes the player move out of bounds or not.
     * Technically the player should only move to left (x = 185) or right (x = 275).
     */
    @Test
    void movePlayer_ShouldNotMoveOutOfBounds(){
        var game = new EatreeModel();
        game.move("left");
        assertEquals(185, game.getPlayer().getX());
        assertTrue(game.getPlayer().getLeft());
        game.move("right");
        assertEquals(275, game.getPlayer().getX());
        assertFalse(game.getPlayer().getLeft());
    }

    /**
     * Tests if the game saves the score to the scores.txt file or not.
     */
    @Test
    void ShouldWriteScores(){
        var game = new EatreeModel();
        assertDoesNotThrow(() -> game.writeSaveData(game.getScore()));
    }

    /**
     * Tests if the game successfully load scores from the scores.txt file or not.
     */
    @Test
    void ShouldLoadScores(){
        var game = new EatreeModel();
        assertEquals(0, game.getBestscore());
        assertDoesNotThrow(game::loadScores);
    }
}
