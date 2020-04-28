package com.hunor.chess.model.pieces;

import java.io.Serializable;

public enum PieceColor implements Serializable {
    BLACK,
    WHITE;

    public PieceColor opposite() {
        switch (this) {
            case WHITE:
                return BLACK;
            case BLACK:
                return WHITE;
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
