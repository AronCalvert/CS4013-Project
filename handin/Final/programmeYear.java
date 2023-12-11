import java.util.ArrayList;


/**
 * The programmeYear class represents a specific academic year in a program.
 * It contains two semesters, each consisting of graded course modules.
 * 
 * 
 * @author (Adam Fogarty, 22367748);(Paudie Kelly, 22342842);(Ella Curtin, 22363564);(Aron Calvert, 22370374)
 * @version (1.0)
 */
public class programmeYear
{
    public ArrayList<gradedCourseModule> sem1;
    public ArrayList<gradedCourseModule> sem2;
    
    /**
     * Default constructor for objects of class programmeYear.
     * Initializes the lists for the first and second semesters.
     */
    public programmeYear()
    {
        sem1 = new ArrayList<gradedCourseModule>();
        sem2 = new ArrayList<gradedCourseModule>();
        
    }
}