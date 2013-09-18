package game;

import model.GameData;

public class RulesForStrategy {

    private Context context = null;
    private GameData gameData = null;

    private RulesForStrategy() {
    }

    public RulesForStrategy(GameData gameData) {
        this.gameData = gameData;
    }

    public Context getContext() {
        context = new Context(new TargetStrategy());
        context.setGameData(gameData);
        gameData = null;
        return context;
    }
}
