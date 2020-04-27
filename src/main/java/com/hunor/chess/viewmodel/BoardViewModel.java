package com.hunor.chess.viewmodel;

import com.hunor.chess.logic.Property;
import com.hunor.chess.model.ChessBoard;
import com.hunor.chess.model.SimplePos;
import com.hunor.chess.pieces.ChessPiece;

public class BoardViewModel {

    private Property<ChessBoard> boardProp = new Property<>();
    private Property<ChessPiece> involvedPieceProp = new Property<>();
    private Property<SimplePos> mousePositionProp = new Property<>();

    public Property<ChessBoard> getBoardProp() {
        return boardProp;
    }

    public Property<ChessPiece> getInvolvedPieceProp() {
        return involvedPieceProp;
    }

    public Property<SimplePos> getMousePositionProp() {
        return mousePositionProp;
    }
}
