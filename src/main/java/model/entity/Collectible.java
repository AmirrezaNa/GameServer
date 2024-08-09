package model.entity;

import java.io.Serializable;

public class Collectible implements Serializable {
    private static final long serialVersionUID = 1L;
    public double x;
    public double y;
    public static double collectibleSize = 10;
    public int collectibleHealth;
    public int xp;

    public Collectible(double x, double y, int xp) {
        this.x = x;
        this.y = y;
        this.collectibleHealth = 1;
        this.xp = xp;
    }
}

