//All part of a game I made, can be run using MainGame
// Michael Holloway Version 1 
import org.lwjgl.input.Keyboard;
public  class InputHandler {
	
	public InputHandler()
	{

	}
	public static boolean isUpPressed()
	{
		 if(Keyboard.isKeyDown(Keyboard.KEY_UP))
		 {
			 return true;
		 }

		 else
		 {
			 return false;
		 }
	}
	public static boolean isDownPressed()
	{
		 if(Keyboard.isKeyDown(Keyboard.KEY_DOWN))
		 {
			 return true;
		 }

		 else
		 {
			 return false;
		 }
	}
	public static boolean isUpLeftPressed()
	{
		 if(Keyboard.isKeyDown(Keyboard.KEY_LEFT))
		 {
			 return true;
		 }

		 else
		 {
			 return false;
		 }
	}
	public static boolean isRightPressed()
	{
		 if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT))
		 {
			 return true;
		 }

		 else
		 {
			 return false;
		 }
	}
	public static boolean isFPressed()
	{
		 if(Keyboard.isKeyDown(Keyboard.KEY_F))
		 {
			 return true;
		 }

		 else
		 {
			 return false;
		 }
	}
	public static boolean isSpacePresssed()
	{
		 if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
		 {
			 return true;
		 }

		 else
		 {
			 return false;
		 }
	}
	public static boolean isAPressed()
	{
		 if(Keyboard.isKeyDown(Keyboard.KEY_A))
		 {
			 return true;
		 }

		 else
		 {
			 return false;
		 }
	}
	

}
