package edu.utc.MichaelHolloway;
/**
 * Interface for something that sends data
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface DataProvider
{
    public boolean hasData();
    public LookupTable nextData();
    
}
