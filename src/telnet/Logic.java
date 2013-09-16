package telnet;

import java.util.ArrayDeque;
import java.util.Deque;
import weather.GameAction;
import weather.Player;

public class Logic {

    public Logic() {
    }

    public Deque<GameAction> getActions(Player player) {
        Deque<GameAction> dq = new ArrayDeque<>();
        if (player != null) {
            GameAction b = new GameAction("backstab " + player.getEnemy());
            GameAction h = new GameAction("heartplunge");
            GameAction e = new GameAction("enervate");
            GameAction c = new GameAction("confuse");
            dq.add(b);
            dq.add(h);
            dq.add(e);
            dq.add(c);
        }
        return dq;
    }
}
