import edu.utc.MichaelHolloway.LookupTable;
import edu.utc.MichaelHolloway.DataProvider;
import edu.utc.MichaelHolloway.DataProcessor;
import edu.utc.MichaelHolloway.DataDumper;
import java.util.Scanner;
/**
 * Write a description of class MathSampler here.
 * 
 * @author (Michael Holloway) 
 * @version (3/4/2013)
 */

public class MathSampler implements DataProvider
{  
    int numberofTimes=0;
    Function function;
    double startPoint;
    double endPoint;
    double x;
    double deltaX=0;
    int Times=0;
    /**
     * This is the math sampler constructo
     * @param startPoint1 Is the startpoint of the given mathmatical function
     * @param endPoint1   Is the endpoint of the given mathmatical function
     * @param numberofTimes1 is the number of points the user wants it to generate
     * @param J is the function user wants it to use
     */
    public MathSampler(double startPoint1, double endPoint1, int numberofTimes1, Function J)
    {
        function=J;
        startPoint=startPoint1;
        endPoint=endPoint1;
        numberofTimes=numberofTimes1;
        deltaX=(endPoint-startPoint)/(numberofTimes);
        Times=numberofTimes;
    }

    
    /**
     * This method gives out the next data in line
     * @param There are no paramaters
     * @return this method returns a lookup table of the values.
     */
    public LookupTable nextData()
    {   //So each point will have different keys
        int xVal=0;
        int yVal=0;
        LookupTable lt=new LookupTable();

        if(Times==numberofTimes)
        {
            x=startPoint;
        }
        else
        {
        x=x+deltaX;
        }
        Times=Times-1;
        lt.set("x", x+"");
        lt.set("f(x)",function.f(x));
        
        return lt;
    }   

    /**
     * This method checks to see if the given mathSampler has data to pass
     * @return this method returns a boolean indicating if it has data or not.
     */
    public boolean hasData()
    {if(Times>=0)
        {return true;
        }
        return false;
    }

}


