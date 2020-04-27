package com.hunor.chess.pieces.rules;

import com.hunor.chess.model.ChessBoard;
import com.hunor.chess.pieces.ChessPiece;

public class DiagonalRule {


    /**
     * @param dx   is the absolute delta x between the position and the target
     * @param dy   is the absolute delta y between the position and the target
     * @param xDir is the x direction of the movement, it can be 1 or -1
     * @param yDir is the y direction of the movement, it can be 1 or -1
     */
    public static boolean canPieceMoveTo(ChessPiece piece, int dx, int dy, int xDir, int yDir, ChessBoard chessBoard) {
        if (dx == dy) {
            int x = 1;
            int y = 1;
            if (dx > 1)
                do {
                    if (chessBoard.pieceAt(piece.getPos().getX() + x * xDir, piece.getPos().getY() + y * yDir) != null)
                        return false;

                    x++;
                    y++;
                } while (x < dx && y < dy);
            return true;
        }
        return false;
    }

}
