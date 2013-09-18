package game;

import java.util.Deque;
import model.GameAction;
import model.GameData;

public interface Strategy {

    public Deque<GameAction> execute(GameData gameData);
}
