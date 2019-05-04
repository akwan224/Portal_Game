package Model.OrangeBluePortals;

import java.awt.*;

public abstract class PortalAbstract{
    public static final int Size_X = 5;
    public static final int Size_Y = 10;
    public static final int DX = 8;
    public static final int DY = 8;
    protected int x;
    protected int y;
    private int horizontalDirection;
    private int verticalDirection;
    protected int xdir;
    protected int ydir;

    public PortalAbstract(int x, int y, int xdir, int ydir) {
        this.y = y;
        this.x = x;
        this.xdir = xdir;
        this.ydir = ydir;
        horizontalDirection = xdir;
        verticalDirection = ydir;
    }

    public int getX() { return x; }

    public int getY() { return y; }

    public void move() {
        x = x + horizontalDirection * DX;
        y = y + verticalDirection * DY;
    }
}
