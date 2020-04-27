package com.hunor.chess.view;

import com.hunor.chess.model.pieces.ChessPiece;
import javafx.scene.image.Image;

public class ImageLoader {
    public static Image loadPieceImage(ChessPiece piece) {
        if (piece == null)
            return null;
        return new Image(ImageLoader.class.getResourceAsStream("/img/" + piece.getPieceColor() + "/" +
                piece.getClass().getSimpleName().toLowerCase() + ".png"));
    }
}
