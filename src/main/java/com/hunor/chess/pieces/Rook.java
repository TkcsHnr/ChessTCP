package com.hunor.chess.pieces;

import com.hunor.chess.model.ChessBoard;
import com.hunor.chess.model.SimplePos;
import com.hunor.chess.pieces.rules.StraightRule;

public class Rook extends ChessPiece {
    public Rook(PieceColor pieceColor, int x, int y) {
        super(pieceColor, x, y);
        this.important = false;
    }

    @Override
    public boolean canMoveTo(SimplePos target, ChessBoard chessBoard) {
        if (!super.canMoveTo(target, chessBoard))
            return false;

        int dx = target.getX() - this.pos.getX();
        int dy = target.getY() - this.pos.getY();
        int xDir = Integer.compare(dx, 0);
        int yDir = Integer.compare(dy, 0);
//        dx = dx * xDir;
//        dy = dy * yDir;

        return StraightRule.canPieceMoveTo(this, dx, dy, xDir, yDir, chessBoard);
    }
}
