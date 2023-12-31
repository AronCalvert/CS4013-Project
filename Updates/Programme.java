import java.util.ArrayList;
import java.io.*;

/**
 * Write a description of class Programme here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Programme
{
    // instance variables 
    private String progName;
    private String progCode;
    private int numberOfYears;
    private ArrayList<programmeYear> programmeYears;

    /**
     * Constructor for objects of class Programme
     */
    public Programme(String progCode, String progName, int numberOfYears)
    {
        programmeYears = new ArrayList<programmeYear>();
        for(int i = 0; i < numberOfYears; i ++)
        {
            programmeYears.add(new programmeYear());
        }
        this.progName = progName;
        this.progCode = progCode;
        this.numberOfYears = numberOfYears;        
    }
    
    public void programmeSaveCSV()
    {
        try 
        {
            String fileName = progCode + ".CSV";
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write(progCode + "," + progName + "," + String.valueOf(numberOfYears) + "\n");
            for(int i = 0; i < numberOfYears; i++)
            {
                programmeYear year = programmeYears.get(i);
                
                String modulesInSemester = new String();
                    for(int j = 0; j < year.sem1.size(); j++)
                    {
                         courseModule module = year.sem1.get(j);
                         modulesInSemester += "," + module.getModuleCode();
                    }
                myWriter.write(String.valueOf(i + 1) + modulesInSemester + "\n");
                modulesInSemester = "";
                    for(int j = 0; j < year.sem2.size(); j++)
                        {
                             courseModule module = year.sem2.get(j);
                             modulesInSemester += "," + module.getModuleCode();
                        }
                myWriter.write(String.valueOf(i + 1) + modulesInSemester + "\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } 
        catch (IOException e) 
        {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
    }
    
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
}