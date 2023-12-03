import java.util.List;
import java.util.ArrayList;

/**
 * Class to represent a module object of a course
 */
public class courseModule
{
    private String moduleCode;
    private String moduleName;
    private int moduleCredits;
    private List<Faculty> assignedFaculties;

    /*
     * Constructor for objects of class courseModule
     * @param moduleCode
     * @param moduleName
     * @param moduleCredits
     * @param faculties
     */
    public courseModule(String moduleCode, String moduleName, int moduleCredits, List<Faculty> faculties)
    {
        this.moduleCode = new String(moduleCode);
        this.moduleName = new String(moduleName);
        this.moduleCredits = moduleCredits;
        this.assignedFaculties = new ArrayList<>(faculties);
    }
    
    /*
     * Gets module code
     * @return moduleCode
     */
    public String getModuleCode()
    {
        return moduleCode;
    }
    
    /*
     * Gets module name
     * @return moduleName
     */
    public String getModuleName()
    {
        return moduleName;
    }
    
    /*
     * Gets module credits
     * @return moduleCredits
     */
    public int getModuleCredits()
    {
        return moduleCredits;
    }
    
    /*
     * Sets module code
     * @param moduleCode
     */
    public void setModuleCode(String moduleCode)
    {
        this.moduleCode = new String(moduleCode);
    }
    
    /*
     * Sets module name
     * @param moduleName
     */
    public void setModuleName(String moduleName)
    {
        this.moduleName = new String(moduleName);
    }
    
    /*
     * Sets module credits
     * @param moduleCredits
     */
    public void setModuleCredits(int moduleCredits)
    {
        this.moduleCredits = moduleCredits;
    }
    
    /*
     * Gets the module CSV fle
     * @return CSV
     */
    public String getCourseModuleCSV()
    {
        String credits = String.format("%d",moduleCredits);
        String CSV = moduleCode + "," + moduleName + "," + credits;
        
        return CSV;
    }

    /*
     * Gets the assigned faculties
     * @return assignedFaculties
     */
    public List<Faculty> getAssignedFaculties() {
        return assignedFaculties;
    }
    
    /*
     * Sets assigned faculties
     * @param faculties
     */
     public void setAssignedFaculties(List<Faculty> faculties) {
        this.assignedFaculties = faculties;
    }
}