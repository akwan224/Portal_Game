package Model.Blocks;

import Model.OrangeBluePortals.BluePortal;
import Model.OrangeBluePortals.OrangePortal;
import Model.PortalGame;

import java.awt.*;

public abstract class Block {
    public static final int Size_X = 20;
    public static final int Size_Y = 20;
    protected int x;
    protected int y;
    public static final Color COLOR = new Color(255,255,255);


    public Block(int x, int y) {
        this.y = y;
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public abstract void changeColor();

    // Determines if this block has collided with a missile
	// modifies: none
	// effects:  returns true if this invader has collided with missile m,
	//           false otherwise
//	public boolean collidedWithOrange(Block b, OrangePortal o) {
//        int bRangeXLow = b.getX() - 10;
//        int bRangeXHigh = b.getX() + 10;
//        int bRangeYLow = b.getY() - 10;
//        int bRangeYHigh = b.getY() + 10;
//        if ((bRangeXLow < o.getX() || o.getX() <= bRangeXHigh) && (bRangeYLow < o.getY() || bRangeYHigh <= o.getY())) {
//            return true;
//        } else {
//            return false;
//        }
//	}

    public boolean collidedWithBlue(BluePortal b) {
        Rectangle blockBoundingRect = new Rectangle(getX() - Size_X / 2, getY() - Size_Y / 2, Size_X, Size_Y);
        Rectangle bluePortalBoundingRect = new Rectangle(b.getX() - BluePortal.Size_X/ 2, b.getY() - BluePortal.Size_Y/ 2,
                BluePortal.Size_X, BluePortal.Size_Y);
        return blockBoundingRect.intersects(bluePortalBoundingRect);
    }

    //	private void handleBoundary() {
//		if (x < 0)
//			x = 0;
//		else if (x > SIGame.WIDTH)
//			x = SIGame.WIDTH;
//	}


}


