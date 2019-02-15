package Model;

import Model.Blocks.Block;
import Model.Blocks.FinishPoint;
import Model.Blocks.Lava;
import Model.Blocks.WallBlock;
import Model.OrangeBluePortals.BluePortal;
import Model.OrangeBluePortals.OrangePortal;
import Model.SpawnPortal.BlueSpawnPortal;
import Model.SpawnPortal.OrangeSpawnPortal;
import javafx.scene.input.KeyCode;

import javax.sound.sampled.Port;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Atlas {

    public static final int SIZE_X = 15;
    public static final int SIZE_Y = 15;
    public static final int DX = 5;
    public static final int DY = 5;
    public static final int Y_POS = 50;
    public static final int X_POS = 50;
    public static final Color colorBody = new Color(250, 250, 250);
    public static final Color colorEye = new Color(0, 0, 0);
    public static final Color colorRetina = new Color(51, 153, 255);

    private int horizontalDirection;
    private int verticalDirection;
    private int x;
    private int y;
    private int newX;
    private int newY;
    boolean save;
    private int rangeLava = 5;
    private int range = 9;
    private int rangeBlock = 15;
    private int rangeFP = 3;


    // Construct Atlas (main character)
    // effects: place Atlas at pos (x, Y_POS) moving right.
    public Atlas(int x, int y) {
        this.x = x;
        this.y = y;
        horizontalDirection = 0;
        verticalDirection = 0;
        newX = 0;
        newY = 0;
        save = false;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int i) {
        this.x = i;
    }

    public void setY(int i) {
        this.y = i;
    }

    public int getnewX() {
        return newX;
    }

    public int getnewY() {
        return newY;
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

    // Updates the tank on clock tick
    // modifies: this
    // effects:  tank is moved DX units in whatever direction it is facing and is
    //           constrained to remain within vertical boundaries of game
    public void move() {
        x = x + horizontalDirection * DX;
        y = y + verticalDirection * DY;
        handleBoundary();
    }

    public void stay() {
        this.x = x;
        this.y = y;
    }

    private void handleBoundary() {
        if (x < 0) {
            x = 0;
        } else if (y < 0) {
            y = 0;
        } else if (x > PortalGame.WIDTH) {
            x = PortalGame.WIDTH;
        } else if (y > PortalGame.HEIGHT) {
            y = PortalGame.HEIGHT;
        }
    }

    public boolean save() {
        saveLocation();
        return true;
    }

    private void saveLocation() {
        int newX = x;
        int newY = y;
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

    public boolean isCollideOrangePortal(Atlas a, OrangeSpawnPortal o) {
        if (o.getX() - range <= a.getX() && a.getX() <= o.getX() + range && o.getY() - range <= a.getY() && a.getY() <= o.getY() + range) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isCollideBluePortal(Atlas a, BlueSpawnPortal b) {
        if (b.getX() - range <= a.getX() && a.getX() <= b.getX() + range && b.getY() - range <= a.getY() && a.getY() <= b.getY() + range) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isCollideWallBlock(Atlas a, Block b) {
        if ((b.getX()  <= a.getX() - range || b.getX()  <= a.getX() + range)
                && (a.getX() - range <= b.getX() + rangeBlock  || a.getX() <= b.getX()  + rangeBlock)
                && (b.getY()  <= a.getY() - range || b.getY()  <= a.getY() + range)
                && (a.getY() - range <= b.getY() + rangeBlock || a.getY() + range <= b.getY() + rangeBlock )) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isCollidedWithLava(Atlas a, Lava l) {
        if ((l.getX()  <= a.getX() - rangeLava - 5|| l.getX()  <= a.getX() + rangeLava - 5)
                && (a.getX() - rangeLava <= l.getX() + rangeBlock || a.getX() <= l.getX() + rangeBlock)
                && (l.getY()  <= a.getY() - rangeLava - 5 || l.getY()  <= a.getY() + rangeLava - 5)
                && (a.getY() - rangeLava <= l.getY() + rangeBlock || a.getY() + rangeLava <= l.getY() + rangeBlock )) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isCollideFinishPoint(Atlas a, FinishPoint p) {
        if ((p.getX()  <= a.getX() - rangeFP || p.getX()  <= a.getX() + rangeFP)
                && (a.getX() - range <= p.getX() + rangeFP  || a.getX() <= p.getX()  + rangeFP)
                && (p.getY()  <= a.getY() - range || p.getY()  <= a.getY() + range)
                && (a.getY() - range <= p.getY() + rangeFP || a.getY() + range <= p.getY() + rangeFP )) {
            return true;
        } else {
            return false;
        }
    }

}












