package game;

import java.util.logging.Logger;
import model.GameDataBean;

public class RulesForStrategy {

    private static Logger log = Logger.getLogger(RulesForStrategy.class.getName());
    private Context context = null;
    private GameDataBean data = null;

    public RulesForStrategy() {
    }

    private RulesForStrategy(GameDataBean data) {
        this.data = data;
    }

    public Context getContext() {
        if (getData() != null) {
            log.fine(getData().toString());
            context = new Context(new TargetStrategy());
            context.setData(getData());
            setData(null);
        }
        return context;
    }

    public GameDataBean getData() {
        return data;
    }

    public void setData(GameDataBean data) {
        this.data = data;
    }

}