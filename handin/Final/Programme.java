import java.util.ArrayList;
import java.io.*;
import java.util.List;

/**
 * The Programme class represents an academic programme with modules and related information.
 * 
 * 
 * @author (Paudie Kelly, 22342842);(Ella Curtin, 22363564)
 * @version (1.0)
 */
public class Programme
{
    // instance variables 
    private String progName;
    private String progCode;
    private int numberOfYears;
    private ArrayList<programmeYear> programmeYears;

    /**
     * Constructs a new Programme object with the specified programme code, programme name, and number of years.
     *
     * @param progCode      The code representing the programme.
     * @param progName      The name of the programme.
     * @param numberOfYears The number of years in the programme.
     */
    public Programme(String progCode, String progName, int numberOfYears)
    {
        programmeYears = new ArrayList<programmeYear>();
        for(int i = 0; i < numberOfYears; i ++)
        {
            programmeYears.add(new programmeYear());
        }
        this.progName = new String(progName);
        this.progCode = new String(progCode);
        this.numberOfYears = numberOfYears;        
    }
    
    /**
     * Generates a CSV representation of the programme's information, including module details for each semester.
     *
     * @return A CSV string representing the programme's information.
     */
    public String getProgrammeCSV()
    {
            String progCSV = new String();
            
            progCSV += progCode + "," + progName + "," + String.valueOf(numberOfYears) + "\n";
            for(int i = 0; i < numberOfYears; i++)
            {
                programmeYear year = programmeYears.get(i);
                
                String modulesInSemester = new String();
                    for(int j = 0; j < year.sem1.size(); j++)
                    {
                         courseModule module = year.sem1.get(j);
                         modulesInSemester += "," + module.getModuleCode();
                    }
                progCSV += String.valueOf(i + 1) + modulesInSemester + "\n";
                modulesInSemester = "";
                    for(int j = 0; j < year.sem2.size(); j++)
                        {
                             courseModule module = year.sem2.get(j);
                             modulesInSemester += "," + module.getModuleCode();
                        }
                progCSV += String.valueOf(i + 1) + modulesInSemester + "\n";
            }       
            return progCSV;
    }
    
     /**
     * Adds a new module to a specified programme year and semester.
     *
     * @param progYear   The programme year in which to add the module.
     * @param semester   The semester in which to add the module.
     * @param newModule  The module to be added.
     */
    public void addModule(int progYear, int semester, courseModule newModule)
    {
        programmeYear year = programmeYears.get(progYear -1);
        gradedCourseModule gradedModule = new gradedCourseModule(newModule);
        if(semester == 1)
        {
            year.sem1.add(gradedModule);
        }
        else
        {
            year.sem2.add(gradedModule);
        }
    }
    
    /**
     * Checks if a module with a specified module code is present in the programme.
     *
     * @param moduleCode The module code to check.
     * @return True if the module is present in the programme, false otherwise.
     */
    public boolean checkModuleInProg(String moduleCode)
    {
            boolean result = false;
            for(int i = 0; i < numberOfYears; i++)
            {
                programmeYear year = programmeYears.get(i);
                    for(int j = 0; j < year.sem1.size(); j++)
                    {
                         courseModule module = year.sem1.get(j);
                         if(module.getModuleCode().equals(moduleCode))
                         {
                             result = true;
                             break;
                         }
                         
                    }
 
                    for(int j = 0; j < year.sem2.size(); j++)
                    {
                        courseModule module = year.sem2.get(j);
                        if(module.getModuleCode().equals(moduleCode))
                        {
                             result = true;
                             break;
                        }
                    }
 
            }       
            return result;
    }
    
    /**
     * Retrieves the programme code.
     *
     * @return The programme code.
     */
    public String getProgCode()
    {
        return progCode;
    }
    
    /**
     * Retrieves the name of the programme.
     *
     * @return The name of the programme.
     */
    public String getName() {
        return this.progName;
    }
    
    /**
     * Retrieves the number of years in the programme.
     *
     * @return The number of years in the programme.
     */
    public int getNumberOfYears(){
        return this.numberOfYears;
    }
    
    /**
     * Retrieves the list of programme years.
     *
     * @return The list of programme years.
     */
    public ArrayList<programmeYear> getProgYears()
    {
        return programmeYears;
    }
    
    /**
     * Retrieves a list of all modules in the programme.
     *
     * @return A list of all modules in the programme.
     */
    public List<courseModule> getModules() {
        List<courseModule> allModules = new ArrayList<>();

        for (programmeYear year : programmeYears) {
            allModules.addAll(year.sem1); // Add all modules from the first semester
            allModules.addAll(year.sem2); // Add all modules from the second semester
        }

        return allModules;
    }
    
    /**
     * Sets the grade for a specified module in a given programme year and semester.
     *
     * @param courseYear The programme year in which the module is located.
     * @param semester   The semester in which the module is located.
     * @param moduleCode The module code for which to set the grade.
     * @param grade      The grade to be set.
     */
    public void setModuleGrade(int courseYear, int semester, String moduleCode, String grade)
    {
        ArrayList<gradedCourseModule> semesterModules = null;
        if(semester == 1)
        {
            semesterModules = programmeYears.get(courseYear - 1).sem1;
        }
        else
        {
            semesterModules = programmeYears.get(courseYear - 1).sem2;
        }
        
        for(gradedCourseModule mod : semesterModules)
        {
            if(mod.getModuleCode().equals(moduleCode))
            {
                mod.setGrade(grade); 
            }
        }
    }
}