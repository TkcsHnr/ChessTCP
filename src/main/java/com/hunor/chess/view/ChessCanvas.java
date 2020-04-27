package com.hunor.chess.view;

import com.hunor.chess.model.ChessBoard;
import com.hunor.chess.model.SimplePos;
import com.hunor.chess.pieces.ChessPiece;
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

    public ChessCanvas(int size, BoardViewModel boardViewModel) {
        this.mainCanvas  = new Canvas(size, size);
        this.getChildren().add(mainCanvas);

        this.highlightCanvas = new Canvas(size, size);
        this.getChildren().add(highlightCanvas);

        this.boardViewModel = boardViewModel;
        boardViewModel.getBoardProperty().listen(this::redraw);
        boardViewModel.getMousePosition().listen(this::highlight);
        boardViewModel.getInvolvedPiece().listen(v -> {
            if (v == null) {
                System.out.println("send packet");
                highlightG.clearRect(0, 0, 8, 8);
            }
        });

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
        try {
            Point2D point = affine.inverseTransform(mouseEvent.getX(), mouseEvent.getY());
            int simX = (int) point.getX();
            int simY = (int) point.getY();

            ChessPiece involvedPiece = boardViewModel.getBoardProperty().get().pieceAt(simX, simY);
            if (involvedPiece != null)
                boardViewModel.getInvolvedPiece().set(involvedPiece);
        } catch (NonInvertibleTransformException e) {
            e.printStackTrace();
        }
    }

    private void handleDrag(MouseEvent mouseEvent) {
        try {
            Point2D point = affine.inverseTransform(mouseEvent.getX(), mouseEvent.getY());
            SimplePos simplePos = new SimplePos((int)point.getX(), (int)point.getY());
            if (!simplePos.equals(boardViewModel.getMousePosition().get()) && boardViewModel.getInvolvedPiece().hasValue()) {
                boardViewModel.getMousePosition().set(simplePos);
            }
        } catch (NonInvertibleTransformException e) {
            e.printStackTrace();
        }
    }

    private void handleRelease(MouseEvent mouseEvent) {
        boardViewModel.getMousePosition().clear();

        if (boardViewModel.getInvolvedPiece().hasValue())
            boardViewModel.getInvolvedPiece().clear();
    }

    private void redraw(ChessBoard chessBoard) {
        if (chessBoard == null)
            return;

        mainG.drawImage(new Image(getClass().getResourceAsStream("/img/board.png")), 0, 0, 8, 8);

        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                ChessPiece piece = chessBoard.pieceAt(x, y);
                if (piece != null) {
                    Image image = new Image(getClass().getResourceAsStream("/img/" + piece.getClass().getSimpleName().toLowerCase()
                            + "_" + piece.getPieceColor() + ".png"));
                    mainG.drawImage(image, x+0.05, y+0.05, 0.9, 0.9);
                }
            }
        }
    }

    private void highlight(SimplePos highlightPos) {
        highlightG.clearRect(0, 0, 8, 8);
        if (highlightPos != null) {
            highlightG.setStroke(Color.GREEN);
            highlightG.strokeRect(highlightPos.getX(), highlightPos.getY(), 1, 1);
        }
    }

}
