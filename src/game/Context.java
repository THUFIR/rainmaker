package game;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.logging.Logger;
import model.GameAction;
import model.GameDataBean;

public class Context {

    private static Logger log = Logger.getLogger(Context.class.getName());
    private Strategy strategy;
    private GameDataBean data = null;

    private Context() {
    }

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public Deque<GameAction> executeStrategy() {
        log.info(strategy.toString());
        return this.strategy.execute(data);
    }

    void setData(GameDataBean data) {
        this.data = data;
    }
}
