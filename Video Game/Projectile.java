// Michael Holloway Version 1 
//All part of a game I made, can be run using MainGame
import org.lwjgl.util.Rectangle;
import org.lwjgl.opengl.GL11;

import java.io.IOException;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;


public class Projectile extends Entity {

    private static final int WIDTH=20;
    private static final int HEIGHT=10;
   // private static final float SPEED=1f;

    private Vector2f velocity;
   // private float mass;
   // private int direction;
    private Texture texture;
    private float width_ratio;
    private float height_ratio;
  final  private int UP = 0;
    final private int DOWN = 1;
   final  private int LEFT = 2;
   final private int RIGHT = 3; 
   int damage = 20;
  int direction = 1;
    // initial x,y.  direction should be 1 to go right, -1 to go left
    public Projectile(int x, int y, int direction) {
        super(x,y,WIDTH,HEIGHT);
        velocity = new Vector2f(0, -.2f);
        this.direction = direction;
        try
        {
        	System.out.println("x is " + x + "y is " + y);
            // load texture as png from res/ directory (this can throw IOException)
            texture = TextureLoader.getTexture("PNG",
                                               ResourceLoader.getResourceAsStream("res/padding_butter.png"));

            // textures come in as a power of 2.  use these ratios to
            // calculate texture offsets for sprite based on box size
            width_ratio = (1.0f)*texture.getImageWidth() / texture.getTextureWidth();
            height_ratio = (1.0f)*texture.getImageHeight() / texture.getTextureHeight();

            // create a Rectangle at the origin where height is calculated from
            // texture aspect ratio
            hitbox = new Rectangle(x, y,
                                hitbox.getWidth(), 
                                (int)(hitbox.getWidth() * (float)texture.getImageHeight()/texture.getImageWidth()));
        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.err.println("failed to load image");
            System.exit(-1);
        }
    }

    public void update(float delta) {

        float x = hitbox.getX();
        float y = hitbox.getY();
        
        // apply gravity
        if(direction==1)
        {
        Vector2f.add(velocity,
                     (Vector2f)new Vector2f(0, -.0001f).scale(delta),
                     velocity);
        y += velocity.getY()*delta;
        }
        else if(direction ==2)
        {
        	Vector2f.add(velocity,
                    (Vector2f)new Vector2f(0, -.0001f).scale(delta),
                    velocity);
        	y -= velocity.getY()*delta;
        }


        x += velocity.getX()*delta;
       // y -= velocity.getY()*delta;


        if (y < 0 - hitbox.getHeight() || y > Display.getHeight())
        {
            this.deactivate();
        }

        hitbox.setLocation((int)x,(int)y);
    }
    public void onCollision(Entity other)
    {
    	this.deactivate();
    	this.destroy();
    }
    public int getDamage()
    {
    	return damage;
    }
    public int addDamage(int amount)
    {
    	
    	return damage += amount;
    }
    public void draw() {

    	 //  int x = hitbox.getX();
//         int y = hitbox.getY();
//         int w = hitbox.getWidth();
//         int h = hitbox.getHeight();
         float x = (float)hitbox.getX();
         float y = (float)hitbox.getY();
         float w = (float)hitbox.getWidth();
         float h = (float)hitbox.getHeight();
         
         // GL11.glColor3f(1,1,1); // interacts with color3f
         GL11.glColor3f(1,1, 0); // interacts with color3f

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
         GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
     }

     }


