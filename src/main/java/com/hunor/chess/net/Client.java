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

public class Client {

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private EventBus eventBus;

    public Client(String host, int port, EventBus eventBus) {
        super();
        this.eventBus = eventBus;

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

    public PieceColor managerColor() {
        return PieceColor.BLACK;
    }

    public void sendPacket(Object packet) {
        try {
            out.reset();
            out.writeObject(packet);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
