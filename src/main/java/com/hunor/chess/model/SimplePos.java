package com.hunor.chess.model;

import java.io.Serializable;

public class SimplePos implements Serializable {
    private int x, y;

    public SimplePos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SimplePos) {
            return this.x == ((SimplePos) obj).x && this.y == ((SimplePos) obj).y;
        }
        return super.equals(obj);
    }
}
