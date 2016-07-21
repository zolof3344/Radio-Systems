//All part of a game I made, can be run using MainGame
// Michael Holloway Version 1 
 import org.lwjgl.util.Rectangle;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.input.Keyboard;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;


public class EnemyShip extends Entity
{
    private LinkedList<Projectile> bullets;
    private int direction;
    private Texture texture;
    private float width_ratio;
    float speed2 = 0;
    int hitCount = 0;
    private float height_ratio;
    Target2 target2;
    private LinkedList<Target2> targets;
    Target target;
    Shooter thePlayer;
    static int lastShip = 0;
    float getHarder = 1000000000;
    float prevTime;
    float shootDelay = 1000000000;
    float prevShotTime;
    int speed=1;
    static double moveSpeed = 5;
    final int slow = 10;
    final int fast = 1;
    final int medium = 5;

    float targetX;
    private Random rand = new Random();
    
    int health = 100;
   
   // private LinkedList<Target2> targets;
    public EnemyShip(LinkedList<Projectile> bullets,Target target) {
        super(100,100,100+lastShip,100);
        this.target = target;
        this.bullets = bullets;
        direction = 2;
       // targetX = rand.nextInt(Display.getWidth());
        speed = medium;
        try
        {
        	lastShip+=1;

            // load texture as png from res/ directory (this can throw IOException)
            texture = TextureLoader.getTexture("PNG",
                                               ResourceLoader.getResourceAsStream("res/born cube.png"));

            // textures come in as a power of 2.  use these ratios to
            // calculate texture offsets for sprite based on box size
            width_ratio = (1.0f)*texture.getImageWidth() / texture.getTextureWidth();
            height_ratio = (1.0f)*texture.getImageHeight() / texture.getTextureHeight();

            // create a Rectangle at the origin where height is calculated from
            // texture aspect ratio
            hitbox = new Rectangle(100, 100,
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
        public EnemyShip(Shooter target, LinkedList<Projectile> bullets, int x, int y,int health, float speed) {
            super(x,y,100,100);
            if(moveSpeed>1)
            {
            moveSpeed = (float) (moveSpeed-0.2);
            }
            this.thePlayer = target;
            this.bullets = bullets;
            direction = 2;
            this.health = health;
            targetX = target.getX();
            this.speed2 = speed;
            try
            {

                // load texture as png from res/ directory (this can throw IOException)
                texture = TextureLoader.getTexture("PNG",
                                                   ResourceLoader.getResourceAsStream("res/born cube.png"));

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
    
    
    public EnemyShip(LinkedList<Projectile> bullets,Target2 target) {
        super(0,0,100,100);
        this.target2 = target;
        this.bullets = bullets;
        direction = 2;
        try
        {

            // load texture as png from res/ directory (this can throw IOException)
            texture = TextureLoader.getTexture("PNG",
                                               ResourceLoader.getResourceAsStream("res/born cube.png"));

            // textures come in as a power of 2.  use these ratios to
            // calculate texture offsets for sprite based on box size
            width_ratio = (1.0f)*texture.getImageWidth() / texture.getTextureWidth();
            height_ratio = (1.0f)*texture.getImageHeight() / texture.getTextureHeight();

            // create a Rectangle at the origin where height is calculated from
            // texture aspect ratio
            hitbox = new Rectangle(100, 100,
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
       targetX = thePlayer.getX();
//        if (thePlayer.getX()+200>x) 
//        {
//            x += delta/5;
//            direction=1;
//        }
//        if (thePlayer.getX()-200<x) 
//        {
//            x -= delta/5;
//            direction=-1;
//        }
//        if (thePlayer.getY()-200<y) 
//        {
//            y -= delta/5;
//        }
//        if (thePlayer.getY()+200>y) 
//        {
//            y += delta/5;
//        }
        
        if(x == targetX)
        {
        	 
        	  System.out.println("target x is " + targetX);
        }
        else if(x<targetX)
        {
        	x+=delta/moveSpeed;
        }
        else if(x>targetX)
        {
        	x-=delta/moveSpeed;
        }
        hitbox.setLocation((int)x, (int)y);
       if(bullets != null)
       {
    	   float currentTime = System.nanoTime();
    	   if((currentTime-prevShotTime >=shootDelay*speed2))
           {
        	   prevShotTime =  System.nanoTime();
        
        	AudioManager2.getInstance().play("laser");
            bullets.add(new Projectile((int)hitbox.getX()+hitbox.getWidth()/2,(int)hitbox.getY()+hitbox.getHeight(),2));
            System.out.println("I'm shooting! i am the bad guy");
            System.out.println("x is " + x + "y is" + y);
        }
       }

        
        hitbox.setLocation((int)x, (int)y);

        if(intersects(thePlayer)==true)
        {
        	//revert location
        	hitbox.setLocation((int)oldX,(int)oldY);
        }
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
    public void makeFaster()
    {
    	moveSpeed = moveSpeed-0.5;
    }
    public void onCollision(Entity other)
    {
    	System.out.println("The target has been hit!");
    	
    	
    	if(other instanceof Shooter)
    	{
    	this.deactivate();
    	}
    	if(other instanceof Projectile)
    	{
    		if(other instanceof captureLaser)
        	{
        		health += 20;
        	}
    		else
    		{
    		System.out.println(other.getDamage());
    		System.out.println(health);
    		health -= other.getDamage();
    		}
    	}
    	
    	if(health<=0)
    	{
    		this.deactivate();
    	}
    	
    		
    	
    	
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


