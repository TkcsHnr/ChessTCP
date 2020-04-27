package com.hunor.chess.view;

import com.hunor.chess.BoardEvent;
import com.hunor.chess.ImageLoader;
import com.hunor.chess.model.ChessBoard;
import com.hunor.chess.model.SimplePos;
import com.hunor.chess.model.pieces.ChessPiece;
import com.hunor.chess.util.event.EventBus;
import com.hunor.chess.viewmodel.BoardViewModel;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.transform.Affine;
import javafx.scene.transform.NonInvertibleTransformException;

public class ChessCanvas extends Pane {

    private Affine affine;
    private Canvas mainCanvas;
    private Canvas highlightCanvas;
    private GraphicsContext mainG;
    private GraphicsContext highlightG;

    private BoardViewModel boardViewModel;
    private EventBus eventBus;

    public ChessCanvas(int size, BoardViewModel boardViewModel, EventBus eventBus) {
        this.mainCanvas = new Canvas(size, size);
        this.getChildren().add(mainCanvas);

        this.highlightCanvas = new Canvas(size, size);
        this.getChildren().add(highlightCanvas);

        this.boardViewModel = boardViewModel;
        boardViewModel.getBoardProp().listen(this::redraw);
        boardViewModel.getMousePositionProp().listen(this::highlight);

        this.eventBus = eventBus;

        this.setOnMousePressed(this::handlePress);
        this.setOnMouseDragged(this::handleDrag);
        this.setOnMouseReleased(this::handleRelease);

        this.affine = new Affine();
        affine.appendScale(size / 8., size / 8.);

        this.mainG = this.mainCanvas.getGraphicsContext2D();
        mainG.setTransform(this.affine);

        this.highlightG = this.highlightCanvas.getGraphicsContext2D();
        highlightG.setTransform(this.affine);
        highlightG.setLineWidth(0.025);
        highlightG.setLineJoin(StrokeLineJoin.ROUND);
    }

    private void handlePress(MouseEvent mouseEvent) {
        eventBus.emit(new BoardEvent(BoardEvent.Type.PRESSED, getMouseCoords(mouseEvent)));
    }

    private void handleDrag(MouseEvent mouseEvent) {
        eventBus.emit(new BoardEvent(BoardEvent.Type.DRAGGED, getMouseCoords(mouseEvent)));
    }

    private void handleRelease(MouseEvent mouseEvent) {
        eventBus.emit(new BoardEvent(BoardEvent.Type.RELEASED, getMouseCoords(mouseEvent)));
    }

    private SimplePos getMouseCoords(MouseEvent mouseEvent) {
        try {
            Point2D point = affine.inverseTransform(mouseEvent.getX(), mouseEvent.getY());
            return new SimplePos((int)point.getX(), (int)point.getY());
        } catch (NonInvertibleTransformException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void redraw(ChessBoard chessBoard) {
        if (chessBoard == null)
            return;

        mainG.drawImage(new Image(getClass().getResourceAsStream("/img/board.png")), 0, 0, 8, 8);

        for (ChessPiece piece : chessBoard.getPieces()) {
            Image image = ImageLoader.loadPieceImage(piece);
            mainG.drawImage(image, piece.getPos().getX() + 0.05, piece.getPos().getY() + 0.05, 0.9, 0.9);
        }
    }

    private void highlight(SimplePos target) {
        highlightG.clearRect(0, 0, 8, 8);

        ChessPiece involvedPiece = boardViewModel.getInvolvedPiece();

        if (target != null) {
            boolean canMove = involvedPiece.canMoveTo(target, boardViewModel.getBoardProp().get());
            if (canMove) {
                highlightG.setStroke(Color.GREEN);
            } else {
                highlightG.setStroke(Color.RED);
            }
            highlightG.strokeRect(target.getX(), target.getY(), 1, 1);
            highlightG.strokeRect(involvedPiece.getPos().getX(), involvedPiece.getPos().getY(), 1, 1);
        }
    }

}
