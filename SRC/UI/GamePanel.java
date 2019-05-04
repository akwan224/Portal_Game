package UI;

import Model.Atlas;
import Model.Blocks.Block;
import Model.Blocks.FinishPoint;
import Model.Blocks.Lava;
import Model.Levels.*;
import Model.OrangeBluePortals.BluePortal;
import Model.OrangeBluePortals.OrangePortal;
import Model.PortalGame;
import Model.SpawnPortal.BlueSpawnPortal;
import Model.SpawnPortal.OrangeSpawnPortal;
import Model.SpawnPortal.SpawnPortal;


import javax.swing.JPanel;
import java.awt.*;
import java.util.List;


public class GamePanel extends JPanel {
//    private static final String OVER = "Game Over!";
//    private static final String REPLAY = "R to replay";
    private PortalGame game;
    private List<Block> blocks;
    private List<Lava> lavas;

    private final int SPACE = 20;
    private Level1 levelone = new Level1();

    public GamePanel(PortalGame g) {
        setPreferredSize(new Dimension(PortalGame.WIDTH, PortalGame.HEIGHT));
        setBackground(Color.BLACK);
        this.game = g;
//        initWorld();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawGame(g);
        if (game.isOver()) {
            gameOver(g);
        }
    }

    // fillShape of Characters/Level
    private void drawGame(Graphics g) {
        drawBlocks(g);
        drawLavas(g);
        drawFinishPoints(g);
        drawingBluePortal(g);
        drawingOrangePortal(g);
        drawingSpawnPortal(g);
        drawAtlas(g);
    }


    private void drawingSpawnPortal(Graphics g) {
        BlueSpawnPortal bsp = game.getBlueSpawnPortal();
        OrangeSpawnPortal osp = game.getOrangeSpawnPortal();
//        for (BluePortal bp: game.getBluePortal())
        if (bsp != null) {
            drawBlueSpawnPortal(g, bsp);
        }
        if (osp != null) {
            drawOrangeSpawnPortal(g, osp);
//        } else {System.out.println("should not be here!");}
        }
    }


    private  void drawBlueSpawnPortal(Graphics g, BlueSpawnPortal bs) {
        Color savedColor = g.getColor();
        g.setColor(BlueSpawnPortal.COLOR);
        g.fillOval(bs.getX() - SpawnPortal.Size_X / 2, bs.getY() - SpawnPortal.Size_Y / 2, SpawnPortal.Size_X, SpawnPortal.Size_Y);
        g.setColor(savedColor);
    }

    private  void drawOrangeSpawnPortal(Graphics g, OrangeSpawnPortal os) {
        Color savedColor = g.getColor();
        g.setColor(OrangeSpawnPortal.COLOR);
        g.fillOval(os.getX() - SpawnPortal.Size_X / 2, os.getY() - SpawnPortal.Size_Y / 2, SpawnPortal.Size_X, SpawnPortal.Size_Y);
        g.setColor(savedColor);
    }

    // Draw Atlas
    // modifies: g
    // effects:  draws Atlas onto g
    private void drawAtlas(Graphics g) {
        Atlas a = game.getAtlas();
        Color savedColor = g.getColor();
        g.setColor(Atlas.colorBody);
        g.fillOval(a.getX() - Atlas.SIZE_X / 2, a.getY() - Atlas.SIZE_Y / 2, Atlas.SIZE_X, Atlas.SIZE_Y);
        g.setColor(Atlas.colorEye);
        g.fillOval(a.getX() - Atlas.SIZE_X / 2 + 4, a.getY() - Atlas.SIZE_Y / 2 + 4, Atlas.SIZE_X / 2, Atlas.SIZE_Y / 2);
        g.setColor(Atlas.colorRetina);
        g.fillOval(a.getX() - Atlas.SIZE_X / 2 + 5, a.getY() - Atlas.SIZE_Y / 2 + 5, Atlas.SIZE_X / 3, Atlas.SIZE_Y / 3);
        g.setColor(savedColor);
    }

    // Draws the missiles
    // modifies: g
    // effects:  draws the bluePortal onto g
    private void drawingBluePortal(Graphics g) {
        BluePortal bp = game.getBluePortal();
        if (bp != null) {
            drawBluePortal(g, bp);
        }
    }


    // Draws the missiles
    // modifies: g
    // effects:  draws the orangePortal onto g
    private void drawingOrangePortal(Graphics g) {
        OrangePortal op = game.getOrangePortal();
//        for (OrangePortal op: game.getOrangePortal())
        if (op != null) {
            drawOrangePortal(g, op);
//            System.out.println("Get here Orange?!");
//        } else {System.out.println("but why?!");}
        }
    }

    // Draws a bluePortal
    // modifies: g
    // effects:  draws the portal p onto g
    private void drawBluePortal(Graphics g, BluePortal b) {
        Color savedCol = g.getColor();
        g.setColor(BluePortal.COLOR);
        g.fillOval(b.getX() - BluePortal.Size_X / 2, b.getY() - BluePortal.Size_Y / 2, BluePortal.Size_X, BluePortal.Size_Y);
        g.setColor(savedCol);
    }

    // Draws a orangePortal
    // modifies: g
    // effects:  draws the portal p onto g
    private void drawOrangePortal(Graphics g, OrangePortal o) {
        Color savedCol = g.getColor();
        g.setColor(OrangePortal.COLOR);
        g.fillOval(o.getX() - OrangePortal.Size_X / 2, o.getY() - OrangePortal.Size_Y / 2, OrangePortal.Size_X, OrangePortal.Size_Y);
        g.setColor(savedCol);
    }


    private void drawBlock(Graphics g, Block b) {
        Color savedCol = g.getColor();
        g.setColor(Block.COLOR);
        g.fillRect(b.getX(), b.getY(), Block.Size_X, Block.Size_Y);
        g.setColor(savedCol);
}

    private void drawLava(Graphics g, Lava l) {
            Color savedCol = g.getColor();
            g.setColor(Lava.LAVA_COLOR);
            g.fillRect(l.getX(), l.getY(), Lava.Size_X ,Lava.Size_Y);
            g.setColor(savedCol);
        }

    private void drawBlocks(Graphics g) {
        if (game.getLevel() == 1) {
            Level1 level1 = new Level1();
            level1.setup();
            for (Block block : level1.getBlocks()) {
                drawBlock(g, block);
            }
        }
        if (game.getLevel() == 2) {
            Level1midway level1midway = new Level1midway();
            level1midway.setup();
            for (Block block : level1midway.getBlocks()) {
                drawBlock(g, block);
            }
        }
        if (game.getLevel() == 3) {
            Level2 level2 = new Level2();
            level2.setup();
            for (Block block : level2.getBlocks()) {
                drawBlock(g, block);
            }
        }
        if (game.getLevel() == 4) {
            Level3 level3 = new Level3();
            level3.setup();
            for (Block block : level3.getBlocks()) {
                drawBlock(g, block);
            }
        }
        if (game.getLevel() == 5) {
            EndLevel levelEnd = new EndLevel();
            levelEnd.setup();
            for (Block block : levelEnd.getBlocks()) {
                drawBlock(g, block);
            }
        }
    }



    private void drawLavas(Graphics g) {

        if (game.getLevel() == 1) {
            Level1 level1 = new Level1();
            level1.setup();
            for (Lava lava: level1.getLavas()) {
                drawLava(g, lava);
            }
        }
        if (game.getLevel() == 2) {
            Level1midway level1mw = new Level1midway();
            level1mw.setup();
            for (Lava lava: level1mw.getLavas()) {
                drawLava(g, lava);
            }
        }
        if (game.getLevel() == 3) {
            Level2 level2 = new Level2();
            level2.setup();
            for (Lava lava: level2.getLavas()) {
                drawLava(g, lava);
            }
        }
        if (game.getLevel() == 4) {
            Level3 level3 = new Level3();
            level3.setup();
            for (Lava lava: level3.getLavas()) {
                drawLava(g, lava);
            }
        }
        if (game.getLevel() == 5) {
            EndLevel levelEnd = new EndLevel();
            levelEnd.setup();
            for (Lava lava: levelEnd.getLavas()) {
                drawLava(g, lava);
            }
        }
    }

    private void drawFinishPoints(Graphics g) {
        if (game.getLevel() == 1) {
            Level1 level1 = new Level1();
            level1.setup();
            for (FinishPoint fp : level1.getFinishPoints()) {
                drawFinishPoint(g, fp);
            }
        }
        if (game.getLevel() == 2) {
            Level1midway level1midway = new Level1midway();
            level1midway.setup();
            for (FinishPoint fp : level1midway.getFinishPoints()) {
                drawFinishPoint(g, fp);
            }
        }
        if (game.getLevel() == 3) {
            Level2 level2 = new Level2();
            level2.setup();
            for (FinishPoint fp : level2.getFinishPoints()) {
                drawFinishPoint(g, fp);
            }
        }
        if (game.getLevel() == 4) {
            Level3 level3 = new Level3();
            level3.setup();
            for (FinishPoint fp : level3.getFinishPoints()) {
                drawFinishPoint(g, fp);
            }
        }
    }

    private void drawFinishPoint(Graphics g, FinishPoint fp) {
        Color savedCol = g.getColor();
        g.setColor(FinishPoint.COLORPINK);
        g.fillRect(fp.getX(), fp.getY(), FinishPoint.Size_X ,FinishPoint.Size_Y);
        g.setColor(savedCol);
    }


    private void gameOver(Graphics g) {
        Color saved = g.getColor();
        g.setColor(new Color(115, 25, 3));
        g.setFont(new Font("Comic Sans", 20, 20));
        FontMetrics fm = g.getFontMetrics();
//        centreString(OVER, g, fm, PortalGame.HEIGHT / 2);
//        centreString(REPLAY, g, fm, PortalGame.HEIGHT / 2 + 50);
        g.setColor(saved);
    }


//    private void centreString(String str, Graphics g, FontMetrics fm, int yPos) {
//        int width = fm.stringWidth(str);
//        g.drawString(str, (PortalGame.WIDTH - width) / 2, yPos);
//    }
}



