import java.util.*;

/**
 * Represents Faculty member
 * View prog and module assigned to them
 * View list of students assigned to each module
 * Assign grades to individual students
 * 
 */
public class Faculty
{
    
    private String name;
    private List<courseModule> assignedModules;
    private String department;

    /*
     * Constructor for objects of class Faculty
     * @param name  name of faculty member
     * @param department    that faculty member belongs to
     */
    public Faculty(String name, String department)
    {
        this.name = name;
        this.department = department;
        this.assignedModules = new ArrayList<>();
        
    }
    
    /*
     * Gets the name of the faculty member
     * @return name  
     */
    public String getName()
    {
        return name;    
    }
    
    /*
     * Gets the department of the faculty member
     * @return department 
     */
    public String getDepartment()
    {
        return department;
    }

    public void assignModules(courseModule module){
        this.assignedModules.add(module);
    }
    
    public List<courseModule> getAssignedModules(){
        return assignedModules;
    }
    
    
    
   
}
