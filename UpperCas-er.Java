/**
 * Write a description of class UpperCaser here.
 * 
 * @author Michael Holloway 
 * @version 1.0
 */
import javax.swing.JFileChooser;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
public class UpperCaser

{ 

    public static void makeUpperCase(Scanner s,PrintWriter p)
    {  String stuff="hello";
        String upperCase="hello";
        while(s.hasNextLine())
        { stuff=s.nextLine();
            upperCase=stuff.toUpperCase();
            p.println(upperCase);
        }

        s.close();
    }

  
    public static void main(String[] args) 
    {   
    try {
            File inFile=null;
            JFileChooser chooser= new JFileChooser();
            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            { 
                inFile=chooser.getSelectedFile();
            }
            //in file is either a valid file or not
            if(inFile==null)
            {
                System.out.println("no file selected");
                return;
            }
            Scanner in=new Scanner(inFile);
            //Must specify to open this text file with notepad.
            File UpperCaseFile=new File(inFile + "-UPPERCASE");
            PrintWriter p=new PrintWriter(UpperCaseFile);  

            UpperCaser.makeUpperCase(in,p);
            p.close();

        }
        catch(FileNotFoundException e)
        { System.out.println("The file you are looking for could not be found, it may have been deleted");
        }
    }
}

