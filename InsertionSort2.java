
/**
 * Short Insertion Sort Implementation
 * 
 * @author Michael Holloway
 * @version 1.0
 */
public class InsertionSort2
{
   int i =0 ;
   int temp =0;
   int[] array = {1,2,3,4,23,4,545,67};
   public InsertionSort2()
   {
    for(i=0; i<array.length;i++)
    { int j = i-1;
        temp = array[i];
        for(j=i-1; j>=0 && array[j] > temp; j--)
        {
            array[j + 1] = array[j];
        }
        array[j+1] = temp;
    }
    for(i=0; i<array.length;i++)
    {
        System.out.println(array[i]);
    }
}
}
