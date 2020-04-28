package com.hunor.chess;

import com.hunor.chess.model.board.BoardEvent;
import com.hunor.chess.model.board.BoardManager;
import com.hunor.chess.model.board.ChessBoard;
import com.hunor.chess.model.pieces.PieceColor;
import com.hunor.chess.net.packet.PieceMovementPacket;
import com.hunor.chess.util.event.EventBus;
import com.hunor.chess.view.ChessCanvas;
import com.hunor.chess.viewmodel.BoardViewModel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public abstract class ChessFX extends Application {

    private BoardViewModel boardViewModel;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        EventBus eventBus = new EventBus();
        eventBus.listenFor(PieceMovementPacket.class, this::sendPacket);

        ChessBoard chessBoard = new ChessBoard();

        this.boardViewModel = new BoardViewModel();
        ChessCanvas chessCanvas = new ChessCanvas(700, boardViewModel, eventBus);

        BoardManager boardManager = new BoardManager(boardViewModel, eventBus, managerColor());
        eventBus.listenFor(BoardEvent.class, boardManager::handle);

        Scene scene = new Scene(chessCanvas, 700, 700);
        stage.setScene(scene);
        stage.setOnCloseRequest(windowEvent -> exit());
        stage.show();

        boardViewModel.getBoardProp().set(chessBoard);
    }

    public void packetReceived(Object packet) {
        if (packet instanceof PieceMovementPacket) {
            ChessBoard newBoard = boardViewModel.getBoardProp().get().copy();
            newBoard.movePieceTo(newBoard.pieceAt(((PieceMovementPacket) packet).getInitial()), ((PieceMovementPacket) packet).getTarget());
            newBoard.switchActualColor();
            boardViewModel.getBoardProp().set(newBoard);
        }
    }

    public abstract PieceColor managerColor();

    public abstract void sendPacket(Object packet);

    public abstract void exit();
}