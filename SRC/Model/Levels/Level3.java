package Model.Levels;

import Model.Blocks.Block;
import Model.Blocks.FinishPoint;
import Model.Blocks.Lava;
import Model.Blocks.WallBlock;
import Model.PortalGame;

import java.util.ArrayList;
import java.util.List;

public class Level3 {


    private Block block;
    private List<Block> blocks;
    private List<Lava> lavas;
    private List<FinishPoint> finishPoints;
    private int SPACE = 20;
    private int OFFSET = 30;
    public FinishPoint fp;

    private String level3 =
                      "#ooovvvvvv###vvvvvvvvvoo#\n"
                    + "#ooovvv#vvvoovvv###vvvo@#\n"
                    + "#ooovvvvvvvoo#oo#oovvvoo#\n"
                    + "vvvvoo#ovvvoo#oo#oovvv###\n"
                    + "vvvvvooovvvvvoo#ooovvvoo#\n"
                    + "#####ooovvvvvvoo#ooovvoo#\n"
                    + "vo@ov###vvvvvvvoo####vvv#\n"
                    + "o###ovvvvvvvvooooovoovoo#\n"
                    + "ooooo#####oovvooooovo####\n"
                    + "#vvvvvvvvvoo#vvvv#oovoooo\n"
                    + "#vvoo#oovoo#oovooo#oovo#o\n"
                    + "#vvoo#oovo#oovooooo#oo#vv\n"
                    + "#vvoo#oov#oovoo###ooo#ooo\n"
                    + "#vvoo##ooaoovvooooo#oo#vvv\n"
                    + "#vvvv###oovvvooooo####ooo\n"
                    + "#vvvvvvvvvvvo#ovvvvvvvvvv\n"
                    + "#oovvvvvvvvvovvvvvvvvvvv#\n"
                    + "#ooovvvvvvvvovo#oooovv@v#\n"
                    + "#ooovvvv#ooo#vo#oovvvvvvv\n"
                    + "@####ooo#oovvvvovvvoo####\n"
                    + "vvvvvvoo#ooovvvooo#ovoooo\n"
                    + "vvvvvvoo###ovvvovvvvvvvoo\n"
                    + "#oovvvvvvvoo#oo#ovvvoooo#\n"
                    + "#oovvvvvvvoo#oo#ovvvoooo#\n"
                    + "#oovvvvvvvoo#oo#ovvv#####\n" ;

    public Level3() {
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
        for (int i = 0; i < level3.length(); i++) {
            char item = level3.charAt(i);
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
