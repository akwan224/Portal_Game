package Model.Blocks;

import Model.Atlas;
import Model.Blocks.Block;
import Model.OrangeBluePortals.BluePortal;
import Model.OrangeBluePortals.OrangePortal;

import java.awt.*;
import java.util.List;

public class Lava extends Block {

    private Lava lava;
    public static final Color LAVA_COLOR = new Color(255,0,0);
    private List<Block> blocks;
    public Lava(int x, int y) {
        super(x,y);
    }

//    @Override
//    public boolean collidedWithOrange(OrangePortal o) {
//        return false;
//    }

    @Override
    public boolean collidedWithBlue(BluePortal b) {
        return false;
    }

    public boolean collideWithAtlas(Atlas a) {
        if (a.getX() == lava.getX() && a.getY() == lava.getY()) {
            return true;
        } return false;
    }

    @Override
    public void changeColor() {
        for (Block block: blocks) {
            block.changeColor();
        }
    }


}
