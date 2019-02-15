package Model.Levels;


import Model.Blocks.Block;
import Model.Blocks.FinishPoint;
import Model.Blocks.Lava;
import Model.Blocks.WallBlock;
import Model.PortalGame;
import javafx.scene.layout.TilePane;

import java.util.ArrayList;
import java.util.List;

public class Level2 {
    private Block block;
    private List<Block> blocks;
    private List<Lava> lavas;
    private List<FinishPoint> finishPoints;
    private int SPACE = 20;
    private int OFFSET = 30;
    public FinishPoint fp;

    private String level2 =
                      "oooooooooooooo#oooooooooo\n"
                    + "oooo###oooo#oo#oooooooooo\n"
                    + "oooooo#oo#o#oo#oooooo####\n"
                    + "#######vv###vv#o#oooooooo\n"
                    + "@#ooooovvvvvoooo#oooovooo\n"
                    + "#oooovvvvvvvoooo#####oooo\n"
                    + "vvvvvoooovvvoooo#oooooooo\n"
                    + "ooooooooooovoooo#oooooooo\n"
                    + "vvooo####oo#oooo#oooo####\n"
                    + "ooovv#ooooo#oooo#oooooooo\n"
                    + "vvooo#ooooo#oooo#####oooo\n"
                    + "ooovv#ooovo###oooooo#oooo\n"
                    + "ooooo#ooooooo#oooooo#vvvv\n"
                    + "vvvvo####oooo#oooooo#oooo\n"
                    + "oooooooo#oooo###oooo#oooo\n"
                    + "#####ooo#oooooo#oooo#oooo\n"
                    + "#ooooooo#oooooo#oooo#vvv#\n"
                    + "#ooooooo###oovo#oooovvov#\n"
                    + "#ooooooo#@#oooo#oooo#####\n"
                    + "#ooooooovo#ooovoooooooooo\n"
                    + "#ooooooovo#ooovvooooo##oo\n"
                    + "#ooooooo###ooovvv#ov#oo##\n"
                    + "#ooooooo#vvvvvvov#ovoooo@\n"
                    + "#ooooooo#vvvvvvoovvvvvvv#\n"
                    + "#ooooooo#oooooo#oooooooo#\n" ;

    public Level2() {
        setup();
    }

    public void setup() {
        Block b;
        Lava l;
        int x = 0;
        int y = 0;
        blocks = new ArrayList<>();
        lavas = new ArrayList<>();
        finishPoints = new ArrayList<>();
        for (int i = 0; i < level2.length(); i++) {
            char item = level2.charAt(i);
            if (item == '\n') {
                y += SPACE;
                if (PortalGame.WIDTH < x) {
                    x = PortalGame.WIDTH;
                }
                x = 0;
            }
            if (item == '#') {
                b = new WallBlock(x, y);
                blocks.add(b);
                x += SPACE;
            } else if (item == 'o') {
                x += SPACE;
            } else if (item == 'v') {
                l = new Lava(x,y);
                lavas.add(l);
                x += SPACE;
            } else if (item == '@') {
                fp = new FinishPoint(x, y);
                finishPoints.add(fp);
                x += SPACE;
            }
        }
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public List<Lava> getLavas() {
        return lavas;
    }

    public List<FinishPoint> getFinishPoints() {
        return  finishPoints;
    }

    public FinishPoint getFinishPoint() {
        return fp;
    }

    public boolean isLevelDone() {
        return false;
    }
}
