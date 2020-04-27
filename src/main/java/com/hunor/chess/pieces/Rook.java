package com.hunor.chess.pieces;

import com.hunor.chess.model.ChessBoard;
import com.hunor.chess.model.SimplePos;

public class Rook extends ChessPiece {
    public Rook(PieceColor pieceColor, int x, int y) {
        super(pieceColor, x, y);
        this.canJump = false;
        this.important = false;
    }

    @Override
    public boolean canMoveTo(SimplePos target, ChessBoard chessBoard) {
        int dx = Math.abs(target.getX() - this.pos.getX());
        int dy = Math.abs(target.getY() - this.pos.getY());

        if (dx == 0) {
            return true;
        }
        else if (dy == 0) {
            return true;
        }
        else return false;
    }
}
