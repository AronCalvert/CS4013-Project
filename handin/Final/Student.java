import java.util.*;
import java.io.*;

/**
 * The Student class represents a student in the system.
 * 
 * 
 * @author (Adam Fogarty, 22367748);(Paudie Kelly, 22342842);(Ella Curtin, 22363564)
 * @version (2.0)
 */
public class Student {
    private String studentID;
    private String name;
    private Programme programme;
    private int yearOfStudy;
    private int regYear;
    
    /**
     * Constructs a new Student object with the specified parameters.
     *
     * @param studentID   The unique identifier for the student.
     * @param name        The name of the student.
     * @param programme   The program in which the student is enrolled.
     * @param yearOfStudy The current year of study for the student.
     * @param regYear     The registration year of the student.
     * 
     * @author (Adam Fogarty, 22367748);(Paudie Kelly, 22342842);(Ella Curtin, 22363564)
     * @version (2.0)
     */
    public Student(String studentID, String name, Programme programme, int yearOfStudy, int regYear) {
        this.studentID = studentID;
        this.name = name;
        this.programme = programme;
        this.yearOfStudy = yearOfStudy;
        this.regYear = regYear;
    }

    /**
     * Retrieves the student's ID.
     *
     * @return The student's ID.
     */
    public String getStudentID()
    {
        return studentID;
    }
    
    /**
     * Sets the student's ID.
     *
     * @param studentID The new ID for the student.
     */
    public void setStudentID(String studentID)
    {
        this.studentID = studentID;
    }
    
    /**
     * Retrieves the student's name.
     *
     * @return The student's name.
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Sets the student's name.
     *
     * @param name The new name for the student.
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     * Retrieves the program in which the student is enrolled.
     *
     * @return The program of the student.
     */
    public Programme getProgramme()
    {
        return programme;
    }
    
    /**
     * Sets the program for the student.
     *
     * @param programme The new program for the student.
     */
    public void setProgramme(Programme programme)
    {
        this.programme = programme;
    }
    
    /**
     * Retrieves the current year of study for the student.
     *
     * @return The current year of study.
     */
    public int getYearOfStudy()
    {
        return yearOfStudy;
    }
    
    /**
     * Sets the current year of study for the student.
     *
     * @param yearOfStudy The new year of study for the student.
     */
    public void setYearOfStudy(int yearOfStudy)
    {
        this.yearOfStudy = yearOfStudy;
    }
    
    /**
     * Retrieves the registration year of the student.
     *
     * @return The registration year of the student.
     */
    public int getRegYear()
    {
        return regYear;
    }
    
    /**
     * Sets the registration year for the student.
     *
     * @param regYear The new registration year for the student.
     */
    public void setRegYear(int regYear)
    {
        this.regYear = regYear;
    }
    
    /**
     * Generates a CSV representation of the student's information, including program details and module grades.
     *
     * @return A CSV string representing the student's information.
     */
    public String getStudentCSV()
    {    
        String studCSV = new String();
            
        studCSV += studentID + "," + name + "," + programme.getProgCode() + "," + programme.getName() + "," + String.valueOf(yearOfStudy) + "," + String.valueOf(regYear) + "\n";

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