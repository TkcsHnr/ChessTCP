package com.hunor.chess.model;

import com.hunor.chess.model.pieces.*;

import java.util.ArrayList;

public class ChessBoard {

    private ArrayList<ChessPiece> pieces;

    private PieceColor actualColor = PieceColor.WHITE;

    public ChessBoard() {
        pieces = new ArrayList<>(32);

        for (int x = 0; x < 8; x++) {
            pieces.add(new Pawn(PieceColor.WHITE, x, 6));
            pieces.add(new Pawn(PieceColor.BLACK, x, 1));
        }

        pieces.add(new Rook(PieceColor.BLACK, 0, 0));
        pieces.add(new Rook(PieceColor.BLACK, 7, 0));
        pieces.add(new Rook(PieceColor.WHITE, 0, 7));
        pieces.add(new Rook(PieceColor.WHITE, 7, 7));

        pieces.add(new Knight(PieceColor.BLACK, 1, 0));
        pieces.add(new Knight(PieceColor.BLACK, 6, 0));
        pieces.add(new Knight(PieceColor.WHITE, 1, 7));
        pieces.add(new Knight(PieceColor.WHITE, 6, 7));

        pieces.add(new Bishop(PieceColor.BLACK, 2, 0));
        pieces.add(new Bishop(PieceColor.BLACK, 5, 0));
        pieces.add(new Bishop(PieceColor.WHITE, 2, 7));
        pieces.add(new Bishop(PieceColor.WHITE, 5, 7));

        pieces.add(new Queen(PieceColor.BLACK, 3, 0));
        pieces.add(new Queen(PieceColor.WHITE, 3, 7));

        pieces.add(new King(PieceColor.BLACK, 4, 0));
        pieces.add(new King(PieceColor.WHITE, 4, 7));
    }

    public ArrayList<ChessPiece> getPieces(PieceColor pieceColor) {
        ArrayList<ChessPiece> pieces = new ArrayList<>();

        for (ChessPiece piece : this.pieces) {
            if (piece != null) {
                if (piece.getPieceColor() == pieceColor)
                    pieces.add(piece);
            }
        }

        return pieces;
    }

    public ArrayList<ChessPiece> getPieces() {
        return pieces;
    }

    public ChessPiece pieceAt(SimplePos simplePos) {
        return pieceAt(simplePos.getX(), simplePos.getY());
    }

    public ChessPiece pieceAt(int x, int y) {
        for (ChessPiece piece : pieces) {
            if (piece.getPos().getX() == x && piece.getPos().getY() == y) {
                return piece;
            }
        }

        return null;
    }

    public void movePieceTo(ChessPiece piece, SimplePos target) {
        if (pieceAt(target) != null) {
            this.pieces.remove(pieceAt(target));
        }

        ChessPiece involved = pieceAt(piece.getPos());

        involved.setPos(target);
    }

    public PieceColor getActualColor() {
        return actualColor;
    }

    public void switchActualColor() {
        actualColor = actualColor.opposite();
    }
}
