package com.hunor.chess.model.pieces;

<<<<<<< HEAD
import java.io.Serializable;

public enum PieceColor implements Serializable {
=======
public enum PieceColor {
>>>>>>> 14676a0c26466530c9259f42704d2e3d71721487
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
