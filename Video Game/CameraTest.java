//All part of a game I made, can be run using MainGame
// Michael Holloway Version 1 based on code by Dr. Craig Tanis, UTC
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.input.Keyboard;
import java.util.LinkedList;


public class CameraTest extends Scene {

    private Jumper jumper;
    private static LinkedList<Platform> platforms;
    private static LinkedList<Target>    targets;
    public CameraTest()
    {
        jumper = new Jumper();
        Randomize();
        
    }
    public static void Randomize()
    {   platforms = new LinkedList<>();
        targets   = new LinkedList<>();
        platforms.add(new Platform(0, Display.getHeight()-50, Display.getWidth(), 10));
    	int maxRange = Display.getWidth();
        int minRange = 0;
     
    int maxHeight = 200;
    int minHeight =  20;
    //RandomlyGenerate platforms, etc
      for(int i =1; i<40;i++)
      {  int range = maxRange-minRange;
         int heightDifferenceRange = maxHeight-minHeight;
       int  currentLocation = (int) (Math.random()*range)+minRange;
       int  currentHeight = (int) (Math.random()*heightDifferenceRange) + minHeight;
       System.out.println(currentLocation);
      	platforms.add(new Platform(currentLocation,Display.getHeight()-50-currentHeight,20,currentHeight));
         //minRange = currentLocation;
      	targets.add(new Target(currentLocation,Display.getHeight()-250-currentHeight));
      }
    }


    private int w=200;

    public boolean drawFrame(float delta)
    {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

        if (Keyboard.isKeyDown(Keyboard.KEY_A)) 
        {
            w ++;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_B)) 
        {
            w --;
        }

        // draw the main screen
        GL11.glViewport(0,0,Display.getWidth(),Display.getHeight());
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(jumper.getX() - w, jumper.getX()+w,
                     jumper.getY()+w, jumper.getY()-w,
                     1,-1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);



        jumper.update(delta);
// This is very inefficient! but I was in a hurry :(

        for (Platform p : platforms)
        {
            p.update(delta);
            p.draw();
        }
        for(Target t : targets)
        {
        	t.update(delta);
        	t.draw();
        }

        for (Platform p : platforms)
        {
            jumper.testCollision(p);
            
        }
        for (Target t : targets)
        {
            t.testCollision(jumper);
            
        }
        
        jumper.draw();


        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);

        // draw the minimap
        GL11.glViewport(Display.getWidth()-200, Display.getHeight()-200, 200, 200);
        
        GL11.glColor3f(1,1,0);
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2f(0, 0);
        GL11.glVertex2f(Display.getWidth(), 0);
        GL11.glVertex2f(Display.getWidth(), Display.getHeight());
        GL11.glVertex2f(0, Display.getHeight());
        GL11.glEnd();
        


        for (Platform p : platforms)
        {
            p.draw();
        }
        jumper.draw();

        return true;
    }

    


}