package com.hunor.chess.pieces;

import com.hunor.chess.model.ChessBoard;
import com.hunor.chess.model.SimplePos;

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

        if (dx == 0) {
            for (int y = 1; y < dy * yDir; y++) {
                if (chessBoard.pieceAt(this.pos.getX(), this.pos.getY() + y * yDir) != null)
                    return false;
            }
            return true;
        } else if (dy == 0) {
            for (int x = 1; x < dx * xDir; x++) {
                if (chessBoard.pieceAt(this.pos.getX() + x * xDir, this.pos.getY()) != null)
                    return false;
            }
            return true;
        } else {
            dx = dx * xDir;
            dy = dy * yDir;
            if (dx == dy) {
                int x = 1;
                int y = 1;
                if (dx > 1)
                    do {
                        if (chessBoard.pieceAt(this.pos.getX() + x * xDir, this.pos.getY() + y * yDir) != null)
                            return false;

                        x++;
                        y++;
                    } while (x < dx && y < dy);
                return true;
            }
        }

        return false;
    }
}
