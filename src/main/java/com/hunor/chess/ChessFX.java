package com.hunor.chess;

import com.hunor.chess.model.board.ChessBoard;
import com.hunor.chess.net.packet.MovementEvent;
import com.hunor.chess.util.event.EventBus;
import com.hunor.chess.view.ConstructView;
import com.hunor.chess.view.View;
import com.hunor.chess.viewmodel.BoardViewModel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class ChessFX extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        EventBus eventBus = new EventBus();

        View view = new View();
        ConstructView constructView = new ConstructView(eventBus);
        view.setCenter(constructView);

        eventBus.listenFor(ConstructEvent.class, event -> constructView.construct(view, eventBus, stage, this));

        Scene scene = new Scene(view, 700, 700);
        stage.setScene(scene);
        stage.show();

//        EventBus eventBus = new EventBus();
//
//        ChessBoard chessBoard = new ChessBoard();
//
//        BoardViewModel boardViewModel = new BoardViewModel();
//        ChessCanvas chessCanvas = new ChessCanvas(700, boardViewModel, eventBus);
//        view.setCenter(chessCanvas);
//
//        boardViewModel.getBoardProp().set(chessBoard);
//
//        Server server = new Server(3218, eventBus);
////        Client client = new Client("127.0.0.1", 3218, eventBus);
//        stage.setOnCloseRequest(windowEvent -> server.exit());
//
//        BoardManager boardManager = new BoardManager(boardViewModel, eventBus, server.managerColor());
//        eventBus.listenFor(BoardEvent.class, boardManager::handle);
//
//        eventBus.listenFor(MovementEvent.class, event -> {
//            if (event.getType() == Type.SEND)
//                server.sendPacket(event);
//            else if (event.getType() == Type.RECEIVE)
//                packetReceived(event, boardViewModel);
//        });
    }

    public void packetReceived(Object packet, BoardViewModel boardViewModel) {
        if (packet instanceof MovementEvent) {
            ChessBoard newBoard = boardViewModel.getBoardProp().get().copy();
            newBoard.movePieceTo(newBoard.pieceAt(((MovementEvent) packet).getInitial()), ((MovementEvent) packet).getTarget());
            newBoard.switchActualColor();
            boardViewModel.getBoardProp().set(newBoard);
        }
    }

}