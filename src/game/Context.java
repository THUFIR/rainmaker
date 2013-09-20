package game;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.logging.Logger;
import model.GameAction;
import model.GameTarget;

public class Context {

    private static Logger log = Logger.getLogger(Context.class.getName());
    private Strategy strategy;
    private GameTarget gameData = null;

    private Context() {
    }

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public Deque<GameAction> executeStrategy() {
        log.fine(strategy.toString());
        return this.strategy.execute(gameData);
    }

    public void setGameData(GameTarget gameData) {
        this.gameData = gameData;
    }
}
