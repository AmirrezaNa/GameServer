package controller.client;

import model.ClientModel;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class DataTransfer {

    public static void sendGameState(Socket socket, ClientModel client) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        while (!client.gameController.gameOver) {
            out.writeObject(client.gameController);
            out.flush();
            out.reset(); // You may not need to call reset in this scenario
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
