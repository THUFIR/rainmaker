package player;

import java.util.logging.Logger;

public class DataFromRegex {

    private static Logger log = Logger.getLogger(DataFromRegex.class.getName());
    private String enemy = null;

    private DataFromRegex(Builder builder) {
        this.enemy = builder.enemy;
        log.info(enemy);
    }

    private DataFromRegex() {
    }

    public String getEnemy() {
        return enemy;
    }

    @Override
    public String toString() {
        if (enemy != null) {
            return enemy;
        } else {
            return "null enemy";
        }
    }

    public static class Builder {

        private String enemy = null;

        public Builder enemy(String enemy) {
            this.enemy = enemy;
            return this;
        }

        public DataFromRegex build() {
            return new DataFromRegex(this);
        }
    }
}