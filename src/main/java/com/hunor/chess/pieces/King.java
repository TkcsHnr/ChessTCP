package com.hunor.chess.pieces;

import com.hunor.chess.model.ChessBoard;
import com.hunor.chess.model.SimplePos;

public class King extends ChessPiece {
    public King(PieceColor pieceColor, int x, int y) {
        super(pieceColor, x, y);
        this.important = true;
    }

    @Override
    public boolean canMoveTo(SimplePos target, ChessBoard chessBoard) {
        if (!super.canMoveTo(target, chessBoard))
            return false;

        int dx = Math.abs(target.getX() - this.pos.getX());
        int dy = Math.abs(target.getY() - this.pos.getY());

        if (dx > 0 && dy > 0)
            if (dx + dy == 2)
                return true;

        return dx + dy == 1;
    }
}
