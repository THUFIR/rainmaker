package game;

import java.util.logging.Logger;
import model.GameDataBean;

public class RulesForStrategySimple {

    private static Logger log = Logger.getLogger(RulesForStrategySimple.class.getName());
    private Context context = null;
    private GameDataBean data = null;

    private RulesForStrategySimple() {
    }

    public RulesForStrategySimple(GameDataBean data) {
        this.data = data;
    }

    public Context getContext() throws NullPointerException {
        log.info("new data\n" + data.toString());
        context = new Context(new TargetStrategy());
        context.setData(data);
        return context;
    }
}