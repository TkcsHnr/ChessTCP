package com.hunor.chess.pieces;

import com.hunor.chess.model.ChessBoard;
import com.hunor.chess.model.SimplePos;

public class Bishop extends ChessPiece {
    public Bishop(PieceColor pieceColor, int x, int y) {
        super(pieceColor, x, y);
        this.canJump = false;
        this.important = false;
    }

    @Override
    public boolean canMoveTo(SimplePos target, ChessBoard chessBoard) {
        if (!super.canMoveTo(target, chessBoard))
            return false;

        int dx = Math.abs(target.getX() - this.pos.getX());
        int dy = Math.abs(target.getY() - this.pos.getY());

        return dx == dy;
    }
}
