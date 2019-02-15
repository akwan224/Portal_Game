package Model;

import Model.Blocks.Block;
import Model.Blocks.FinishPoint;
import Model.Blocks.Lava;
import Model.Blocks.WallBlock;
import Model.Levels.*;
import Model.OrangeBluePortals.BluePortal;
import Model.OrangeBluePortals.OrangePortal;
import Model.SpawnPortal.BlueSpawnPortal;
import Model.SpawnPortal.OrangeSpawnPortal;
import Model.SpawnPortal.SpawnPortal;

import java.awt.event.KeyEvent;
import java.util.*;

public class PortalGame {
    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;


    public static final Random RND = new Random();

    private Atlas atlas;
    private BluePortal bluePortal;
    private OrangePortal orangePortal;
    private OrangeSpawnPortal spawnOrangePortal;
    private BlueSpawnPortal spawnBluePortal;
    private List<FinishPoint> finishPoints;
    private Level1 level1 = new Level1();
    private Level1midway level1mw = new Level1midway();
    private Level2 level2 = new Level2();
    private Level3 level3 = new Level3();
    private EndLevel levelEnd = new EndLevel();
    private boolean isGameOver;
    private boolean isNextLevel;
    private int level;
    private int opposite;
    private Map<Integer, Integer> levelMap = new HashMap<>();
    private int spawnRange = 15;

    private int directionBlue;
    private int directionOrange;
    public int directionSpawnOrange;
    public int directionSpawnBlue;
    public int atlasLastDirection;

    // Constructs 2D portal Game
    // effects: creates
    public PortalGame() {
        level = 1;
        if (level == 1) {
            level1.setup();
        }
        opposite = 1;
        setUp();
    }

    public void update() {
        moveBluePortal();
        moveOrangePortal();
        checkLavaCollision();
        if (bluePortal != null) {
            checkPortalCollisionBlue();
        }
        if (orangePortal != null) {
            checkPortalCollisionOrange();
        }
        if (spawnOrangePortal != null && spawnBluePortal != null) {
            checkTeleportCollisionOrange();
            checkTeleportCollisionBlue();
        }
        if (level != 5) {
            checkFinishPointCollision();
        }
        checkNextLevel();
        checkGameOver();
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_E) {
            fireOrangePortal();
        } else if (keyCode == KeyEvent.VK_Q) {
            fireBluePortal();
        } else if (keyCode == KeyEvent.VK_O) {
            if (levelMap.get(level) == 0) {
                opposite++;
                levelMap.replace(level, 0, opposite);
            } else {
                opposite--;
                levelMap.replace(level, 1, opposite);
                atlasOppositeControl(keyCode);
            }
        } else if (keyCode == KeyEvent.VK_X) {
            System.exit(0);
        } else if (keyCode == KeyEvent.VK_M) {
            if (level < 5)
                level++;
                spawnBluePortal = null;
                spawnOrangePortal = null;
            resetAtlasPos();
        } else if (keyCode == KeyEvent.VK_N) {
            if (level > 1)
                level--;
            spawnBluePortal = null;
            spawnOrangePortal = null;
            resetAtlasPos();
        }
//        else if (levelMap.get(level) == 0) {
//            atlasOppositeControl(keyCode);
//        }
        else {
            atlasControl(keyCode);
        }
    }

    public boolean isOver() {
        return isGameOver;
    }

    // retreive orangeprojectile location
    public OrangePortal getOrangePortal() {
        return orangePortal;
    }

    // retreive blueprojectile location
    public BluePortal getBluePortal() {
        return bluePortal;
    }

    // retreive finishpoint location
    public List<FinishPoint> getFinishPoints() {
        if (level == 1) {
            return level1.getFinishPoints();
        }
        if (level == 2) {
            return level1mw.getFinishPoints();
        }
        if (level == 3) {
            return level2.getFinishPoints();
        }
        if (level == 4) {
            return level3.getFinishPoints();
        }
        return null;
    }

    // retreive atlas location
    public Atlas getAtlas() {
        return atlas;
    }

    // retreive blueportallocation
    public BlueSpawnPortal getBlueSpawnPortal() {
        return spawnBluePortal;
    }

    // retreive orangeportallocation
    public OrangeSpawnPortal getOrangeSpawnPortal() {
        return spawnOrangePortal;
    }

    // Sets / resets the game
    // modifies: this
    // effects:  start from level 1, initializes atlas
    private void setUp() {
        orangePortal = null;
        bluePortal = null;
        if (level < 4) {
            spawnBluePortal = null;
            spawnOrangePortal = null;
        }
        finishPoints = null;
        atlas = new Atlas(Atlas.X_POS, Atlas.Y_POS);
        levelMap.put(level, opposite);
        isGameOver = false;
        isNextLevel = false;
    }

    // shoots out blue projectile
    private void fireBluePortal() {
        int yDir = 0;
        int xDir = 0;
        firingBluePortal(yDir, xDir);
    }


    // shoots out orange projectile
    private void fireOrangePortal() {
        int yDir = 0;
        int xDir = 0;
        firingOrangePortal(yDir, xDir);
    }

    // updates the blueProjectile
    // modifies: this
    // effects: moves the projectile
    private void moveBluePortal() {
        if (bluePortal != null) {
            bluePortal.move();
        }
    }

    // updates the orangeProjectile
    // modifies: this
    // effects: moves the projectile
    private void moveOrangePortal() {
        if (orangePortal != null) {
            orangePortal.move();
        }
    }

    //     Controls Atlas
//     modifies: thiss
//     effects: turns atlas in response to key code
    private void atlasControl(int keyCode) {
        if (keyCode == KeyEvent.VK_P)
            save();
        if (!checkWallCollision()) {
            atlasControls(keyCode);
            return;
        }
        if (checkWallCollision()) {
            if (atlasLastDirection == 3) {
                atlasControlNoLeft(keyCode);
            }
            if (atlasLastDirection == 2) {
                atlasControlNoRight(keyCode);
            }
            if (atlasLastDirection == 1) {
                atlasControlNoUp(keyCode);
            }
            if (atlasLastDirection == 0) {
                atlasControlNoDown(keyCode);
            }
            return;
        }
    }


    private void atlasControls(int keyCode) {
        if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_KP_LEFT || keyCode == KeyEvent.VK_A) {
            atlas.faceLeft();
            atlasLastDirection = 3;
            atlas.move();
        }
        if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_KP_RIGHT || keyCode == KeyEvent.VK_D) {
            atlas.faceRight();
            atlasLastDirection = 2;
            atlas.move();
        }
        if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_KP_UP || keyCode == KeyEvent.VK_W) {
            atlas.faceUp();
            atlasLastDirection = 1;
            atlas.move();
        }
        if (keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_KP_DOWN || keyCode == KeyEvent.VK_S) {
            atlas.faceDown();
            atlasLastDirection = 0;
            atlas.move();
        }
    }

    //     Controls Atlas
//     modifies: this
//     effects: turns atlas in response to key code
    private void atlasOppositeControl(int keyCode) {
        if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_KP_RIGHT || keyCode == KeyEvent.VK_D)
            atlas.faceLeft();
        if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_KP_LEFT || keyCode == KeyEvent.VK_A)
            atlas.faceRight();
        if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_KP_UP || keyCode == KeyEvent.VK_W)
            atlas.faceDown();
        if (keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_KP_DOWN || keyCode == KeyEvent.VK_S)
            atlas.faceUp();
        if (keyCode == KeyEvent.VK_P)
            save();

        atlas.move();

    }

    private boolean checkWallCollision() {
        for (Block b: retreiveLevelBlocks(level)) {
            if (atlas.isCollideWallBlock(atlas, b)) {
                return true;
            }
        } return false;
    }

    public void checkLavaCollision() {
        for (Lava l : retreiveLevelLava(level)) {
            if (atlas.isCollidedWithLava(atlas, l)) {
                isGameOver = true;
            }
        }
    }


    // effects: initiate gameOverSequence
    private void checkGameOver() {
        if (isGameOver) {
            setUp();
        }
    }

    // effects: move to next Level
    private int checkNextLevel() {
        if (isNextLevel) {
            level++;
        }
        levelMap.replace(level, opposite);
        return level;
    }

    private boolean save() {
//        atlas.save();
        return true;
    }



    // Checks for collisions between a Portal and a Block
    // modifies: this
    // effects:  removes any block that has been shot with a portal
    //           and removes corresponding portal from play
    public void checkPortalCollisionOrange() {
        for (Block target : retreiveLevelBlocks(level)) {
            if (checkBlockHitOrange(target, orangePortal)) {
                target = null;
//                retreiveLevelBlocks(level).remove(target);
                directionSpawnOrange = directionOrange;
                if (directionSpawnOrange == 1) {
                    spawnOrangePortal = new OrangeSpawnPortal(orangePortal.getX(), orangePortal.getY() + spawnRange);
                    orangePortal = null;
                    return;
                }
                if (directionSpawnOrange == 3) {
                    spawnOrangePortal = new OrangeSpawnPortal(orangePortal.getX() + spawnRange, orangePortal.getY());
                    orangePortal = null;
                    return;
                }
                spawnOrangePortal = new OrangeSpawnPortal(orangePortal.getX(), orangePortal.getY());
                orangePortal = null;
            }
        }
    }
    public void checkPortalCollisionBlue()  {
      for (Block target : retreiveLevelBlocks(level)) {
            if (checkBlockHitBlue(target, bluePortal)) {
                target = null;
                directionSpawnBlue = directionBlue;
                if (directionSpawnBlue == 1) {
                    spawnBluePortal = new BlueSpawnPortal(bluePortal.getX(), bluePortal.getY() + spawnRange);
                    bluePortal = null;
                    return;
                }
                if (directionSpawnBlue == 3) {
                    spawnBluePortal = new BlueSpawnPortal(bluePortal.getX() + spawnRange, bluePortal.getY());
                    bluePortal = null;
                    return;
                }
                spawnBluePortal = new BlueSpawnPortal(bluePortal.getX(), bluePortal.getY());
                bluePortal = null;
            }
        }
    }


    private boolean checkBlockHitOrange(Block target, OrangePortal orangeportal) {
        if (orangeportal != null) {
            if (orangeportal.collidedWithOrange(target, orangeportal)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkBlockHitBlue(Block target, BluePortal blueportal) {
        if (blueportal != null) {
            if (blueportal.collidedWithBlue(target, blueportal)) {
                return true;
            }
        }
        return false;
    }


    // spawns atlas in new location
    public void checkTeleportCollisionOrange() {
        if (atlas.isCollideOrangePortal(atlas, getOrangeSpawnPortal())) {
            atlas = null;
            if (directionSpawnBlue == 0) {
                atlas = new Atlas(getBlueSpawnPortal().getX(), getBlueSpawnPortal().getY() - spawnRange);
            }
            if (directionSpawnBlue == 1) {
                atlas = new Atlas(getBlueSpawnPortal().getX(), getBlueSpawnPortal().getY() + spawnRange);
            }
            if (directionSpawnBlue == 2) {
                atlas = new Atlas(getBlueSpawnPortal().getX() - spawnRange, getBlueSpawnPortal().getY());
            }
            if (directionSpawnBlue == 3) {
                atlas = new Atlas(getBlueSpawnPortal().getX() + spawnRange, getBlueSpawnPortal().getY());
            }
        }
    }

    // spawns atlas in new location
    public void checkTeleportCollisionBlue() {
            if (atlas.isCollideBluePortal(atlas, getBlueSpawnPortal())) {
                atlas = null;
                if ( directionSpawnOrange == 0) {
                    atlas = new Atlas(getOrangeSpawnPortal().getX(), getOrangeSpawnPortal().getY() - spawnRange);
                }
                if ( directionSpawnOrange == 1) {
                    atlas = new Atlas(getOrangeSpawnPortal().getX(), getOrangeSpawnPortal().getY() + spawnRange);
                }
                if ( directionSpawnOrange == 2) {
                    atlas = new Atlas(getOrangeSpawnPortal().getX() - spawnRange, getOrangeSpawnPortal().getY());
                }
                if ( directionSpawnOrange == 3) {
                    atlas = new Atlas(getOrangeSpawnPortal().getX() + spawnRange, getOrangeSpawnPortal().getY());
                }
            }
        }

    private List<Block> retreiveLevelBlocks(int level) {
        if (level == 1) {
            return level1.getBlocks();
        }
        if (level == 2) {
            return level1mw.getBlocks();
        }
        if (level == 3) {
            return level2.getBlocks();
        }
        if (level == 4) {
            return level3.getBlocks();
        }
        if (level == 5) {
            return levelEnd.getBlocks();
        }
        return null;
    }

    private List<Lava> retreiveLevelLava(int level) {
        if (level == 1) {
            return level1.getLavas();
        }
        if (level == 2) {
            return level1mw.getLavas();
        }
        if (level == 3) {
            return level2.getLavas();
        }
        if (level == 4) {
            return level3.getLavas();
        }
        if (level == 5) {
            return levelEnd.getLavas();
        }
        return null;
    }




    private void atlasControlNoLeft (int keyCode) {
        if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_KP_LEFT || keyCode == KeyEvent.VK_A) {
            atlas.faceLeft();
            atlasLastDirection = 3;
            return;
        }
        if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_KP_RIGHT || keyCode == KeyEvent.VK_D) {
            atlas.faceRight();
            atlasLastDirection = 2;
        }
        if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_KP_UP || keyCode == KeyEvent.VK_W) {
            atlas.faceUp();
            atlasLastDirection = 1;
        }
        if (keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_KP_DOWN || keyCode == KeyEvent.VK_S) {
            atlas.faceDown();
            atlasLastDirection = 0;
        }
        atlas.move();
    }

    private void atlasControlNoRight (int keyCode) {
        if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_KP_LEFT || keyCode == KeyEvent.VK_A) {
            atlas.faceLeft();
            atlasLastDirection = 3;
        }
        if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_KP_RIGHT || keyCode == KeyEvent.VK_D) {
            atlas.faceRight();
            atlasLastDirection = 2;
            return;
        }
        if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_KP_UP || keyCode == KeyEvent.VK_W) {
            atlas.faceUp();
            atlasLastDirection = 1;

        }
        if (keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_KP_DOWN || keyCode == KeyEvent.VK_S) {
            atlas.faceDown();
            atlasLastDirection = 0;
        }
        atlas.move();
    }

    private void atlasControlNoDown (int keyCode) {
        if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_KP_LEFT || keyCode == KeyEvent.VK_A) {
            atlas.faceLeft();
            atlasLastDirection = 3;
        }
        if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_KP_RIGHT || keyCode == KeyEvent.VK_D) {
            atlas.faceRight();
            atlasLastDirection = 2;
            return;
        }
        if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_KP_UP || keyCode == KeyEvent.VK_W) {
            atlas.faceUp();
            atlasLastDirection = 1;

        }
        if (keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_KP_DOWN || keyCode == KeyEvent.VK_S) {
            atlas.faceDown();
            atlasLastDirection = 0;
            return;
        }
        atlas.move();
    }

    private void atlasControlNoUp (int keyCode) {
        if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_KP_LEFT || keyCode == KeyEvent.VK_A) {
            atlas.faceLeft();
            atlasLastDirection = 3;
        }
        if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_KP_RIGHT || keyCode == KeyEvent.VK_D) {
            atlas.faceRight();
            atlasLastDirection = 2;
        }
        if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_KP_UP || keyCode == KeyEvent.VK_W) {
            atlas.faceUp();
            atlasLastDirection = 1;
            return;
        }
        if (keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_KP_DOWN || keyCode == KeyEvent.VK_S) {
            atlas.faceDown();
            atlasLastDirection = 0;
        }
        atlas.move();
    }

    private void firingBluePortal(int yDir, int xDir) {
        if (atlas.isfacingDown()) {
            yDir = 1;
            bluePortal = new BluePortal(atlas.getX(), atlas.getY(), xDir, yDir);
            directionBlue = 0;
        }
        if (atlas.isfacingUp()) {
            yDir = -1;
            bluePortal = new BluePortal(atlas.getX(), atlas.getY(), xDir, yDir);
            directionBlue = 1;
        }
        if (atlas.isfacingRight()) {
            xDir = 1;
            bluePortal = new BluePortal(atlas.getX(), atlas.getY(), xDir, yDir);
            directionBlue = 2;
        }
        if (atlas.isfacingLeft()) {
            xDir = -1;
            bluePortal = new BluePortal(atlas.getX(), atlas.getY(), xDir, yDir);
            directionBlue = 3;
        }
    }


    private void firingOrangePortal(int yDir, int xDir) {
        if (atlas.isfacingDown()) {
            yDir = 1;
            orangePortal = new OrangePortal(atlas.getX(), atlas.getY(), xDir, yDir);
            directionOrange = 0;
        }
        if (atlas.isfacingUp()) {
            yDir = -1;
            orangePortal = new OrangePortal(atlas.getX(), atlas.getY(), xDir, yDir);
            orangePortal.faceUp();
            directionOrange = 1;
        }
        if (atlas.isfacingRight()) {
            xDir = 1;
            orangePortal = new OrangePortal(atlas.getX(), atlas.getY(), xDir, yDir);
            directionOrange = 2;
        }
        if (atlas.isfacingLeft()) {
            xDir = -1;
            orangePortal = new OrangePortal(atlas.getX(), atlas.getY(), xDir, yDir);
            directionOrange = 3;
        }
    }

    public int getLevel() {
        return level;
    }

    public void checkFinishPointCollision() {
        for (FinishPoint fp : getFinishPoints()) {
            if (atlas.isCollideFinishPoint(atlas, fp)) {
                level++;
                    spawnBluePortal = null;
                    spawnOrangePortal = null;
                resetAtlasPos();
                if (level == 5) {
                    System.out.printf("U WIN FAM, GRATZ");
                }
            }
        }
    }

    private void resetAtlasPos() {
        atlas = null;
        atlas = new Atlas(Atlas.X_POS, Atlas.Y_POS);
    }
}

