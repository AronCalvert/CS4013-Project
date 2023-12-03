import java.util.ArrayList;


/**
 * Class to represent the year of a programme
 * There is 2 semesters in a year
 */
public class programmeYear
{
    public ArrayList<courseModule> sem1;
    public ArrayList<courseModule> sem2;
    
    /*
     * Constructors to create programmeYear object with two semsters
     */
    public programmeYear()
    {
        sem1 = new ArrayList<courseModule>();
        sem2 = new ArrayList<courseModule>();
        
    }
}