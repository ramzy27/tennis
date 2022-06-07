package ra.sa.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class Game {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private Player firstPlayer;
    private Player secondPlayer;
    private Map<Player, Point> gameMap = new HashMap<>();


    public Game(Player firstPlayer, Player secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        gameMap.put(firstPlayer, Point.ZERO);
        gameMap.put(secondPlayer, Point.ZERO);
    }
    public void setGameMap(Map<Player, Point> gameMap) {
        this.gameMap = gameMap;
    }
    public Map<Player, Point> getGameMap() {
        return gameMap;
    }

    public boolean score(Player player) {
        Player opponentPlayer;
        Point currentPlayerPoint;
        Point opponentPlayerPoint;
        if (player == firstPlayer) {
            opponentPlayer = secondPlayer;
            currentPlayerPoint = gameMap.get(firstPlayer);
            opponentPlayerPoint = gameMap.get(secondPlayer);
        } else {
            opponentPlayer = firstPlayer;
            currentPlayerPoint = gameMap.get(secondPlayer);
            opponentPlayerPoint = gameMap.get(firstPlayer);
        }

                log.info("actual score {} - {} , {} is scoring new point",gameMap.get(firstPlayer).getLabel(),gameMap.get(secondPlayer).getLabel(),player.getName());
        if (isWinningPoint(currentPlayerPoint, opponentPlayerPoint)) {
            return true;
        }
        if (currentPlayerPoint.getScore() < 3) {
            currentPlayerPoint = Point.of(currentPlayerPoint.getScore() + 1);
        } else if (currentPlayerPoint.getScore() == 3 && opponentPlayerPoint.getScore() == 3) {
            currentPlayerPoint = Point.ADVANTAGE;
        } else if (currentPlayerPoint.getScore() == 3 && opponentPlayerPoint.equals(Point.ADVANTAGE)) {
            currentPlayerPoint = Point.THREE;
            opponentPlayerPoint = Point.THREE;
        }
        updateGameMap(player, opponentPlayer, currentPlayerPoint, opponentPlayerPoint);
        return false;


    }

    private void updateGameMap(Player player, Player opponentPlayer, Point currentPlayerPoint, Point opponentPlayerPoint) {
        gameMap.put(player, currentPlayerPoint);
        gameMap.put(opponentPlayer, opponentPlayerPoint);
    }

    private boolean isWinningPoint(Point currentPlayerPoint, Point opponentPlayerPoint) {
        return (currentPlayerPoint.getScore() == 3 && opponentPlayerPoint.getScore() < 3) || (Point.ADVANTAGE.equals(currentPlayerPoint));
    }


}
