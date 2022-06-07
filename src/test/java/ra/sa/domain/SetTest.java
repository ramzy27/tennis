package ra.sa.domain;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

public class SetTest {

    private Player player;
    private Player secondPlayer;
    private Game game;

    @Before
    public void setUp() {
        player = new Player(1, "Ramzi");
        secondPlayer = new Player(2, "SADOK");
        game = new Game(player,secondPlayer);
    }


    @Test
    public void should_increment_set_when_there_is_no_winner_with_zero_winGame() {
        //GIVEN
        Set set = new Set(player, secondPlayer);
        //WHEN
        boolean isWinner = set.winGame(player,game);
        //THEN
        assertFalse(isWinner);
        assertEquals(set.getWonGamesMap().get(player).size(), 1);
    }

    @Test
    public void should_increment_sets_when_there_is_no_winner_with_not_zero_winGame() {
        //GIVEN
        Set set = new Set(player, secondPlayer);
        Map<Player, List<Game>> newSet = set.getWonGamesMap();
        newSet.put(player,new ArrayList<>(asList(game,game)));
        //WHEN
        boolean isWinner = set.winGame(player,game);
        //THEN
        assertFalse(isWinner);
        assertEquals(set.getWonGamesMap().get(player).size(), 3);
    }
    @Test
    public void should_increment_sets_without_winner_when_equals() {
        //GIVEN
        Set set = new Set(player, secondPlayer);
        Map<Player, List<Game>> newSet = set.getWonGamesMap();
        newSet.put(player,new ArrayList<>(asList(game,game,game,game,game,game)));
        newSet.put(secondPlayer,new ArrayList<>(asList(game,game,game,game,game,game)));
        //WHEN
        boolean isWinner = set.winGame(player,game);
        //THEN
        assertFalse(isWinner);
        assertEquals(set.getWonGamesMap().get(player).size(), 7);
    }

    @Test
    public void should_win_the_set_when_sixth_game_with_less_than_five_opponent_games() {
        //GIVEN
        Set set = new Set(player, secondPlayer);
        Map<Player, List<Game>> newSet = set.getWonGamesMap();
        newSet.put(player,new ArrayList<>(asList(game,game,game,game,game)));
        newSet.put(secondPlayer,new ArrayList<>(asList(game,game,game,game)));
        //WHEN
        boolean isWinner = set.winGame(player,game);
        //THEN
        assertTrue(isWinner);
        assertEquals(set.getWonGamesMap().get(player).size(), 6);
    }

    @Test
    public void should_win_the_set_when_two_difference_games_with_more_than_five_opponent_games() {
        //GIVEN
        Set set = new Set(player, secondPlayer);
        Map<Player, List<Game>> newSet = set.getWonGamesMap();
        newSet.put(player,new ArrayList<>(asList(game,game,game,game,game,game)));
        newSet.put(secondPlayer,new ArrayList<>(asList(game,game,game,game,game)));
        //WHEN
        boolean isWinner = set.winGame(player,game);
        //THEN
        assertTrue(isWinner);
        assertEquals(set.getWonGamesMap().get(player).size(), 7);
    }
}