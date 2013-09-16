package weather;

public class Player {

    public static class Builder {

        private String enemy = null;

        public Builder enemy(String enemy) {
            this.enemy = enemy;
            return this;
        }

        public Player build() {
            return new Player(this);
        }
    }
    private String enemy = null;

    private Player() {
    }

    private Player(Builder builder) {
    }

    public String getEnemy() {
        return enemy;
    }
}