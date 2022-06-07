package ra.sa.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Stream;

public class Match {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private Player firstPlayer;
    private Player secondPlayer;
    private Map<Player, List<Set>> setMap = new HashMap<>();


    public Match(Player firstPlayer, Player secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        setMap.put(firstPlayer, new ArrayList<>());
        setMap.put(secondPlayer, new ArrayList<>());
    }

    public void playRandomMatch() {
        log.info("new game has started");
        Player randomPlayer = getRandomPlayer();
        playUntilWinMatch(randomPlayer);
    }

    private void playUntilWinMatch(Player randomPlayer) {
        boolean matchIsWon = false;
        while (!matchIsWon) {
            boolean setIsWon = false;
            Set set = new Set(firstPlayer, secondPlayer);
            randomPlayer = playUntilWinGame(randomPlayer, setIsWon, set);
            matchIsWon = winSet(randomPlayer, set);
        }
    }

    private Player playUntilWinGame(Player randomPlayer, boolean setIsWon, Set set) {
        while (!setIsWon) {
            Game game = new Game(firstPlayer, secondPlayer);
            while (!game.score(randomPlayer)) {
                randomPlayer = getRandomPlayer();
            }

            setIsWon = set.winGame(randomPlayer, game);
        }
        return randomPlayer;
    }

    public boolean winSet(Player player, Set set) {
        log.info("actual match score {} - {} , {} is winning new set", setMap.get(firstPlayer).size(), setMap.get(secondPlayer).size(), player.getName());
        List<Set> setsWonByCurrentPlayer;
        setsWonByCurrentPlayer = setMap.get(player);
        setsWonByCurrentPlayer.add(set);
        return setsWonByCurrentPlayer.size() == 2;
    }

    public Player getRandomPlayer() {
        Random rand = new Random();
        int randomId = rand.nextInt(2) + 1;
        return Stream.of(firstPlayer, secondPlayer).filter(player -> randomId == player.getId()).findFirst().orElse(firstPlayer);
    }
}
