package Test;

import Model.Exception.OutOfTimeException;
import Model.PortalGame;
import UI.PortalMazes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestGame {
   private PortalGame g;
   private PortalMazes p;

   @BeforeEach
   public void runBefore(){
       g = new PortalGame();
   }

   @Test
   public void testThrownOutOfTimeException() throws OutOfTimeException{
       try {
           g = new PortalGame();
           addTimer();
         
       } catch (OutOfTimeException e) {
           System.out.printf("caught ittt");
       }
   }
}
