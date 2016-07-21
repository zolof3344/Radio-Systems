
/**
 * Simply Creates an employee and prints out its name salary, bonus * 
 * @author Michael Holloway
 * @version 1.0 */
//This imports the commands needed for a scanner ect.
import java.util.Scanner;
import java.util.ArrayList;
public class EmployeeTesterClass
{
 public static void main(String[] args)
 { //This creates and ArrayList of type EmployeeClass
   ArrayList<EmployeeClass> Employee=new ArrayList<EmployeeClass>();
   for(int i=0;i<5;i++)
   {
       //This asks the user for an Employee Name and Salary creates a scanner to read th
       //e Input and adds an Employee to each EmployeeClass
     System.out.println("Please enter Your Employees Salary then Name");
   Scanner in=new Scanner(System.in);
   Scanner inn=new Scanner(System.in);
   double input=in.nextDouble();
   String input2=inn.nextLine();  
   EmployeeClass Employee1= new EmployeeClass(input2,input);
   Employee.add(i,Employee1);
   }
   for(int i=0;i<5;i++)
   {System.out.println("The Employee's name is "+ Employee.get(i).getName());
    System.out.println("The Employee's Salary is "+ Employee.get(i).getSalary());
    System.out.println("The Employee's Bonus is "+ Employee.get(i).getBonus());
    }
}
}