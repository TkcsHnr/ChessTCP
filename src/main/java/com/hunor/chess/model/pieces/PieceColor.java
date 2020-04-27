package com.hunor.chess.model.pieces;

public enum PieceColor {
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
