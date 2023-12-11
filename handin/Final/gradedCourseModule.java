
/**
 * The gradedCourseModule class represents a course module with an associated grade.
 * It extends the courseModule class, inheriting basic module information.
 * 
 * 
 * @author (Adam Fogarty, 22367748);(Ella Curtin, 22363564);(Aron Calvert, 22370374)
 * @version (1.0)
 */
public class gradedCourseModule extends courseModule
{
    private String grade;

    /**
     * Default constructor for objects of class gradedCourseModule.
     */
    public gradedCourseModule()
    {
        
    }
    
     /**
     * Parameterized constructor for objects of class gradedCourseModule with a specified grade.
     *
     * @param moduleCode    The code of the module.
     * @param moduleName    The name of the module.
     * @param moduleCredits The credit value of the module.
     * @param grade         The grade assigned to the module.
     */
    public gradedCourseModule(String moduleCode, String moduleName, int moduleCredits, String grade)
    {
        super(moduleCode, moduleName, moduleCredits);
        this.grade = new String(grade);
    }
    
     /**
     * Parameterized constructor for objects of class gradedCourseModule without specifying a grade.
     *
     * @param moduleCode    The code of the module.
     * @param moduleName    The name of the module.
     * @param moduleCredits The credit value of the module.
     */
    public gradedCourseModule(String moduleCode, String moduleName, int moduleCredits)
    {
        super(moduleCode, moduleName, moduleCredits);
        this.grade = new String("NG");
    }
    
    /**
     * Constructor for objects of class gradedCourseModule using an existing courseModule.
     * The grade is set to "NG" (No Grade) by default.
     *
     * @param course The courseModule object from which to inherit module information.
     */
    public gradedCourseModule(courseModule course)
    {
        super(course.getModuleCode(), course.getModuleName(), course.getModuleCredits());
        this.grade = new String("NG");
    }
    
    /**
     * Retrieves the grade associated with the module.
     *
     * @return The grade of the module.
     */
    public String getGrade()
    {
        return grade;    
    }
    
    /**
     * Sets the grade for the module.
     *
     * @param grade The new grade to be set.
     */
    public void setGrade(String grade)
    {
        this.grade = new String(grade);
    }
    
     /**
     * Generates a CSV representation of the course module information, including the grade.
     *
     * @return A CSV string representing the module information with the grade.
     */
    public String getCourseModuleCSV()
    {
        String CSV = super.getCourseModuleCSV() + "," + grade;
        
        return CSV;
    }
    
    /**
     * Generates a text representation of the course module information, including the grade.
     *
     * @return A text string representing the module information with the grade.
     */
    public String getCourseModuleText()
    {
        String CSV = super.getCourseModuleText() + "\t" + grade;
        
        return CSV;
    }
}
