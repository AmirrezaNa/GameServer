package controller.client;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ClientConnection {
    ServerSocket serverSocket;

    public static ArrayList<String> clients = new ArrayList<>();

    public ClientConnection(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }


    public static void connectToClient(ServerSocket serverSocket) {
        while (true) {
            System.out.println("waiting for a client to connect...");
            try {
                Socket socket = serverSocket.accept();
                System.out.println("client connected.");
                Thread clientThread = new ClientThread(socket);
                clientThread.start();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
