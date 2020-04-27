package com.hunor.chess.pieces;

import com.hunor.chess.model.ChessBoard;

public class Knight extends ChessPiece {
    public Knight(PieceColor pieceColor, int x, int y) {
        super(pieceColor, x, y);
        this.canJump = true;
        this.important = false;
    }

    @Override
    public boolean canMoveTo(int x, int y, ChessBoard chessBoard) {
        int dx = Math.abs(x - this.x);
        int dy = Math.abs(y - this.y);

        return (dx == 2 || dy == 2) && dx + dy == 3;
    }
}
