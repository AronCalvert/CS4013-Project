import java.util.HashMap;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Map;
/**
 * Class to represent a Student in a college
 */
public class Student {
    private String studentID;
    private String name;
    private String program;
    private String department;
    private int yearOfStudy;
    private HashMap<String, Double> grades;
    private String output = "Desktop/output.csv";

    /*
     * Constructor to create Student object
     * @param studentID
     * @param name
     * @param program
     * @param department
     * @param yearOfStudy
     */
    public Student(String studentID, String name, String program, String department, int yearOfStudy) {
        this.studentID = studentID;
        this.name = name;
        this.program = program;
        this.department = department;
        this.yearOfStudy = yearOfStudy;
        this.grades = new HashMap<>();
    }

    /*
     * Method to add a grade of the student of a particular module
     * @param moduleCode
     * @param grade
     */
    public void addGrade(String moduleCode, double grade) {
        grades.put(moduleCode, grade);
    }

    /*
     * Method to calculate QCA
     * @return  grades.size() > 0 ? totalGrades / grades.size() : 0;  (QCA)
     */
    public double calculateQCA() {
        double totalGrades = 0;
        for (double grade : grades.values()) {
            totalGrades += grade;
        }
        return grades.size() > 0 ? totalGrades / grades.size() : 0;
    }

    /*
     * Method to view transcript of particular student
     */
    public void viewTranscript() {
        System.out.println("Transcript for " + name);
        for (String module : grades.keySet()) {
            System.out.println("Module: " + module + ", Grade: " + grades.get(module));
        }
    }

    /*
     * Method to check current QCA if still progressing in your course
     * @return qca >=2.0
     */
    public boolean checkProgression() {
        double qca = calculateQCA();
        return qca >= 2.0;
    }

    /*
     * Gets student id number
     * @return this.studentID
     */
    public String getStudentID() {
        return this.studentID;
    }

    /*
     * Gets name of student
     * @return this.name
     */
    public String getName(){
        return this.name;
    }

    /*
     * Gets program (course) of student
     * @return this.program
     */
    public String getProgram(){
        return this.program;
    }

    /*
     * Gets year of study of student
     * @return this.yearOfStudy
     */
    public int getYearOfStudy(){
        return this.yearOfStudy;
    }

    /*
     * Method to get the transcripts of the student
     * 
     */
    public void getTranscript() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(output))) {
            try (BufferedWriter writer2 = new BufferedWriter(new FileWriter(output))) {
                writer.write("Student Transcript");
                writer.newLine();
                writer.newLine();
                writer.write("Name          " + this.name);
                writer.write("Name: " + this.name);
                writer.newLine();
                writer.write("Student ID: " + this.studentID);
                writer.newLine();
                writer.newLine();
                writer.write("Student ID    " + this.studentID);
            }catch(Exception e) {
                for (Map.Entry<String, Double> entry : grades.entrySet()) {
                    writer.write("Module: " + entry.getKey() + ", Grade: " + entry.getValue());
                    writer.newLine();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
