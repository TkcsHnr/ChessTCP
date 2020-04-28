package com.hunor.chess.model.pieces.rules;

import com.hunor.chess.model.board.ChessBoard;
import com.hunor.chess.model.pieces.ChessPiece;

public class StraightRule {

    /**
     * @param dx   is the delta x between the position and the target
     * @param dy   is the delta y between the position and the target
     * @param xDir is the x direction of the movement, it can be 1, 0 or -1
     * @param yDir is the y direction of the movement, it can be 1, 0 or -1
     */
    public static boolean canPieceMoveTo(ChessPiece piece, int dx, int dy, int xDir, int yDir, ChessBoard chessBoard) {
        if (dx == 0) {
            for (int y = 1; y < dy * yDir; y++) {
                if (chessBoard.pieceAt(piece.getPos().getX(), piece.getPos().getY() + y * yDir) != null)
                    return false;
            }
            return true;
        } else if (dy == 0) {
            for (int x = 1; x < dx * xDir; x++) {
                if (chessBoard.pieceAt(piece.getPos().getX() + x * xDir, piece.getPos().getY()) != null)
                    return false;
            }
            return true;
        }
        return false;
    }

}
