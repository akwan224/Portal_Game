package Model.OrangeBluePortals;

import Model.Blocks.Block;
import Model.Blocks.WallBlock;

import java.awt.*;
import java.util.Objects;

public class OrangePortal extends PortalAbstract{

    public static final Color COLOR = new Color(255,122,0);
//
//    public static final int Size_X = 5;
//    public static final int Size_Y = 10;
//    protected int x;
//    protected int y;
//    public static final int DY = 3;
//    public static final int DX = 3;
    private int horizontalDirection;
    private int verticalDirection;
    private BluePortal bluePortal;
    private Integer rangeNum = 15;

    public OrangePortal(int x, int y, int xdir, int ydir) {
        super(x,y, xdir, ydir);
        this.xdir = xdir;
        this.ydir = ydir;
        horizontalDirection = xdir;
        verticalDirection = ydir;
        bluePortal = null;

    }
    @Override
    public int getX() { return x; }

    @Override
    public int getY() { return y; }

    @Override
    public void move() {
        x += horizontalDirection * DX;
        y += verticalDirection * DY;
    }

    public boolean collidedWithOrange(Block b, OrangePortal o) {
            if (withinRange(b, o)) {
                return true;
            }
            return false;
        }

        // sets range hitbox
    public boolean withinRange(Block b, OrangePortal o) {
        int bRangeXLow = b.getX() - rangeNum;
        int bRangeXHigh = b.getX() + rangeNum;
        int bRangeYLow = b.getY() - rangeNum;
        int bRangeYHigh = b.getY() + rangeNum;

        if ((bRangeXLow < o.getX() && o.getX() < bRangeXHigh && bRangeYLow < o.getY() && o.getY() < bRangeYHigh)
                || (o.getX() < bRangeXHigh && bRangeXLow < o.getX()  && o.getY() < bRangeYHigh && bRangeYLow < o.getY()))
        {
            return true;
        }
         return false;
    }


    public boolean canAddOP(BluePortal bluePortal) {
        if (bluePortal == null) {
            return true;
        } return false;
    }

    // modifies: this
    // effects: Atlas faces right
    public void faceRight() {
        horizontalDirection = 1;
        verticalDirection = 0;
    }

    // modifies: this
    // effects: Atlas faces left
    public void faceLeft() {
        horizontalDirection = -1;
        verticalDirection = 0;
    }

    // Faces tank to the up
    // modifies: this
    // effects: tank is facing up
    public void faceUp() {
        verticalDirection = -1;
        horizontalDirection = 0;
    }

    // Faces tank to the down
    // modifies: this
    // effects: tank is facing down
    public void faceDown() {
        verticalDirection = 1;
        horizontalDirection = 0;
    }

    public boolean isfacingUp() {
        if (verticalDirection == -1 && horizontalDirection == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isfacingDown() {
        if (verticalDirection == 1 && horizontalDirection == 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isfacingLeft() {
        if (verticalDirection == 0 && horizontalDirection == -1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isfacingRight() {
        if (verticalDirection == 0 && horizontalDirection == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrangePortal that = (OrangePortal) o;
        return horizontalDirection == that.horizontalDirection &&
                verticalDirection == that.verticalDirection &&
                Objects.equals(bluePortal, that.bluePortal);
    }

    @Override
    public int hashCode() {

        return Objects.hash(horizontalDirection, verticalDirection, bluePortal);
    }
}
