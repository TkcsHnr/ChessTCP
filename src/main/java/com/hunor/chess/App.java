package com.hunor.chess;

import com.hunor.chess.model.BoardManager;
import com.hunor.chess.model.ChessBoard;
import com.hunor.chess.util.event.EventBus;
import com.hunor.chess.view.ChessCanvas;
import com.hunor.chess.viewmodel.BoardViewModel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        EventBus eventBus = new EventBus();

        BoardViewModel boardViewModel = new BoardViewModel();
        ChessCanvas chessCanvas = new ChessCanvas(700, boardViewModel, eventBus);

        ChessBoard chessBoard = new ChessBoard();

        BoardManager boardManager = new BoardManager(boardViewModel);
        eventBus.listenFor(BoardEvent.class, boardManager::handle);

        Scene scene = new Scene(chessCanvas, 700, 700);
        stage.setScene(scene);
        stage.show();

        boardViewModel.getBoardProp().set(chessBoard);
    }

    public static void main(String[] args) {
        launch();
    }

}