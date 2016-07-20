
/**
 * Write a description of class SwingPlotter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import javax.swing.JComponent;
import edu.utc.MichaelHolloway.LookupTable;
import edu.utc.MichaelHolloway.DataProvider;
import edu.utc.MichaelHolloway.DataProcessor;
import edu.utc.MichaelHolloway.DataDumper;
import java.util.ArrayList;
import java.awt.geom.Line2D;
import java.awt.Rectangle; 
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
public class SwingPlotter extends JComponent implements DataProcessor 
{
    // extents
    private double minx, maxx, miny, maxy;
    ArrayList<Point> coords;
    // inner class to keep x,y together

    private class Point
    {
        /**
         * initializes the point 
         * @param x takes an x value corresponding to the point 
         * @param y takes a y value corresponding to the point
         */
        public Point(double x, double y)
        {
            this.x=x;
            this.y=y;
        }
        public double x;
        public double y;
        /**
         * gets the x value from the point
         * @return returns a double the x value
         */
        public double getX()
        {
            return x;
        }
          /**
         * gets the y value from the point
         * @return returns a double the y value
         */
        public double getY()
        {return y;
        }
         
        public String toString()
        { String string=""+x+":"+y;
            return string;
        }
    }
    // constructor
    SwingPlotter()
    {
        coords = new ArrayList<Point>();
    }

    // pull the x,y out of lt, turning Strings into doubles
    /**
     * Processes the lookup table it gets, storing the x and y coordinates into
     * @param lt this is a lookupTable which it takes those values from.
     */
    public void process(LookupTable lt)
    { ArrayList<String> p=lt.getKeys();
        double x=0;
        double y=0;
        double x1=0;
        double y1=0;
        Point first=new Point(x,y);

        x=Double.parseDouble(lt.get(p.get(0)));
        y=Double.parseDouble(lt.get(p.get(1)));
        first=new Point(x,y);
        coords.add(first);
        System.out.println(first);
    }
    /**
     * finishes the method getting the largest x, and y coordinates
     */
    public void finish()
    {  //Sets up points for the largest X and Y
        double largestX=0;
        double largestY=0;
        double smallestX=0;
        double smallestY=0;
        //This loop loops through all the points in the coords ArrayList
        //Getting the x and y as it goes
        for(int i=0;i<coords.size()-1;i++)
        {
            if(i==0)
            { largestX=coords.get(i).getX();
            }

            else if(largestX<coords.get(i).getX())
            {largestX=coords.get(i).getX();
            }
            if(i==0)
            {largestY=coords.get(i).getY();
            }
            else if(largestY<coords.get(i).getY())
            {largestY=coords.get(i).getY();
            }
            if(i==0)
            { smallestX=coords.get(i).getX();
            }

            else if(smallestX>coords.get(i).getX())
            {smallestX=coords.get(i).getX();
            }
            if(i==0)
            {smallestY=coords.get(i).getY();
            }
            else if(smallestY>coords.get(i).getY())
            {smallestY=coords.get(i).getY();
            }

        }

        miny=smallestY;
        minx=smallestX;
        maxx=largestX;
        maxy=largestY;
        System.out.println(miny+":"+minx+":"+maxx+":"+maxy+":");
        Point largest=new Point(largestX,largestY);
        repaint();
    }
    /**
     * paints the component
     * @param takes in the graphics which it will print out
     */
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        Rectangle rect = g2.getClipBounds();
        Graphics2D g3 = (Graphics2D) g;
        java.awt.Rectangle rect2 = g3.getClipBounds();
        Line2D.Double seg = new Line2D.Double(0, 0, rect.width, rect.height);

        for(int i=0; i<coords.size()-1;i++)
        {
            double x=((coords.get(i).x-minx)/(maxx-minx))*rect.width;
            double y=(rect.height-((coords.get(i).y-miny)/(maxy-miny))*rect.height);
            double x2=((coords.get(i+1).x-minx)/(maxx-minx))*rect.width;
            double y2=(rect.height-((coords.get(i+1).y-miny)/(maxy-miny))*rect.height);
            Line2D.Double segg = new Line2D.Double(x,y,x2,y2);
            g2.draw(segg);
        }
    }

    // iterate over consecutive points

}

