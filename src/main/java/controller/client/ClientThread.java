package controller.client;

import controller.data.DataManager;
import controller.gameLoop.phase1.GameFrame;
import controller.gameLoop.phase1.GamePanel;
import model.ClientModel;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientThread extends Thread {

    public Socket socket;


    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String name = input.readLine();
            ClientConnection.clients.add(name);
            for (String a : ClientConnection.clients) {
                System.out.println(a);
            }
            ClientModel client = new ClientModel();
            client.player.setName(name);
            try {
                if (DataManager.checkPlayerExists(client.player.getName())) {
                    DataManager.loadPlayerData(client.player.getName(), client.player);
                }
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            client.gameFrame = new GameFrame(client);
            client.gameFrame.gamePanel = new GamePanel(client.gameController, client);
            client.gameFrame.thread = new Thread(client.gameFrame.gamePanel);
            client.gameFrame.thread.start();
            DataTransfer.sendGameState(socket, client);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}

