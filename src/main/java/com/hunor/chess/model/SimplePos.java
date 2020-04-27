package com.hunor.chess.model;

public class SimplePos {
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

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void update(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SimplePos) {
            return this.x == ((SimplePos) obj).x && this.y == ((SimplePos) obj).y;
        }
        return super.equals(obj);
    }
}
