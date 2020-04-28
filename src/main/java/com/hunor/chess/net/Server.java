package com.hunor.chess.net;

import com.hunor.chess.ChessFX;
import com.hunor.chess.model.pieces.PieceColor;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends ChessFX {

    private static int PORT;
    private ServerSocket listener;

    private ObjectInputStream in;
    private ObjectOutputStream out;

    public Server() {
        super();

        try {
            listener = new ServerSocket(PORT);
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
                    packetReceived(in.readObject());
                }
            } catch (EOFException closed) {
                System.out.println("closed");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        try {
            PORT = Integer.parseInt(args[0]);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException argExc) {
            System.exit(-1);
            return;
        }

        launch(args);
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
            in.close();
            out.close();
            listener.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
