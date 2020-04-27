package com.hunor.chess.model.pieces;

import com.hunor.chess.model.SimplePos;
import com.hunor.chess.model.board.ChessBoard;
import com.hunor.chess.model.pieces.rules.DiagonalRule;
import com.hunor.chess.model.pieces.rules.StraightRule;

public class Queen extends ChessPiece {
    public Queen(PieceColor pieceColor, int x, int y) {
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

        boolean canGoStraight = StraightRule.canPieceMoveTo(this, dx, dy, xDir, yDir, chessBoard);
        if (canGoStraight)
            return true;
        else {
            dx = dx * xDir;
            dy = dy * yDir;
            return DiagonalRule.canPieceMoveTo(this, dx, dy, xDir, yDir, chessBoard);
        }
    }
}
