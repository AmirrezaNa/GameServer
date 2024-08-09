package model.entity;

import controller.gameController.FrameOfObject;

import java.io.Serializable;

public class BulletModel implements Serializable {
    private static final long serialVersionUID = 1L;
    public double x;
    public double y;
    public double dx;
    public double dy;
    public int bulletHealth;
    public static int bulletSpeed = 15;
    public static int bulletSize = 10;
    public boolean bulletOut;
    public int bulletFrame;
    public static boolean bulletSlaughter = false;

    public BulletModel(double x, double y) {
        this.x = x;
        this.y = y;
        this.bulletOut = false;
        this.bulletHealth = 1;
        this.bulletFrame = 0;
    }
}
