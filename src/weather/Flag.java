package weather;

import java.util.ArrayDeque;
import java.util.Deque;

public enum Flag {

    BACKSTAB() {

        @Override
        Deque<WeatherAction> getActionsForState() {
            Deque<WeatherAction> actions = new ArrayDeque<>();
            return actions;
        }
    };

    abstract Deque<WeatherAction> getActionsForState();
}