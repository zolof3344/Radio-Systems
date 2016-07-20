
/**
 * Write a description of class Data here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.utc.MichaelHolloway.LookupTable;
import edu.utc.MichaelHolloway.DataProvider;
import edu.utc.MichaelHolloway.DataProcessor;
import edu.utc.MichaelHolloway.DataDumper;
import java.util.Scanner;
public class Data 
{   
     /**
      * This method takes in a Data provider and processor and runs the necassary methods to make
      * them provide and process the data properly.
      * @param in: This paramater is the DataProvider which the method takes
      * @param out: This paramater is the DataProcessor which the method takes
      * @return This method does not return anything.
      */ 
    public static void run(DataProvider in,DataProcessor out)
    {  
        while(in.hasData())
        {   
            out.process(in.nextData());
            
        }
        out.finish();
    }
}