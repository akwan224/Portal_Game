package Model.Blocks;

import Model.OrangeBluePortals.BluePortal;
import Model.OrangeBluePortals.OrangePortal;

import java.awt.*;

public class FinishPoint extends Block {
    public static final Color COLORWHITE = new Color(255, 255, 255);
    public static final Color COLORBLACK = new Color(0, 0, 0);
    public static final Color COLORPINK = new Color(255, 182, 193);

    private boolean isPink = true;

    public FinishPoint(int x, int y) {
        super(x, y);
    }

//    @Override
//    public boolean collidedWithOrange(OrangePortal o) {
//        return false;
//    }



    @Override
    // MODIFIES: this
    // EFFECTS: changes color of leaf from green to red or vice versa
    public void changeColor() {
        this.isPink = !isPink;
        System.out.println(" I AM CHANGING TO: " + getColour());
    }

    public String getColour() {
        if (isPink) return "pink";
        return "purple";
    }

    @Override
    public boolean collidedWithBlue(BluePortal b) {
        return false;
}
}
