// Michael Holloway Version 1 based on code by Dr. Craig Tanis, UTC
//All part of a game I made, can be run using MainGame
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

//import edu.utc.Holloway3520.AudioManager;

import java.io.IOException;
import java.util.Random;
import org.lwjgl.opengl.Display;

public class Target2 extends Entity {
	
    private float r,g,b;
    private Random rand = new Random();
    int newX = 0;
	int newY = 0;
    private Texture texture;
    private float width_ratio;
    int currX = 0;
    int currY = 0;
    private float height_ratio;
	
    public Target2() {
    	super(0, 0, 60, 60);
    	 try
         {

             // load texture as png from res/ directory (this can throw IOException)
             texture = TextureLoader.getTexture("PNG",
                                                ResourceLoader.getResourceAsStream("res/Photorealistic_Toast.png"));

             // textures come in as a power of 2.  use these ratios to
             // calculate texture offsets for sprite based on box size
             width_ratio = (1.0f)*texture.getImageWidth() / texture.getTextureWidth();
             height_ratio = (1.0f)*texture.getImageHeight() / texture.getTextureHeight();

             // create a Rectangle at the origin where height is calculated from
             // texture aspect ratio
             hitbox = new Rectangle(0, 0,
                                 hitbox.getWidth(), 
                                 (int)(hitbox.getWidth() * (float)texture.getImageHeight()/texture.getImageWidth()));
         }
         catch(IOException e)
         {
             e.printStackTrace();
             System.err.println("failed to load image");
             System.exit(-1);
         }
        randomize();
    }

    private void randomize() {
        int x = rand.nextInt(Display.getWidth()-100) + 50;
        int y = rand.nextInt(Display.getHeight()-100) + 50;

        this.hitbox.setLocation(x,y);

        r = rand.nextFloat();
        g = rand.nextFloat();
        b = rand.nextFloat();
    }
    public void random() {
        int x = rand.nextInt(Display.getWidth()-100) + 50;
        int y = rand.nextInt(Display.getHeight()-100) + 50;
        newX = rand.nextInt(Display.getWidth()-100) + 50;
    	newY = rand.nextInt(Display.getHeight()-100) ;
      //  this.hitbox.setLocation(x,y);

        r = rand.nextFloat();
        g = rand.nextFloat();
        b = rand.nextFloat();
    }
    private void move()
    {
       if(currX < newX )
       {
    	   currX = hitbox.getX();
    	   currX = currX + 1;
    	   hitbox.setX(currX);
       }
       else  if(currX > newX)
       {
    	   currX = hitbox.getX();
    	   currX = currX - 1;
    	   hitbox.setX(currX);
       }
       if(currY>newY)
       {
    	   currY = hitbox.getY();
    	   currY -= currY;
    	   hitbox.setY(currY);
       }
       else  if(currY<newY)
       {
    	   currY = hitbox.getY();
    	   currY += currY;
    	   hitbox.setY(currY);
       }
       
    	
    }


    public void onCollision(Entity other) {
        System.out.println("The target has been hit!");
       int count = rand.nextInt(4);
        if(count == 0)
        {
       AudioManager.getInstance().play("g");
        }
        if(count==1)
        {
        	AudioManager.getInstance().play("C");
        }
        if(count==2)
        {
        	AudioManager.getInstance().play("D");
        }
        if(count == 3)
        {
        	//AudioManager.getInstance().play("E");
        }
        if(count== 3)
        {
        	AudioManager.getInstance().play("F");
        }
        
       
        randomize();
    }


    public void draw() {
    	move();
//    	  int x = hitbox.getX();
//        int y = hitbox.getY();
//        int w = hitbox.getWidth();
//        int h = hitbox.getHeight();
        float x = (float)hitbox.getX();
        float y = (float)hitbox.getY();
        float w = (float)hitbox.getWidth();
        float h = (float)hitbox.getHeight();
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
        // GL11.glColor3f(1,1,1); // interacts with color3f
        GL11.glColor3f(1,1, 1); // interacts with color3f

        GL11.glBegin(GL11.GL_QUADS);

  
        // top-left of texture tied to top-left of box        
        GL11.glTexCoord2f(0,0);
        GL11.glVertex2f(x,y);
        
        // top-right 
        GL11.glTexCoord2f(width_ratio,0);
        GL11.glVertex2f(x+w, y);

        // bottom-right
        GL11.glTexCoord2f(width_ratio, height_ratio); 
        GL11.glVertex2f(x+w,y+h);

        // bottom-left
        GL11.glTexCoord2f(0,height_ratio);
        GL11.glVertex2f(x,y+h);
        GL11.glEnd();
    }

    }


