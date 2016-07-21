// Michael Holloway Version 1 
//All part of a game I made, can be run using MainGame
import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

//Main Version of the game, Space Trek. I made while messing around

public class MainGame  {

    public static void main(String[] args) throws IOException, LWJGLException {

        initGL(1024, 768);

        Menu gameMenu = new Menu();
       // AudioManager am = new AudioManager();
     //   gameMenu.addItem("empty", new JumperTest());
        gameMenu.addItem("Space Trek", new SpaceScene(gameMenu));
        gameMenu.addSpecial("Exit", Menu.DO_EXIT);
       
        Scene currScene = gameMenu;
        AudioManager aman = AudioManager.getInstance();
    	aman.loadSample("doot", "res/cello.wav");
    	aman.play("doot");
        while ( currScene.go()  )
        {
             // if nextScene() returns null (the default) reload the menu
            currScene = currScene.nextScene();

            if (currScene == null)
            {
                currScene = gameMenu;
            }

            System.out.println("Changing Scene: " + currScene);
        }


        Display.destroy();
        AudioManager.getInstance().destroy();
    }


    public static void initGL(int width, int height) throws LWJGLException
    {
        // open window of appropriate size
        Display.setDisplayMode(new DisplayMode(width, height));
        Display.create();
        Display.setVSyncEnabled(true);
        
        // enable 2D textures
        GL11.glEnable(GL11.GL_TEXTURE_2D);              
     
        // set "clear" color to black
        GL11.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);         

        // enable alpha blending
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
         
        // set viewport to entire window
        GL11.glViewport(0,0,width,height);
         
        // set up orthographic projectionr
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, width, height, 0, 1, -1);
        // GLU.gluPerspective(90f, 1.333f, 2f, -2f);
        // GL11.glTranslated(0, 0, -500);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
    }
}
