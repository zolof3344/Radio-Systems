// Michael Holloway Version 1 based on code by Dr. Craig Tanis, UTC
//All part of a game I made, can be run using MainGame
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.Rectangle;

public class Jumper extends Entity {
    private float elapsedTime =0;
    private static int height=100;
    private static int width=20;
    float r=0;
    float b=1;
    float g=0;
    int direction=0;
    private Vector2f velocity;
    private float mass;
    private Texture texture;
    private float width_ratio;
    private float height_ratio;

    public Jumper()
    {
        super(0, 0, width, height);
        velocity = new Vector2f(0,0);
        mass = 2;
        try
        {

            // load texture as png from res/ directory (this can throw IOException)
            texture = TextureLoader.getTexture("PNG",
                                               ResourceLoader.getResourceAsStream("res/Shiba1.png"));

            // textures come in as a power of 2.  use these ratios to
            // calculate texture offsets for sprite based on box size
            width_ratio = (1.0f)*texture.getImageWidth() / texture.getTextureWidth();
            height_ratio = (1.0f)*texture.getImageWidth() / texture.getTextureHeight();

            // create a Rectangle at the origin where height is calculated from
            // texture aspect ratio
            hitbox = new Rectangle(
            		0, 0,
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
    public void color(float r, float g, float b)
    {
    	this.r=r;
    	this.g=g;
    	this.b=b;
    }

    public void draw() {
            
    	{
            float x = (float)hitbox.getX();
            float y = (float)hitbox.getY();
            float width = (float)hitbox.getWidth();
            float height = (float)hitbox.getHeight();
            

            // going to send a series of quad vertices...



            // if(debug)
            // GL11.glColor3f(1.0f, 0.0f, 0.0f);
            // GL11.glBegin(GL11.GL_QUADS);
            // GL11.glVertex2f(x,y);
            // GL11.glVertex2f(x+width, y);
            // GL11.glVertex2f(x+width,y+height);
            // GL11.glVertex2f(x,y+height);
            // GL11.glEnd();


            // make the loaded texture the active texture for the OpenGL context
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
            // GL11.glColor3f(1,1,1); // interacts with color3f
            GL11.glColor3f(1,1, 1); // interacts with color3f

            GL11.glBegin(GL11.GL_QUADS);


            // top-left of texture tied to top-left of box        
            GL11.glTexCoord2f(0,0);
            GL11.glVertex2f(x,y);
            
            // top-right 
            GL11.glTexCoord2f(width_ratio,0);
            GL11.glVertex2f(x+width, y);

            // bottom-right
            GL11.glTexCoord2f(width_ratio, height_ratio); 
            GL11.glVertex2f(x+width,y+height);

            // bottom-left
            GL11.glTexCoord2f(0,height_ratio);
            GL11.glVertex2f(x,y+height);


            GL11.glEnd();
            GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
        }
        
    }
    

    public void update(float delta) {
        elapsedTime = elapsedTime + delta;
        float x = hitbox.getX();
        float y = hitbox.getY();

        // fix on boundaries...
        if (x < 0)
        {
            x = 0;
            velocity.setX(0);
        }

        if (x > Display.getWidth() - width)
        {
           // x = Display.getWidth() - width;
        	 x= 0;
            velocity.setX(0);
            CameraTest.Randomize();
        }

        if (y < 0)
        {
            y = 0;
            velocity.setY(0);
        }

        if (y > Display.getHeight() - height)
        {
            y = Display.getHeight() - height;
            velocity.setY(0);
        }




        Vector2f extraForce = new Vector2f(0,0);
       
        
        // apply gravity
        Vector2f.add(extraForce,
                     (Vector2f) new Vector2f(0, .001f).scale(delta/mass),
                     extraForce);



        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE))
        {
            Vector2f.add(extraForce, new Vector2f(0f, -.02f), extraForce); // force going up
        }

        // add some horizontal forces in response to key presses
        if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
        {
            Vector2f.add(extraForce, new Vector2f(.001f, 0), extraForce);
            direction=1;
         
        }
      if(direction==1&& velocity.getX()>=0)
      {
    	  //apply friction
          Vector2f.add(extraForce,
                  (Vector2f) new Vector2f(-.0001f, 0),
                  extraForce);
          
      }
      if(direction==2 && extraForce.getX()!=0)
      {
    	  Vector2f.add(extraForce,
                  (Vector2f) new Vector2f(.0005f, 0),
                  extraForce);
      }
   //   System.out.println(extraForce.getX());
        // apply force to velocity vector
        extraForce.scale(delta/mass);
        
        Vector2f.add(velocity, extraForce, velocity);
        
        


        x += velocity.getX()*delta;
        y += velocity.getY()*delta;


        

        hitbox.setLocation((int)x,(int)y);
    }
    public float getX()
    {
    	return hitbox.getX();
    }
    public float getY()
    {
    	return hitbox.getY();
    }
    public void onCollision(Entity other)
    {
        if (other instanceof Platform)
        {

            Rectangle overlap = intersection(other);
            float x =hitbox.getX();
            float y =hitbox.getY();
            double width = overlap.getWidth();
            double height = overlap.getHeight();
            if (height > width)
            {
                // horizontal
                x-=width;
                velocity.setX(0);
                direction=2;
            }
            else if(height>width && direction==2)
            {
            	  x+=width;
                  velocity.setX(0);
                  direction=1;
            }
            else
            {
                // vertical collision
                y -= height;
                velocity.setY(0);
            }

            hitbox.setLocation((int)x,(int)y);
        }
    }
}


