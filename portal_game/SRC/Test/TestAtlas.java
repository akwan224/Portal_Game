package Test;

import Model.Atlas;
import Model.PortalGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAtlas {
    private static final int startX = PortalGame.WIDTH / 4;
    private static final int startY = PortalGame.HEIGHT/ 4;
    private Atlas a;

    @BeforeEach
    public void runBefore() {
        a = new Atlas(startX, startY);
    }

    @Test
    public void testGetXY() {
        assertEquals(startX, a.getX());
        assertEquals(startY, a.getY());
    }

    @Test
    public void testUpdate() {
        final int NUM_UPDATES = 8;
        a.faceRight();
        a.move();
        assertEquals(startX + 2, a.getX());
        a.faceDown();
        a.move();
        assertEquals(startY + 2, a.getY());
        for(int count = 1; count < NUM_UPDATES; count++) {
            a.faceDown();
            a.move();
            a.faceRight();
            a.move();
        }
        assertEquals(startX + NUM_UPDATES * Atlas.DX, a.getX());
        assertEquals(startY + NUM_UPDATES * Atlas.DY, a.getY());
    }

    @Test
    public void testFlipDirection() {
        a.move();
        assertEquals(startX , a.getX());
        a.faceLeft();
        a.move();
        assertEquals(startX - 2, a.getX());
        a.faceRight();
        a.move();
        assertEquals(startX, a.getX());
    }

    @Test
    public void testLeftBoundary() {
        a.faceLeft();
        for(int count = 0; count < PortalGame.WIDTH / 2 / Atlas.DX + 1; count++)
            a.move();
        assertEquals(0, a.getX());
        a.move();
        assertEquals(0, a.getX());
    }

    @Test
    public void testRightBoundary() {
        a.faceRight();
        for(int count = 0; count < PortalGame.WIDTH / Atlas.DX + 1; count++)
            a.move();
        assertEquals(PortalGame.WIDTH, a.getX());
        a.move();
        assertEquals(PortalGame.WIDTH, a.getX());
    }

    @Test
    public void testUpBoundary() {
        a.faceUp();
        for(int count = 0; count < PortalGame.HEIGHT / 2 / Atlas.DY + 1; count++)
            a.move();
        assertEquals(0, a.getY());
        a.move();
        assertEquals(0, a.getY());
    }

    @Test
    public void testDownBoundary() {
        a.faceDown();
        for(int count = 0; count < PortalGame.HEIGHT / Atlas.DY + 1; count++)
            a.move();
        assertEquals(PortalGame.HEIGHT, a.getY());
        a.move();
        assertEquals(PortalGame.HEIGHT, a.getY());
    }
}
