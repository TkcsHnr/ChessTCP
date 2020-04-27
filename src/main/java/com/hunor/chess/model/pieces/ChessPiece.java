package com.hunor.chess.model.pieces;

import com.hunor.chess.model.ChessBoard;
import com.hunor.chess.model.SimplePos;

import java.io.Serializable;

public abstract class ChessPiece implements Serializable {
    protected SimplePos pos;
    protected PieceColor pieceColor;
    protected boolean important;

    public ChessPiece(PieceColor pieceColor, int x, int y) {
        this.pieceColor = pieceColor;
        this.pos = new SimplePos(x, y);
    }

    public boolean canMoveTo(SimplePos target, ChessBoard chessBoard) {
        if (target == null)
            return false;
        if (chessBoard == null)
            return false;

        if (chessBoard.getActualColor() != this.pieceColor)
            return false;

        ChessPiece targetPiece = chessBoard.pieceAt(target);
        if (targetPiece != null) {
            return targetPiece.getPieceColor() != this.getPieceColor();
        }

        return true;
    }

    public SimplePos getPos() {
        return pos;
    }

    public void setPos(SimplePos pos) {
        this.pos = pos;
    }

    public PieceColor getPieceColor() {
        return pieceColor;
    }

    public boolean isImportant() {
        return important;
    }
}
