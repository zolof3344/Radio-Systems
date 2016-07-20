
/**
 * A way to implement sine in order to get the y values for a given x
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Sin implements Function
    {  
          /**
           * Returns the y value of the function
           * @param x the x value that you want the corresponding y to
           * @return  returns the y value of the function.
           */
        public String f(double x)
        {   String stringX=Math.sin(x)+"";
            return stringX;
        }
    
    
    }