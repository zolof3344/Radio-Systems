// Michael Holloway Version 1 
//All part of a game I made, can be run using MainGame
import java.util.List;
import java.util.LinkedList;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Rectangle;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import java.awt.Font;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;
import org.newdawn.slick.Color;
import java.io.IOException;

public class Menu extends Scene {


    // a menu item: label and associated Scene to jump to
	AudioManager2 aman = AudioManager2.getInstance();
	 
	float width_ratio;
	float height_ratio;
	Rectangle hitbox;
	Texture texture;
    private static class Item
    {
        public String label;
        public Scene  scene;

        public Item(String label, Scene s)
        {
            this.label = label;
            this.scene = s;
        }

    }

    public static final int DO_EXIT=0;

    private static class Special extends Item
    {
        public int tag;

        public Special(String label, int tag)
        {
            super(label, null);
            this.tag = tag;
        }
    }


    // these menu items
    private List<Item> items;

    // currently selected items
    private int currItem;


    public Menu() throws IOException, LWJGLException
    {
    	aman.loadSample("doot", "res/Opening_Remastered.wav");
    	aman.loadSample("menu", "res/tos-turboliftdoor.wav");
    	aman.getInstance().play("doot");
        items = new LinkedList<>();

        try 
        {
            AudioManager.getInstance().loadSample("menuSelect", "res/ChillingMusic.wav");
            AudioManager.getInstance().loadSample("menuChoose", "res/cello.wav");
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        // reset menu
    }

    public void clear()
    {
        items.clear();
    }

    public void addItem(String label, Scene s)
    {
        items.add(new Item(label,s));
    }
    
    public void addSpecial(String label, int tag)
    {
        items.add(new Special(label, tag));
    }


    public Scene nextScene()
    {
        return items.get(currItem).scene;
    }


    public boolean drawFrame(float delta)
    {
    	

        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

        // process keyboard input

        while (Keyboard.next())
        {
            if (Keyboard.getEventKeyState()) // key was pressed
            {
                switch (Keyboard.getEventKey())
                {
                 case Keyboard.KEY_DOWN:
                     currItem = (currItem + 1) % items.size();
                     aman.play("menu");
                     AudioManager.getInstance().play("menuSelect");
                     

                     break;

                 case Keyboard.KEY_UP:

                     currItem--;

                     if (currItem < 0)
                     {
                         currItem += items.size(); // go to end
                     }
                     
                 	aman.play("menu");
                    

                     break;

                 case Keyboard.KEY_RETURN:

                     // TODO: play sound
                	
                     Item item = items.get(currItem);

                     if (item instanceof Special)
                     {
                         switch (((Special)item).tag)
                         {
                          case DO_EXIT: exit();
                              break;
                         }

                     }

                     AudioManager.getInstance().play("menuChoose");

                     return false;

                }
            }
        }



        // draw menu, highlighting currItem
        try
        {
             
            // load texture as png from res/ directory (this can throw IOException)
            texture = TextureLoader.getTexture("PNG",
                                               ResourceLoader.getResourceAsStream("res/starfleet1.png"));

            // textures come in as a power of 2.  use these ratios to
            // calculate texture offsets for sprite based on box size
            width_ratio = (1.0f)*texture.getImageWidth() / texture.getTextureWidth();
            height_ratio = (1.0f)*texture.getImageHeight() / texture.getTextureHeight();

            // create a Rectangle at the origin where height is calculated from
            // texture aspect ratio
            hitbox = new Rectangle(0, 0,
                                Display.getWidth(), 
                                (int)(Display.getWidth() * (float)texture.getImageHeight()/texture.getImageWidth()));
        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.err.println("failed to load image");
            System.exit(-1);
        }
//        GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
//        GL11.glColor3f(1,1,0);
//        GL11.glBegin(GL11.GL_QUADS);
//        GL11.glVertex2f(0, 0);
//        GL11.glVertex2f(Display.getWidth(), 0);
//        GL11.glVertex2f(Display.getWidth(), Display.getHeight());
//        GL11.glVertex2f(0, Display.getHeight());
//        
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
        // GL11.glColor3f(1,1,1); // interacts with color3f
        GL11.glColor3f(1,1, 1); // interacts with color3f

        GL11.glBegin(GL11.GL_QUADS);

      int w = Display.getWidth();
      int h = Display.getHeight();
        // top-left of texture tied to top-left of box        
        GL11.glTexCoord2f(0,0);
        GL11.glVertex2f(0,0);
        
        // top-right 
        GL11.glTexCoord2f(width_ratio,0);
        GL11.glVertex2f(0+w, 0);

        // bottom-right
        GL11.glTexCoord2f(width_ratio, height_ratio); 
        GL11.glVertex2f(0+w,0+h);

        // bottom-left
        GL11.glTexCoord2f(0,height_ratio);
        GL11.glVertex2f(0,0+h);
        
    GL11.glEnd();

        float spacing = Display.getHeight()/(items.size() + 4);
        float offset = 2*spacing;

        TrueTypeFont menuFont = new TrueTypeFont(new Font("Times New Roman", Font.BOLD, 24), true);

        for (int i=0; i<items.size(); i++)
        {
            if (i == currItem)
            {
                menuFont.drawString(Display.getWidth()/2, offset, items.get(i).label, Color.red);
            }
            else
            {
                menuFont.drawString(Display.getWidth()/2, offset, items.get(i).label);
            }
            

            offset += spacing;
        }
       //menuFont.drawString(Display.getWidth()/2, offset, "the previous Score was + " , Color.purple);
        // font binds a texture, so let's turn it off..
      
       
   

        return true;
    }
 
}


