package com.hunor.chess.model.board;

import com.hunor.chess.model.SimplePos;
import com.hunor.chess.util.event.Event;

public class BoardEvent implements Event {

    private Type type;
    private SimplePos pos;

    public BoardEvent(Type type, SimplePos pos) {
        this.type = type;
        this.pos = pos;
    }

    public Type getType() {
        return type;
    }

    public SimplePos getPos() {
        return pos;
    }

    public enum Type {
        PRESSED,
        DRAGGED,
        RELEASED
    }
}
