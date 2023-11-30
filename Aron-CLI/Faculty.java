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
    private String department;
    private List<courseModule> assignedModules;

    public Faculty(String name, String department) {
        this.name = name;
        this.department = department;
        this.assignedModules = new ArrayList<>();
    }
    
    public String getName()
    {
        return name;    
    }
    
    public String getDepartment()
    {
        return department;
    }

    //public void addModule(String moduleCode)
    //{
    //    moduleCodes.add(moduleCode);
    //}
    
    //public ArrayList<String> getModules()
    //{
    //    return moduleCodes;
    //}
    
    // /public String getCSVString()
    //{
    //    String facCSV = new String();
     //   facCSV += name + "," + department + ",";
     //   for(int i = 0; i < moduleCodes.size(); i ++)
     //   {
     //       facCSV += moduleCodes.get(i);
     //       if(i != moduleCodes.size() - 1)
     //       {
     //           facCSV += ",";
     //       }
     //   }
    //    facCSV += "\n";
    //    return facCSV;
    //}
    
    public void assignModule(courseModule module) {
        this.assignedModules.add(module);
    }

    public List<courseModule> getAssignedModules() {
        return assignedModules;
    }
}