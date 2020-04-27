package com.hunor.chess.pieces;

public enum PieceColor {
    BLACK,
    WHITE;


    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
