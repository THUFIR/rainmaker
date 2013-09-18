package model;

import java.util.ArrayDeque;
import java.util.Deque;

public enum Flag {

    BACKSTAB() {

        @Override
        Deque<GameAction> getActionsForState() {
            Deque<GameAction> actions = new ArrayDeque<>();
            return actions;
        }
    };

    abstract Deque<GameAction> getActionsForState();
}