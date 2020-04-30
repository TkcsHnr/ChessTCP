package com.hunor.chess.view;

import com.hunor.chess.ChessFX;
import com.hunor.chess.ConstructEvent;
import com.hunor.chess.model.board.BoardEvent;
import com.hunor.chess.model.board.BoardManager;
import com.hunor.chess.model.board.ChessBoard;
import com.hunor.chess.net.Client;
import com.hunor.chess.net.Participant;
import com.hunor.chess.net.Server;
import com.hunor.chess.net.packet.MovementEvent;
import com.hunor.chess.net.packet.MovementEvent.Type;
import com.hunor.chess.util.event.EventBus;
import com.hunor.chess.viewmodel.BoardViewModel;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ConstructView extends VBox {

    private ComboBox<ConstructType> modeChoose;
    private Button launchButton;
    private TextField hostField;
    private TextField portField;

    public ConstructView(EventBus eventBus) {
        this.hostField = new TextField();
        this.portField = new TextField();
        this.portField.textProperty().addListener((observableValue, oldV, newV) -> {
            if (!newV.matches("\\d*")) {
                portField.setText(newV.replaceAll("\\D+", ""));
            }
        });

        this.modeChoose = new ComboBox<>(FXCollections.observableArrayList(ConstructType.values()));
        this.modeChoose.setValue(ConstructType.SERVER);
        this.modeChoose.valueProperty().addListener((observableValue, constructType, t1) -> {
            switch (t1) {
                case SERVER:
                    this.getChildren().remove(this.hostField);
                    break;
                case CLIENT:
                    this.getChildren().add(1, this.hostField);
                    break;
            }
        });

        this.launchButton = new Button("Launch");
        this.launchButton.setOnAction(actionEvent -> eventBus.emit(new ConstructEvent()));

        this.getChildren().addAll(modeChoose, portField, launchButton);
    }

    public void construct(View view, EventBus eventBus, Stage stage, ChessFX chessFX) {
        if (portField.getText().isBlank()) return;

        int port = Integer.parseInt(portField.getText());
        final Participant participant;
        switch (modeChoose.getValue()) {
            case SERVER:
                participant = new Server(port, eventBus);
                break;
            case CLIENT:
                if (hostField.getText().isBlank()) return;
                String host = hostField.getText();
                participant = new Client(host, port, eventBus);
                break;
            default:
                participant = null;
        }

        ChessBoard chessBoard = new ChessBoard();

        BoardViewModel boardViewModel = new BoardViewModel();
        ChessCanvas chessCanvas = new ChessCanvas(700, boardViewModel, eventBus);
        view.setCenter(chessCanvas);

        boardViewModel.getBoardProp().set(chessBoard);

        stage.setOnCloseRequest(windowEvent -> participant.exit());

        BoardManager boardManager = new BoardManager(boardViewModel, eventBus, participant.managerColor());
        eventBus.listenFor(BoardEvent.class, boardManager::handle);

        eventBus.listenFor(MovementEvent.class, event -> {
            if (event.getType() == Type.SEND)
                participant.sendPacket(event);
            else if (event.getType() == Type.RECEIVE)
                chessFX.packetReceived(event, boardViewModel);
        });
    }

    public enum ConstructType {
        SERVER,
        CLIENT
    }
}
