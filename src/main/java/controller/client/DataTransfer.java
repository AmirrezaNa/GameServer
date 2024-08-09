package controller.client;

import controller.gameController.GameController;
import controller.gameController.listener.Inputs;
import controller.gameController.listener.KeyInputListener;
import controller.gameController.listener.MouseInputListener;
import model.ClientModel;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class DataTransfer {

    public static void sendGameState(Socket socket, ClientModel client) throws IOException {

        KeyInputListener.getKeyPoint(client);
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        while (!client.gameController.gameOver) {
            try {
                client.inputs = (Inputs) in.readObject();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            objectOutputStream.writeObject(client.gameController);
            objectOutputStream.reset();
            objectOutputStream.flush();


            MouseInputListener.x = client.inputs.mousePoint.x;
            MouseInputListener.y = client.inputs.mousePoint.y;
            MouseInputListener.getMousePoint(client.gameController, client.inputs);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
