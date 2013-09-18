package game;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.logging.Logger;
import model.GameAction;
import model.GameData;
import telnet.TargetStrategy;


public class LogicalContext {

    private static Logger log = Logger.getLogger(LogicalContext.class.getName());
    private TargetStrategy target;
    private Strategy strategy;

    private LogicalContext() {
    }

    public LogicalContext(Strategy strategy) {
        this.strategy = strategy;
    }

    public Deque<GameAction> getActions(GameData data) {
        Deque<GameAction> dq = new ArrayDeque<>();
        if (data != null) {
            log.info(data.getEnemy());
            log.fine("found bob");
            GameAction b = new GameAction("backstab " + target.toString());
            GameAction h = new GameAction("heartplunge");
            GameAction e = new GameAction("enervate");
            GameAction c = new GameAction("confuse");
            dq.add(b);
            dq.add(h);
            dq.add(e);
            dq.add(c);
        }
        log.fine(dq.toString());
        return dq;
    }

    void setTarget(TargetStrategy target) {
        this.target = target;
        log.info(target.toString());
    }

    public void executeStrategy() {
        strategy.execute();
    }
}
