package Test;

import Model.Atlas;
import Model.Blocks.Block;
import Model.Blocks.WallBlock;
import Model.OrangeBluePortals.BluePortal;
import Model.OrangeBluePortals.OrangePortal;
import Model.SpawnPortal.BlueSpawnPortal;
import Model.SpawnPortal.OrangeSpawnPortal;
import Model.SpawnPortal.SpawnPortal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestPortal {
    private static final int XLOC = 50;
    private static final int YLOC = 100;
    private BluePortal b;
    private BluePortal bp;
    private OrangePortal o;
    private OrangePortal op;
    private Atlas a;
    private OrangeSpawnPortal osp;
    private BlueSpawnPortal bsp;
    private Block block;

    @BeforeEach
    public void runBefore() {
        a = new Atlas(XLOC, YLOC);
        b = new BluePortal(XLOC, YLOC, 1, 0);
        bp = new BluePortal(a.getX(), a.getY(), 0, 1);

        o = new OrangePortal(XLOC, YLOC, 1, 0);
        op = new OrangePortal(XLOC, YLOC, 0, 1);


    }

    @Test
    public void testGetX() {
        assertEquals(XLOC, b.getX());
        assertEquals(XLOC, o.getX());
        assertEquals(XLOC, bp.getX());
    }

    @Test
    public void testGetY() {
        assertEquals(YLOC, b.getY());
        assertEquals(YLOC, o.getY());
        assertEquals(XLOC, bp.getX());
    }

    @Test
    public void testUpdateBlue() {
        final int NUM_UPDATES = 8;
        a.faceDown();
        b.move();
        assertEquals(XLOC+ BluePortal.DX, b.getX());
        a.faceLeft();
        bp.move();
        assertEquals(YLOC + BluePortal.DY, bp.getY());

        for(int count = 1; count < NUM_UPDATES; count++) {
            b.move();
            bp.move();
        }
        assertEquals(XLOC + NUM_UPDATES * BluePortal.DX, b.getX());
        assertEquals(YLOC + NUM_UPDATES * BluePortal.DY, bp.getY());

    }

    @Test
    public void testUpdateOrange(){
        final int NUM_UPDATES = 8;
        a.faceRight();
        o.move();
        assertEquals(XLOC+ OrangePortal.DX, o.getX());
        a.faceDown();
        op.move();
        assertEquals(YLOC + OrangePortal.DY, op.getY());

        for(int count = 1; count < NUM_UPDATES; count++) {
            a.faceRight();
            o.move();
            a.faceDown();
            op.move();
        }
        assertEquals(XLOC + NUM_UPDATES * OrangePortal.DX, o.getX());
        assertEquals(YLOC + NUM_UPDATES * OrangePortal.DY, op.getY());
    }

    @Test
    public void testCollideBlock() {
        final int NUM_UPDATES = 5;
        block = new WallBlock(59, 100);
        a.faceRight();

        for(int count = 1; count < NUM_UPDATES; count++) {
            o.move();
            if (o.collidedWithOrange(block, o)) {
                o = null;
                osp = new OrangeSpawnPortal(block.getX(), block.getY());

            }
        }
        assertTrue(osp != null);
        assertEquals(100, osp.getY());
        assertEquals(59, osp.getX());
    }


}

