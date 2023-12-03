import java.util.*;
import java.io.*;

public class Student {
    private String studentID;
    private String name;
    private Programme programme;
    private int yearOfStudy;
    private int regYear;

    public Student(String studentID, String name, Programme programme, int yearOfStudy, int regYear) {
        this.studentID = studentID;
        this.name = name;
        this.programme = programme;
        this.yearOfStudy = yearOfStudy;
        this.regYear = regYear;
    }

    public String getStudentID()
    {
        return studentID;
    }
    
    public void setStudentID(String studentID)
    {
        this.studentID = studentID;
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public Programme getProgramme()
    {
        return programme;
    }
    
    public void setProgramme(Programme programme)
    {
        this.programme = programme;
    }
    
    public int getYearOfStudy()
    {
        return yearOfStudy;
    }
    
    public void setYearOfStudy(int yearOfStudy)
    {
        this.yearOfStudy = yearOfStudy;
    }
    
    public int getRegYear()
    {
        return regYear;
    }
    
    public void setRegYear(int regYear)
    {
        this.regYear = regYear;
    }
    
    public String getStudentCSV()
    {    
        String studCSV = new String();
            
        studCSV += studentID + "," + name + "," + programme.getProgCode() + "," + programme.getName() + "," + String.valueOf(regYear) + "," + String.valueOf(yearOfStudy) + "\n";

            for(int i = 0; i < programme.getNumberOfYears(); i++)
            {
                ArrayList<programmeYear> programmeYears = programme.getProgYears();
                programmeYear year = programmeYears.get(i);
                
                String moduleDetails = new String();
                    for(int j = 0; j < year.sem1.size(); j++)
                    {
                        gradedCourseModule module = year.sem1.get(j);
                        moduleDetails += module.getCourseModuleCSV();
                        studCSV += String.valueOf(i + 1) + "," + "1," + moduleDetails + "\n";
                        moduleDetails = "";
                    }
                
               
                    for(int j = 0; j < year.sem2.size(); j++)
                    {
                        gradedCourseModule module = year.sem2.get(j);
                        moduleDetails += module.getCourseModuleCSV();
                        studCSV += String.valueOf(i + 1) + "," + "2," + moduleDetails + "\n";
                        moduleDetails = "";
                    }
                
            }             
        return studCSV;
    }
}