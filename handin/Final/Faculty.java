import java.util.*;

/**
 * 
 * Represents a faculty in an educational institution.
 * A faculty is associated with a name, department, and a list of module codes.
 * 
 * @author (Adam Fogarty, 22367748);(Aron Calvert, 22370374)
 * @version (3.0)

 */
public class Faculty
{
    // instance variables 
    private String name;
    private String department;
    private ArrayList<String> moduleCodes;
    //private List<courseModule> assignedModules;

    /**
     * Constructs a new Faculty with the specified name and department.
     *
     * @param name       The name of the faculty.
     * @param department The department to which the faculty belongs.
     */
    public Faculty(String name, String department) {
        this.name = new String(name);
        this.department = new String(department);
        moduleCodes = new ArrayList<String>();
        //this.assignedModules = new ArrayList<>();
    }
    
    /**
     * Retrieves the name of the faculty.
     *
     * @return The name of the faculty.
     */
    public String getName()
    {
        return name;    
    }
    
    /**
     * Retrieves the department to which the faculty belongs.
     *
     * @return The department of the faculty.
     */
    public String getDepartment()
    {
        return department;
    }

    /**
     * Adds a module to the list of module codes associated with the faculty.
     *
     * @param moduleCode The code of the module to be added.
     */
    public void addModule(String moduleCode)
    {
        moduleCodes.add(moduleCode);
    }
    
    /**
     * Retrieves the list of module codes associated with the faculty.
     *
     * @return The list of module codes.
     */
    public ArrayList<String> getModules()
    {
        return moduleCodes;
    }
    
    /**
     * Generates a CSV string representation of the faculty, including its name, department, and associated module codes.
     *
     * @return The CSV string representation of the faculty.
     */
    public String getCSVString()
    {
        String facCSV = new String();
       facCSV += name + "," + department + ",";
       for(int i = 0; i < moduleCodes.size(); i ++)
       {
           facCSV += moduleCodes.get(i);
           if(i != moduleCodes.size() - 1)
           {
               facCSV += ",";
           }
       }
       facCSV += "\n";
       return facCSV;
    }
    
    /*
    public void assignModule(courseModule module) {
        this.assignedModules.add(module);
    }
    
    public List<courseModule> getAssignedModules() {
        return assignedModules;
    }
    */
}