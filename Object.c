/* One possible approach to building objects (data + methods) in C using macros.
   Michael Holloway Based on object.c by Dr. Tanis
   2/11/16
*/


#include <stdio.h>
#include <stdlib.h>


/* -- the `Student' class -- order of these parts matters */

/* definition of struct with instance data and methods
   no overloading
   no inheritance
 */
typedef struct student
{
    int age;
    char* name;
    
   // void (*setValue)(struct student*,int);
  //  int (*getValue)(struct student*);
    void (*printName)(struct student*); //print out the name of the student
    void (*setName)(struct student*);   // Set the name of teh studnet

} student;

void student_setName(student* this, char* name)
{
    this->name = name; //just set name from the student equal to whatever name is entered.
}
void student_printName(student* this)
{  

   printf("%s\n",this->name);
  // free(this->name); This was the source of that error!
    
}

/* a method definition */
void student_construct(student* this,char* name, int age)
{
   this->printName = student_printName;
   this->name = (char*)malloc(1000*sizeof(char)); //free some memory for the name
   this->name = name; //Set the name from the student equal to the name of the construct
   
   this->age = age;
   //this->setName = student_setName; Not actually  going to use the SetName since it isn't required
}
/* -- end `Student' class */


/* generic object construction and manipulation macros */
#define NEW(class, obj, ...) class obj; class ## _construct(&obj, ## __VA_ARGS__)
#define METHOD(this, method, ...) this.method(&this, ## __VA_ARGS__)


int main(int argc, char *argv[])
{
    /* this macro expansion declares struct myStydebt in the current scope, and
     * calls the constructor associated with the class */

    NEW(student,myStudent,"michael",20);


    /* see, it's a real data structure with a size... */
    printf("size: %lu\n", sizeof(myStudent));





    //use the print Name method
    METHOD(myStudent,printName);
  

    return 0;
}
