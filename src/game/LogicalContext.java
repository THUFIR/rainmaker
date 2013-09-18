package game;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.logging.Logger;
import model.GameAction;

public class LogicalContext {

    private static Logger log = Logger.getLogger(LogicalContext.class.getName());
    private Strategy strategy;

    private LogicalContext() {
    }

    public LogicalContext(Strategy strategy) {
        this.strategy = strategy;
    }

    public Deque<GameAction> executeStrategy() {
        log.info(strategy.toString());
        Deque<GameAction> deque = new ArrayDeque<>();
        deque = strategy.execute();
        return deque;
    }
}
