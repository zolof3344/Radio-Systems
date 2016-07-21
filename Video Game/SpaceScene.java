// Michael Holloway Version 1 based on code by Dr. Craig Tanis, UTC
//All part of a game I made, can be run using MainGame
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Rectangle;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;

import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import java.util.Iterator;
//import java.awt.Image;
import java.util.LinkedList;
import java.util.Random;

import org.newdawn.slick.*;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;



public class SpaceScene extends Scene {
	private LinkedList<Projectile> bullets;
	private static LinkedList<Projectile> enemyBullets;
    private static Shooter playerShip;
    private static int oldScore =0;
    private static float oldTimeAlive = 0;
    private Texture texture;
    float speed =1;
    // intialize all variables
    private LinkedList<Projectile> enemybullets;
 
    private static LinkedList<Target>    targets;
    private Target target = new Target(100,100);
    private static LinkedList<EnemyShip> enemyShips;
    private EnemyShip enemy;
    boolean exit = true;
    int score = 0;
 
    private Powerup health = new Powerup(100,100,20,20); 
     EnemyShip secondEnemy;
     
    		
    private Image backGround;
    Scene menu;
    float prevTime;
    float spawnDelay = 1000000000*2;
    float prevSpawnTime;
    int makeFasterCount = 0;
    prisoner p;
    Random rand = new Random();
   //have a notion of death etc.
    AudioManager2 aman = AudioManager2.getInstance();
    public SpaceScene(Scene menu) throws IOException, LWJGLException
    { 
    	//Initializes the audio manager etc.
    	aman.loadSample("explosion", "res/explosion.wav");
    	aman.loadSample("RedAlert", "res/tos-redalert (1).wav");
    	aman.loadSample("spock", "res/Spock_Livelong.wav");
    	//aman.play("doot");
     //get a list of bullets, ships, pretty much whatever is gonna be made
        bullets = new LinkedList<>();
        enemyShips = new LinkedList<>();
        enemyBullets = new LinkedList<>();
        //Initialize t he player ship!
        playerShip = new Shooter(bullets,target,speed);
        //make one enemy so there is always one guy on screen, ya feel me?
        enemy  = new EnemyShip(playerShip, enemyBullets,Display.getHeight()/2,100, 1,speed);
       
        this.menu = menu;
        p = new prisoner(playerShip,Display.getWidth(),rand.nextInt(Display.getHeight()), 1000);
        enemyShips.add(enemy);
        try
        {

            // load texture as png from res/ directory (this can throw IOException)
            texture = TextureLoader.getTexture("PNG",
                                               ResourceLoader.getResourceAsStream("res/Metal-Dark-Texture.png"));

            // textures come in as a power of 2.  use these ratios to
            // calculate texture offsets for sprite based on box size
            
        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.err.println("failed to load image");
            System.exit(-1);
        }
        Randomize();
        
    }
   // public Scene nextScene1()
    {

//        Menu gameMenu = new Menu();
//       // AudioManager am = new AudioManager();
//     //   gameMenu.addItem("empty", new JumperTest());
//        gameMenu.addItem("Space Trek", new SpaceScene(gameMenu));
//        gameMenu.addSpecial("Exit", Menu.DO_EXIT);
//        return gameMenu;
    }
    
    //this method does aboslutely nothing at the moment 
    public static void Randomize()
    {  
        targets   = new LinkedList<>();
       
    	int maxRange = Display.getWidth();
        int minRange = 0;
     
    int maxHeight = 200;
    int minHeight =  20;
    //RandomlyGenerate some enemies and some targets to rescue right now we are creating 40 each, yikes!
//      for(int i =1; i<10;i++)
//      {  LinkedList<Projectile> enemybullets;
//      enemyBullets = new LinkedList<>();
//    	  
 // 	  int range = maxRange-minRange;
 //       int heightDifferenceRange = maxHeight-minHeight;
//       int  currentLocation = (int) (Math.random()*range)+minRange;
//       int  currentHeight = (int) (Math.random()*heightDifferenceRange) + minHeight;
//       System.out.println(currentLocation);
//      
//        enemyShips.add(new EnemyShip(playerShip,enemyBullets));
    //  	targets.add(new Target(currentLocation,Display.getHeight()-250-currentHeight));
//      }
   }
    public void reset()
    {
    	speed = 1;
    	//spawnDelay = 1000000000*2;
        bullets = new LinkedList<>();
        enemyShips = new LinkedList<>();
        enemyBullets = new LinkedList<>();
        //Initialize t he player ship!
        playerShip = new Shooter(bullets,target,speed);
        //make one enemy so there is always one guy on screen, ya feel me?
        enemy  = new EnemyShip(playerShip, enemyBullets,Display.getHeight()/2,100, 1,speed);
      
        this.menu = menu;
        makeFasterCount = 0;
        score = 0 ;
        p = new prisoner(playerShip,Display.getWidth(),rand.nextInt(Display.getHeight()-100), 1000);
        enemyShips.add(enemy);
    }
    protected Scene nextScene() { if(exit != true)
    	
    	{return null;}
    else
    {
      return menu;
    }
    }

//ignore this variable for now, probably will be delted
    private int w=200;

    public boolean drawFrame(float delta)
    {
    	if(enemy == null)
    	{
    		if(speed>=1/6)
    		{
    			System.out.println("speed is " + speed);
    		speed = speed/2;
    		}
    		playerShip.updateSpeed(speed);
    		 enemy  = new EnemyShip(playerShip, enemyBullets,rand.nextInt(Display.getWidth()),100, 200,speed);
    		 if(speed>=1/6 && makeFasterCount<=4)
    		 {
    			 enemy.makeFaster();
    		 }
    		 enemyShips.add(enemy);
    	}
    	if(p ==null)
    	{
    		 speed = speed*2;
    		 p = new prisoner(playerShip,Display.getWidth(),rand.nextInt(Display.getHeight()), 1000); 
    		 playerShip.addBuff(.7);
    		 playerShip.addHealth();
    	}
    	// clear the screen first
    	  GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
       // GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
//ignore these key presses for now
        if (Keyboard.isKeyDown(Keyboard.KEY_A)) 
        {
            w ++;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_B)) 
        {
            w --;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_R))
        {
        	exit = true;
        }
        float currentTime = System.nanoTime();



        // draw the main screen


         
    
    
        Projectile bullet;
    

     //lets go ahead and update where the ships are
     
       enemy.update(delta);
       health.update(delta);
       
       p.update(delta);
        playerShip.update(delta);
      

        Iterator<Projectile> it= bullets.iterator();
        Iterator<Projectile> it2 = enemyBullets.iterator();
        Iterator<EnemyShip>  enemyShip = enemyShips.iterator();
        while (it.hasNext())
        {
            bullet = it.next();
            
            bullet.update(delta);
          

            if (! bullet.isActive())
            {
                System.out.println("removing inactive projectile");
                it.remove();
            }
            else 
            {
            	bullet.draw();
           	 //for (EnemyShip s  : enemyShips)
                 {                                        		 
                    if( bullet.testCollision(enemy)==true)
                    {
                    	if(bullet instanceof Projectile)
                    	{
                    		if(bullet instanceof captureLaser)
                    		{
                    			score -= 1;	
                    		}
                    		else
                    		{
                    		score +=1;
                    		}
                    	
                    	}
                    	
                    }
                 if(bullet.testCollision(p))
                 {
                		if(bullet instanceof Projectile)
                    	{
                    		if(bullet instanceof captureLaser)
                    		{
                    			score += 1;	
                    			playerShip.addHealth();
                    		}
                    		else{
                    		score -=1;
                    		}
                    	
                    	} 
                 }
                    p.testCollision(bullet);
                    enemy.testCollision(bullet);
                if (! bullet.isActive())
                {
                    System.out.println("removing inactive projectile");
                    it.remove();
                }
                
                 }
            	 
            }
        }
        
        while (it2.hasNext())
        {
            bullet = it2.next();
            
            bullet.update(delta);
          

            if (! bullet.isActive())
            {
                System.out.println("removing inactive projectile");
                it2.remove();
            }
            else 
            {
            	bullet.draw();
           	                              		 
                if(playerShip.testCollision(bullet));    
                bullet.testCollision(playerShip);
                if(bullet.testCollision(p))
                {
                	score--;
                }
                    
                if (! bullet.isActive())
                {
                    System.out.println("removing inactive projectile");
                    it2.remove();
                }
                
                 }
            	 
            
        }


//Draw the targets and the ships
     
       
 health.draw();
 if(p.isActive())
 {
 p.draw(); 
 }
 else
 {
	 
	 p = null;
	 aman.play("spock");
 }

     //   playerShip.draw();
      //  enemy.draw();
        
      //  GL11.glMatrixMode(GL11.GL_PROJECTION);
      //  GL11.glLoadIdentity();
     //   GL11.glOrtho(0, Display.getWidth(), Display.getHeight(), 0, 1, -1);
     //   GL11.glMatrixMode(GL11.GL_MODELVIEW);


        // draw the minimap
       // GL11.glViewport(Display.getWidth()-200, Display.getHeight()-200, 200, 200);
        health.draw();
       GL11.glColor3f(0,0,1);
        TrueTypeFont menuFont = new TrueTypeFont(new Font("Times New Roman", Font.BOLD, 24), true);
        menuFont.drawString(0,0,"You've Scored " + score +  " The Best Score Is " +  oldScore+ " " + "your health is " + " " + playerShip.health );
        GL11.glBegin(GL11.GL_QUADS);
        GL11.glVertex2f(0, 0);
        GL11.glVertex2f(Display.getWidth(), 0);
        GL11.glVertex2f(Display.getWidth(), Display.getHeight());
        GL11.glVertex2f(0, Display.getHeight());
        
      



        
        health.draw();
        if(enemy.isActive())
        {
        	
        enemy.draw();
        }
        else
        {
        	aman.play("explosion");
        	enemy = null;
        	//speed = speed;
        }
        
        playerShip.draw();
        GL11.glEnd();
      if(currentTime-prevSpawnTime >= spawnDelay)
      {
      	prevSpawnTime = currentTime;
      	
      	aman.play("RedAlert");
      }
        
     if(Keyboard.isKeyDown(Keyboard.KEY_R )|| playerShip.health<=0)
     {
    	 
    	 oldScore = score;
    	// oldTimeAlive = System.nanoTime()/(100000000*60);
    	 reset();
    	 return false;
     }
     else
     {
        return true;
     }
    }
}

    


