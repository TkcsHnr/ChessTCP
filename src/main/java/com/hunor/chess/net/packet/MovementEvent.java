package com.hunor.chess.net.packet;

import com.hunor.chess.model.SimplePos;
import com.hunor.chess.util.event.Event;

public class MovementEvent implements Event {
    private Type type;
    private SimplePos initial, target;

    public MovementEvent(Type type, SimplePos initial, SimplePos target) {
        this.type = type;
        this.initial = initial;
        this.target = target;
    }

    public Type getType() {
        return type;
    }

    public enum Type {
        SEND,
        RECEIVE
    }

    public SimplePos getInitial() {
        return initial;
    }

    public SimplePos getTarget() {
        return target;
    }
}
