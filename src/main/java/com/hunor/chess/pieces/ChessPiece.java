package com.hunor.chess.pieces;

import com.hunor.chess.ChessBoard;

public abstract class ChessPiece {
    protected int x, y;
    protected PieceColor pieceColor;
    protected boolean canJump;
    protected boolean important;

    public ChessPiece(PieceColor pieceColor, int x, int y) {
        this.pieceColor = pieceColor;
        this.x = x;
        this.y = y;
    }

    public abstract boolean canMoveTo(int x, int y, ChessBoard chessBoard);

    public PieceColor getPieceColor() {
        return pieceColor;
    }

    public boolean isImportant() {
        return important;
    }
}
