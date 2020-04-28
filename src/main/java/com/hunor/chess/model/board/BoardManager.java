package com.hunor.chess.model.board;

<<<<<<< HEAD
import com.hunor.chess.model.SimplePos;
import com.hunor.chess.model.pieces.ChessPiece;
import com.hunor.chess.model.pieces.PieceColor;
import com.hunor.chess.net.packet.PieceMovementPacket;
import com.hunor.chess.util.event.EventBus;
=======
import com.hunor.chess.model.pieces.ChessPiece;
>>>>>>> 14676a0c26466530c9259f42704d2e3d71721487
import com.hunor.chess.viewmodel.BoardViewModel;

public class BoardManager {

<<<<<<< HEAD
    private PieceColor pieceColor;
    private BoardViewModel boardViewModel;
    private EventBus eventBus;

    public BoardManager(BoardViewModel boardViewModel, EventBus eventBus, PieceColor pieceColor) {
        this.boardViewModel = boardViewModel;
        this.eventBus = eventBus;
        this.pieceColor = pieceColor;
=======
    private BoardViewModel boardViewModel;

    public BoardManager(BoardViewModel boardViewModel) {
        this.boardViewModel = boardViewModel;
>>>>>>> 14676a0c26466530c9259f42704d2e3d71721487
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
<<<<<<< HEAD
        if (involvedPiece == null)
            return;
        if (involvedPiece.getPieceColor() != this.pieceColor)
            return;
        if (involvedPiece.getPieceColor() != boardViewModel.getBoardProp().get().getActualColor())
            return;
        boardViewModel.setInvolvedPiece(involvedPiece);
    }

    private void dragged(BoardEvent boardEvent) {
        if (boardEvent.getPos().equals(boardViewModel.getMousePositionProp().get()))
            return;
        if (boardViewModel.getInvolvedPiece() == null)
            return;
        boardViewModel.getMousePositionProp().set(boardEvent.getPos());
=======
        if (involvedPiece != null && involvedPiece.getPieceColor() == boardViewModel.getBoardProp().get().getActualColor()) {
            boardViewModel.setInvolvedPiece(involvedPiece);
        }
    }

    private void dragged(BoardEvent boardEvent) {
        if (!boardEvent.getPos().equals(boardViewModel.getMousePositionProp().get()) && boardViewModel.getInvolvedPiece() != null) {
            boardViewModel.getMousePositionProp().set(boardEvent.getPos());
        }
>>>>>>> 14676a0c26466530c9259f42704d2e3d71721487
    }

    private void released(BoardEvent boardEvent) {
        ChessPiece involvedPiece = boardViewModel.getInvolvedPiece();
<<<<<<< HEAD
        if (involvedPiece == null)
            return;

        ChessBoard chessBoard = boardViewModel.getBoardProp().get().copy();
        if (involvedPiece.canMoveTo(boardEvent.getPos(), chessBoard)) {
            SimplePos initial = involvedPiece.getPos();
            //asdasd

            chessBoard.movePieceTo(involvedPiece, boardEvent.getPos());
            chessBoard.switchActualColor();
            boardViewModel.getBoardProp().set(chessBoard);

            eventBus.emit(new PieceMovementPacket(initial, boardEvent.getPos()));
        }

        boardViewModel.setInvolvedPiece(null);
=======
        if (involvedPiece != null) {
            ChessBoard chessBoard = boardViewModel.getBoardProp().get().copy();
            if (involvedPiece.canMoveTo(boardEvent.getPos(), chessBoard)) {
                chessBoard.movePieceTo(involvedPiece, boardEvent.getPos());
                chessBoard.switchActualColor();
                boardViewModel.getBoardProp().set(chessBoard);
            }

            boardViewModel.setInvolvedPiece(null);
        }

>>>>>>> 14676a0c26466530c9259f42704d2e3d71721487
        boardViewModel.getMousePositionProp().clear();
    }

}
