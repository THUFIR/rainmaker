package game;

import java.util.Deque;
import model.GameAction;
import model.GameTarget;

public interface Strategy {

    public Deque<GameAction> execute(GameTarget gameData);
}
