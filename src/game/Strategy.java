package game;

import java.util.Deque;
import model.GameAction;
import model.GameDataBean;

public interface Strategy {

    public Deque<GameAction> execute(GameDataBean gameData);
}
