// Michael Holloway Version 1 based on code by Dr. Craig Tanis, UTC
//All part of a game I made, can be run using MainGame
import java.io.IOException;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Rectangle;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Platform extends Entity {
    private Texture texture;
    private float width_ratio;
    private float height_ratio;
    public Platform(int x, int y, int width, int height)
    {
        super(x,y,width,height);
        try
        {
        	//System.out.println("x is " + x + "y is " + y);
            // load texture as png from res/ directory (this can throw IOException)
            texture = TextureLoader.getTexture("PNG",
                                               ResourceLoader.getResourceAsStream("res/Metal-Dark-Texture.png"));

            // textures come in as a power of 2.  use these ratios to
            // calculate texture offsets for sprite based on box size
            width_ratio = (1.0f)*texture.getImageWidth() / texture.getTextureWidth();
            height_ratio = (1.0f)*texture.getImageHeight() / texture.getTextureHeight();

            // create a Rectangle at the origin where height is calculated from
            // texture aspect ratio
            hitbox = new Rectangle(hitbox.getX(), hitbox.getY(),
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
    float r=1;
    float b=1;
    float g=1;
    public void setColor(float r, float b, float g)
    {
    	this.r=r;
    	this.b=b;
    	this.g=g;
    }
    public void draw() {
//
//        int x = hitbox.getX();
//        int y = hitbox.getY();
//        int w = hitbox.getWidth();
//        int h = hitbox.getHeight();
//        
//        GL11.glColor3f(r,b,g);
//        GL11.glBegin(GL11.GL_QUADS);
//
//        GL11.glVertex2f(x,y);
//        GL11.glVertex2f(x+w,y);
//        GL11.glVertex2f(x+w,y+h);
//        GL11.glVertex2f(x,y+h);
//
//        GL11.glEnd();            
    	float x = (float)hitbox.getX();
        float y = (float)hitbox.getY();
        float w = (float)hitbox.getWidth();
        float h = (float)hitbox.getHeight();
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
        // GL11.glColor3f(1,1,1); // interacts with color3f
    //    GL11.glColor3f(1,1, 1); // interacts with color3f

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


