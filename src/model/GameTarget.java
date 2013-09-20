package model;

import java.util.Map;
import java.util.logging.Logger;

public class GameTarget {

    private static Logger log = Logger.getLogger(GameTarget.class.getName());
    private String enemy = null;

    public GameTarget() {
    }

    public String getEnemy() {
        return enemy;
    }

    public void setEnemy(String enemy) {
        this.enemy = enemy;
    }
}