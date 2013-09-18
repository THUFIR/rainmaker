package telnet;

import game.Strategy;
import java.util.logging.Logger;

public class TargetStrategy implements Strategy {

    private static Logger log = Logger.getLogger(TargetStrategy.class.getName());
    private String target;

    private TargetStrategy() {
    }

    public TargetStrategy(String target) {
        this.target = target;
    }

    @Override
    public String toString() {
        return target;
    }

    @Override
    public void execute() {
        log.info(target);
    }
}
