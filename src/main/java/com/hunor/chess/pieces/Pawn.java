package com.hunor.chess.pieces;

import com.hunor.chess.model.ChessBoard;
import com.hunor.chess.model.SimplePos;

public class Pawn extends ChessPiece {
    private int initialY;
    private int dir;

    public Pawn(PieceColor pieceColor, int x, int y) {
        super(pieceColor, x, y);
        this.important = false;

        this.initialY = y;
        switch (pieceColor) {
            case BLACK:
                this.dir = 1;
                break;
            case WHITE:
                this.dir = -1;
                break;
        }
    }

    @Override
    public boolean canMoveTo(SimplePos target, ChessBoard chessBoard) {
        if (!super.canMoveTo(target, chessBoard))
            return false;

        int dx = Math.abs(target.getX() - this.pos.getX());
        int dy = target.getY() - this.pos.getY();

        if (this.getPos().getY() == this.initialY) {
            if (dy == 2 * this.dir && dx == 0) {
                for (int s = 1; s <= 2; s++) {
                    if (chessBoard.pieceAt(this.pos.getX(), this.initialY + s * this.dir) != null)
                        return false;
                }
                return true;
            }

        }

        if (dy == this.dir) {
            if (dx == 1) {
                ChessPiece targetPiece = chessBoard.pieceAt(target);
                if (targetPiece == null) {
                    return false;
                } else if (targetPiece.getPieceColor() == this.getPieceColor().opposite()) {
                    return true;
                }
            } else if (dx == 0) {
                if (chessBoard.pieceAt(target.getX(), target.getY()) == null)
                    return true;
            }

        }

        return false;
    }
}
