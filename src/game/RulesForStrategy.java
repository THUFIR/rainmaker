package game;

import java.util.logging.Logger;
import model.GameData;

public class RulesForStrategy {

    private static Logger log = Logger.getLogger(RulesForStrategy.class.getName());
    private Context context = null;
    private GameData gameData = null;

    private RulesForStrategy() {
    }

    public RulesForStrategy(GameData gameData) {
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
