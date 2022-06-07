package ra.sa;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.slf4j.LoggerFactory;
import ra.sa.domain.Match;
import ra.sa.domain.Player;
public class Main {

    public static void main(String[] args){
        Logger root = (Logger) LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
        root.setLevel(Level.INFO);
        Player firstPlayer = new Player(1,"Ramzi");
        Player secondPlayer = new Player(2,"Sadok");

        Match match = new Match(firstPlayer,secondPlayer);
        match.playRandomMatch();
    }
}
