package ra.sa.domain;


import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class GameTest {

    private Player player;
    private Player secondPlayer;
    private Game game;

    @Before
    public void setUp() {
        player = new Player(1, "Ramzi");
        secondPlayer = new Player(2, "SADOK");
    }


    @Test
    public void should_increment_score_when_there_is_no_winner_with_zero_score() {
        //GIVEN
        Game game = new Game(player, secondPlayer);
        //WHEN
        boolean isWinner = game.score(player);
        //THEN
        assertFalse(isWinner);
        assertEquals(game.getGameMap().get(player), Point.ONE);
    }

    @Test
    public void should_increment_score_when_there_is_no_winner_with_not_zero_score() {
        //GIVEN
        Game game = new Game(player, secondPlayer);
        Map<Player,Point> newGame = game.getGameMap();
        newGame.put(player,Point.TWO);
        //WHEN
        boolean isWinner = game.score(player);
        //THEN
        assertFalse(isWinner);
        assertEquals(game.getGameMap().get(player), Point.THREE);
    }
    @Test
    public void should_increment_score_and_not_win_when_both_forty() {
        //GIVEN
        Game game = new Game(player, secondPlayer);
        Map<Player,Point> newGame = game.getGameMap();
        newGame.put(player,Point.THREE);
        newGame.put(secondPlayer,Point.THREE);
        //WHEN
        boolean isWinner = game.score(player);
        //THEN
        assertFalse(isWinner);
        assertEquals(game.getGameMap().get(player), Point.ADVANTAGE);
    }

    @Test
    public void should_win_the_game_when_forty_with_two_points_difference() {
        //GIVEN
        Game game = new Game(player, secondPlayer);
        Map<Player,Point> newGame = game.getGameMap();
        newGame.put(player,Point.THREE);
        newGame.put(secondPlayer,Point.TWO);
        //WHEN
        boolean isWinner = game.score(player);
        //THEN
        assertTrue(isWinner);
    }

    @Test
    public void should_win_the_game_when_score_with_advantage() {
        //GIVEN
        Game game = new Game(player, secondPlayer);
        Map<Player,Point> newGame = game.getGameMap();
        newGame.put(player,Point.ADVANTAGE);
        newGame.put(secondPlayer,Point.THREE);
        //WHEN
        boolean isWinner = game.score(player);
        //THEN
        assertTrue(isWinner);
    }
}