package com.hunor.chess.net;

import com.hunor.chess.ChessFX;
import com.hunor.chess.model.pieces.PieceColor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class Client extends ChessFX {

    private static String HOST_NAME;
    private static int PORT;
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public Client() {
        super();

        try {
            socket = new Socket(HOST_NAME, PORT);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            try {
                while (true) {
                    packetReceived(in.readObject());
                }
            } catch (SocketException closed) {
                System.out.println("closed");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        try {
            HOST_NAME = args[0];
            PORT = Integer.parseInt(args[1]);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException argExc) {
            System.exit(-1);
            return;
        }

        launch(args);
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
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
