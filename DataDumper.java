package edu.utc.MichaelHolloway;
/**
 * Write a description of class DataDumper here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DataDumper implements DataProcessor
{ 
  public void process(LookupTable lt)
  {lt.printAll();
  }    
  public void finish()
  {
  }
}
