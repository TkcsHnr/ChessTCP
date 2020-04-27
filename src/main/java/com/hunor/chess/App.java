package com.hunor.chess;

import com.hunor.chess.model.ChessBoard;
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
        BoardViewModel boardViewModel = new BoardViewModel();
        ChessCanvas chessCanvas = new ChessCanvas(700, boardViewModel);

        ChessBoard chessBoard = new ChessBoard();

        Scene scene = new Scene(chessCanvas, 700, 700);
        stage.setScene(scene);
        stage.show();

        boardViewModel.getBoardProp().set(chessBoard);
    }

    public static void main(String[] args) {
        launch();
    }

}