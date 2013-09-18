package game;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.logging.Logger;
import model.GameAction;
import model.GameData;
import telnet.TargetStrategy;

public class LogicalContext {

    private static Logger log = Logger.getLogger(LogicalContext.class.getName());
    private Strategy strategy;

    private LogicalContext() {
    }

    public LogicalContext(Strategy strategy) {
        this.strategy = strategy;
    }

    public Deque<GameAction> executeStrategy() {
        //strategy.execute
        Deque<GameAction> deque = new ArrayDeque<>();
        log.fine("found bob");
        GameAction backstab = new GameAction("backstab " + strategy.execute());
        GameAction confuse = new GameAction("confuse");
        GameAction heartplunge = new GameAction("heartplunge");
        GameAction enervate = new GameAction("enervate");
        deque.add(backstab);
        deque.add(confuse);
        deque.add(heartplunge);
        deque.add(enervate);
        return deque;
    }
}
