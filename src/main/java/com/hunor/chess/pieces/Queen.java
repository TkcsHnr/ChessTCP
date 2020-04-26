package com.hunor.chess.pieces;

import com.hunor.chess.ChessBoard;

public class Queen extends ChessPiece {
    public Queen(PieceColor pieceColor, int x, int y) {
        super(pieceColor, x, y);
        this.canJump = false;
        this.important = false;
    }

    @Override
    public boolean canMoveTo(int x, int y, ChessBoard chessBoard) {
        int dx = Math.abs(x - this.x);
        int dy = Math.abs(y - this.y);

        return dx == 0 || dy == 0 || dx == dy;
    }
}
