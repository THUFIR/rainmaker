package game;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.logging.Logger;
import model.GameAction;
import model.GameData;

public class TargetStrategy implements Strategy {

    private static Logger log = Logger.getLogger(TargetStrategy.class.getName());
    private GameData gameData = null;

    private TargetStrategy() {
    }

    public TargetStrategy(GameData gameData) {
        this.gameData = gameData;
    }

    public String toString() {
        return "target";
    }

    @Override
    public Deque<GameAction> execute() {
        Deque<GameAction> deque = new ArrayDeque<>();
        GameAction backstab = new GameAction("backstab " + gameData.getEnemy());
        GameAction confuse = new GameAction("confuse " + gameData.getEnemy());
        GameAction heartplunge = new GameAction("heartplunge " + gameData.getEnemy());
        GameAction enervate = new GameAction("enervate " + gameData.getEnemy());
        deque.add(backstab);
        deque.add(confuse);
        deque.add(heartplunge);
        deque.add(enervate);
        return deque;
    }
}
