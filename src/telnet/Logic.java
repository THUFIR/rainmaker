package telnet;

import java.util.ArrayDeque;
import java.util.Deque;
import player.GameAction;
import player.DataFromRegex;

public class Logic {

    public Logic() {
    }

    public Deque<GameAction> getActions(DataFromRegex data) {
        Deque<GameAction> dq = new ArrayDeque<>();
        if (data != null) {
            if (data.getEnemy()=="bob") {
                GameAction b = new GameAction("backstab " + data.getEnemy());
                GameAction h = new GameAction("heartplunge");
                GameAction e = new GameAction("enervate");
                GameAction c = new GameAction("confuse");
                dq.add(b);
                dq.add(h);
                dq.add(e);
                dq.add(c);
            }
        }
        return dq;
    }
}
