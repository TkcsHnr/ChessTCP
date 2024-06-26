package com.hunor.chess.viewmodel;

import com.hunor.chess.model.SimplePos;
import com.hunor.chess.model.board.ChessBoard;
import com.hunor.chess.model.pieces.ChessPiece;
import com.hunor.chess.util.Property;

public class BoardViewModel {

    private Property<ChessBoard> boardProp = new Property<>();
    private Property<SimplePos> mousePositionProp = new Property<>();
    private ChessPiece involvedPiece;

    public Property<ChessBoard> getBoardProp() {
        return boardProp;
    }

    public Property<SimplePos> getMousePositionProp() {
        return mousePositionProp;
    }

    public ChessPiece getInvolvedPiece() {
        return involvedPiece;
    }

    public void setInvolvedPiece(ChessPiece involvedPiece) {
        this.involvedPiece = involvedPiece;
    }
}
