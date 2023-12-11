import java.util.ArrayList;
import java.util.List;

/**
 * The courseModule class represents a course module with informationsuch as module code, module name, and module credits.
 * 
 * Instances of this class can be used to store and retrieve information about individual course modules.
 * 
 *
 * @author (Adam Fogarty, 22367748);(Paudie Kelly, 22342842);(Ella Curtin, 22363564);(Aron Calvert, 22370374)
 * @version (3.0)
 */
public class courseModule
{
    // instance variables - replace the example below with your own
    private String moduleCode;
    private String moduleName;
    private int moduleCredits;
    //private List<Faculty> assignedFaculties;

    /**
     * Default constructor for objects of class courseModule.
     */
    public courseModule()
    {
        
    }

    /**
     * Parameterized constructor for objects of class courseModule.
     * 
     * @param moduleCode    The code of the course module.
     * @param moduleName    The name of the course module.
     * @param moduleCredits The credit hours associated with the course module.
     */
    public courseModule(String moduleCode, String moduleName, int moduleCredits)
    {
        this.moduleCode = new String(moduleCode);
        this.moduleName = new String(moduleName);
        this.moduleCredits = moduleCredits;
    }
    
    /*
     *removed by Adam, not needed in class.
        public courseModule(String moduleCode, String moduleName, int moduleCredits, List<Faculty> faculties) {
            this.moduleCode = new String(moduleCode);
            this.moduleName = new String(moduleName);
            this.moduleCredits = moduleCredits;
            this.assignedFaculties = new ArrayList<>(faculties);
        }
    */
    
    /**
     * Gets the module code.
     * 
     * @return The module code.
     */
    public String getModuleCode()
    {
        return moduleCode;
    }
    
    /**
     * Gets the module name.
     * 
     * @return The module name.
     */
    public String getModuleName()
    {
        return moduleName;
    }
    
    /**
     * Gets the module credits.
     * 
     * @return The module credits.
     */
    public int getModuleCredits()
    {
        return moduleCredits;
    }
    
    /**
     * Sets the module code.
     * 
     * @param moduleCode The new module code.
     */
    public void setModuleCode(String moduleCode)
    {
        this.moduleCode = new String(moduleCode);
    }
    
    /**
     * Sets the module name.
     * 
     * @param moduleName The new module name.
     */
    public void setModuleName(String moduleName)
    {
        this.moduleName = new String(moduleName);
    }
    
    /**
     * Sets the module credits.
     * 
     * @param moduleCredits The new module credits.
     */
    public void setModuleCredits(int moduleCredits)
    {
        this.moduleCredits = moduleCredits;
    }
    
    /**
     * Generates a CSV representation of the course module.
     * 
     * @return A CSV string representing the course module.
     */
    public String getCourseModuleCSV()
    {
        String credits = String.format("%d",moduleCredits);
        String CSV = moduleCode + "," + moduleName + "," + credits;
        
        return CSV;
    }
    
    /**
     * Generates a formatted text representation of the course module.
     * 
     * @return A formatted text string representing the course module.
     */
    public String getCourseModuleText()
    {
        String credits = String.format("%d",moduleCredits);
        String padding = new String(new char[55 - moduleName.length()]).replace("\0", " ");
        String CSV = moduleCode + "\t" + moduleName + padding + "\t" + credits;
        
        return CSV;
    }
    
    /*
     * removed by Adam, not needed in class.
     public List<Faculty> getAssignedFaculties() {
        return assignedFaculties;
    }
    
    public void setAssignedFaculties(List<Faculty> faculties) {
        this.assignedFaculties = faculties;
    }
    */
}