package model.entity;

import java.io.Serializable;

public class BallModel implements Serializable {
    private static final long serialVersionUID = 1L;

    public double x;
    public double y;
    public double dx = 2;
    public double dy = 2;
    public double ax = 0;
    public double ay = 0;
    public static double ballAcceleration;
    public static int ballRadius = 20;
    public int HP;
    public boolean ballInArchmire;
    public boolean ballInArchmireTrace;
    public boolean ballInBlackOrb;
    public boolean ballCerberus;
    public boolean ballChiron;
    public boolean ballDismay;
    public boolean ballSlumber;
    public boolean ballSlaughter;

    private static BallModel instance;

    private BallModel(double x, double y) {
        this.x = x;
        this.y = y;
        ballAcceleration = 3;
        this.HP = 100;
        this.ballInArchmire = false;
        this.ballInArchmireTrace = false;
        this.ballInBlackOrb = false;
        this.ballCerberus = false;
        this.ballChiron = false;
        this.ballDismay = false;
        this.ballSlumber = false;
        this.ballSlaughter = false;
    }

    // Static method to get the singleton instance
    public synchronized static BallModel getInstance(double x, double y) {
        if (instance == null) {
            instance = new BallModel(x, y);
        }
        return instance;
    }
}
