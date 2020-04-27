package com.hunor.chess.pieces;

import com.hunor.chess.model.ChessBoard;
import com.hunor.chess.model.SimplePos;

import java.io.Serializable;

public abstract class ChessPiece implements Serializable {
    protected SimplePos pos;
    protected PieceColor pieceColor;
    protected boolean canJump;
    protected boolean important;

    public ChessPiece(PieceColor pieceColor, int x, int y) {
        this.pieceColor = pieceColor;
        this.pos = new SimplePos(x, y);
    }

    public abstract boolean canMoveTo(SimplePos target, ChessBoard chessBoard);

    public SimplePos getPos() {
        return pos;
    }

    public PieceColor getPieceColor() {
        return pieceColor;
    }

    public boolean isImportant() {
        return important;
    }
}
