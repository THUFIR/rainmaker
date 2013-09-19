package game;

import java.util.logging.Logger;
import model.GameDataBean;

public class RulesForStrategy {

    private static Logger log = Logger.getLogger(RulesForStrategy.class.getName());
    private Context context = null;
    private GameDataBean gameData = null;

    private RulesForStrategy() {
    }

    public RulesForStrategy(GameDataBean gameData) {
        this.gameData = gameData;
    }

    public Context getContext() {
        if (gameData != null) {
            log.fine(gameData.toString());
            context = new Context(new TargetStrategy());
            context.setGameData(gameData);
            gameData = null;
        }
        return context;
    }
}
