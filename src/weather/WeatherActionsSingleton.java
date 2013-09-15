package weather;

import java.util.Deque;

public enum WeatherActionsSingleton {

    INSTANCE;
    private ActionsDequeuWrapper actions = new ActionsDequeuWrapper();

    public void addActions(Deque<WeatherAction> newActions) {
        actions.add(newActions);
    }

    public ActionsDequeuWrapper getActions() {
        return actions;
    }

    public void reset() {
        actions = new ActionsDequeuWrapper();
    }
}
