//package Test;
//
//import Model.Atlas;
//import Model.PortalGame;
//import javafx.scene.input.KeyCode;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.awt.event.KeyEvent;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class TestSave {
//    private static final int startX = PortalGame.WIDTH / 4;
//    private static final int startY = PortalGame.HEIGHT / 4;
//    private Atlas a;
//    private int m;
//
//    @BeforeEach
//    public void runBefore() {
//        a = new Atlas(startX, startY);
//        m = 2;
//    }
//
//    @Test
//    public void testGetSavedXY() {
//        a.move();
//
//        assertEquals(startX + m, a.getX());
//        assertEquals(startY + m, a.getY());
//
//        int keyCode = KeyEvent.VK_CODE_INPUT;
//        if (keyCode == KeyEvent.VK_P) {
//            assertEquals(startX + m, a.getnewX());
//            assertEquals(startY + m, a.getnewY());
//        }
//    }
//}
