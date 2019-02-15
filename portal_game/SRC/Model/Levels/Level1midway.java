package Model.Levels;

import Model.Blocks.Block;
import Model.Blocks.FinishPoint;
import Model.Blocks.Lava;
import Model.Blocks.WallBlock;
import Model.PortalGame;

import java.util.ArrayList;
import java.util.List;

public class Level1midway {


    private Block block;
    private List<Block> blocks;
    private List<Lava> lavas;
    private List<FinishPoint> finishPoints;
    private int SPACE = 20;
    private int OFFSET = 30;
    public FinishPoint fp;
    private String level1midway =
            "#########################\n"
                    + "#ooooooooooo#ooooooooooo#\n"
                    + "#ooooooooooo#ooooooooooo#\n"
                    + "#ooooooooooo#ooooooooooo#\n"
                    + "#ooooooooooo#ooooooooooo#\n"
                    + "#ooooooooooo#ooooooooooo#\n"
                    + "#ooooooooooo#ooooooooooo#\n"
                    + "#ooooooooooo#ooooooooooo#\n"
                    + "vvvvvvvvvvvvvvvvvvvvvvvvv\n"
                    + "vvvvvvvvvvvvvvvvvvvvvvvvv\n"
                    + "vvvvvvvvvvvvvvvvvvvvvvvvv\n"
                    + "vv#vvvvvvvvvvvvvvvvvvvvvv\n"
                    + "vvovvvvvvvvvvvvvvvvvvvvvv\n"
                    + "vvovvvvvvvvvvvvvvvvvvvoo#\n"
                    + "vvvvvvvvvvvvvvvvvvvvvvvvv\n"
                    + "vvvvvvvvvvvvvvvvvvvvvvvvv\n"
                    + "#ooooooooooo#ooooooooooo#\n"
                    + "#ooooooooooo#ooooooooooo#\n"
                    + "#ooooooooooo#ooooooooooo#\n"
                    + "#ooooooooooo#ooooooooooo#\n"
                    + "#ooooooooooo#ooooooooooo#\n"
                    + "#ooooooooooo#ooooooooooo#\n"
                    + "#ooooooooooo#oooooo@oooo#\n"
                    + "#ooooooooooo#ooooooooooo#\n"
                    + "#########################\n" ;

    public Level1midway(){
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
        for (int i = 0; i < level1midway.length(); i++) {
            char item = level1midway.charAt(i);
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

    public FinishPoint getFinishPoint() {
        return fp;
    }

    public List<FinishPoint> getFinishPoints() {
        return finishPoints;
    }

    public boolean isLevelDone() {
        return false;
    }
}

