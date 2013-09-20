package game;

import java.util.logging.Logger;
import model.GameTarget;
import model.MonitorStats;

public class RulesForStrategy {

    private static Logger log = Logger.getLogger(RulesForStrategy.class.getName());
    private Context context = null;
    private GameTarget target = null;
    private MonitorStats monitor = null;

    public RulesForStrategy() {
    }

    private RulesForStrategy(GameTarget target) {
        this.target = target;
    }

    public Context getContext() {
        if (getTarget() != null) {
            log.fine(getTarget().toString());
            context = new Context(new TargetStrategy());
            context.setGameData(getTarget());
            setTarget(null);
        }
        return context;
    }

    public GameTarget getTarget() {
        return target;
    }

    public void setTarget(GameTarget target) {
        this.target = target;
    }

    public MonitorStats getMonitor() {
        return monitor;
    }

    public void setMonitor(MonitorStats monitor) {
        this.monitor = monitor;
    }
}