package com.hunor.chess.pieces;

import com.hunor.chess.model.ChessBoard;

public class Bishop extends ChessPiece {
    public Bishop(PieceColor pieceColor, int x, int y) {
        super(pieceColor, x, y);
        this.canJump = false;
        this.important = false;
    }

    @Override
    public boolean canMoveTo(int x, int y, ChessBoard chessBoard) {
        int dx = x - this.x;
        int dy = y - this.y;

        return dx == dy;
    }
}
