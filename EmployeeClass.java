
/**
 * Write a description of class EmployeeClass here.
 * 
 * @author Michael Holloway * @version 1.0 */
public class EmployeeClass
{
//This creates the instance variables used in the constructor
String name="null";
double salary=0;
//This is a constructor used for taking input
public EmployeeClass(String name1, double salary1)
{
 name=name1;
 salary=salary1;
}
//This is a method that gets the name of the employee
public String getName()
{
return name;
}
//This gets the salary of the employee
public double getSalary()
{
return salary;
}
//This gets the bonus of the employee
public double getBonus()
{
double bonus=salary*.10;
return bonus;
}
}