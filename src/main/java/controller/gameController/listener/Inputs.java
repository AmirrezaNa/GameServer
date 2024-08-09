package controller.gameController.listener;

import java.awt.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Inputs implements Serializable {
    private static final long serialVersionUID = 1L;
    public Point mousePoint;
    public Set<Integer> pressedKeys;

    public Inputs() {
        this.mousePoint = new Point();

        this.pressedKeys = new HashSet<>();
    }
}
