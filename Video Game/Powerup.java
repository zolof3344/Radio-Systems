// Michael Holloway Version 1
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

public class Powerup extends Entity {
	
    private float r,g,b;
    private Random rand = new Random();

    private Texture texture;
    private float width_ratio;
    private float height_ratio;
	int x = 0;
	int y = 0;
	int currX = 0;
	int currY = 0;
	int targetX, targetY;
	int maxHealth = 200;
	int extraHealth = 20;
	int extraDamage = 30;
	int property = 1;
    public Powerup() {
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
    	 
        randomize(100,100);
    }
    public Powerup(int x, int y, int healthAmount, int DamageAmount)
    {
    	super(x, y, 60, 60);
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
    	 hitbox.setX(x); 
    	 hitbox.setY(y);
    //	 randomize(20, 20);
    }
    private void randomize(int boundX, int boundY) {
         x = rand.nextInt(Display.getWidth()-100 + boundX) + 50;
         y = rand.nextInt(Display.getHeight()-100 + boundY) + 50;
         targetX = x;
         targetY = y;
        this.hitbox.setLocation(x,y);

        r = rand.nextFloat();
        g = rand.nextFloat();
        b = rand.nextFloat();
    }
   private void move()
   {
	  
	   if(x!=targetX)
	   {
	  currX = x+1;
	   }
	   if(y!=targetY)
	   {
	   currY = y+1;
	   }
	   this.hitbox.setLocation(x,y);
   }
   public int getHealthBuff()
   {
	   return extraHealth;
   }
public void playSound()
{
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
}
public void playOther()
{
	AudioManager.getInstance().play("collisionSound");
}

    public void onCollision(Entity other) {
        System.out.println("The target has been hit!");
       playOther();
        r=1;
        g=1;
        b=1;
        randomize(0,0);
       
        
        
    }


    public void draw() {
    	//move();
        x = hitbox.getX();
        y = hitbox.getY();
        int w = hitbox.getWidth();
        int h = hitbox.getHeight();
        
        GL11.glColor3f(0,1,0);
        GL11.glBegin(GL11.GL_QUADS);

        GL11.glVertex2f(x,y);
        GL11.glVertex2f(x+w,y);
        GL11.glVertex2f(x+w,y+h);
        GL11.glVertex2f(x,y+h);

        GL11.glEnd();            
    }

    }

    


