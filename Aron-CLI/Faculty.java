import java.util.*;

/**
 * 
 * View prog and module assigned to them
 * View list of students assigned to each module
 * Assign grades to individual students
 * 
 */
public class Faculty
{
    // instance variables - replace the example below with your own
    private String name;
    private ArrayList<String> moduleCodes;
    private String department;

    /**
     * Constructor for objects of class Faculty
     */
    public Faculty(String name, String department)
    {
        this.name = new String(name);
        this.department = new String(department);
        moduleCodes = new ArrayList<String>();
        
    }
    
    public String getName()
    {
        return name;    
    }
    
    public String getDepartment()
    {
        return department;
    }

    public void addModule(String moduleCode)
    {
        moduleCodes.add(moduleCode);
    }
    
    public ArrayList<String> getModules()
    {
        return moduleCodes;
    }
    
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
}