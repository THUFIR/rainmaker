package game;

import java.util.logging.Logger;
import model.GameDataBean;

public class RulesForStrategy {

    private static Logger log = Logger.getLogger(RulesForStrategy.class.getName());
    private Context context = null;
    private GameDataBean oldData = null;
    private GameDataBean newData = null;

    public RulesForStrategy() {
    }

    private RulesForStrategy(GameDataBean data) {
        this.newData = data;
    }

    public Context getContext() {
        if ((oldData != null) && (newData != null)) {
            log.fine(getData().toString());
            if (newData.getEnemy() == null) {
                newData.setEnemy(oldData.getEnemy());
            }
            context = new Context(new TargetStrategy());
            context.setData(getData());
            oldData = null;
            newData = null;
        }
        return context;
    }

    public GameDataBean getData() {
        return newData;
    }

    public void setData(GameDataBean oldData, GameDataBean newData) {
        this.oldData = oldData;
        this.newData = newData;
    }
}