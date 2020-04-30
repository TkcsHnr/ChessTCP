package com.hunor.chess.net;

import com.hunor.chess.model.pieces.PieceColor;

public interface Participant {
    void sendPacket(Object packet);

    void exit();

    PieceColor managerColor();
}
