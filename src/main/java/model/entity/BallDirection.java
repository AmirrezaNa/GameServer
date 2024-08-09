package model.entity;

import java.io.Serializable;

public class BallDirection implements Serializable {
    private static final long serialVersionUID = 1L;
    public double x;
    public double y;
    public double angle;

    public BallDirection(double x, double y) {
        this.x = x;
        this.y = y;
        this.angle = 0;
    }
}
