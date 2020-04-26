package com.hunor.chess.pieces;

import com.hunor.chess.ChessBoard;

public class Pawn extends ChessPiece {
    private int initialY;

    public Pawn(PieceColor pieceColor, int x, int y) {
        super(pieceColor, x, y);
        this.initialY = y;
        this.canJump = false;
        this.important = false;
    }

    @Override
    public boolean canMoveTo(int x, int y, ChessBoard chessBoard) {
        if (this.y == this.initialY && x - this.x == 0) {
            switch (this.pieceColor) {
                case WHITE:
                    if (this.y - y == 2)
                        return true;
                    break;
                case BLACK:
                    if (y - this.y == 2)
                        return true;
                    break;
            }
        }
        switch (this.pieceColor) {
            case WHITE:
                if (this.y - y == 1 && Math.abs(this.x - x) <= 1) {
                    ChessPiece targetPiece = chessBoard.pieceAt(x, y);
                    if (targetPiece == null) {
                        return true;
                    } else if (targetPiece.getPieceColor() == PieceColor.BLACK) {
                        return true;
                    }
                }
                break;
            case BLACK:
                if (y - this.y == 1) {
                    ChessPiece targetPiece = chessBoard.pieceAt(x, y);
                    if (targetPiece == null) {
                        return true;
                    } else if (targetPiece.getPieceColor() == PieceColor.WHITE) {
                        return true;
                    }
                }
                break;
        }

        return false;
    }
}
