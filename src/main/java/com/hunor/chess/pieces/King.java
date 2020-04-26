package com.hunor.chess.pieces;

import com.hunor.chess.ChessBoard;

public class King extends ChessPiece {
    public King(PieceColor pieceColor, int x, int y) {
        super(pieceColor, x, y);
        this.canJump = false;
        this.important = true;
    }

    @Override
    public boolean canMoveTo(int x, int y, ChessBoard chessBoard) {
        int dx = Math.abs(x - this.x);
        int dy = Math.abs(y - this.y);

        return dx + dy == 1 || dx + dy == 2;
    }
}
