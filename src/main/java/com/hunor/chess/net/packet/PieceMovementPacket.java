package com.hunor.chess.net.packet;

import com.hunor.chess.model.SimplePos;
import com.hunor.chess.util.event.Event;

public class PieceMovementPacket implements Event {
    private SimplePos initial, target;

    public PieceMovementPacket(SimplePos initial, SimplePos target) {
        this.initial = initial;
        this.target = target;
    }

    public SimplePos getInitial() {
        return initial;
    }

    public SimplePos getTarget() {
        return target;
    }
}
