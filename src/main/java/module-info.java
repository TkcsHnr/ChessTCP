module com.hunor.chess {
    requires javafx.controls;
    exports com.hunor.chess;
    exports com.hunor.chess.model;
    exports com.hunor.chess.model.board;
    exports com.hunor.chess.model.pieces;
    exports com.hunor.chess.model.pieces.rules;
    exports com.hunor.chess.view;
    exports com.hunor.chess.viewmodel;
    exports com.hunor.chess.util;
    exports com.hunor.chess.util.event;
    exports com.hunor.chess.net;
    exports com.hunor.chess.net.packet;
}