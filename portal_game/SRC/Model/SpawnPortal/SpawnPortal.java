package Model.SpawnPortal;

import java.awt.*;

public abstract class SpawnPortal {
    private int x;
    private int y;
    public static final int Size_Y = 20;
    public static final int Size_X = 10;

    public SpawnPortal(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }

    public int getY() { return y; }


}
