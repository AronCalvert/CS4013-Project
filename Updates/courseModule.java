
/**
 * Write a description of class courseModule here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class courseModule
{
    // instance variables - replace the example below with your own
    private String moduleCode;
    private String moduleName;
    private int moduleCredits;

    /**
     * Constructor for objects of class courseModule
     */
    public courseModule()
    {
        
    }

    /**
     * Constructor for objects of class courseModule
     */
    public courseModule(String moduleCode, String moduleName, int moduleCredits)
    {
        this.moduleCode = new String(moduleCode);
        this.moduleName = new String(moduleName);
        this.moduleCredits = moduleCredits;
    }
    
    public String getModuleCode()
    {
        return moduleCode;
    }
    
    public String getModuleName()
    {
        return moduleName;
    }
    
    public int getModuleCredits()
    {
        return moduleCredits;
    }
    
    public void setModuleCode(String moduleCode)
    {
        this.moduleCode = new String(moduleCode);
    }
    
    public void setModuleName(String moduleName)
    {
        this.moduleName = new String(moduleName);
    }
    
    public void setModuleCredits(int moduleCredits)
    {
        this.moduleCredits = moduleCredits;
    }
    
    public String getCourseModuleCSV()
    {
        String credits = String.format("%d",moduleCredits);
        String CSV = moduleCode + "," + moduleName + "," + credits;
        
        return CSV;
    }
}