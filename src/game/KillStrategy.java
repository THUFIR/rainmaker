package game;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.logging.Logger;
import model.GameAction;
import model.GameData;

public class KillStrategy implements Strategy {

    private static Logger log = Logger.getLogger(KillStrategy.class.getName());

    public KillStrategy() {
    }

    public String toString() {
        return "target";
    }

    @Override
    public Deque<GameAction> execute(GameData gameData) {
        Deque<GameAction> deque = new ArrayDeque<>();
        GameAction backstab = new GameAction("backstab "+ gameData.getEnemy());
        deque.add(backstab);
        return deque;
    }
}
