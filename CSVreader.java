/**
 * A reader for comma seperated values
 * 
 * @author Michael Holloway 
 * @version 1.0
 */
import java.util.Scanner;
import java.io.*;
import edu.utc.MichaelHolloway.LookupTable;
import edu.utc.MichaelHolloway.DataProvider;
import edu.utc.MichaelHolloway.DataProcessor;
import edu.utc.MichaelHolloway.DataDumper;
import java.util.Scanner;
import java.util.ArrayList;
public class CSVreader implements DataProvider 
{ File file=null;
  Scanner input=null;
    /**
     * This is the constructor for the CSV Reader
     * @param it takes a file from which it will read
     */
    public CSVreader(File f) throws IOException
    {file=f;
     ;
     input=new Scanner(file);
    }

    /**
     * This method gives out the next data in line
     * @return it returns a lookupTable with the next data in line.
     */
    public LookupTable nextData()
    {   int x=0;
        LookupTable lt2=new LookupTable();
         
        while(input.hasNextLine())
        { Scanner lineScanner=new Scanner(input.nextLine());
           lineScanner.useDelimiter(",");
            while(lineScanner.hasNextLine())
            {    
                lt2.set("Column:"+x,lineScanner.next());
                x=x+1;
             }
            }
            x=0;
        
       
        return lt2;
    }

    /**
     * This method checks to see if the given mathSampler has data to pass
     * @return this method returns a boolean indicating if it has data or not.
     */
    public boolean hasData()
    {if(input.hasNextLine())
        { return true;
        }
        else
        {
        return false;
        }
    }
}
