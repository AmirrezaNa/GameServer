package org.example;

import controller.client.ClientConnection;
import controller.gameController.AutoAttack;
import controller.gameLoop.phase1.GameFrame;
import controller.gameLoop.phase1.GamePanel;
import model.ClientModel;

import java.io.IOException;
import java.net.ServerSocket;

public class Main {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9000);
        ClientConnection.connectToClient(serverSocket);

    }
}