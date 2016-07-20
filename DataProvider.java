package edu.utc.MichaelHolloway;
/**
 * Write a description of interface DataProvider here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public interface DataProvider
{
    public boolean hasData();
    public LookupTable nextData();
    
}
