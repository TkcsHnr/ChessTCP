package com.hunor.chess.net;

import com.hunor.chess.model.pieces.PieceColor;
import com.hunor.chess.net.packet.MovementEvent;
import com.hunor.chess.net.packet.MovementEvent.Type;
import com.hunor.chess.util.event.EventBus;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class Client implements Participant {

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public Client(String host, int port, EventBus eventBus) {
        super();

        try {
            socket = new Socket(host, port);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            try {
                while (true) {
                    Object packet = in.readObject();
                    if (packet instanceof MovementEvent)
                        eventBus.emit(new MovementEvent(Type.RECEIVE, ((MovementEvent) packet).getInitial(), ((MovementEvent) packet).getTarget()));
                }
            } catch (SocketException closed) {
                System.out.println("closed");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public PieceColor managerColor() {
        return PieceColor.BLACK;
    }

    @Override
    public void sendPacket(Object packet) {
        try {
            out.reset();
            out.writeObject(packet);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void exit() {
        try {
            if (in != null)
                in.close();
            if (out != null)
                out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
