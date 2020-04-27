package com.hunor.chess.model;

import com.hunor.chess.BoardEvent;
import com.hunor.chess.model.pieces.ChessPiece;
import com.hunor.chess.viewmodel.BoardViewModel;

public class BoardManager {

    private BoardViewModel boardViewModel;

    public BoardManager(BoardViewModel boardViewModel) {
        this.boardViewModel = boardViewModel;
    }

    public void handle(BoardEvent boardEvent) {
        switch (boardEvent.getType()) {
            case PRESSED:
                pressed(boardEvent);
                dragged(boardEvent);
                break;
            case DRAGGED:
                dragged(boardEvent);
                break;
            case RELEASED:
                released(boardEvent);
                break;
        }
    }

    private void pressed(BoardEvent boardEvent) {
        ChessPiece involvedPiece = boardViewModel.getBoardProp().get().pieceAt(boardEvent.getPos());
        if (involvedPiece != null && involvedPiece.getPieceColor() == boardViewModel.getBoardProp().get().getActualColor()) {
            boardViewModel.setInvolvedPiece(involvedPiece);
        }
    }

    private void dragged(BoardEvent boardEvent) {
        if (!boardEvent.getPos().equals(boardViewModel.getMousePositionProp().get()) && boardViewModel.getInvolvedPiece() != null) {
            boardViewModel.getMousePositionProp().set(boardEvent.getPos());
        }
    }

    private void released(BoardEvent boardEvent) {
        ChessPiece involvedPiece = boardViewModel.getInvolvedPiece();
        if (involvedPiece != null) {
            ChessBoard chessBoard = boardViewModel.getBoardProp().get().copy();
            if (involvedPiece.canMoveTo(boardEvent.getPos(), chessBoard)) {
                chessBoard.movePieceTo(involvedPiece, boardEvent.getPos());
                chessBoard.switchActualColor();
                boardViewModel.getBoardProp().set(chessBoard);
            }

            boardViewModel.setInvolvedPiece(null);
        }

        boardViewModel.getMousePositionProp().clear();
    }

}
