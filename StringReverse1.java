
/**
 * Simple Program that Reverses a String
 * 
 * @author Michael Holloway
 * @version 1.0
 */
public class StringReverse1
{
    
    public StringReverse1()
    {
        String input = "poil";
        StringBuilder sb = new StringBuilder();
        sb.append(input);
        sb.reverse();
        input = sb.toString();
        System.out.println(input);
        
    }
}
