package ra.sa.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class Set {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private Player firstPlayer;
    private Player secondPlayer;



    private Map<Player, List<Game>> wonGamesMap = new HashMap<>();

    public Set(Player firstPlayer, Player secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        wonGamesMap.put(firstPlayer, new ArrayList<>());
        wonGamesMap.put(secondPlayer, new ArrayList<>());
    }
    public Map<Player, List<Game>> getWonGamesMap() {
        return wonGamesMap;
    }

    public void setWonGamesMap(Map<Player, List<Game>> wonGamesMap) {
        this.wonGamesMap = wonGamesMap;
    }

    public boolean winGame(Player player, Game game) {

        List<Game> gamesWonByCurrentPlayer;
        List<Game> gamesWonByOpponentPlayer;
        if (player == firstPlayer) {
            gamesWonByCurrentPlayer = wonGamesMap.get(firstPlayer);
            gamesWonByOpponentPlayer = wonGamesMap.get(secondPlayer);
        } else {
            gamesWonByCurrentPlayer = wonGamesMap.get(secondPlayer);
            gamesWonByOpponentPlayer = wonGamesMap.get(firstPlayer);
        }
        log.info("actual set score {} - {} , {} is winning new game",wonGamesMap.get(firstPlayer).size(),wonGamesMap.get(secondPlayer).size(),player.getName());
        if (gamesWonByCurrentPlayer.size() >= 5 && (gamesWonByCurrentPlayer.size() > gamesWonByOpponentPlayer.size())) {
            gamesWonByCurrentPlayer.add(game);
            return true;
        } else {
            gamesWonByCurrentPlayer.add(game);
        }

        return false;
    }
}
