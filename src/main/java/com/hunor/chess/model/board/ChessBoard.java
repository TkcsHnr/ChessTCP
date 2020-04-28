package com.hunor.chess.model.board;

import com.hunor.chess.model.SimplePos;
import com.hunor.chess.model.pieces.*;

import java.util.ArrayList;

public class ChessBoard {

    private ArrayList<ChessPiece> pieces;

    private PieceColor actualColor;

    public ChessBoard() {
        pieces = new ArrayList<>(32);

        initDefaultPieces();
    }

    private void initDefaultPieces() {
        pieces.clear();

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

        this.actualColor = PieceColor.WHITE;
    }

    public ChessBoard copy() {
        ChessBoard copy = new ChessBoard();

        for (int i = 0; i < this.pieces.size(); i++) {
            copy.pieces.set(i, this.pieces.get(i));
        }
        copy.actualColor = this.actualColor;

        return copy;
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

    public void setPieces(ArrayList<ChessPiece> pieces) {
        this.pieces = pieces;
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
        pieceAt(piece.getPos()).setPos(target);

        boolean wKing = false;
        boolean bKing = false;
        for (ChessPiece wPiece : getPieces(PieceColor.WHITE)) {
            if (wPiece.isImportant()) {
                wKing = true;
                break;
            }
        }
        for (ChessPiece bPiece : getPieces(PieceColor.BLACK)) {
            if (bPiece.isImportant()) {
                bKing = true;
                break;
            }
        }

        if (!wKing)
            initDefaultPieces();
        else if (!bKing)
            initDefaultPieces();
    }

    public PieceColor getActualColor() {
        return actualColor;
    }

    public void setActualColor(PieceColor actualColor) {
        this.actualColor = actualColor;
    }

    public void switchActualColor() {
        actualColor = actualColor.opposite();
    }
}
