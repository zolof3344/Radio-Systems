//All part of a game I made, can be run using MainGame
// Michael Holloway Version 1 based on code by Dr. Craig Tanis, UTC
import org.lwjgl.util.Rectangle;




public abstract class Entity {

    protected Rectangle hitbox;
    private boolean active;

    public Entity() {
        hitbox = new Rectangle(); // empty rectangle
        active = true;
    }

    public Entity(int x, int y, int w, int h) {
        hitbox = new Rectangle(x,y,w,h); // non-empty rectangle
        active = true;
    }

    public void init()
    {
    }

    public void destroy()
    {
    }

    public void update(float delta)
    {
    }

    public void draw()
    {
    }

    public boolean intersects(Entity other)
    {
        return hitbox.intersects(other.hitbox);
    }

    public Rectangle intersection(Entity other)
    {
        Rectangle rval = new Rectangle();
        return hitbox.intersection(other.hitbox, rval);
    }
   public float getX()
   {
	   return hitbox.getX();
   }
  public float getY()
  {
	  return hitbox.getY();
  }
  public int getWidth()
  {
	  return hitbox.getWidth();
  }
    public boolean testCollision(Entity other)
    {
        if (hitbox.intersects(other.hitbox)) 
        {
            onCollision(other);
            return true;
        }
        else 
        {
            return false;
        }
    }

    public void onCollision(Entity other)
    {
    }
    public int getDamage()
    {
    	return 100;
    }

    public boolean isActive()
    {
        return active;
    }

    protected void deactivate()
    {
        active = false;
    }
    public void drawAt(float x, float y, float dx, float dy)
    {
        
    }

	public void setColor(int yellow) {
		// TODO Auto-generated method stub
		
	}
}

