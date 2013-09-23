package game;

import java.util.logging.Logger;
import model.GameDataBean;

public class RulesForStrategyComplex {

    private static Logger log = Logger.getLogger(RulesForStrategyComplex.class.getName());
    private Context context = null;
    private GameDataBean oldData = null;
    private GameDataBean newData = null;

    private RulesForStrategyComplex() {
    }

    private RulesForStrategyComplex(GameDataBean data) {
    }

    public RulesForStrategyComplex(GameDataBean oldData, GameDataBean newData) {
        this.oldData = oldData;
        this.newData = newData;
    }

    public Context getContext() {
        if ((oldData != null) && (newData != null)) {
            log.info("old data\n" + oldData.toString());
            log.info("new data\n" + newData.toString());
            if (newData.getEnemy() == null) {
                newData.setEnemy(oldData.getEnemy());
            }
            context = new Context(new TargetStrategy());
            context.setData(newData);
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