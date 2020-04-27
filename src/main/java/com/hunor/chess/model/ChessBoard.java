package com.hunor.chess.model;

import com.hunor.chess.pieces.*;

import java.util.ArrayList;

public class ChessBoard {

    private ChessPiece[][] board;

    public ChessBoard() {
        board = new ChessPiece[8][8];

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                board[x][y] = null;
            }
        }

        for (int x = 0; x < 8; x++) {
            board[x][6] = new Pawn(PieceColor.WHITE, x, 6);
            board[x][1] = new Pawn(PieceColor.BLACK, x, 1);
        }

        board[0][0] = new Rook(PieceColor.BLACK, 0, 0);
        board[7][0] = new Rook(PieceColor.BLACK, 7, 0);
        board[0][7] = new Rook(PieceColor.WHITE, 0, 7);
        board[7][7] = new Rook(PieceColor.WHITE, 7, 7);

        board[1][0] = new Knight(PieceColor.BLACK, 1, 0);
        board[6][0] = new Knight(PieceColor.BLACK, 6, 0);
        board[1][7] = new Knight(PieceColor.WHITE, 1, 7);
        board[6][7] = new Knight(PieceColor.WHITE, 6, 7);

        board[2][0] = new Bishop(PieceColor.BLACK, 2, 0);
        board[5][0] = new Bishop(PieceColor.BLACK, 5, 0);
        board[2][7] = new Bishop(PieceColor.WHITE, 2, 7);
        board[5][7] = new Bishop(PieceColor.WHITE, 5, 7);

        board[3][0] = new Queen(PieceColor.BLACK, 3, 0);
        board[3][7] = new Queen(PieceColor.WHITE, 3, 7);

        board[4][0] = new King(PieceColor.BLACK, 4, 0);
        board[4][7]  = new King(PieceColor.WHITE, 4, 7);
    }

    public ArrayList<ChessPiece> getPieces(PieceColor pieceColor) {
        ArrayList<ChessPiece> pieces = new ArrayList<>();

        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (board[x][y].getPieceColor() == pieceColor) {
                    pieces.add(board[x][y]);
                }
            }
        }

        return pieces;
    }

    public ChessPiece pieceAt(int x, int y) {
        if (x >= 8 || x < 0 || y >= 8 || y < 0) {
            throw new IllegalArgumentException("Too big indexes");
        }

        return board[x][y];
    }

    public ChessPiece[][] getBoard() {
        return board;
    }

    public void setBoard(ChessPiece[][] board) {
        this.board = board;
    }
}
