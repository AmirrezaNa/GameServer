package model.entity;

import java.io.Serializable;

public class BallAngle implements Serializable {
    private static final long serialVersionUID = 1L;
    public double x;
    public double y;
    public final static int ballAngleRadius = 5;
    public boolean angleExists;
    public double angle;

    public BallAngle(double x, double y) {
        this.x = x;
        this.y = y;
        this.angle = 0;
        this.angleExists = false;
    }
}