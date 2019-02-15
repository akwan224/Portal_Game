package Model.Blocks;

import Model.Blocks.Block;

import java.util.List;

public class WallBlock extends Block {
    private List<Block> blocks;

    public WallBlock(int x, int y) {
        super(x,y);
    }

    @Override
    public void changeColor() {
        for (Block block: blocks) {
            block.changeColor();
        }
    }
}
