
/**
 * Write a description of class gradedCourseModule here.
 *
 * @author (Adam Fogarty, 22367748)
 * @version (1.0)
 */
public class gradedCourseModule extends courseModule
{
    private String grade;

    /**
     * Constructor for objects of class gradedCourseModule
     */
    public gradedCourseModule()
    {
        
    }
    
    public gradedCourseModule(String moduleCode, String moduleName, int moduleCredits, String grade)
    {
        super(moduleCode, moduleName, moduleCredits);
        this.grade = new String(grade);
    }
    
    public gradedCourseModule(String moduleCode, String moduleName, int moduleCredits)
    {
        super(moduleCode, moduleName, moduleCredits);
        this.grade = new String("NG");
    }
    
    public gradedCourseModule(courseModule course)
    {
        super(course.getModuleCode(), course.getModuleName(), course.getModuleCredits());
        this.grade = new String("NG");
    }
    
    public String getGrade()
    {
        return grade;    
    }
    
    public void setGrade(String grade)
    {
        this.grade = new String(grade);
    }
    
    public String getCourseModuleCSV()
    {
        String CSV = super.getCourseModuleCSV() + "," + grade;
        
        return CSV;
    }
    
    public String getCourseModuleText()
    {
        String CSV = super.getCourseModuleText() + "\t" + grade;
        
        return CSV;
    }
}
