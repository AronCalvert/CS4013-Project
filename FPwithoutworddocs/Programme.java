import java.util.ArrayList;
import java.io.*;
import java.util.List;

/**
 * Class to represent a college Course
 */
public class Programme
{
    private String progName;
    private String progCode;
    private int numberOfYears;
    private ArrayList<programmeYear> programmeYears;

    /*
     * Constructor for objects of class Programme
     * @param progCode
     * @param progName
     * @param numberOfYears
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
    
    /*
     * Method to check if a module is a part of a course
     * @param moduleCode
     * @return result  true if module is present in programme, false if not
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
    
    /*
     * Gets CSV file of the courses in the college
     * @return progCSV
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
    
    /*
     * Method to add a module to course
     * @param progYear
     * @param semester
     * @param newMoudle  of type courseModule
     */
    public void addModule(int progYear, int semester, courseModule newModule)
    {
        programmeYear year = programmeYears.get(progYear -1);
        
        if(semester == 1)
        {
            year.sem1.add(newModule);
        }
        else
        {
            year.sem2.add(newModule);
        }
    }
    
    /*
     * Gets course code
     * @return progCode
     */
    public String getProgCode()
    {
        return progCode;
    }
    
   

    /*
     * Gets number of years the course is
     * @return numberOfYears
     */
     public int getNumberOfYears(){
        return this.numberOfYears;
    }
    
    public List<courseModule> getModules() {
        List<courseModule> allModules = new ArrayList<>();

        for (programmeYear year : programmeYears) {
            allModules.addAll(year.sem1); // Add all modules from the first semester
            allModules.addAll(year.sem2); // Add all modules from the second semester
        }
           return allModules;
    }
    /*
     * Gets name of course
     * @return progName
     */
      
    public String getName(){
        return progName;
    }
}