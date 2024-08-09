package model.entity.enemy;

import java.io.Serializable;

public abstract class EnemyModel implements Serializable {
    private static final long serialVersionUID = 1L;
    public double x;
    public double y;

    public EnemyModel(double x, double y) {
        this.x = x;
        this.y = y;
    }

}
