package weather;

import java.util.ArrayDeque;
import java.util.Deque;

public class ActionsDequeuWrapper {

    private Deque<WeatherAction> ga = new ArrayDeque<>();

    public ActionsDequeuWrapper() {
    }

    public void add(Deque<WeatherAction> actions) {
        ga.addAll(actions);
    }

    public Deque<WeatherAction> get() {
        return ga;
    }

}
