package player;

public class DataFromRegex {

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
    private String enemy = null;

    private DataFromRegex() {
    }

    private DataFromRegex(Builder builder) {
    }

    public String getEnemy() {
        return enemy;
    }

    @Override
    public String toString() {
        if (enemy!=null) {
            return enemy;
        } else {
            return "null enemy";
        }
    }
}