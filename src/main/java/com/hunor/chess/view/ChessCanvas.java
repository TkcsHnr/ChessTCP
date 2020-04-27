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
        boardViewModel.getBoardProp().listen(this::redraw);
        boardViewModel.getMousePositionProp().listen(this::highlight);

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
        SimplePos mousePos = getMouseCoords(mouseEvent);
        if (mousePos != null) {
            ChessPiece involvedPiece = boardViewModel.getBoardProp().get().pieceAt(mousePos);
            if (involvedPiece != null)
                boardViewModel.getInvolvedPieceProp().set(involvedPiece);
        }
    }

    private void handleDrag(MouseEvent mouseEvent) {
        SimplePos mousePos = getMouseCoords(mouseEvent);
        if (mousePos != null) {
            if (!mousePos.equals(boardViewModel.getMousePositionProp().get()) && boardViewModel.getInvolvedPieceProp().hasValue()) {
                boardViewModel.getMousePositionProp().set(mousePos);
            }
        }
    }

    private void handleRelease(MouseEvent mouseEvent) {
        ChessPiece involvedPiece = boardViewModel.getInvolvedPieceProp().get();
        if (involvedPiece != null) {
            SimplePos target = getMouseCoords(mouseEvent);
            if (involvedPiece.canMoveTo(target, boardViewModel.getBoardProp().get())) {
                boardViewModel.getBoardProp().get().movePieceTo(involvedPiece, target);
                this.redraw(boardViewModel.getBoardProp().get());

//                System.out.println("send packet");
            }

            highlightG.clearRect(0, 0, 8, 8);
            boardViewModel.getInvolvedPieceProp().clear();
        }


        boardViewModel.getMousePositionProp().clear();
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
            Image image = new Image(getClass().getResourceAsStream("/img/" + piece.getClass().getSimpleName().toLowerCase()
                    + "_" + piece.getPieceColor() + ".png"));
            mainG.drawImage(image, piece.getPos().getX() + 0.05, piece.getPos().getY() + 0.05, 0.9, 0.9);
        }
    }

    private void highlight(SimplePos highlightPos) {
        highlightG.clearRect(0, 0, 8, 8);
        if (highlightPos != null) {
            switch (boardViewModel.getInvolvedPieceProp().get().getPieceColor()) {
                case BLACK:
                    highlightG.setStroke(Color.BLACK);
                    break;
                case WHITE:
                    highlightG.setStroke(Color.WHITE);
            }
            highlightG.strokeRect(highlightPos.getX(), highlightPos.getY(), 1, 1);
        }
    }

}
