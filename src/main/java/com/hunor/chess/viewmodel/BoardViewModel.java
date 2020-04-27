package com.hunor.chess.viewmodel;

import com.hunor.chess.logic.Property;
import com.hunor.chess.model.ChessBoard;
import com.hunor.chess.model.SimplePos;
import com.hunor.chess.pieces.ChessPiece;

public class BoardViewModel {

    private Property<ChessBoard> boardProperty = new Property<>();
    private Property<ChessPiece> involvedPiece = new Property<>();
    private Property<SimplePos> mousePosition = new Property<>();

    public Property<ChessBoard> getBoardProperty() {
        return boardProperty;
    }

    public Property<ChessPiece> getInvolvedPiece() {
        return involvedPiece;
    }

    public Property<SimplePos> getMousePosition() {
        return mousePosition;
    }
}
