package Model.Levels;

import Model.Blocks.Block;
import Model.Blocks.FinishPoint;
import Model.Blocks.Lava;
import Model.Blocks.WallBlock;
import Model.PortalGame;

import java.util.ArrayList;
import java.util.List;

public class EndLevel {
    private Block block;
    private List<Block> blocks;
    private List<Lava> lavas;
    private List<FinishPoint> finishPoints;
    private int SPACE = 20;
    private int OFFSET = 30;
    public FinishPoint fp;
    private String endLevel =
            "#########################\n"
                    + "#ooooooooooooooooooooooo#\n"
                    + "#ooooooooooooooooooooooo#\n"
                    + "#ooooooooooooooooooooooo#\n"
                    + "#ooooooooooooooooooooooo#\n"
                    + "#ooooooooooooooooooooooo#\n"
                    + "#ooooooooooooooooooooooo#\n"
                    + "#ooooooooooooooooooooooo#\n"
                    + "#vvvvvvvvvvvvvvvvvvvvvvv#\n"
                    + "#vv####vvvv#vvvv##vvv#vv#\n"
                    + "#vv#vvvvvvv#vvvv#v#vv#vv#\n"
                    + "#vv####vvvv#vvvv#vv#v#vv#\n"
                    + "#vv#vvvvvvv#vvvv#vvv##vv#\n"
                    + "#vv#vvvvvvv#vvvv#vvvv#vv#\n"
                    + "#vvvvvvvvvvvvvvvvvvvvvvv#\n"
                    + "#vvvvvvvvvvvvvvvvvvvvvvv#\n"
                    + "#ooooooooooooooooooooooo#\n"
                    + "#ooooooooooooooooooooooo#\n"
                    + "#ooooooooooooooooooooooo#\n"
                    + "#ooooooooooooooooooooooo#\n"
                    + "#ooooooooooooooooooooooo#\n"
                    + "#ooooooooooooooooooooooo#\n"
                    + "#ooooooooooooooooooooooo#\n"
                    + "#ooooooooooooooooooooooo#\n"
                    + "#########################\n" ;

    public EndLevel(){
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
        for (int i = 0; i < endLevel.length(); i++) {
            char item = endLevel.charAt(i);
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
