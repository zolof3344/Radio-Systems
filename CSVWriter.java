
/**
 * Write a description of class CSVWriter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.Scanner;
import java.io.*;
import edu.utc.MichaelHolloway.LookupTable;
import edu.utc.MichaelHolloway.DataProvider;
import edu.utc.MichaelHolloway.DataProcessor;
import edu.utc.MichaelHolloway.DataDumper;
import java.util.Scanner;
import java.util.ArrayList;
public class CSVWriter implements DataProcessor 
{   
   
 
   
   LookupTable lt=new LookupTable();
   PrintWriter printer=null;
   PrintWriter poop=null;
   /**
    * Constructs the CSV writer to print to a csv file
    */
     public CSVWriter() throws FileNotFoundException
     {
       printer=new PrintWriter("CSVFile.txt");
       poop=new PrintWriter("ka.txt");
     }
     /**
      * Processes the data passed to it, the data being a lookup table it prints out all the data through a loop
      * @param lt the process method takes in a lookupTable 
      */
     public void process(LookupTable lt) 
     { 
       ArrayList<String> strings=lt.getKeys();
       printer.println(strings.get(0)+","+lt.get(strings.get(0)));
       printer.println(strings.get(1)+","+lt.get(strings.get(1)));
         
      }
    /**
     * Closes the print writer
     */
     public void finish()
     { printer.close();
       poop.close();
     }
   }