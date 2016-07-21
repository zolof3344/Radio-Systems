// Michael Holloway Version 1 
//All part of a game I made, can be run using MainGame
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;

import java.io.IOException;
import java.util.LinkedList;

public class Shooter extends Entity {

    private LinkedList<Projectile> bullets;
    private int direction=1;
    private Texture texture;
    private float width_ratio;
    private float height_ratio;
    Target2 target2;
    private double buff = 1;
    private LinkedList<Target2> targets;
    Target target;
    public int health = 400;
   // private LinkedList<Target2> targets;
    float getHarder = 1000000000;
    float prevTime;
    float shootDelay = 1000000000/2;
    float prevShotTime;
    float speed;
    AudioManager2 aman = new AudioManager2().getInstance();
	

    
    public Shooter(LinkedList<Projectile> bullets,Target target,float speed) {
    	
    	  super(Display.getWidth()/2,Display.getHeight()/2,100,100);
        this.target = target;
        this.speed = speed;
        this.bullets = bullets;
        this.direction = 1;
       
        try
        {
        	aman.loadSample("laser2", "res/tos-computer-01.wav");
        	aman.loadSample("laser", "res/laser.wav");

            // load texture as png from res/ directory (this can throw IOException)
            texture = TextureLoader.getTexture("PNG",
                                               ResourceLoader.getResourceAsStream("res/FTLVoyagerSprite.png"));

            // textures come in as a power of 2.  use these ratios to
            // calculate texture offsets for sprite based on box size
            width_ratio = (1.0f)*texture.getImageWidth() / texture.getTextureWidth();
            height_ratio = (1.0f)*texture.getImageHeight() / texture.getTextureHeight();

            // create a Rectangle at the origin where height is calculated from
            // texture aspect ratio
            hitbox = new Rectangle(Display.getWidth()/2, Display.getHeight()/2,
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
        public Shooter(LinkedList<Projectile> bullets,LinkedList<Target2> target) {
            super(Display.getWidth()/2,Display.getHeight()/2,100,100);
            this.targets = target;
            this.bullets = bullets;
            this.direction = 1;
            try
            {
            	aman.loadSample("laser", "res/laser.wav");

                // load texture as png from res/ directory (this can throw IOException)
                texture = TextureLoader.getTexture("PNG",
                                                   ResourceLoader.getResourceAsStream("res/FTLVoyagerSprite.png"));

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
        }
    
    
    public Shooter(LinkedList<Projectile> bullets,Target2 target) throws IOException, LWJGLException {
        super(0,0,50,50);
        this.target2 = target;
        this.bullets = bullets;
        this.direction = 1;
        aman.loadSample("laser", "res/laser.wav");
        try
        {

            // load texture as png from res/ directory (this can throw IOException)
            texture = TextureLoader.getTexture("PNG",
                                               ResourceLoader.getResourceAsStream("res/FTLVoyagerSprite.png"));

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
    }


    public void update(float delta) {
        
        float x = hitbox.getX();
        float y = hitbox.getY();
        float oldX=x;
        float oldY=y;
        float currentTime = System.nanoTime();
        if (InputHandler.isRightPressed()) 
        {
        	if(x>=Display.getWidth())
        	{
        		x =0;
        	}
        	else
        	{
            x += delta/2;
        	}
         
        }
        if (InputHandler.isUpLeftPressed()) 
        {
        	if(x<=0)
        	{
        		x = Display.getWidth();
        	}
        	else
        	{
            x -= delta/2;
        	}
            
        }
        if (InputHandler.isUpPressed()) 
        {
        	if(y<=0)
        	{
        		y = Display.getHeight();
        	}
        	else
        	{
            y -= delta/2;
        	}
        }
        if (InputHandler.isDownPressed()) 
        {
        	if(y>=Display.getHeight())
        	{
        		y = 0;
        	}
        	else
        	{
            y += delta/2;
        	}
        }


       if((currentTime-prevShotTime >=shootDelay/2*buff))
       {
    	   prevShotTime =  System.nanoTime();
       
        if (InputHandler.isSpacePresssed())
        {
        //	AudioManager.getInstance().play("laser");
            bullets.add(new Projectile((int)hitbox.getX()+(hitbox.getWidth()/2),(int)hitbox.getY(),direction));
            aman.play("laser");
            System.out.println("I'm shooting!");
            System.out.println("x is " + x + "y is" + y);
        }
       
        if(InputHandler.isFPressed())
        {
        	aman.play("laser2");
        	bullets.add(new captureLaser((int)hitbox.getX()+(hitbox.getWidth()/2),(int)hitbox.getY(),direction));
        	}
       }


        
        hitbox.setLocation((int)x, (int)y);

        if(intersects(target)==true)
        {
        	//revert location
        	hitbox.setLocation((int)oldX,(int)oldY);
        }
    }
    public void powerUp(Powerup p)
    {
    	if(p.property == 1)
    	{
    	//health = p.getHealthBuff() + health;
    	}
    }
    public void addHealth()
    {
    	health +=20;
    }
    public void updateSpeed(float speed)
    {
    	this.speed = speed;
    }
    public boolean intersects(Entity other)
    {
    	if(other.hitbox.getX()==hitbox.getX())
    	{
    		return true;
    	}
    	else
    		
    	{
    		return false;
    	}
    }
    public void addBuff(double speed)
    {
    	buff = speed;
    }
    public void onCollision(Entity other)
    {
    	float currentTime = System.nanoTime();
        if(currentTime-prevTime >=getHarder*speed)
        {
     	  
     	   prevTime = System.nanoTime();
        
    	if(other instanceof Projectile)
    	{
    		other.deactivate();
    		health-=20;
    	}
        }
        
    }
   public float getX()
   {
	   return hitbox.getX();
   }
   public float getY()
   {
	   return hitbox.getY();
   }


    public void draw() {

//        int x = hitbox.getX();
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


