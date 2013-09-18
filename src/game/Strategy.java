package game;

import java.util.Deque;
import model.GameAction;

public interface Strategy {

    public Deque<GameAction> execute();
    
}
