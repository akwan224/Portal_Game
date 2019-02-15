package Model.OrangeBluePortals;

import Model.Blocks.Block;

import java.awt.*;
import java.util.Objects;

public class BluePortal extends PortalAbstract {

    public static final Color COLOR = new Color(0,0,204);

//    public BluePortal(int x, int y) {
//        super(y, x);
//    public static final int Size_X = 5;
//    public static final int Size_Y = 10;
//    public static final int DX = 3;
//    public static final int DY = 3;
//    protected int x;
//    protected int y;
    private int horizontalDirection;
    private int verticalDirection;
    private OrangePortal orangePortal;
    private int rangeNum = 15;

    public BluePortal(int x, int y, int xdir, int ydir) {
        super(x, y, xdir, ydir);
        this.xdir = xdir;
        this.ydir = ydir;
        horizontalDirection = xdir;
        verticalDirection = ydir;
        orangePortal = null;
    }

    @Override
    public int getX() { return x; }

    @Override
    public int getY() { return y; }

    @Override
    public void move() {
        x = x + horizontalDirection * DX;
        y = y + verticalDirection * DY;
    }


    public boolean collidedWithBlue(Block b, BluePortal bp) {
        if (withinRange(b, bp)) {
            return true;
        }
        return false;
    }


    public boolean withinRange(Block b, BluePortal bp) {
        int bRangeXLow = b.getX() - rangeNum;
        int bRangeXHigh = b.getX() + rangeNum;
        int bRangeYLow = b.getY() - rangeNum;
        int bRangeYHigh = b.getY() + rangeNum;

        if (bRangeXLow < bp.getX() && bp.getX() < bRangeXHigh && bRangeYLow < bp.getY() && bp.getY() < bRangeYHigh) {
            return true;
        }
        return false;
    }

    public boolean canAddBP(OrangePortal orangePortal) {
        if (orangePortal == null) {
            return true;
        } return false;
    }

    public void createPortal(Graphics g) {
        g.setColor(COLOR);
        g.drawOval(x,y ,5, 10);
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
        BluePortal that = (BluePortal) o;
        return horizontalDirection == that.horizontalDirection &&
                verticalDirection == that.verticalDirection &&
                Objects.equals(orangePortal, that.orangePortal);
    }

    @Override
    public int hashCode() {

        return Objects.hash(horizontalDirection, verticalDirection, orangePortal);
    }
}
