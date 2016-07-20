package edu.utc.MichaelHolloway;
/**
 * Write a description of class LookupTable here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
/**
 * LookupTable class This class is used to create objects of type LookupTable providing the user with a way to store pairs and lookup.
 *  keys and their associated values, and methods to get those keys
 * @author (Michael Holloway) 
 * @version (1/28/13)
 */

public class LookupTable
{
    /**
     * The variable items is an arraylist of items of type StringPair this type holds a pair of Strings. 
     */
    ArrayList<StringPair> items=new ArrayList<StringPair>();
    ArrayList<String> getKeysString=new ArrayList<String>();
    public LookupTable()
    {
    }

    /**
     * Sets a lookup table associated value and key
     * Use (@link LookupTable() to create a new LookupTable)
     * @param key String that is being used as the first value or "key" to the second value
     * @param associatedValue Value that is associated with the key value entered. The pair stays together
     * @return                 The method returns nothing
     */
    public void set(String key,String associatedValue)
    {
        for(StringPair p:items)

            {
                
            if(p.getKey().equals(key))
            {p.setValue(associatedValue);
                return;
            }
           
            
        }
        items.add(new StringPair(key,associatedValue));
        
    }

    /**
     * This get method gets a string's associated value by taking the key as input.
     *
     *@param key The key of who's associated value the user would like to recieve as output.
     *@return returns the keys associated value, or null if there is none. WARNING this method is case sensitive. 
     */
    public String get(String key)
    {
        for(StringPair p:items)

            if(p.getKey().equals(key))
            {
                return p.getAssociated();

           }
        return null;
    }

    /**
     * This method prints all keys and their associate values
     * 
     * 
     * @return This method returns all the keys and their associat values or if there is none it returns null. 
     */
    public String printAll()
    {
        for(StringPair p:items)
        {
            System.out.println(p.getKey() +"-->" + p.getAssociated());
        }
        return " ";
    }
    /** 
     * This Method Returns the keys in Array form for the entire Lookup Table\
     * @return This method returns all the keys in ArrayList form.
     */
    public ArrayList<String> getKeys()
    {   ArrayList<String> getKeysString=new ArrayList<String>();
        for(StringPair p:items)
        {
            getKeysString.add(p.getKey());
        }
        return getKeysString;
    }
    
        
    private class StringPair
    {   
        /**
         * The variable Item holds an item which the StringPair constructor will take as input for the "key".
         * The Variable associatedItem1 holds the associatedValue that goes with the Item variable. 
         */
        private String item=null;
        private String associatedItem1=null;
        public StringPair(String key, String value)
        {
            item=key;
            associatedItem1=value;
        }

        /** 
         * Gets a key 
         * 
         * @return This method returns the first item or "key" accepted in the constructor StringPair.
         */
        public String getKey()
        {
            return item;
        }

        /**
         * Returns the associated value
         * 
         * @return This method returns the second item or the "associated value" accepted in the constructor StringPair.
         */
        public String getAssociated()
        {
            return associatedItem1;
        }

        /** 
         * Sets the value of the associatedItem
         * @param value is the value to which the user wishes to set the associatedItem.
         * @return This method returns nothing.
         */
        public void setValue(String value)
        {
            associatedItem1=value;
        }
    }
}
