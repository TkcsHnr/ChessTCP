package com.hunor.chess.pieces;

import com.hunor.chess.model.ChessBoard;

public class Rook extends ChessPiece {
    public Rook(PieceColor pieceColor, int x, int y) {
        super(pieceColor, x, y);
        this.canJump = false;
        this.important = false;
    }

    @Override
    public boolean canMoveTo(int x, int y, ChessBoard chessBoard) {
        int dx = Math.abs(x - this.x);
        int dy = Math.abs(y - this.y);

        if (dx == 0) {
            return true;
        }
        else if (dy == 0) {
            return true;
        }
        else return false;
    }
}
