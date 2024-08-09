package controller.gameLoop.phase2.normalAndMiniBossEnemies;

import java.io.Serializable;

public class CreateFrames implements Serializable {
    private static final long serialVersionUID = 1L;
    public int x;
    public int y;
    public int width;
    public int height;

    public CreateFrames(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
