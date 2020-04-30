package com.hunor.chess.net;

import com.hunor.chess.model.pieces.PieceColor;
import com.hunor.chess.net.packet.MovementEvent;
import com.hunor.chess.net.packet.MovementEvent.Type;
import com.hunor.chess.util.event.EventBus;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server implements Participant {

    private ServerSocket listener;

    private ObjectInputStream in;
    private ObjectOutputStream out;

    public Server(int port, EventBus eventBus) {
        super();

        try {
            listener = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
            exit();
        }

        new Thread(() -> {
            try {
                Socket socket = listener.accept();

                out = new ObjectOutputStream(socket.getOutputStream());
                in = new ObjectInputStream(socket.getInputStream());

                while (true) {
                    Object packet = in.readObject();
                    if (packet instanceof MovementEvent)
                        eventBus.emit(new MovementEvent(Type.RECEIVE, ((MovementEvent) packet).getInitial(), ((MovementEvent) packet).getTarget()));
                }
            } catch (EOFException | SocketException closed) {
                System.out.println("closed");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public PieceColor managerColor() {
        return PieceColor.WHITE;
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
            listener.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
